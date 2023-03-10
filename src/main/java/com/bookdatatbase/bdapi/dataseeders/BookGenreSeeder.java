package com.bookdatatbase.bdapi.dataseeders;

import com.bookdatatbase.bdapi.entities.BookGenre;
import com.bookdatatbase.bdapi.json.BookGenreDeserializer;
import com.bookdatatbase.bdapi.services.BookGenreService;
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
public class BookGenreSeeder implements CommandLineRunner {
    @Autowired
    private BookGenreService bookGenreService;

    @Override
    public void run(String... args) throws Exception {
        this.seedBookGenreData();
    }

    private void seedBookGenreData() {
        if(bookGenreService.count() == 0) {
            int count = 0;
            int limit = 50;

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
                    count++;
                    BookGenre bookGenre = gson.fromJson(line, BookGenre.class);
                    bookGenreService.saveBookGenre(bookGenre);

                    if (count >= limit) {
                        break;
                    }

                    line = reader.readLine();
                }
            } catch (IOException e) {
                System.out.println("Could not load book genre data.");
                e.printStackTrace();
            }
        }
        else {
            System.out.printf("bd.api database is already populated with %d items", bookGenreService.count());
        }
    }
}
