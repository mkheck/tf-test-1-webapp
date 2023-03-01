package com.thehecklers.tftest1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class TfController {
    private final AuthorRepository repo;

    TfController(AuthorRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public Iterable<Author> getAllAuthors() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Author> getAuthorById(@PathVariable Long id) {
        return repo.findById(id);
    }
}
