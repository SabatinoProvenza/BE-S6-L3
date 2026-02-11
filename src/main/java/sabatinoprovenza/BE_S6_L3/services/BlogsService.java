package sabatinoprovenza.BE_S6_L3.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sabatinoprovenza.BE_S6_L3.entities.Author;
import sabatinoprovenza.BE_S6_L3.entities.Blog;
import sabatinoprovenza.BE_S6_L3.exceptions.NotFoundException;
import sabatinoprovenza.BE_S6_L3.payloads.BlogPayload;
import sabatinoprovenza.BE_S6_L3.repositories.AuthorsRepository;
import sabatinoprovenza.BE_S6_L3.repositories.BlogsRepository;

import java.util.UUID;

@Service
public class BlogsService {
    private final BlogsRepository blogsRepository;
    private final AuthorsRepository authorsRepository;

    public BlogsService(BlogsRepository blogsRepository, AuthorsRepository authorsRepository) {
        this.blogsRepository = blogsRepository;
        this.authorsRepository = authorsRepository;
    }

    public Blog saveBlog(BlogPayload payload) {

        Author author = this.authorsRepository.findById(payload.getAuthorId())
                .orElseThrow(() -> new NotFoundException("Autore con id " + payload.getAuthorId() + " non trovato"));

        Blog newBlog = new Blog(
                payload.getCategoria(),
                payload.getTitolo(),
                payload.getContenuto(),
                payload.getTempoDiLettura()
        );

        newBlog.setAuthor(author);
        return this.blogsRepository.save(newBlog);
    }

    public Page<Blog> findAll(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return this.blogsRepository.findAll(pageable);
    }

    public Blog findById(UUID id) {
        return this.blogsRepository.findById(id).orElseThrow(() -> new NotFoundException("Il blog con id: " + id + " non è stato trovato"));
    }
}
