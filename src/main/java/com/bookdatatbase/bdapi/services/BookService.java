package com.bookdatatbase.bdapi.services;

import com.bookdatatbase.bdapi.entities.Book;
import com.bookdatatbase.bdapi.exceptions.BookNotFoundException;
import com.bookdatatbase.bdapi.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public ResponseEntity<Book> findBookByIsbn(String isbn) {
        return ResponseEntity.ok(this.getBookByIsbn(isbn));
    }

    public ResponseEntity<Page<Book>>  findAllBooksPaginated(int offset, int limit) {
        return ResponseEntity.ok(bookRepository.findAll(PageRequest.of(offset, limit, Sort.by(DESC,"averageRating"))));
    }

    public ResponseEntity<Book> saveBook(Book book) {
        return ResponseEntity.ok(bookRepository.save(book));
    }

    public ResponseEntity<Book> updateBookByIsbn(String isbn, Book book) {
        if (Objects.isNull(book)) {
            throw new IllegalArgumentException("Book input param cannot be null");
        }
        Book bookToUpdate = this.getBookByIsbn(isbn);

        bookToUpdate.setIsbn(book.getIsbn());
        bookToUpdate.setTextReviewsCount(book.getTextReviewsCount());
        bookToUpdate.setSeries(book.getSeries());
        bookToUpdate.setCountryCode(book.getCountryCode());
        bookToUpdate.setLanguageCode(book.getLanguageCode());
        bookToUpdate.setPopularShelves(book.getPopularShelves());
        bookToUpdate.setAsin(book.getAsin());
        bookToUpdate.setIsEbook(book.getIsEbook());
        bookToUpdate.setAverageRating(book.getAverageRating());
        bookToUpdate.setKindleAsin(book.getKindleAsin());
        bookToUpdate.setSimilarBooks(book.getSimilarBooks());
        bookToUpdate.setDescription(book.getDescription());
        bookToUpdate.setFormat(book.getFormat());
        bookToUpdate.setLink(book.getLink());
        bookToUpdate.setAuthors(book.getAuthors());
        bookToUpdate.setPublisher(book.getPublisher());
        bookToUpdate.setNumPages(book.getNumPages());
        bookToUpdate.setPublicationDay(book.getPublicationDay());
        bookToUpdate.setIsbn13(book.getIsbn13());
        bookToUpdate.setPublicationMonth(book.getPublicationMonth());
        bookToUpdate.setEdition_information(book.getEdition_information());
        bookToUpdate.setPublicationYear(book.getPublicationYear());
        bookToUpdate.setUrl(book.getUrl());
        bookToUpdate.setImageUrl(book.getImageUrl());
        bookToUpdate.setBookId(book.getBookId());
        bookToUpdate.setRatingsCount(book.getRatingsCount());
        bookToUpdate.setWorkId(book.getWorkId());
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setTitleWithoutSeries(book.getTitleWithoutSeries());

        return this.saveBook(bookToUpdate);
    }

    public ResponseEntity<String> deleteBookByIsbn(String isbn) {
        Book bookToDelete = this.getBookByIsbn(isbn);
        bookRepository.delete(bookToDelete);

        return ResponseEntity.ok("Successfully deleted book with ISBN: " + isbn);
    }

    public long count() {
        return bookRepository.count();
    }

    private Book getBookByIsbn(String isbn) {
        return bookRepository.findById(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }

}
