package com.bookdatatbase.bdapi.controllers;

import com.bookdatatbase.bdapi.entities.Book;
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

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("{isbn}")
    public ResponseEntity<Book> findBookByIsbn(@PathVariable String isbn) {
        return bookService.findBookByIsbn(isbn);
    }

    /**
     * @param offset page index to return results from.
     * @param limit number of results to include per page.
     * @return returns a paginated list as a Page object.
     */
    @GetMapping("{offset}/{limit}")
    public ResponseEntity<Page<Book>> findAllBooksPaginated(@PathVariable int offset, @PathVariable int limit) {
        return bookService.findAllBooksPaginated(offset, limit);
    }

    @PostMapping
    public ResponseEntity<Book> saveBook(@RequestBody Book book) {
        if(Objects.isNull(book)) {
            throw new IllegalArgumentException("Book input cannot be null.");
        }
        return bookService.saveBook(book);
    }

    @PutMapping("{isbn}")
    public ResponseEntity<Book> updateBookByIsbn(@PathVariable String isbn, @RequestBody Book book) {
        if(Objects.isNull(book)) {
            throw new IllegalArgumentException("Book input cannot be null.");
        }
        return bookService.updateBookByIsbn(isbn, book);
    }

    @DeleteMapping("{isbn}")
    public ResponseEntity<String> deleteBookByIsbn(@PathVariable String isbn) {
        return bookService.deleteBookByIsbn(isbn);
    }

}

