//package eddy.springkafka.service;
//
//
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//
//@Service
//public class KafkaConsumer {
//    @KafkaListener(topics = "${message.topic.name}", groupId = "group-id-oring")
//    public void consume(String message) throws IOException {
//        System.out.println("receive message : " + message);
//    }
//}
