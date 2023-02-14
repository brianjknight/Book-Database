package com.bookdatatbase.bdapi;

import com.bookdatatbase.bdapi.entities.Book;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestApp {
    public static void main(String[] args) {

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String jsonBook = "{\"isbn\": \"12345\", \"text_reviews_count\": \"5\", \"series\": [], \"country_code\": \"US\", \"language_code\": \"en-GB\", \"popular_shelves\": [{\"count\": \"27\", \"name\": \"to-read\"}, {\"count\": \"3\", \"name\": \"currently-reading\"}, {\"count\": \"2\", \"name\": \"kindle\"}, {\"count\": \"1\", \"name\": \"indie-barely-read\"}, {\"count\": \"1\", \"name\": \"thriller\"}, {\"count\": \"1\", \"name\": \"mysteries-and-thrillers\"}, {\"count\": \"1\", \"name\": \"authors\"}, {\"count\": \"1\", \"name\": \"all-kindle-books\"}, {\"count\": \"1\", \"name\": \"stand-alone\"}, {\"count\": \"1\", \"name\": \"red-kindle\"}, {\"count\": \"1\", \"name\": \"kindle-owned\"}, {\"count\": \"1\", \"name\": \"free-reads\"}, {\"count\": \"1\", \"name\": \"owned\"}, {\"count\": \"1\", \"name\": \"free-ereader\"}, {\"count\": \"1\", \"name\": \"unread-purchases\"}], \"asin\": \"B00UQVGQMO\", \"is_ebook\": \"true\", \"average_rating\": \"3.80\", \"kindle_asin\": \"B00UQVGQMO\", \"similar_books\": [], \"description\": \"\", \"format\": \"\", \"link\": \"https://www.goodreads.com/book/show/25162836-dark-flames-rising\", \"authors\": [{\"author_id\": \"13658169\", \"role\": \"\"}], \"publisher\": \"\", \"num_pages\": \"\", \"publication_day\": \"\", \"isbn13\": \"\", \"publication_month\": \"\", \"edition_information\": \"\", \"publication_year\": \"\", \"url\": \"https://www.goodreads.com/book/show/25162836-dark-flames-rising\", \"image_url\": \"https://s.gr-assets.com/assets/nophoto/book/111x148-bcc042a9c91a29c1d680899eff700a03.png\", \"book_id\": \"25162836\", \"ratings_count\": \"8\", \"work_id\": \"44866515\", \"title\": \"Dark Flames Rising\", \"title_without_series\": \"Dark Flames Rising\"}";
        System.out.println("Json string:");
        System.out.println(jsonBook);

        Book darkFlamesRising = gson.fromJson(jsonBook, Book.class);
        System.out.println("Book object:");
        System.out.println(darkFlamesRising);

        String darkFlamesRisingJson = gson.toJson(darkFlamesRising);
        System.out.println("bookToJson:");
        System.out.println(darkFlamesRisingJson);

    }

}
