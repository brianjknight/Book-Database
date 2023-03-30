package com.bookdatatbase.bdapi.dataseeders;

import com.bookdatatbase.bdapi.entities.Book;
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

@Component
public class BookGenreSeeder implements CommandLineRunner {
    @Autowired
    private BookGenreService bookGenreService;

//    @Autowired
//    private BookService bookService;

    @Override
    public void run(String... args) throws Exception {
        this.seedBookGenreData();
    }

    private void seedBookGenreData() {
        if(bookGenreService.count() == 0) {
            int seeded = 0;
            int notSeeded = 0;
            int limit = 1000;

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

                    bookGenreService.saveBookGenre(bookGenre);
                    seeded++;

//                    Book book = bookService.findByBookId(bookGenre.getBookId());
//
//                    if(Objects.nonNull(book)) {
//                        seeded++;
//                        bookGenreService.saveBookGenre(bookGenre);
//                    } else {
//                        notSeeded++;
//                    }

                    if (seeded >= limit) {
                        break;
                    }

                    line = reader.readLine();
                }
                System.out.println("Number of BookGenres persisted to the database = " + seeded);
                System.out.println("Number of BookGenres NOT persisted to the database = " + notSeeded);
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
