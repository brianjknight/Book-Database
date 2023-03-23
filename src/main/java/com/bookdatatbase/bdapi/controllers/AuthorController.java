package com.bookdatatbase.bdapi.controllers;

import com.bookdatatbase.bdapi.entities.Author;
import com.bookdatatbase.bdapi.search.SearchRequest;
import com.bookdatatbase.bdapi.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping("{authorId}")
    public ResponseEntity<Author> findAuthorByAuthorId(@PathVariable Integer authorId) {
        return authorService.findAuthorByAuthorId(authorId);
    }

    @PostMapping(value = "/search")
    public ResponseEntity<Page<Author>> searchAuthorDatabase(@RequestBody SearchRequest request) {
        return authorService.searchAuthorDatabase(request);
    }

    @PostMapping
    public ResponseEntity<Author> saveAuthor(@RequestBody Author author) {
        return authorService.saveAuthor(author);
    }

    @PutMapping("{authorId}")
    public ResponseEntity<Author> updateAuthorByAuthorId(@PathVariable Integer authorId, @RequestBody Author author) {
        return authorService.updateAuthorByAuthorId(authorId, author);
    }

    @DeleteMapping("{authorId}")
    public ResponseEntity<String> deleteAuthorByAuthorId(@PathVariable Integer authorId) {
        return authorService.deleteAuthorByAuthorId(authorId);
    }
}
