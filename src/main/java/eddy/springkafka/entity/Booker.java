package eddy.springkafka.entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Table(name = "msa_booker")
@Entity
public class Booker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mb_index", nullable = false)
    public Integer index;

    @Column(name = "mb_name", columnDefinition = " varchar(100) default ''", nullable = false)
    public String name;

    @Column(name ="mb_register_datetime", columnDefinition = "Timestamp")
    private LocalDateTime register_datetime;

    public LocalDateTime convertLocalDateNowTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return LocalDateTime.parse(currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public LocalDateTime convertLocalDateTimeFromString(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date, dateTimeFormatter);
    }
}


