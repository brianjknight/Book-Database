package com.bookdatatbase.bdapi.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
import java.util.UUID;

/**
 *POJO for Author entity and table.
 */

@Entity
@Table(name = "Authors")
@Embeddable
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private UUID id;

    @Column(name = "author_id")
    private Integer authorId;

    @Column(name = "average_rating")
    private Double averageRating;

    @Column(name = "text_reviews_count")
    private Integer textReviewsCount;

    @Column
    private String name;

    @Column(name = "ratings_count")
    private Integer ratingsCount;

    public Author() {}

    public Author(Integer authorId, Double averageRating, Integer textReviewsCount, String name, Integer ratingsCount) {
        this.authorId = authorId;
        this.averageRating = averageRating;
        this.textReviewsCount = textReviewsCount;
        this.name = name;
        this.ratingsCount = ratingsCount;
    }

    public Author(UUID id, Integer authorId, Double averageRating, Integer textReviewsCount, String name, Integer ratingsCount) {
        this.id = id;
        this.authorId = authorId;
        this.averageRating = averageRating;
        this.textReviewsCount = textReviewsCount;
        this.name = name;
        this.ratingsCount = ratingsCount;
    }

    public UUID getId() {
        return id;
    }

    public Integer getAuthorId() {
        return authorId;
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
        return  Objects.equals(id, author.id) &&
                Objects.equals(authorId, author.authorId) &&
                Objects.equals(averageRating, author.averageRating) &&
                Objects.equals(textReviewsCount, author.textReviewsCount) &&
                Objects.equals(name, author.name) &&
                Objects.equals(ratingsCount, author.ratingsCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authorId, averageRating, textReviewsCount, name, ratingsCount);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", averageRating=" + averageRating +
                ", textReviewsCount=" + textReviewsCount +
                ", name='" + name + '\'' +
                ", ratingsCount=" + ratingsCount +
                '}';
    }
}