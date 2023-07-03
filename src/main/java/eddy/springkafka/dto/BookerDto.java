package eddy.springkafka.dto;

import lombok.Data;


@Data
public class BookerDto {
    public Integer index;
    public String name;
    public String register_datetime;
}
