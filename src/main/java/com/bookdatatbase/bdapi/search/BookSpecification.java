package com.bookdatatbase.bdapi.search;

import com.bookdatatbase.bdapi.entities.Book;
import com.bookdatatbase.bdapi.entities.BookGenre;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;

public class BookSpecification {

    public BookSpecification() {}

    /**
     * A method to create a JPA Specification that JOINS Book & BookGenre tables querying Books that have bookGenres that contain the given string.
     * @param withGenre string used in a LIKE SQL command.
     * @return Specification<Book>
     */
    public static Specification<Book> hasBookWithGenreLike(String withGenre) {
        return (root, query, criteriaBuilder) -> {
            Join<BookGenre, Book> bookBookGenreJoin = root.join("bookGenre");
            return criteriaBuilder.like(bookBookGenreJoin.get("genres"),"%" + withGenre + "%");
        };
    }

}
