package com.bookdatatbase.bdapi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "Authors")
public class Author {
    @Id
    @Column(updatable = false, nullable = false)
    private Integer author_id;

    @Column(name = "average_rating")
    private Double averageRating;

    @Column(name = "text_reviews_count")
    private Integer textReviewsCount;

    @Column
    private String name;

    @Column(name = "ratings_count")
    private Integer ratingsCount;

    public Author(Integer author_id, Double averageRating, Integer textReviewsCount, String name, Integer ratingsCount) {
        this.author_id = author_id;
        this.averageRating = averageRating;
        this.textReviewsCount = textReviewsCount;
        this.name = name;
        this.ratingsCount = ratingsCount;
    }

    public Integer getAuthor_id() {
        return author_id;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Integer getTextReviewsCount() {
        return textReviewsCount;
    }

    public void setTextReviewsCount(Integer textReviewsCount) {
        this.textReviewsCount = textReviewsCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(Integer ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(author_id, author.author_id) &&
                Objects.equals(averageRating, author.averageRating) &&
                Objects.equals(textReviewsCount, author.textReviewsCount) &&
                Objects.equals(name, author.name) &&
                Objects.equals(ratingsCount, author.ratingsCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author_id, averageRating, textReviewsCount, name, ratingsCount);
    }

    @Override
    public String toString() {
        return "Author{" +
                "author_id=" + author_id +
                ", averageRating=" + averageRating +
                ", textReviewsCount=" + textReviewsCount +
                ", name='" + name + '\'' +
                ", ratingsCount=" + ratingsCount +
                '}';
    }
}
