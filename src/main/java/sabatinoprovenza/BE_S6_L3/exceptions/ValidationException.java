package sabatinoprovenza.BE_S6_L3.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException {
    private final List<String> errorsList;

    public ValidationException(List<String> errorsList) {
        this.errorsList = errorsList;
    }
}
