package eddy.springkafka.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class KafkaBookerFolderDto implements Serializable {
    private Schema schema;
    private Payload payload;
}