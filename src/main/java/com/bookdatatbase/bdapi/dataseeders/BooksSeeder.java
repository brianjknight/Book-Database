package com.bookdatatbase.bdapi.dataseeders;

import com.bookdatatbase.bdapi.entities.Book;
import com.bookdatatbase.bdapi.json.BookDeserializer;
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
public class BooksSeeder implements CommandLineRunner {

    @Autowired
    private BookService bookService;

    @Override
    public void run(String... args) throws Exception {
        seedBookData();
    }

    private void seedBookData() {
        if(bookService.count() == 0) {

            int seeded = 0;
            int notSeeded = 0;
            int limit = 10_000;

            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Book.class, new BookDeserializer());
            Gson gson = builder.create();

            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new GZIPInputStream(
                                        new FileInputStream("data/book-data/goodreads_books.json.gz"))));
                String line = reader.readLine();

                while (line != null) {
                    Book book = gson.fromJson(line, Book.class);

                    if(Objects.isNull(book.getRatingsCount()) ||
                            book.getRatingsCount()<1_000 ||
                            Objects.isNull(book.getDescription()) ||
                            book.getDescription().equals("")) {
                        notSeeded++;
                    }
                    else {
                        bookService.saveBook(book);
                        seeded++;
                    }

                    if (seeded >= limit) {
                        break;
                    }

                    line = reader.readLine();
                }
            } catch (IOException e) {
                System.out.println("Could not load book data.");
                e.printStackTrace();
            }
            System.out.println("Number of books persisted to database = " + seeded);
            System.out.println("Number of books not persisted to database = " + notSeeded);
        } else {
            System.out.printf("bd.api database is already populated with %d Books", bookService.count());
        }

    }
}
