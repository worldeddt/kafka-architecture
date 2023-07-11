package eddy.springkafka.controller;

import eddy.springkafka.dto.BookerDto;
import eddy.springkafka.messagequeue.BookerProducer;
import eddy.springkafka.messagequeue.KafkaProducer;
import eddy.springkafka.service.BookerService;
import eddy.springkafka.vo.BookerVo;
import eddy.springkafka.vo.ResponseBooker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/booker/v1")
@AllArgsConstructor
@Slf4j
public class BookerController {

    private BookerService bookerService;
    private KafkaProducer kafkaProducer;
    private BookerProducer bookerProducer;

    @PostMapping(value = "/create")
    public ResponseEntity<ResponseBooker> create(@RequestBody BookerVo bookerVo) {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        BookerDto bookerdto = mapper.map(bookerVo, BookerDto.class);
        BookerDto createBooker = bookerService.create(bookerdto);

        ResponseBooker responseBooker = mapper.map(createBooker, ResponseBooker.class);
        bookerVo.setBookerIndex(responseBooker.getIndex());

        //매칭 테이블
        this.bookerProducer.send("msa_booker_folder", "msa_booker_folder", bookerVo);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseBooker);
    }

    //connetor test 용
    @PostMapping(value = "/create/booker")
    public ResponseEntity<ResponseBooker> createBooker(@RequestBody BookerVo bookerVo) {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        BookerDto bookerdto = mapper.map(bookerVo, BookerDto.class);
        BookerDto createBooker = bookerService.create(bookerdto);

        ResponseBooker responseBooker = mapper.map(createBooker, ResponseBooker.class);
        bookerVo.setBookerIndex(responseBooker.getIndex());

        this.bookerProducer.bookerSend("msa_booker", "msa_booker", bookerVo);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseBooker);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<ResponseBooker> update(@RequestBody BookerVo bookerVo) {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        BookerDto bookerdto = mapper.map(bookerVo, BookerDto.class);
        BookerDto createBooker = bookerService.create(bookerdto);

        ResponseBooker responseBooker = mapper.map(createBooker, ResponseBooker.class);
        bookerVo.setBookerIndex(responseBooker.getIndex());
        this.kafkaProducer.send("msa_user_topic", bookerVo);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseBooker);
    }

    @GetMapping("/health_check")
    public String healthCheck() {
        return "this is healthy";
    }
}
