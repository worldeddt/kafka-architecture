package eddy.springkafka.messagequeue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eddy.springkafka.common.CommonUtils;
import eddy.springkafka.dto.*;
import eddy.springkafka.enumerate.BookerStatus;
import eddy.springkafka.payload.BookerPayload;
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

    private List<Field> fields;
    private Schema schema;

    @Autowired
    public BookerProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public BookerVo send(String schemaName, String topic, BookerVo bookerVo) {
        this.fields =Arrays.asList(
                new Field("int32", true, "mbf_booker_index"),
                new Field("int32", true, "mbf_folder_index"));

        this.schema = Schema.builder()
                .type("struct")
                .fields(fields)
                .optional(false)
                .name(schemaName)
                .build();

        ObjectMapper mapper = new ObjectMapper();

        Payload payload = Payload.builder()
                .mbf_booker_index(bookerVo.getBookerIndex())
                .mbf_folder_index(bookerVo.getFolderIndex())
                .build();

        KafkaBookerFolderDto kafkaBookerFolderDto = new KafkaBookerFolderDto(this.schema, payload);

        String jsonInString = "";

        try {
            jsonInString = mapper.writeValueAsString(kafkaBookerFolderDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        this.kafkaTemplate.send(topic, jsonInString);
        log.info("booker Producer send data from the Booker microservice : " + kafkaBookerFolderDto);

        return bookerVo;
    }

    public BookerVo bookerSend(String schemaName, String topic, BookerVo bookerVo) {
        ObjectMapper mapper = new ObjectMapper();

        this.fields =Arrays.asList(
                new Field("string", true, "mb_latitude"),
                new Field("string", true, "mb_longitude"),
                new Field("string", true, "mb_url"),
                new Field("string", true, "mb_memo"),
                new Field("string", true, "mb_name"),
                new Field("string", true, "mb_register_datetime"),
                new Field("string", true, "mb_status")
        );

        this.schema = Schema.builder()
                .type("struct")
                .fields(fields)
                .optional(false)
                .name(schemaName)
                .build();

        BookerPayload payload = BookerPayload.builder()
                .mb_latitude(bookerVo.getLatitude())
                .mb_longitude(bookerVo.getLongitude())
                .mb_url(bookerVo.getMapUrl())
                .mb_memo(bookerVo.getMemo())
                .mb_name(bookerVo.getName())
                .mb_register_datetime(CommonUtils.convertLocalDateNowTime().toString())
                .mb_status(BookerStatus.ACTIVE.getState())
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
