package com.bookdatatbase.bdapi.dataseeders;

import com.bookdatatbase.bdapi.entities.Book;
import com.bookdatatbase.bdapi.json.BookDeserializer;
import com.bookdatatbase.bdapi.services.BookService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

@Component
public class BooksSeeder implements CommandLineRunner {
    @Autowired
    BookService bookService;

    @Override
    public void run(String... args) throws Exception {
        seedBookData();
    }

    private void seedBookData() {
        if(bookService.count() == 0) {

            int count = 0;
            int dbLimit = 20;

            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Book.class, new BookDeserializer());
            Gson gson = builder.create();

            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new GZIPInputStream(
                                        new FileInputStream("data/book-data/goodreads_books_mystery_thriller_crime.json.gz"))));
                String line = reader.readLine();

                while (line != null) {
                    count++;
                    Book book = gson.fromJson(line, Book.class);
                    bookService.saveBook(book);

                    if (count >= dbLimit) {
                        break;
                    }

                    line = reader.readLine();
                }
            } catch (IOException e) {
                System.out.println("Could not load book data.");
                e.printStackTrace();
            }
        } else {
            System.out.printf("bd.api database is already populated with %d Books", bookService.count());
        }

    }
}
