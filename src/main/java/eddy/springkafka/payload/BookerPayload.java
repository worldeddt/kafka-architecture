package eddy.springkafka.payload;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookerPayload {
    private String mb_latitude;
    private String mb_longitude;
    private String mb_url;
    private String mb_memo;
    private String mb_name;
    private String mb_register_datetime;
    private String mb_status;
}
