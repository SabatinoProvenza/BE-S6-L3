package sabatinoprovenza.BE_S6_L3.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sabatinoprovenza.BE_S6_L3.entities.Author;
import sabatinoprovenza.BE_S6_L3.exceptions.BadRequestException;
import sabatinoprovenza.BE_S6_L3.exceptions.NotFoundException;
import sabatinoprovenza.BE_S6_L3.payloads.AuthorPayload;
import sabatinoprovenza.BE_S6_L3.repositories.AuthorsRepository;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthorsService {
    private final AuthorsRepository authorsRepository;
    private final Cloudinary cloudinary;

    public AuthorsService(AuthorsRepository authorsRepository, Cloudinary cloudinary) {
        this.authorsRepository = authorsRepository;
        this.cloudinary = cloudinary;
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

    public Author uploadAvatar(MultipartFile file, UUID authorId) {
        Author author = this.findById(authorId);

        try {
            Map result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String imageUrl = (String) result.get("secure_url");
            author.setAvatar(imageUrl);
            return authorsRepository.save(author);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
