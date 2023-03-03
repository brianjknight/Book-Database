package com.bookdatatbase.bdapi.json;

import com.bookdatatbase.bdapi.entities.Book;
import com.bookdatatbase.bdapi.models.Role;
import com.bookdatatbase.bdapi.models.Shelf;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.stream.Collectors;

public class BookDeserializer implements JsonDeserializer {
    @Override
    public Book deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        Book book = Book.BookBuilder.aBook()
                        .withIsbn(object.get("isbn").getAsString())
                        .withTextReviewsCount(object.get("text_reviews_count").getAsString().equals("") ? null : object.get("text_reviews_count").getAsInt())
                        .withSeries(object.get("series").getAsJsonArray().asList().stream().map(JsonElement::getAsInt).collect(Collectors.toList()))
                        .withCountryCode(object.get("country_code").getAsString())
                        .withLanguageCode(object.get("language_code").getAsString())
                        .withPopularShelves(object.get("popular_shelves").getAsJsonArray().asList().stream().map(
                                jsonElement -> {
                                    JsonObject jsonObject = jsonElement.getAsJsonObject();
                                    Shelf shelf = new Shelf(jsonObject.get("count").getAsInt(), jsonObject.get("name").getAsString());
                                    return shelf;
                                })
                                .collect(Collectors.toList()))
                        .withAsin(object.get("asin").getAsString())
                        .withIsEbook(object.get("is_ebook").getAsBoolean())
                        .withAverageRating(object.get("average_rating").getAsString().equals("") ? null : object.get("average_rating").getAsDouble())
                        .withKindleAsin(object.get("kindle_asin").getAsString())
                        .withSimilarBooks(object.get("similar_books").getAsJsonArray().asList().stream().map(JsonElement::getAsInt).collect(Collectors.toList()))
                        .withDescription(object.get("description").getAsString())
                        .withFormat(object.get("format").getAsString())
                        .withLink(object.get("link").getAsString())
                        .withAuthors(object.get("authors").getAsJsonArray().asList().stream().map(
                                jsonElement -> {
                                    JsonObject jsonObject = jsonElement.getAsJsonObject();
                                    Role role = new Role(jsonObject.get("author_id").getAsInt(), jsonObject.get("role").getAsString());
                                    return role;
                                })
                                .collect(Collectors.toList()))
                        .withPublisher(object.get("publisher").getAsString())
                        .withNumPages(object.get("num_pages").getAsString().equals("") ? null : object.get("num_pages").getAsInt())
                        .withEditioninformation(object.get("edition_information").getAsString())
                        .withPublicationYear(object.get("publication_year").getAsString().equals("") ? null : object.get("publication_year").getAsInt())
                        .withUrl(object.get("url").getAsString())
                        .withImageUrl(object.get("image_url").getAsString())
                        .withBookId(object.get("book_id").getAsString())
                        .withRatingsCount(object.get("ratings_count").getAsString().equals("") ? null : object.get("ratings_count").getAsInt())
                        .withTitle(object.get("title").getAsString())
                        .withTitleWithoutSeries(object.get("title_without_series").getAsString())
                        .build();

        return book;
    }
}
