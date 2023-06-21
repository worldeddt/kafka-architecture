package eddy.springkafka.infra;

import eddy.springkafka.entity.Booker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookerRepository extends JpaRepository<Booker, Integer> {
}
