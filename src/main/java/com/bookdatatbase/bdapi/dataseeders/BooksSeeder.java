package com.bookdatatbase.bdapi.dataseeders;

import com.bookdatatbase.bdapi.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BooksSeeder implements CommandLineRunner {
    @Autowired
    BookService bookService;

    @Override
    public void run(String... args) throws Exception {
        seedBookData();
    }

    private void seedBookData() {

    }
}
