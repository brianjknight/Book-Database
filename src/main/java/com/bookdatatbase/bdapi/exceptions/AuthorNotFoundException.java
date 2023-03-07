package com.bookdatatbase.bdapi.exceptions;

import java.io.Serial;

public class AuthorNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -9168882772655282384L;

    public AuthorNotFoundException(Integer id) {
        super("Author with ID: " + id.toString() + " cannot be found");
    }
}
