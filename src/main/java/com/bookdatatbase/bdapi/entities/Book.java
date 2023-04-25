package com.bookdatatbase.bdapi.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "Books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(insertable = false, updatable = false, nullable = false)
    private UUID id;

    @Column
    private String isbn;

    @Column
    @ElementCollection
    private List<Integer> series;

    @Column(name = "language_code")
    private String languageCode;

    @Column(name = "average_rating")
    private Double averageRating;

    @Column(length = 15000)
    private String description;

    @Column(name = "author_id")
    private Integer authorId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author")
    @Embedded
    private Author author;

    @Column(name = "num_pages")
    private Integer numPages;

    @Column(name = "publication_year")
    private Integer publicationYear;

    @Column(name = "book_id")
    private Integer bookId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_genre")
    @Embedded
    private BookGenre bookGenre;

    @Column(name = "ratings_count")
    private Integer ratingsCount;

    @Column
    private String title;

    @Column(name = "title_without_series")
    private String titleWithoutSeries;

    public Book() {}

    public UUID getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<Integer> getSeries() {
        return series;
    }

    public void setSeries(List<Integer> series) {
        this.series = series;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Integer getNumPages() {
        return numPages;
    }

    public void setNumPages(Integer numPages) {
        this.numPages = numPages;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public BookGenre getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(BookGenre bookGenre) {
        this.bookGenre = bookGenre;
    }

    public Integer getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(Integer ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleWithoutSeries() {
        return titleWithoutSeries;
    }

    public void setTitleWithoutSeries(String titleWithoutSeries) {
        this.titleWithoutSeries = titleWithoutSeries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
                Objects.equals(isbn, book.isbn) &&
                Objects.equals(series, book.series) &&
                Objects.equals(languageCode, book.languageCode) &&
                Objects.equals(averageRating, book.averageRating) &&
                Objects.equals(description, book.description) &&
                Objects.equals(authorId, book.authorId) &&
                Objects.equals(author, book.author) &&
                Objects.equals(numPages, book.numPages) &&
                Objects.equals(publicationYear, book.publicationYear) &&
                Objects.equals(bookId, book.bookId) &&
                Objects.equals(bookGenre, book.bookGenre) &&
                Objects.equals(ratingsCount, book.ratingsCount) &&
                Objects.equals(title, book.title) &&
                Objects.equals(titleWithoutSeries, book.titleWithoutSeries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,
                isbn,
                series,
                languageCode,
                averageRating,
                description,
                authorId,
                author,
                numPages,
                publicationYear,
                bookId,
                bookGenre,
                ratingsCount,
                title,
                titleWithoutSeries);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", series=" + series +
                ", languageCode='" + languageCode + '\'' +
                ", averageRating=" + averageRating +
                ", description='" + description + '\'' +
                ", authorId=" + authorId +
                ", author=" + author +
                ", numPages=" + numPages +
                ", publicationYear=" + publicationYear +
                ", bookId=" + bookId +
                ", bookGenre=" + bookGenre +
                ", ratingsCount=" + ratingsCount +
                ", title='" + title + '\'' +
                ", titleWithoutSeries='" + titleWithoutSeries + '\'' +
                '}';
    }

    public static final class BookBuilder {
        private String isbn;
        private List<Integer> series;
        private String languageCode;
        private Double averageRating;
        private String description;
        private Integer authorId;
        private Author author;
        private Integer numPages;
        private Integer publicationYear;
        private Integer bookId;
        private BookGenre bookGenre;
        private Integer ratingsCount;
        private String title;
        private String titleWithoutSeries;

        private BookBuilder() {
        }

        public static BookBuilder aBook() {
            return new BookBuilder();
        }

        public BookBuilder withIsbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public BookBuilder withSeries(List<Integer> series) {
            this.series = series;
            return this;
        }

        public BookBuilder withLanguageCode(String languageCode) {
            this.languageCode = languageCode;
            return this;
        }

        public BookBuilder withAverageRating(Double averageRating) {
            this.averageRating = averageRating;
            return this;
        }

        public BookBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public BookBuilder withAuthorId(Integer authorId) {
            this.authorId = authorId;
            return this;
        }

        public BookBuilder withAuthor(Author author) {
            this.author = author;
            return this;
        }

        public BookBuilder withNumPages(Integer numPages) {
            this.numPages = numPages;
            return this;
        }

        public BookBuilder withPublicationYear(Integer publicationYear) {
            this.publicationYear = publicationYear;
            return this;
        }

        public BookBuilder withBookId(Integer bookId) {
            this.bookId = bookId;
            return this;
        }

        public BookBuilder withBookGenre(BookGenre bookGenre) {
            this.bookGenre = bookGenre;
            return this;
        }

        public BookBuilder withRatingsCount(Integer ratingsCount) {
            this.ratingsCount = ratingsCount;
            return this;
        }

        public BookBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public BookBuilder withTitleWithoutSeries(String titleWithoutSeries) {
            this.titleWithoutSeries = titleWithoutSeries;
            return this;
        }

        public Book build() {
            Book book = new Book();
            book.setIsbn(isbn);
            book.setSeries(series);
            book.setLanguageCode(languageCode);
            book.setAverageRating(averageRating);
            book.setDescription(description);
            book.setAuthorId(authorId);
            book.setAuthor(author);
            book.setNumPages(numPages);
            book.setPublicationYear(publicationYear);
            book.setBookId(bookId);
            book.setBookGenre(bookGenre);
            book.setRatingsCount(ratingsCount);
            book.setTitle(title);
            book.setTitleWithoutSeries(titleWithoutSeries);
            return book;
        }
    }
}