package com.bookdatatbase.bdapi.entities;

import com.bookdatatbase.bdapi.models.Role;
import com.bookdatatbase.bdapi.models.Shelf;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    @Column(name = "text_reviews_count")
    private Integer textReviewsCount;

    @Column
    @ElementCollection
    private List<Integer> series;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "language_code")
    private String languageCode;

    @Column(name = "popular_shelves")
    @ElementCollection(targetClass = Shelf.class)
    @Embedded
    @JsonIgnore
    private List<Shelf> popularShelves;

    @Column
    private String asin;

    @Column(name = "is_ebook")
    private Boolean isEbook;

    @Column(name = "average_rating")
    private Double averageRating;

    @Column(name = "kindle_asin")
    private String kindleAsin;

    @Column(name = "similar_books")
    @ElementCollection
    private List<Integer> similarBooks;

    @Column(length = 10000)
    private String description;

    @Column
    private String format;

    @Column
    private String link;

    @Column
    @ElementCollection(targetClass = Role.class, fetch = FetchType.LAZY)
    @Embedded
    private List<Role> authors;

    @Column
    @JsonIgnore
    private String publisher;

    @Column(name = "num_pages")
    private Integer numPages;

    @Column(name = "edition_information")
    private String editionInformation;

    @Column(name = "publication_year")
    private Integer publicationYear;

    @Column
    private String url;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "book_id")
    private String bookId;

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

    public Integer getTextReviewsCount() {
        return textReviewsCount;
    }

    public void setTextReviewsCount(Integer textReviewsCount) {
        this.textReviewsCount = textReviewsCount;
    }

    public List<Integer> getSeries() {
        return series;
    }

    public void setSeries(List<Integer> series) {
        this.series = series;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public List<Shelf> getPopularShelves() {
        return popularShelves;
    }

    public void setPopularShelves(List<Shelf> popularShelves) {
        this.popularShelves = popularShelves;
    }

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    @JsonProperty(value = "isEbook")
    public Boolean isEbook() {
        return isEbook;
    }

    public void setIsEbook(Boolean isEbook) {
        this.isEbook = isEbook;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public String getKindleAsin() {
        return kindleAsin;
    }

    public void setKindleAsin(String kindleAsin) {
        this.kindleAsin = kindleAsin;
    }

    public List<Integer> getSimilarBooks() {
        return similarBooks;
    }

    public void setSimilarBooks(List<Integer> similarBooks) {
        this.similarBooks = similarBooks;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<Role> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Role> authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getNumPages() {
        return numPages;
    }

    public void setNumPages(Integer numPages) {
        this.numPages = numPages;
    }

    public String getEditionInformation() {
        return editionInformation;
    }

    public void setEditionInformation(String editionInformation) {
        this.editionInformation = editionInformation;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
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
                Objects.equals(textReviewsCount, book.textReviewsCount) &&
                Objects.equals(series, book.series) &&
                Objects.equals(countryCode, book.countryCode) &&
                Objects.equals(languageCode, book.languageCode) &&
                Objects.equals(popularShelves, book.popularShelves) &&
                Objects.equals(asin, book.asin) &&
                Objects.equals(isEbook, book.isEbook) &&
                Objects.equals(averageRating, book.averageRating) &&
                Objects.equals(kindleAsin, book.kindleAsin) &&
                Objects.equals(similarBooks, book.similarBooks) &&
                Objects.equals(description, book.description) &&
                Objects.equals(format, book.format) &&
                Objects.equals(link, book.link) &&
                Objects.equals(authors, book.authors) &&
                Objects.equals(publisher, book.publisher) &&
                Objects.equals(numPages, book.numPages) &&
                Objects.equals(editionInformation, book.editionInformation) &&
                Objects.equals(publicationYear, book.publicationYear) &&
                Objects.equals(url, book.url) &&
                Objects.equals(imageUrl, book.imageUrl) &&
                Objects.equals(bookId, book.bookId) &&
                Objects.equals(ratingsCount, book.ratingsCount) &&
                Objects.equals(title, book.title) &&
                Objects.equals(titleWithoutSeries, book.titleWithoutSeries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,
                isbn,
                textReviewsCount,
                series,
                countryCode,
                languageCode,
                popularShelves,
                asin,
                isEbook,
                averageRating,
                kindleAsin,
                similarBooks,
                description,
                format,
                link,
                authors,
                publisher,
                numPages,
                editionInformation,
                publicationYear,
                url,
                imageUrl,
                bookId,
                ratingsCount,
                title,
                titleWithoutSeries);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", textReviewsCount='" + textReviewsCount + '\'' +
                ", series=" + series +
                ", countryCode='" + countryCode + '\'' +
                ", languageCode='" + languageCode + '\'' +
                ", popularShelves=" + popularShelves +
                ", asin='" + asin + '\'' +
                ", isEbook='" + isEbook + '\'' +
                ", averageRating='" + averageRating + '\'' +
                ", kindleAsin='" + kindleAsin + '\'' +
                ", similarBooks=" + similarBooks +
                ", description='" + description + '\'' +
                ", format='" + format + '\'' +
                ", link='" + link + '\'' +
                ", authors=" + authors +
                ", publisher='" + publisher + '\'' +
                ", numPages='" + numPages + '\'' +
                ", edition_information='" + editionInformation + '\'' +
                ", publicationYear='" + publicationYear + '\'' +
                ", url='" + url + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", bookId='" + bookId + '\'' +
                ", ratingsCount='" + ratingsCount + '\'' +
                ", title='" + title + '\'' +
                ", titleWithoutSeries='" + titleWithoutSeries + '\'' +
                '}';
    }

    public static final class BookBuilder {
        private String isbn;
        private Integer textReviewsCount;
        private List<Integer> series;
        private String countryCode;
        private String languageCode;
        private List<Shelf> popularShelves;
        private String asin;
        private Boolean isEbook;
        private Double averageRating;
        private String kindleAsin;
        private List<Integer> similarBooks;
        private String description;
        private String format;
        private String link;
        private List<Role> authors;
        private String publisher;
        private Integer numPages;
        private String edition_information;
        private Integer publicationYear;
        private String url;
        private String imageUrl;
        private String bookId;
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

        public BookBuilder withTextReviewsCount(Integer textReviewsCount) {
            this.textReviewsCount = textReviewsCount;
            return this;
        }

        public BookBuilder withSeries(List<Integer> series) {
            this.series = series;
            return this;
        }

        public BookBuilder withCountryCode(String countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        public BookBuilder withLanguageCode(String languageCode) {
            this.languageCode = languageCode;
            return this;
        }

        public BookBuilder withPopularShelves(List<Shelf> popularShelves) {
            this.popularShelves = popularShelves;
            return this;
        }

        public BookBuilder withAsin(String asin) {
            this.asin = asin;
            return this;
        }

        public BookBuilder withIsEbook(Boolean isEbook) {
            this.isEbook = isEbook;
            return this;
        }

        public BookBuilder withAverageRating(Double averageRating) {
            this.averageRating = averageRating;
            return this;
        }

        public BookBuilder withKindleAsin(String kindleAsin) {
            this.kindleAsin = kindleAsin;
            return this;
        }

        public BookBuilder withSimilarBooks(List<Integer> similarBooks) {
            this.similarBooks = similarBooks;
            return this;
        }

        public BookBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public BookBuilder withFormat(String format) {
            this.format = format;
            return this;
        }

        public BookBuilder withLink(String link) {
            this.link = link;
            return this;
        }

        public BookBuilder withAuthors(List<Role> authors) {
            this.authors = authors;
            return this;
        }

        public BookBuilder withPublisher(String publisher) {
            this.publisher = publisher;
            return this;
        }

        public BookBuilder withNumPages(Integer numPages) {
            this.numPages = numPages;
            return this;
        }

        public BookBuilder withEditioninformation(String edition_information) {
            this.edition_information = edition_information;
            return this;
        }

        public BookBuilder withPublicationYear(Integer publicationYear) {
            this.publicationYear = publicationYear;
            return this;
        }

        public BookBuilder withUrl(String url) {
            this.url = url;
            return this;
        }

        public BookBuilder withImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public BookBuilder withBookId(String bookId) {
            this.bookId = bookId;
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
            book.setTextReviewsCount(textReviewsCount);
            book.setSeries(series);
            book.setCountryCode(countryCode);
            book.setLanguageCode(languageCode);
            book.setPopularShelves(popularShelves);
            book.setAsin(asin);
            book.setIsEbook(isEbook);
            book.setAverageRating(averageRating);
            book.setKindleAsin(kindleAsin);
            book.setSimilarBooks(similarBooks);
            book.setDescription(description);
            book.setFormat(format);
            book.setLink(link);
            book.setAuthors(authors);
            book.setPublisher(publisher);
            book.setNumPages(numPages);
            book.setEditionInformation(edition_information);
            book.setPublicationYear(publicationYear);
            book.setUrl(url);
            book.setImageUrl(imageUrl);
            book.setBookId(bookId);
            book.setRatingsCount(ratingsCount);
            book.setTitle(title);
            book.setTitleWithoutSeries(titleWithoutSeries);
            return book;
        }
    }
}
