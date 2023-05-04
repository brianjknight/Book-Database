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

import java.util.UUID;

/**
 * Spring Boot controller class for Authors table.
 * Includes methods for basic CRUD operations as well as a SearchRequest which can include 0 to many filters and sort criteria.
 */

@RestController
@RequestMapping("authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    /**
     * GET mapping for finding a single Author with the primary key.
     * @param id UUID & primary key for the Author you want to find.
     * @return Author of the given id.
     */
    @GetMapping("{id}")
    public ResponseEntity<Author> findById(@PathVariable UUID id) {
        return authorService.findById(id);
    }

    /**
     * GET mapping for finding an Author by authorId which is different from the primary key id.
     * @param authorId authorId of the Author you want to find.
     * @return Author with matching authorId.
     */
    @GetMapping("authorId/{authorId}")
    public ResponseEntity<Author> findByAuthorId(@PathVariable Integer authorId) {
        return ResponseEntity.ok(authorService.findByAuthorId(authorId));
    }

    /**
     * POST mapping queries the Author table given a SearchRequest object. (See SearchRequest class in 'search' package.)
     * A Search request includes fields for filters/sorts for filter and page/size for pagination.
     * @param request a SearchRequest that contains list of filters, list of sorts, page number, and number of results per page.
     * @return Pageable of Authors matching the search parameters.
     */
    @PostMapping(value = "/search")
    public ResponseEntity<Page<Author>> searchAuthorDatabase(@RequestBody SearchRequest request) {
        return authorService.searchAuthorDatabase(request);
    }

    /**
     * POST mapping to save a new Author to the Authors table.
     * @param author new Author object to be saved.
     * @return Author after successfully persisting to the table.
     */
    @PostMapping
    public ResponseEntity<Author> saveAuthor(@RequestBody Author author) {
        return authorService.saveAuthor(author);
    }

    /**
     * PUT mapping to update an Author in the table.
     * @param id UUID & primary key of the author to be updated.
     * @param author Author object with the updated fields.
     * @return the updated Author after being persisted to the database.
     */
    @PutMapping("{id}")
    public ResponseEntity<Author> updateAuthorById(@PathVariable UUID id, @RequestBody Author author) {
        return authorService.updateAuthorById(id, author);
    }

    /**
     * DELETE mapping for delete an Author from the database.
     * @param id UUID & primary key of the author to be deleted.
     * @return String message confirming successful deletion from the table.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAuthorById(@PathVariable UUID id) {
        return authorService.deleteByAuthorId(id);
    }
}
