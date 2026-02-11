package sabatinoprovenza.BE_S6_L3.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sabatinoprovenza.BE_S6_L3.entities.Author;
import sabatinoprovenza.BE_S6_L3.payloads.AuthorPayload;
import sabatinoprovenza.BE_S6_L3.services.AuthorsService;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorsController {
    private final AuthorsService authorsService;

    public AuthorsController(AuthorsService authorsService) {
        this.authorsService = authorsService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Author saveAuthor(@RequestBody AuthorPayload payload) {
        return authorsService.saveAuthor(payload);
    }

    @GetMapping
    public List<Author> findAll() {
        return authorsService.findAll();
    }
}
