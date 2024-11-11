package hexlet.code.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public final class Url {
    private Long id;
    private String name;

    private LocalDateTime createdAt;
    private Url(String name) {
        this.name = name;
    }
}
