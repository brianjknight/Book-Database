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

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public ResponseEntity<Author> findAuthorById(Integer id) {
        return ResponseEntity.ok(this.getAuthorById(id));
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

    public ResponseEntity<Author> updateAuthor(Author author) {
        if(Objects.isNull(author)) {
            throw new IllegalArgumentException("Author provided cannot be null.");
        }

        Author authorToUpdate = this.getAuthorById(author.getAuthorId());

        authorToUpdate.setAverageRating(author.getAverageRating());
        authorToUpdate.setTextReviewsCount(author.getTextReviewsCount());
        authorToUpdate.setName(author.getName());
        authorToUpdate.setRatingsCount(author.getRatingsCount());

        return this.saveAuthor(authorToUpdate);
    }

    public ResponseEntity<String> deleteAuthorById(Integer id) {
        Author authorToDelete = this.getAuthorById(id);
        authorRepository.delete(authorToDelete);

        return ResponseEntity.ok("Successfully deleted author with ID: " + id.toString());
    }

    public long count() {
        return authorRepository.count();
    }

    private Author getAuthorById(Integer id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));
    }
}
