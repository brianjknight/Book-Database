package com.bookdatatbase.bdapi.controllers;

import com.bookdatatbase.bdapi.entities.Book;
import com.bookdatatbase.bdapi.search.SearchRequest;
import com.bookdatatbase.bdapi.services.BookService;

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

import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookService bookService;

    /**
     * GET mapping for finding a single book by primary key.
     * @param id UUID & primary key.
     * @return Book object
     */
    @GetMapping("{id}")
    public ResponseEntity<Book> findBookById(@PathVariable UUID id) {
        return bookService.findBookById(id);
    }

    /**
     * GET mapping for pagination.
     * @param offset page index to return results from.
     * @param limit number of results to include per page.
     * @return returns a paginated list as a Page object.
     */
    @GetMapping("{offset}/{limit}")
    public ResponseEntity<Page<Book>> findAllBooksPaginated(@PathVariable int offset, @PathVariable int limit) {
        return bookService.findAllBooksPaginated(offset, limit);
    }

    /**
     * Post mapping for searching database and filtering responses.
     * @param request - a SearchRequest that contains list of filters, list of sorts, page number, and number of results per page.
     * @return Page object of books matching the search criteria.
     */
    @PostMapping(value = "/search")
    public ResponseEntity<Page<Book>> searchBookDatabase(@RequestBody SearchRequest request) {
        return bookService.searchBookDatabase(request);
    }

    /**
     * POST mapping saves a book to the database.
     * @param book Book object to save.
     * @return saved Book.
     */
    @PostMapping
    public ResponseEntity<Book> saveBook(@RequestBody Book book) {
        if(Objects.isNull(book)) {
            throw new IllegalArgumentException("Book input cannot be null.");
        }
        return bookService.saveBook(book);
    }

    /**
     * PUT mapping to update an existing Book.
     * @param id UUID & primary key.
     * @param book Book object with updated attributes.
     * @return the updated Book.
     */
    @PutMapping("{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable UUID id, @RequestBody Book book) {
        if(Objects.isNull(book)) {
            throw new IllegalArgumentException("Book input cannot be null.");
        }
        return bookService.updateBookById(id, book);
    }

    /**
     * DELETE mapping to delete a book from the database.
     * @param id UUID & primary key.
     * @return String acknowledging deletion of the Book.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable UUID id) {
        return bookService.deleteBookById(id);
    }

}

