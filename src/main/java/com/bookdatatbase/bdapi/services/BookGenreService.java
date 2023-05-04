package com.bookdatatbase.bdapi.services;

import com.bookdatatbase.bdapi.entities.BookGenre;
import com.bookdatatbase.bdapi.exceptions.BookGenreNotFoundException;
import com.bookdatatbase.bdapi.repositories.BookGenreRepository;
import com.bookdatatbase.bdapi.search.SearchRequest;
import com.bookdatatbase.bdapi.search.SearchSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Spring Boot service class for BookGenre.
 * This is a layer for handling requests received by the controller and passed to the repository.
 * Includes corresponding CRUD operations for handling each controller method.
 */
@Service
public class BookGenreService {
    @Autowired
    private BookGenreRepository bookGenreRepository;

    public ResponseEntity<BookGenre> findBookGenreByBookId(Integer bookId) {
        return ResponseEntity.ok(this.getBookGenreByBookId(bookId));
    }

    public ResponseEntity<Page<BookGenre>> searchBookGenreDatabase(SearchRequest request) {
        SearchSpecification<BookGenre> specification = new SearchSpecification<>(request);
        Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());
        return ResponseEntity.ok(bookGenreRepository.findAll(specification, pageable));
    }

    public ResponseEntity<BookGenre> saveBookGenre(BookGenre bookGenre) {
        if(Objects.isNull(bookGenre)) {
            throw new IllegalArgumentException("BookGenre provided cannot be null.");
        }
        return ResponseEntity.ok(bookGenreRepository.save(bookGenre));
    }

    public ResponseEntity<BookGenre> updateBookGenreByBookId(Integer bookId, BookGenre bookGenre) {
        if(Objects.isNull(bookGenre)) {
            throw new IllegalArgumentException("BookGenre provided cannot be null.");
        }

        BookGenre bookGenreToUpdate = this.getBookGenreByBookId(bookId);
        bookGenreToUpdate.setGenres(bookGenre.getGenres());

        return this.saveBookGenre(bookGenreToUpdate);
    }

    public ResponseEntity<String> deleteBookGenreByBookId(Integer bookId) {
        BookGenre bookGenreToDelete = this.getBookGenreByBookId(bookId);
        bookGenreRepository.delete(bookGenreToDelete);

        return ResponseEntity.ok("Successfully deleted BookGenre with ID: " + bookId);
    }

    public long count() {
        return bookGenreRepository.count();
    }

    private BookGenre getBookGenreByBookId(Integer bookId) {
        return bookGenreRepository.findById(bookId).orElseThrow(() -> new BookGenreNotFoundException(bookId));
    }
}
