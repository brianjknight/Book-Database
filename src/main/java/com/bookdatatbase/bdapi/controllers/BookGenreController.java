package com.bookdatatbase.bdapi.controllers;

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

/**
 * Spring Boot controller class for BookGenres table.
 * Includes methods for basic CRUD operations as well as a SearchRequest which can include 0 to many filters and sort criteria.
 */

@RestController
@RequestMapping("bookGenres")
public class BookGenreController {
    @Autowired
    private BookGenreService bookGenreService;

    /**
     * GET mapping for finding a single BookGenre using the primary key bookId.
     * @param bookId primary key of the book to find.
     * @return BookGenre with matching bookId.
     */
    @GetMapping("{bookId}")
    public ResponseEntity<BookGenre> findBookGenreByBookId(@PathVariable Integer bookId) {
        return bookGenreService.findBookGenreByBookId(bookId);
    }

    /**
     * POST mapping queries the BookGenre table given a SearchRequest object. (See SearchRequest class in 'search' package.)
     * A Search request includes fields for filters/sorts for filter and page/size for pagination.
     * @param request a SearchRequest that contains list of filters, list of sorts, page number, and number of results per page.
     * @return Pageable of BookGenres matching the search parameters.
     */
    @PostMapping(value = "/search")
    public ResponseEntity<Page<BookGenre>> searchBookGenreDatabase(@RequestBody SearchRequest request) {
        return bookGenreService.searchBookGenreDatabase(request);
    }

    /**
     * POST mapping to save a new BookGenre to the table.
     * @param bookGenre new BookGenre object to be saved.
     * @return newly saved BookGenre object.
     */
    @PostMapping
    public ResponseEntity<BookGenre> saveBookGenre(@RequestBody BookGenre bookGenre) {
        return bookGenreService.saveBookGenre(bookGenre);
    }

    /**
     * PUT mapping to update an existing BookGenre in the table.
     * @param bookId primary key of the book to be updated.
     * @param bookGenre BookGenre object with updated fields.
     * @return updated BookGenre object after being persisted to the database.
     */
    @PutMapping("{bookId}")
    public ResponseEntity<BookGenre> updateBookGenreByBookId(@PathVariable Integer bookId, @RequestBody BookGenre bookGenre) {
        return bookGenreService.updateBookGenreByBookId(bookId, bookGenre);
    }

    /**
     * DELETE mapping to delete a BookGenre from the table.
     * @param bookId primary key of the BookGenre to be deleted.
     * @return String message confirming successful deletion from the table.
     */
    @DeleteMapping("{bookId}")
    public ResponseEntity<String> deleteBookGenreByBookId(@PathVariable Integer bookId) {
        return bookGenreService.deleteBookGenreByBookId(bookId);
    }
}
