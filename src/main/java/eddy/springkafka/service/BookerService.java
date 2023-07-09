package eddy.springkafka.service;

import eddy.springkafka.dto.BookerDto;
import eddy.springkafka.entity.Booker;
import eddy.springkafka.enumerate.BookerStatus;
import eddy.springkafka.infra.BookerRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


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
        booker.setMemo(bookerDto.getMemo());
        booker.setLatitude(bookerDto.getLatitude());
        booker.setLongitude(bookerDto.getLongitude());
        booker.setMapUrl(bookerDto.getMapUrl());
        booker.setStatus(BookerStatus.ACTIVE.toString());

        Booker createBooker = bookerRepository.save(booker);

        BookerDto bookerDto1 = new BookerDto();
        bookerDto1.setIndex(createBooker.getIndex());
        bookerDto1.setName(createBooker.getName());
        bookerDto1.setRegister_datetime(createBooker.getRegister_datetime().toString());
        bookerDto1.setMemo(createBooker.getMemo());
        bookerDto1.setLatitude(createBooker.getLatitude());
        bookerDto1.setLongitude(createBooker.getLongitude());
        bookerDto1.setMapUrl(createBooker.getMapUrl());
        bookerDto1.setStatus(createBooker.getStatus());

        return bookerDto1;
    }
}
