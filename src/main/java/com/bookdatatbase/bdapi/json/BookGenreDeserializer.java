package com.bookdatatbase.bdapi.json;

import com.bookdatatbase.bdapi.entities.BookGenre;
import com.bookdatatbase.bdapi.models.Genre;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.List;

public class BookGenreDeserializer implements JsonDeserializer<BookGenre> {
    @Override
    public BookGenre deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        Integer bookId = object.get("book_id").getAsString().equals("") ? null : object.get("book_id").getAsInt();

//        List<Genre> genreList = new ArrayList<>();
//        JsonObject genreJSON = object.get("genres").getAsJsonObject();
//
//        for(Map.Entry<String, JsonElement> entry : genreJSON.entrySet()) {
//            Genre genre = new Genre(entry.getKey(), entry.getValue().getAsInt());
//            genreList.add(genre);
//        }

        List<Genre> genreList = object.get("genres").getAsJsonObject().entrySet().stream()
                                    .map(entry -> new Genre(entry.getKey(), entry.getValue().getAsInt())).toList();

        return new BookGenre(bookId, genreList);
    }
}