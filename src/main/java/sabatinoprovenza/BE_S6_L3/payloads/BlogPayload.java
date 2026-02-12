package sabatinoprovenza.BE_S6_L3.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;


public record BlogPayload(@NotBlank(message = "La categoria è obbligatoria") String categoria,
                          @NotBlank(message = "Il titolo è obbligatorio") String titolo,
                          @NotBlank(message = "Il contenuto è obbligatorio") String contenuto,
                          int tempoDiLettura,
                          @NotNull(message = "L'id dell'autore è obbligatorio") UUID authorId) {

}
