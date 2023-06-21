package eddy.springkafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

//    public void sendMessage(String message){
//        this.kafkaTemplate.send("topic 값",message);
//    }

    @Value(value = "${message.topic.name}")
    private String topicName;

//    private final KafkaTemplate<String, String> kafkaTemplate;

//    @Autowired
//    public KafkaProducer(KafkaTemplate kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }

    public void sendMessage(String message) {
        System.out.println(String.format("Produce message : %s", message));
        this.kafkaTemplate.send(topicName, message);
    }
}
