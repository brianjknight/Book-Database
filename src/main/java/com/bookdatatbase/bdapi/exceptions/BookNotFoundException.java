package com.bookdatatbase.bdapi.exceptions;

public class BookNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1194518954950501439L;

    public BookNotFoundException(String isbn) {
        super("Book with ISBN: " + isbn + " cannot be found.");
    }
}
