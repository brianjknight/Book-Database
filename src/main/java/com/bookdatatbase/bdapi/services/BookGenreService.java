package com.bookdatatbase.bdapi.services;

import com.bookdatatbase.bdapi.entities.Author;
import com.bookdatatbase.bdapi.entities.Book;
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
import java.util.UUID;

@Service
public class BookGenreService {
    @Autowired
    private BookGenreRepository bookGenreRepository;

    public ResponseEntity<BookGenre> findBookGenreById(UUID id) {
        return ResponseEntity.ok(this.getBookGenreById(id));
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

    public ResponseEntity<BookGenre> updateBookGenreById(UUID id, BookGenre bookGenre) {
        if(Objects.isNull(bookGenre)) {
            throw new IllegalArgumentException("BookGenre provided cannot be null.");
        }

        BookGenre bookGenreToUpdate = this.getBookGenreById(id);
        bookGenreToUpdate.setGenres(bookGenre.getGenres());

        return this.saveBookGenre(bookGenreToUpdate);
    }

    public ResponseEntity<String> deleteBookGenreById(UUID id) {
        BookGenre bookGenreToDelete = this.getBookGenreById(id);
        bookGenreRepository.delete(bookGenreToDelete);

        return ResponseEntity.ok("Successfully deleted BookGenre with ID: " + id);
    }

    public long count() {
        return bookGenreRepository.count();
    }

    private BookGenre getBookGenreById(UUID id) {
        return bookGenreRepository.findById(id).orElseThrow(() -> new BookGenreNotFoundException(id));
    }
}
