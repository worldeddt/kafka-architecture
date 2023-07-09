package eddy.springkafka.vo;


import lombok.Data;

@Data
public class BookerVo {
    public String userId;
    public Integer bookerIndex;
    public String name;
    public String latitude;
    public String longitude;
    public String memo;
    public Integer folderIndex;
    public String mapUrl;
}
