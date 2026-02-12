package sabatinoprovenza.BE_S6_L3.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;


public record AuthorPayload(@NotBlank(message = "Il nome è obbligatorio") String nome,
                            @NotBlank(message = "Il cognome è obbligatorio") String cognome,
                            @Email(message = "Inserire un' email valida") @NotBlank(message = "L'email è obbligatoria") String email,
                            LocalDate dataDiNascita) {

}
