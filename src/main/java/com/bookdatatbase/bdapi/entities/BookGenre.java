package com.bookdatatbase.bdapi.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "BookGenres")
@Embeddable
public class BookGenre {

    @Id
    @Column(name = "book_id", insertable = false, updatable = false, nullable = false)
    private Integer bookId;

    @Column(name = "genres")
    private String genres;

    public BookGenre() {}

    public BookGenre(Integer bookId, String genres) {
        this.bookId = bookId;
        this.genres = genres;
    }

    public Integer getBookId() {
        return bookId;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookGenre bookGenre = (BookGenre) o;
        return  Objects.equals(bookId, bookGenre.bookId) &&
                Objects.equals(genres, bookGenre.genres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, genres);
    }

    @Override
    public String toString() {
        return "BookGenre{" +
                ", bookId=" + bookId +
                ", genres=" + genres.toString() +
                '}';
    }
}