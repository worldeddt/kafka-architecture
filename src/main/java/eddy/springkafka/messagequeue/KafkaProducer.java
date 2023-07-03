package eddy.springkafka.messagequeue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eddy.springkafka.dto.BookerDto;
import eddy.springkafka.infra.BookerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class KafkaProducer {

    private BookerRepository bookerRepository;
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public BookerDto send(String topic, BookerDto bookerDto) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";

        try {
            jsonInString = mapper.writeValueAsString(bookerDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        this.kafkaTemplate.send(topic, jsonInString);
        log.info("Kafka Producer send data from the Booker microservice" + bookerDto);

        return bookerDto;
    }
}
