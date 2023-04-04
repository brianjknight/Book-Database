package com.bookdatatbase.bdapi.search;

import com.bookdatatbase.bdapi.entities.Book;
import com.bookdatatbase.bdapi.entities.BookGenre;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;

public class BookSpecification {

    public BookSpecification() {}

    public static Specification<Book> hasBookWithGenreLike(String withGenre) {
        return (root, query, criteriaBuilder) -> {
            Join<BookGenre, Book> bookBookGenreJoin = root.join("bookId");
            return criteriaBuilder.equal(bookBookGenreJoin.get("genres"),withGenre);
        };
    }

}
