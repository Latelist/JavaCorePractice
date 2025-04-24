import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public class LocalDT {

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy:MM:dd'##':HH:mm:ss:SSS",
            locale = "ru-RU"
    )
    private LocalDateTime timestamp;

    public LocalDateTime getTimestamp(){
        return timestamp;
    }

    public void setTimestamp(){
        timestamp = LocalDateTime.now();
    }
}
