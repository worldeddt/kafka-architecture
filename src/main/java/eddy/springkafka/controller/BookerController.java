package eddy.springkafka.controller;

import eddy.springkafka.dto.BookerDto;
import eddy.springkafka.messagequeue.KafkaProducer;
import eddy.springkafka.service.BookerService;
import eddy.springkafka.vo.BookerVo;
import eddy.springkafka.vo.ResponseBooker;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/booker/v1")
@AllArgsConstructor
public class BookerController {

    private BookerService bookerService;
    private KafkaProducer kafkaProducer;

    @PostMapping(value = "/create")
    public ResponseEntity<ResponseBooker> create(@RequestBody BookerVo bookerVo) {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        BookerDto bookerdto = mapper.map(bookerVo, BookerDto.class);
        BookerDto createBooker = bookerService.create(bookerdto);

        ResponseBooker responseBooker = mapper.map(createBooker, ResponseBooker.class);
        this.kafkaProducer.send("example-user-topic", bookerdto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseBooker);
    }

    @GetMapping("/health_check")
    public String healthCheck() {
        return "this is healthy";
    }
}
