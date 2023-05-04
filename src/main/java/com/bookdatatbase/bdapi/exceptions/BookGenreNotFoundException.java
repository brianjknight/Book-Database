package com.bookdatatbase.bdapi.exceptions;

import java.io.Serial;

public class BookGenreNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 3545986121159673594L;

    /**
     * Exception to be thrown if a BookGenre is not found in the database.
     * @param bookId Integer and primary key of the BookGenre.
     */
    public BookGenreNotFoundException(Integer bookId) {
        super("BookGenre with bookId: " + bookId.toString() + " cannot be found");
    }
}
