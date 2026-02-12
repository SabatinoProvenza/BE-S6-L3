package sabatinoprovenza.BE_S6_L3.payloads;

import java.util.List;

public record ErrorsWithList(String message, List<String> errorList) {
}
