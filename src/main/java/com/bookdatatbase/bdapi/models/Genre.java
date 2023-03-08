package com.bookdatatbase.bdapi.models;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Genre {
    private String genre;
    private Integer count;

    public Genre() {}

    public Genre(String genre, Integer count) {
        this.genre = genre;
        this.count = count;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre1 = (Genre) o;
        return Objects.equals(genre, genre1.genre) && Objects.equals(count, genre1.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genre, count);
    }

    @Override
    public String toString() {
        return "Genre{" +
                "genre='" + genre + '\'' +
                ", count=" + count +
                '}';
    }
}
