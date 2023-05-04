package com.bookdatatbase.bdapi.json;

import com.bookdatatbase.bdapi.entities.Author;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Customer deserializer for an Author object.
 * The dataset pulled from the Goodreads project is in JSON format with all string types.
 * This deserializer converts strings to proper data types.
 */
public class AuthorDeserializer implements JsonDeserializer<Author> {
    @Override
    public Author deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        Integer authorId = object.get("author_id").getAsString().equals("") ? null : object.get("author_id").getAsInt();
        Double averageRating = object.get("average_rating").getAsString().equals("") ? null : object.get("average_rating").getAsDouble();
        Integer textReviewsCount = object.get("text_reviews_count").getAsString().equals("") ? null : object.get("text_reviews_count").getAsInt();
        String name = object.get("name").getAsString();
        Integer ratingsCount = object.get("ratings_count").getAsString().equals("") ? null : object.get("ratings_count").getAsInt();

        return new Author(authorId, averageRating, textReviewsCount, name, ratingsCount);
    }

}
