package com.bookdatatbase.bdapi.exceptions;

import java.io.Serial;
import java.util.UUID;

public class AuthorNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -9168882772655282384L;

    public AuthorNotFoundException(UUID id) {
        super("Author with authorId: " + id.toString() + " cannot be found");
    }
}
