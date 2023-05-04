package com.bookdatatbase.bdapi.repositories;

import com.bookdatatbase.bdapi.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Spring Boot repository interface for Author entities.
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID>, JpaSpecificationExecutor<Author> {
    public Author findByAuthorId(Integer authorId);
}
