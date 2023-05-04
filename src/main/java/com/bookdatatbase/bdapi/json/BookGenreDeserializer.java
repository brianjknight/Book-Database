package com.bookdatatbase.bdapi.json;

import com.bookdatatbase.bdapi.entities.BookGenre;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Customer deserializer for a BookGenre object.
 * The dataset pulled from the Goodreads project is in JSON format with all string types.
 * This deserializer converts strings to proper data types.
 */
public class BookGenreDeserializer implements JsonDeserializer<BookGenre> {
    @Override
    public BookGenre deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        Integer bookId = object.get("book_id").getAsString().equals("") ? null : object.get("book_id").getAsInt();

        String genres = object.get("genres").getAsJsonObject().entrySet().stream()
                                    .map(Map.Entry::getKey).collect(Collectors.joining(","));

        return new BookGenre(bookId, genres);
    }
}