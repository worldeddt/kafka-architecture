package eddy.springkafka.service;

import eddy.springkafka.entity.Booker;
import eddy.springkafka.infra.BookerRepository;
import eddy.springkafka.vo.BookerVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transactional;


@Slf4j
@Service
@AllArgsConstructor
public class BookerService {

    private BookerRepository bookerRepository;
    @Transactional(rollbackOn=Exception.class)
    public void create(BookerVo bookerVo) {
        Booker booker = new Booker();
        booker.setName(bookerVo.getName());
        booker.setRegister_datetime(booker.convertLocalDateNowTime());

        bookerRepository.save(booker);
    }
}
