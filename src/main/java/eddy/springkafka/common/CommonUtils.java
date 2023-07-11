package eddy.springkafka.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommonUtils {

    public static LocalDateTime convertLocalDateNowTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return LocalDateTime.parse(currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static LocalDateTime convertLocalDateTimeFromString(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date, dateTimeFormatter);
    }
}
