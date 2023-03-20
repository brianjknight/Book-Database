package com.bookdatatbase.bdapi.dataseeders;

import com.bookdatatbase.bdapi.entities.Author;
import com.bookdatatbase.bdapi.json.AuthorDeserializer;
import com.bookdatatbase.bdapi.services.AuthorService;
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
public class AuthorSeeder implements CommandLineRunner {
    @Autowired
    private AuthorService authorService;

    @Override
    public void run(String... args) throws Exception {
        this.seedAuthorData();
    }

    private void seedAuthorData() {
        if(authorService.count() == 0) {
            int count = 0;
            int limit = 50;

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
                    count++;
                    Author author = gson.fromJson(line, Author.class);
                    authorService.saveAuthor(author);

                    if (count >= limit) {
                        break;
                    }

                    line = reader.readLine();
                }
            } catch (IOException e) {
                System.out.println("Could not load author data.");
                e.printStackTrace();
            }
        } else {
            System.out.printf("bd.api database is already populated with %d Authors", authorService.count());
        }

    }
}
