package eddy.springkafka.vo;


import lombok.Data;

@Data
public class BookerVo {
    private String userId;
    private Integer bookerIndex;
    private String name;
    private String latitude;
    private String longitude;
    private String memo;
    private Integer folderIndex;
    private String mapUrl;
}
