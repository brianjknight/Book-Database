package com.bookdatatbase.bdapi.services;

import com.bookdatatbase.bdapi.entities.Author;
import com.bookdatatbase.bdapi.exceptions.AuthorNotFoundException;
import com.bookdatatbase.bdapi.repositories.AuthorRepository;
import com.bookdatatbase.bdapi.search.SearchRequest;
import com.bookdatatbase.bdapi.search.SearchSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

/**
 * Spring Boot service class for Author.
 * This is a layer for handling requests received by the controller and passed to the repository.
 * Includes corresponding CRUD operations for handling each controller method.
 */
@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public ResponseEntity<Author> findById(UUID id) {
        return ResponseEntity.ok(this.getAuthorById(id));
    }

    public Author findByAuthorId(Integer authorId) {
        return authorRepository.findByAuthorId(authorId);
    }

    public ResponseEntity<Page<Author>> searchAuthorDatabase(SearchRequest request) {
        SearchSpecification<Author> specification = new SearchSpecification<>(request);
        Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());
        return ResponseEntity.ok(authorRepository.findAll(specification, pageable));
    }

    public ResponseEntity<Author> saveAuthor(Author author) {
        if(Objects.isNull(author)) {
            throw new IllegalArgumentException("Author provided cannot be null.");
        }

        return ResponseEntity.ok(authorRepository.save(author));
    }

    public ResponseEntity<Author> updateAuthorById(UUID id, Author author) {
        if(Objects.isNull(author)) {
            throw new IllegalArgumentException("Author provided cannot be null.");
        }

        Author authorToUpdate = this.getAuthorById(id);

        authorToUpdate.setAverageRating(author.getAverageRating());
        authorToUpdate.setTextReviewsCount(author.getTextReviewsCount());
        authorToUpdate.setName(author.getName());
        authorToUpdate.setRatingsCount(author.getRatingsCount());

        return this.saveAuthor(authorToUpdate);
    }

    public ResponseEntity<String> deleteByAuthorId(UUID id) {
        Author authorToDelete = this.getAuthorById(id);
        authorRepository.delete(authorToDelete);

        return ResponseEntity.ok("Successfully deleted author with ID: " + id.toString());
    }

    public long count() {
        return authorRepository.count();
    }

    private Author getAuthorById(UUID id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));
    }
}
