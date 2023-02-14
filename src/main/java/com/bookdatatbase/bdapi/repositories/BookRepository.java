package com.bookdatatbase.bdapi.repositories;

import com.bookdatatbase.bdapi.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
}