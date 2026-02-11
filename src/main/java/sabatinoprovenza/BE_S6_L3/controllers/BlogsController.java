package sabatinoprovenza.BE_S6_L3.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sabatinoprovenza.BE_S6_L3.entities.Blog;
import sabatinoprovenza.BE_S6_L3.payloads.BlogPayload;
import sabatinoprovenza.BE_S6_L3.services.BlogsService;

import java.util.UUID;

@RestController
@RequestMapping("/blogs")
public class BlogsController {
    private final BlogsService blogsService;

    public BlogsController(BlogsService blogsService) {
        this.blogsService = blogsService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Blog saveBlog(@RequestBody BlogPayload payload) {
        return blogsService.saveBlog(payload);
    }

    @GetMapping
    public Page<Blog> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "tempoDiLettura") String orderBy
    ) {
        return blogsService.findAll(page, size, orderBy);
    }

    @GetMapping("/{id}")
    public Blog findById(@PathVariable UUID id) {
        return blogsService.findById(id);
    }
}
