package com.bookdatatbase.bdapi.repositories;

import com.bookdatatbase.bdapi.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Spring Boot repository interface for the Book entity.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, UUID>, JpaSpecificationExecutor<Book> {
    public Book findByBookId(Integer bookId);

    public Book findFirstByAuthorId(Integer authorID);
}