package com.bookdatatbase.bdapi.dataseeders;

import com.bookdatatbase.bdapi.entities.Author;
import com.bookdatatbase.bdapi.json.AuthorDeserializer;
import com.bookdatatbase.bdapi.services.AuthorService;
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
public class AuthorSeeder implements CommandLineRunner {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BooksSeeder booksSeeder;

    @Override
    public void run(String... args) throws Exception {
        this.seedAuthorData();
    }

    private void seedAuthorData() {

        if(authorService.count() == 0) {
            long start = System.currentTimeMillis();

            int seeded = 0;
            int notSeeded = 0;
            int limit = 10_000;

            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Author.class, new AuthorDeserializer());
            Gson gson = builder.create();

            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new GZIPInputStream(
                                        new FileInputStream("data/author-data/goodreads_book_authors.json.gz"))));
                String line = reader.readLine();

                while (line != null) {
                    Author author = gson.fromJson(line, Author.class);

                    if(author.getRatingsCount() >= 1_000 && Objects.nonNull(bookService.findFirstByAuthorId(author.getAuthorId()))) {
                        seeded++;
                        authorService.saveAuthor(author);
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

                System.out.println("Time in milliseconds to seed Authors = " + (end-start));
                System.out.println("Number of Authors persisted to the database = " + seeded);
                System.out.println("Number of Authors NOT persisted to the database = " +  notSeeded);

                System.out.println("*".repeat(100));
                System.out.println("*".repeat(100));
                System.out.println("*".repeat(100));

            } catch (IOException e) {
                System.out.println("Could not load author data.");
                e.printStackTrace();
            }
        } else {
            System.out.printf("bd.api database is already populated with %d Authors", authorService.count());
        }

    }
}
