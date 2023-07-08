package eddy.springkafka.vo;


import lombok.Data;

@Data
public class BookerModifyVo {
    public Integer index;
    public String name;
    public String memo;
    public String latitude;
    public String longitude;
    public String mapUrl;
    public Integer folderIndex;
    public String status;
}
