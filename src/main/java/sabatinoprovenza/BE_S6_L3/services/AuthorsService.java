package sabatinoprovenza.BE_S6_L3.services;

import org.springframework.stereotype.Service;
import sabatinoprovenza.BE_S6_L3.entities.Author;
import sabatinoprovenza.BE_S6_L3.exceptions.BadRequestException;
import sabatinoprovenza.BE_S6_L3.exceptions.NotFoundException;
import sabatinoprovenza.BE_S6_L3.payloads.AuthorPayload;
import sabatinoprovenza.BE_S6_L3.repositories.AuthorsRepository;

import java.util.List;
import java.util.UUID;

@Service
public class AuthorsService {
    private final AuthorsRepository authorsRepository;

    public AuthorsService(AuthorsRepository authorsRepository) {
        this.authorsRepository = authorsRepository;
    }

    public Author saveAuthor(AuthorPayload payload) {
        this.authorsRepository.findByEmail(payload.email()).ifPresent(author -> {
            throw new BadRequestException("L' email: " + author.getEmail() + " è già in uso");
        });

        Author newAuthor = new Author(payload.nome(), payload.cognome(), payload.email(), payload.dataDiNascita());

        Author savedAuthor = this.authorsRepository.save(newAuthor);
        System.out.println("L'autore con id: " + savedAuthor.getId() + " è stato salvato correttamente!");
        return savedAuthor;
    }

    public List<Author> findAll() {
        return this.authorsRepository.findAll();
    }

    public Author findById(UUID id) {
        return authorsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Autore con id: " + id + " non trovato"));
    }

}
