package com.bookdatatbase.bdapi.repositories;

import com.bookdatatbase.bdapi.entities.BookGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Boot repository interface for BookGenre entity.
 */
@Repository
public interface BookGenreRepository extends JpaRepository<BookGenre, Integer>, JpaSpecificationExecutor<BookGenre> {
}
