package eddy.springkafka.controller;

import eddy.springkafka.messagequeue.KafkaProducer;
import eddy.springkafka.service.BookerService;
import eddy.springkafka.vo.BookerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/kafka")
public class KafkaController {

    private final KafkaProducer kafkaProducer;
    private final BookerService bookerService;

    @Autowired
    public KafkaController(KafkaProducer kafkaProducer, BookerService bookerService) {
        this.kafkaProducer = kafkaProducer;
        this.bookerService = bookerService;
    }

    @PostMapping(value = "/message")
    public String sendMessage(@RequestParam("message") String message) {
        System.out.println("message :"+message);
        return "success";
    }

    @PostMapping(value = "/create")
    public String create(@RequestBody BookerVo bookerVo) {

        return "create success";
    }
}
