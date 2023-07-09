package eddy.springkafka.messagequeue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eddy.springkafka.dto.Field;
import eddy.springkafka.dto.KafkaBookerDto;
import eddy.springkafka.dto.Payload;
import eddy.springkafka.dto.Schema;
import eddy.springkafka.vo.BookerVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Slf4j
@Service
@AllArgsConstructor
public class BookerProducer {
    private KafkaTemplate<String, String> kafkaTemplate;

    List<Field> fields = Arrays.asList(
            new Field("int32", true, "mbf_booker_index"),
            new Field("int32", true, "mbf_folder_index"));

    Schema schema = Schema.builder()
            .type("struct")
            .fields(fields)
            .optional(false)
            .name("msa_booker_folder")
            .build();

    @Autowired
    public BookerProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public BookerVo send(String topic, BookerVo bookerVo) {
        ObjectMapper mapper = new ObjectMapper();

        Payload payload = Payload.builder()
                .mbf_booker_index(bookerVo.getBookerIndex())
                .mbf_folder_index(bookerVo.getFolderIndex())
                .build();

        KafkaBookerDto kafkaBookerDto = new KafkaBookerDto(this.schema, payload);

        String jsonInString = "";

        try {
            jsonInString = mapper.writeValueAsString(kafkaBookerDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        this.kafkaTemplate.send(topic, jsonInString);
        log.info("booker Producer send data from the Booker microservice : " + kafkaBookerDto);

        return bookerVo;
    }
}
