package com.bookdatatbase.bdapi.controllers;

import com.bookdatatbase.bdapi.entities.Book;
import com.bookdatatbase.bdapi.entities.BookGenre;
import com.bookdatatbase.bdapi.search.SearchRequest;
import com.bookdatatbase.bdapi.services.BookGenreService;
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

@RestController
@RequestMapping("bookGenres")
public class BookGenreController {
    @Autowired
    private BookGenreService bookGenreService;

    @GetMapping("{id}")
    public ResponseEntity<BookGenre> findBookGenreById(@PathVariable UUID id) {
        return bookGenreService.findBookGenreById(id);
    }

    @PostMapping(value = "/search")
    public ResponseEntity<Page<BookGenre>> searchBookGenreDatabase(@RequestBody SearchRequest request) {
        return bookGenreService.searchBookGenreDatabase(request);
    }

    @PostMapping
    public ResponseEntity<BookGenre> saveBookGenre(@RequestBody BookGenre bookGenre) {
        return bookGenreService.saveBookGenre(bookGenre);
    }

    @PutMapping("{id}")
    public ResponseEntity<BookGenre> updateBookGenreById(@PathVariable UUID id, @RequestBody BookGenre bookGenre) {
        return bookGenreService.updateBookGenreById(id, bookGenre);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBookGenreById(@PathVariable UUID id) {
        return bookGenreService.deleteBookGenreById(id);
    }
}
