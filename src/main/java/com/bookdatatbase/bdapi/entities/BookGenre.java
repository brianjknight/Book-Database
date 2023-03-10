package com.bookdatatbase.bdapi.entities;

import com.bookdatatbase.bdapi.models.Genre;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "BookGenres")
public class BookGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "genres")
    @ElementCollection
    @Embedded
    private List<Genre> genres;

    public BookGenre() {}

    public BookGenre(Integer bookId, List<Genre> genres) {
        this.bookId = bookId;
        this.genres = genres;
    }

    public BookGenre(UUID id, Integer bookId, List<Genre> genres) {
        this.id = id;
        this.bookId = bookId;
        this.genres = genres;
    }

    public UUID getId() {
        return id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookGenre bookGenre = (BookGenre) o;
        return Objects.equals(id, bookGenre.id) &&
                Objects.equals(bookId, bookGenre.bookId) &&
                Objects.equals(genres, bookGenre.genres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookId, genres);
    }

    @Override
    public String toString() {
        return "BookGenre{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", genres=" + genres.toString() +
                '}';
    }
}