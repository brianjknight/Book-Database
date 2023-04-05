package com.bookdatatbase.bdapi.dataseeders;

import com.bookdatatbase.bdapi.entities.BookGenre;
import com.bookdatatbase.bdapi.json.BookGenreDeserializer;
import com.bookdatatbase.bdapi.services.BookGenreService;
import com.bookdatatbase.bdapi.services.BookService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.zip.GZIPInputStream;

//@Component
public class BookGenreSeeder implements CommandLineRunner {

    @Autowired
    private BookGenreService bookGenreService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BooksSeeder booksSeeder;

    @Override
    public void run(String... args) throws Exception {
        this.seedBookGenreData();
    }

    private void seedBookGenreData() {
        if(bookGenreService.count() != 0) {
            long start = System.currentTimeMillis();

            int seeded = 0;
            int notSeeded = 0;
            int limit = 1_000;

            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(BookGenre.class, new BookGenreDeserializer());
            Gson gson = builder.create();

            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new GZIPInputStream(
                                        new FileInputStream("data/genre-data/goodreads_book_genres_initial.json.gz"))));
                String line = reader.readLine();

                while (line != null) {
                    BookGenre bookGenre = gson.fromJson(line, BookGenre.class);

                    if(Objects.nonNull(bookService.findByBookId(bookGenre.getBookId()))) {
                        seeded++;
                        bookGenreService.saveBookGenre(bookGenre);
                    } else {
                        notSeeded++;
                    }

                    if (seeded >= limit) {
                        break;
                    }

                    line = reader.readLine();
                }

                long end = System.currentTimeMillis();

                System.out.println("*".repeat(100));
                System.out.println("*".repeat(100));
                System.out.println("*".repeat(100));

                System.out.println("Time in milliseconds to seed BookGenres = " + (end-start));
                System.out.println("Number of BookGenres persisted to the database = " + seeded);
                System.out.println("Number of BookGenres NOT persisted to the database = " + notSeeded);

                System.out.println("*".repeat(100));
                System.out.println("*".repeat(100));
                System.out.println("*".repeat(100));

            } catch (IOException e) {
                System.out.println("Could not load book genre data.");
                e.printStackTrace();
            }
        }
        else {
            System.out.printf("bd.api database is already populated with %d BookGenres", bookGenreService.count());
        }
    }
}
