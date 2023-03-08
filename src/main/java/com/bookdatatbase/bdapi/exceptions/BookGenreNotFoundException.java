package com.bookdatatbase.bdapi.exceptions;

import java.io.Serial;
import java.util.UUID;

public class BookGenreNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 3545986121159673594L;

    public BookGenreNotFoundException(UUID id) {
        super("BookGenre with ID: " + id.toString() + " cannot be found");
    }
}
