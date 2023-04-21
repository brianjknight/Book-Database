package com.bookdatatbase.bdapi.services;

import com.bookdatatbase.bdapi.entities.Book;
import com.bookdatatbase.bdapi.exceptions.BookNotFoundException;
import com.bookdatatbase.bdapi.repositories.BookRepository;
import com.bookdatatbase.bdapi.search.BookSpecification;
import com.bookdatatbase.bdapi.search.SearchRequest;
import com.bookdatatbase.bdapi.search.SearchSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    /**
     * Finds a single book by id.
     * @param id primary key of the Book.
     * @return response entity of Book for the given id.
     */
    public ResponseEntity<Book> findBookById(UUID id) {
        return ResponseEntity.ok(this.getBookById(id));
    }

    public Book findByBookId(Integer bookId) {
        return bookRepository.findByBookId(bookId);
    }

    public Book findFirstByAuthorId(Integer authorID) {
        return bookRepository.findFirstByAuthorId(authorID);
    }

    /**
     * Find all books in the database with pagination default sorted by descending rating.
     * @param offset 0 indexed page number to return results for.
     * @param limit number of results to return per page.
     * @return Pageable of Books
     */
    public ResponseEntity<Page<Book>>  findAllBooksPaginated(int offset, int limit) {
        return ResponseEntity.ok(bookRepository.findAll(PageRequest.of(offset, limit, Sort.by(DESC,"averageRating"))));
    }

    /**
     * Takes in a SearchRequest to search and filter to return a pageable of Books.
     * @param request SearchRequest object consists of zero to many Filters and Sorts as well as page and size for pagination.
     * @return Pageable object of Books filtered by the given criteria.
     */
    public ResponseEntity<Page<Book>> searchBookDatabase(SearchRequest request) {
        SearchSpecification<Book> specification = new SearchSpecification<>(request);
        Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());
        return ResponseEntity.ok(bookRepository.findAll(specification, pageable));
    }


    /**
     * Takes in a SearchRequest param but right now only used the 'value' field to create a Specification<Book> to query the BookRepository.
     * @param request SearchRequest object consists of zero to many Filters and Sorts as well as page and size for pagination.
     * @return Pageable object of Books filtered by the given criteria (only works on the first Filter and its 'value' attribute.
     */
    public ResponseEntity<Page<Book>> findBooksWithGenresLike(SearchRequest request) {
        Specification<Book> specification = new BookSpecification<>(request);
        Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());
        return ResponseEntity.ok(bookRepository.findAll(specification, pageable));
    }

    /**
     * Persists a Book item to the database.
     * @param book given book to save.
     * @return Response entity of the saved book.
     */
    public ResponseEntity<Book> saveBook(Book book) {
        return ResponseEntity.ok(bookRepository.save(book));
    }

    /**
     * Used to update attributes of a single book.
     * @param id UUID primary key of the book in the database.
     * @param book Book object WITH updated attributes to save.
     * @return the saved Book.
     */
    public ResponseEntity<Book> updateBookById(UUID id, Book book) {
        if (Objects.isNull(book)) {
            throw new IllegalArgumentException("Book input param cannot be null");
        }
        Book bookToUpdate = this.getBookById(id);

        bookToUpdate.setIsbn(book.getIsbn());
        bookToUpdate.setSeries(book.getSeries());
        bookToUpdate.setLanguageCode(book.getLanguageCode());
        bookToUpdate.setAverageRating(book.getAverageRating());
        bookToUpdate.setDescription(book.getDescription());
        bookToUpdate.setAuthorId(book.getAuthorId());
        bookToUpdate.setNumPages(book.getNumPages());
        bookToUpdate.setPublicationYear(book.getPublicationYear());
        bookToUpdate.setBookId(book.getBookId());
        bookToUpdate.setRatingsCount(book.getRatingsCount());
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setTitleWithoutSeries(book.getTitleWithoutSeries());

        return this.saveBook(bookToUpdate);
    }

    /**
     * Deletes a book from the database.
     * @param id UUID and primary key of the book.
     * @return ResponseEntity of string with deletion message.
     */
    public ResponseEntity<String> deleteBookById(UUID id) {
        Book bookToDelete = this.getBookById(id);
        bookRepository.delete(bookToDelete);

        return ResponseEntity.ok("Successfully deleted book with ISBN: " + id);
    }

    /**
     *
     * @return current count of books in the repo.
     */
    public long count() {
        return bookRepository.count();
    }

    /**
     * Helper method to get a book by ID or throw an exception if it does not exist.
     * @param id UUID primary key in database.
     * @return Book or BookNotFoundException
     */
    private Book getBookById(UUID id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id.toString()));
    }

}
