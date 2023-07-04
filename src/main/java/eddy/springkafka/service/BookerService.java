package eddy.springkafka.service;

import eddy.springkafka.dto.BookerDto;
import eddy.springkafka.entity.Booker;
import eddy.springkafka.infra.BookerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Slf4j
@Service
@AllArgsConstructor
public class BookerService {

    private BookerRepository bookerRepository;
    @Transactional(rollbackOn=Exception.class)
    public BookerDto create(BookerDto bookerDto) {
        Booker booker = new Booker();
        booker.setName(bookerDto.getName());
        booker.setRegister_datetime(booker.convertLocalDateNowTime());

        Booker createBooker = bookerRepository.save(booker);
        BookerDto bookerDto1 = new BookerDto();
        bookerDto1.setIndex(createBooker.getIndex());
        bookerDto1.setName(createBooker.getName());
        bookerDto1.setRegister_datetime(createBooker.getRegister_datetime().toString());

        return bookerDto1;
    }
}
