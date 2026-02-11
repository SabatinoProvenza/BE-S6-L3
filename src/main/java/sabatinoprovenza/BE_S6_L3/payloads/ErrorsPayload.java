package sabatinoprovenza.BE_S6_L3.payloads;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorsPayload {
    private String message;
    private LocalDateTime timestamp;

    public ErrorsPayload(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

}

