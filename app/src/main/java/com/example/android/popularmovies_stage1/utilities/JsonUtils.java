package com.example.android.popularmovies_stage1.utilities;

import com.example.android.popularmovies_stage1.Movie;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

    public static Movie parseMovieJson (JSONObject json) {
        Movie movie = null;
        try {
            String title = json.getString("title");
            String poster = json.getString("poster_path");
            String description = json.getString("overview");
            String releaseDate = json.getString("release_date");

            movie = new Movie(title, poster, description, releaseDate);
        } catch(JSONException e) {
            e.printStackTrace();
        }
        return movie;
    }
}
