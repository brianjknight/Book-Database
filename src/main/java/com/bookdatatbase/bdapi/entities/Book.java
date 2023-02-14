package com.bookdatatbase.bdapi.entities;

import com.bookdatatbase.bdapi.models.Role;

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

@Entity
@Table(name = "Books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private String isbn;

    @Column(name = "text_reviews_count")
    private String textReviewsCount;

    @Column
    @ElementCollection
    private List<String> series;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "language_code")
    private String languageCode;

    @Column(name = "popular_shelves")
    @ElementCollection(targetClass = Shelf.class)
    @Embedded
    private List<Shelf> popularShelves;

    @Column
    private String asin;

    @Column(name = "is_ebook")
    private String isEbook;

    @Column(name = "average_rating")
    private String averageRating;

    @Column(name = "kindle_asin")
    private String kindleAsin;

    @Column(name = "similar_books")
    @ElementCollection
    private List<String> similarBooks;

    @Column
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
    private String publisher;

    @Column(name = "num_pages")
    private String numPages;

    @Column(name = "publication_day")
    private String publicationDay;

    @Column
    private String isbn13;

    @Column(name = "publication_month")
    private String publicationMonth;

    @Column(name = "editionInformation")
    private String edition_information;

    @Column(name = "publication_year")
    private String publicationYear;

    @Column
    private String url;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "book_id")
    private String bookId;

    @Column(name = "ratings_count")
    private String ratingsCount;

    @Column(name = "work_id")
    private String workId;

    @Column
    private String title;

    @Column(name = "title_without_series")
    private String titleWithoutSeries;

    public Book() {}

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTextReviewsCount() {
        return textReviewsCount;
    }

    public void setTextReviewsCount(String textReviewsCount) {
        this.textReviewsCount = textReviewsCount;
    }

    public List<String> getSeries() {
        return series;
    }

    public void setSeries(List<String> series) {
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

    public String getIsEbook() {
        return isEbook;
    }

    public void setIsEbook(String isEbook) {
        this.isEbook = isEbook;
    }

    public String getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    public String getKindleAsin() {
        return kindleAsin;
    }

    public void setKindleAsin(String kindleAsin) {
        this.kindleAsin = kindleAsin;
    }

    public List<String> getSimilarBooks() {
        return similarBooks;
    }

    public void setSimilarBooks(List<String> similarBooks) {
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

    public String getNumPages() {
        return numPages;
    }

    public void setNumPages(String numPages) {
        this.numPages = numPages;
    }

    public String getPublicationDay() {
        return publicationDay;
    }

    public void setPublicationDay(String publicationDay) {
        this.publicationDay = publicationDay;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getPublicationMonth() {
        return publicationMonth;
    }

    public void setPublicationMonth(String publicationMonth) {
        this.publicationMonth = publicationMonth;
    }

    public String getEdition_information() {
        return edition_information;
    }

    public void setEdition_information(String edition_information) {
        this.edition_information = edition_information;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
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

    public String getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(String ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
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
        return Objects.equals(isbn, book.isbn) && Objects.equals(textReviewsCount, book.textReviewsCount) && Objects.equals(series, book.series) && Objects.equals(countryCode, book.countryCode) && Objects.equals(languageCode, book.languageCode) && Objects.equals(popularShelves, book.popularShelves) && Objects.equals(asin, book.asin) && Objects.equals(isEbook, book.isEbook) && Objects.equals(averageRating, book.averageRating) && Objects.equals(kindleAsin, book.kindleAsin) && Objects.equals(similarBooks, book.similarBooks) && Objects.equals(description, book.description) && Objects.equals(format, book.format) && Objects.equals(link, book.link) && Objects.equals(authors, book.authors) && Objects.equals(publisher, book.publisher) && Objects.equals(numPages, book.numPages) && Objects.equals(publicationDay, book.publicationDay) && Objects.equals(isbn13, book.isbn13) && Objects.equals(publicationMonth, book.publicationMonth) && Objects.equals(edition_information, book.edition_information) && Objects.equals(publicationYear, book.publicationYear) && Objects.equals(url, book.url) && Objects.equals(imageUrl, book.imageUrl) && Objects.equals(bookId, book.bookId) && Objects.equals(ratingsCount, book.ratingsCount) && Objects.equals(workId, book.workId) && Objects.equals(title, book.title) && Objects.equals(titleWithoutSeries, book.titleWithoutSeries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, textReviewsCount, series, countryCode, languageCode, popularShelves, asin, isEbook, averageRating, kindleAsin, similarBooks, description, format, link, authors, publisher, numPages, publicationDay, isbn13, publicationMonth, edition_information, publicationYear, url, imageUrl, bookId, ratingsCount, workId, title, titleWithoutSeries);
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
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
                ", publicationDay='" + publicationDay + '\'' +
                ", isbn13='" + isbn13 + '\'' +
                ", publicationMonth='" + publicationMonth + '\'' +
                ", edition_information='" + edition_information + '\'' +
                ", publicationYear='" + publicationYear + '\'' +
                ", url='" + url + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", bookId='" + bookId + '\'' +
                ", ratingsCount='" + ratingsCount + '\'' +
                ", workId='" + workId + '\'' +
                ", title='" + title + '\'' +
                ", titleWithoutSeries='" + titleWithoutSeries + '\'' +
                '}';
    }

    public static final class BookBuilder {
        private String isbn;
        private String textReviewsCount;
        private List<String> series;
        private String countryCode;
        private String languageCode;
        private List<Shelf> popularShelves;
        private String asin;
        private String isEbook;
        private String averageRating;
        private String kindleAsin;
        private List<String> similarBooks;
        private String description;
        private String format;
        private String link;
        private List<Role> authors;
        private String publisher;
        private String numPages;
        private String publicationDay;
        private String isbn13;
        private String publicationMonth;
        private String edition_information;
        private String publicationYear;
        private String url;
        private String imageUrl;
        private String bookId;
        private String ratingsCount;
        private String workId;
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

        public BookBuilder withTextReviewsCount(String textReviewsCount) {
            this.textReviewsCount = textReviewsCount;
            return this;
        }

        public BookBuilder withSeries(List<String> series) {
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

        public BookBuilder withIsEbook(String isEbook) {
            this.isEbook = isEbook;
            return this;
        }

        public BookBuilder withAverageRating(String averageRating) {
            this.averageRating = averageRating;
            return this;
        }

        public BookBuilder withKindleAsin(String kindleAsin) {
            this.kindleAsin = kindleAsin;
            return this;
        }

        public BookBuilder withSimilarBooks(List<String> similarBooks) {
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

        public BookBuilder withNumPages(String numPages) {
            this.numPages = numPages;
            return this;
        }

        public BookBuilder withPublicationDay(String publicationDay) {
            this.publicationDay = publicationDay;
            return this;
        }

        public BookBuilder withIsbn13(String isbn13) {
            this.isbn13 = isbn13;
            return this;
        }

        public BookBuilder withPublicationMonth(String publicationMonth) {
            this.publicationMonth = publicationMonth;
            return this;
        }

        public BookBuilder withEdition_information(String edition_information) {
            this.edition_information = edition_information;
            return this;
        }

        public BookBuilder withPublicationYear(String publicationYear) {
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

        public BookBuilder withRatingsCount(String ratingsCount) {
            this.ratingsCount = ratingsCount;
            return this;
        }

        public BookBuilder withWorkId(String workId) {
            this.workId = workId;
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
            book.setPublicationDay(publicationDay);
            book.setIsbn13(isbn13);
            book.setPublicationMonth(publicationMonth);
            book.setEdition_information(edition_information);
            book.setPublicationYear(publicationYear);
            book.setUrl(url);
            book.setImageUrl(imageUrl);
            book.setBookId(bookId);
            book.setRatingsCount(ratingsCount);
            book.setWorkId(workId);
            book.setTitle(title);
            book.setTitleWithoutSeries(titleWithoutSeries);
            return book;
        }
    }
}
