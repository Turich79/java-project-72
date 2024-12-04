package hexlet.code.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
//import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@AllArgsConstructor
@Getter
@Setter
public final class Url {
    private Long id;
    private String name;
    private Timestamp createdAt;
    private Optional<UrlCheck> lastCheck;

//    private LocalDateTime createdAt;
    public Url(String name) {
        this.name = name;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    public String getCreatedAtFormatted() {
        return createdAt.toLocalDateTime().format(DateTimeFormatter.ofPattern("dd.MM.y H:mm"));
    }
}
