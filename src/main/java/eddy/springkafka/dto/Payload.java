package eddy.springkafka.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Payload {
    public Integer mbf_booker_index;
    public Integer mbf_folder_index;
}
