package eddy.springkafka.messagequeue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eddy.springkafka.infra.BookerRepository;
import eddy.springkafka.vo.BookerVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void send(String topic, BookerVo bookerVo) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";

        try {
            jsonInString = mapper.writeValueAsString(bookerVo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        this.kafkaTemplate.send(topic, jsonInString);
        log.info("Kafka Producer send data from the Booker microservice : " + bookerVo);
    }
}
