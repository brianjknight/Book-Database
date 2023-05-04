package com.bookdatatbase.bdapi.exceptions;

import java.util.UUID;

public class BookNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1194518954950501439L;

    /**
     * Exception to be thrown if a Book is not found in the database.
     * @param id UUID and primary key of the Book not found.
     */
    public BookNotFoundException(UUID id) {
        super("Book with ISBN: " + id + " cannot be found.");
    }
}
