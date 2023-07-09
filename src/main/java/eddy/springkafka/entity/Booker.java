package eddy.springkafka.entity;


import eddy.springkafka.enumerate.BookerStatus;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Table(name = "msa_booker")
@Entity
public class Booker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mb_index", nullable = false)
    private Integer index;

    @Column(name = "mb_name", columnDefinition = " varchar(100) default ''", nullable = false)
    private String name;

    @Column(name = "mb_memo", columnDefinition = " varchar(1000) default ''")
    private String memo;

    @Column(name = "mb_latitude", columnDefinition = " varchar(100) default ''", nullable = false)
    private String latitude;

    @Column(name = "mb_longitude", columnDefinition = " varchar(100) default ''", nullable = false)
    private String longitude;

    @Column(name="mb_url", columnDefinition = "varchar(500) default '' comment '지도 api url'")
    private String mapUrl;

    @Column(name ="mb_register_datetime", columnDefinition = "Timestamp", nullable = false)
    @CreatedDate
    private LocalDateTime register_datetime;

    @Column(nullable = false, name ="mb_status", columnDefinition = "enum('ACTIVE', 'DELETE') default 'ACTIVE'")
    private String status;

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


