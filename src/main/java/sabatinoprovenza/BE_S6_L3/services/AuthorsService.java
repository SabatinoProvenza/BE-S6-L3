package sabatinoprovenza.BE_S6_L3.services;

import org.springframework.stereotype.Service;
import sabatinoprovenza.BE_S6_L3.entities.Author;
import sabatinoprovenza.BE_S6_L3.exceptions.BadRequestException;
import sabatinoprovenza.BE_S6_L3.payloads.AuthorPayload;
import sabatinoprovenza.BE_S6_L3.repositories.AuthorsRepository;

@Service
public class AuthorsService {
    private final AuthorsRepository authorsRepository;

    public AuthorsService(AuthorsRepository authorsRepository) {
        this.authorsRepository = authorsRepository;
    }

    public Author saveAuthor(AuthorPayload payload) {
        this.authorsRepository.findByEmail(payload.getEmail()).ifPresent(author -> {
            throw new BadRequestException("L' email: " + author.getEmail() + " è già in uso");
        });

        Author newAuthor = new Author(payload.getNome(), payload.getCognome(), payload.getEmail(), payload.getDataDiNascita());
        newAuthor.setAvatar("https://ui-avatars.com/api?name=" + payload.getNome() + "+" + payload.getCognome());

        Author savedAuthor = this.authorsRepository.save(newAuthor);
        System.out.println("L'autore con id: " + savedAuthor.getId() + " è stato salvato correttamente!");
        return savedAuthor;
    }
}
