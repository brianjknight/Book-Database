package com.bookdatatbase.bdapi.exceptions;

import java.io.Serial;
import java.util.UUID;

public class AuthorNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -9168882772655282384L;

    /**
     * Exception to be thrown if an Author is not found in the database.
     * @param id UUID and primary key of the Author.
     */
    public AuthorNotFoundException(UUID id) {
        super("Author with authorId: " + id.toString() + " cannot be found");
    }
}
