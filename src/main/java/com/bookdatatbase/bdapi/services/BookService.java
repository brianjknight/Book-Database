package com.bookdatatbase.bdapi.services;

import com.bookdatatbase.bdapi.entities.Book;
import com.bookdatatbase.bdapi.exceptions.BookNotFoundException;
import com.bookdatatbase.bdapi.repositories.BookRepository;
import com.bookdatatbase.bdapi.search.SearchRequest;
import com.bookdatatbase.bdapi.search.SearchSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public ResponseEntity<Book> findBookById(UUID id) {
        return ResponseEntity.ok(this.getBookById(id));
    }

    public ResponseEntity<Page<Book>>  findAllBooksPaginated(int offset, int limit) {
        return ResponseEntity.ok(bookRepository.findAll(PageRequest.of(offset, limit, Sort.by(DESC,"averageRating"))));
    }

    public ResponseEntity<Page<Book>> searchBookDatabase(SearchRequest request) {
        SearchSpecification<Book> specification = new SearchSpecification<>(request);
        Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());
        return ResponseEntity.ok(bookRepository.findAll(specification, pageable));
    }

    public ResponseEntity<Book> saveBook(Book book) {
        return ResponseEntity.ok(bookRepository.save(book));
    }

    public ResponseEntity<Book> updateBookById(UUID id, Book book) {
        if (Objects.isNull(book)) {
            throw new IllegalArgumentException("Book input param cannot be null");
        }
        Book bookToUpdate = this.getBookById(id);

        bookToUpdate.setIsbn(book.getIsbn());
        bookToUpdate.setTextReviewsCount(book.getTextReviewsCount());
        bookToUpdate.setSeries(book.getSeries());
        bookToUpdate.setCountryCode(book.getCountryCode());
        bookToUpdate.setLanguageCode(book.getLanguageCode());
        bookToUpdate.setPopularShelves(book.getPopularShelves());
        bookToUpdate.setAsin(book.getAsin());
        bookToUpdate.setIsEbook(book.isEbook());
        bookToUpdate.setAverageRating(book.getAverageRating());
        bookToUpdate.setKindleAsin(book.getKindleAsin());
        bookToUpdate.setSimilarBooks(book.getSimilarBooks());
        bookToUpdate.setDescription(book.getDescription());
        bookToUpdate.setFormat(book.getFormat());
        bookToUpdate.setLink(book.getLink());
        bookToUpdate.setAuthors(book.getAuthors());
        bookToUpdate.setPublisher(book.getPublisher());
        bookToUpdate.setNumPages(book.getNumPages());
        bookToUpdate.setEditionInformation(book.getEditionInformation());
        bookToUpdate.setPublicationYear(book.getPublicationYear());
        bookToUpdate.setUrl(book.getUrl());
        bookToUpdate.setImageUrl(book.getImageUrl());
        bookToUpdate.setBookId(book.getBookId());
        bookToUpdate.setRatingsCount(book.getRatingsCount());
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setTitleWithoutSeries(book.getTitleWithoutSeries());

        return this.saveBook(bookToUpdate);
    }

    public ResponseEntity<String> deleteBookById(UUID id) {
        Book bookToDelete = this.getBookById(id);
        bookRepository.delete(bookToDelete);

        return ResponseEntity.ok("Successfully deleted book with ISBN: " + id);
    }

    public long count() {
        return bookRepository.count();
    }

    private Book getBookById(UUID id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id.toString()));
    }

}
