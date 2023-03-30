package com.bookdatatbase.bdapi.json;

import com.bookdatatbase.bdapi.entities.Book;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.stream.Collectors;

public class BookDeserializer implements JsonDeserializer<Book> {
    @Override
    public Book deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        Book book = Book.BookBuilder.aBook()
                        .withIsbn(object.get("isbn").getAsString())
                        .withSeries(object.get("series").getAsJsonArray().asList().stream().map(JsonElement::getAsInt).collect(Collectors.toList()))
                        .withLanguageCode(object.get("language_code").getAsString())
                        .withAverageRating(object.get("average_rating").getAsString().equals("") ? null : object.get("average_rating").getAsDouble())
                        .withDescription(object.get("description").getAsString())
                        .withAuthorId(object.get("authors").getAsJsonArray().asList()
                                .stream()
                                .findFirst()
                                .map(jsonElement -> jsonElement.getAsJsonObject().get("author_id").getAsInt())
                                .orElse(null))
                        .withNumPages(object.get("num_pages").getAsString().equals("") ? null : object.get("num_pages").getAsInt())
                        .withPublicationYear(object.get("publication_year").getAsString().equals("") ? null : object.get("publication_year").getAsInt())
                        .withBookId(object.get("book_id").getAsString().equals("") ? null : object.get("book_id").getAsInt())
                        .withRatingsCount(object.get("ratings_count").getAsString().equals("") ? null : object.get("ratings_count").getAsInt())
                        .withTitle(object.get("title").getAsString())
                        .withTitleWithoutSeries(object.get("title_without_series").getAsString())
                        .build();

        return book;
    }
}
