package hexlet.code.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
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

    public Url(String name) {
        this.name = name;
    }

    public String getCreatedAtFormatted() {
        return createdAt.toLocalDateTime().format(DateTimeFormatter.ofPattern("dd.MM.y H:mm"));
    }
}
