package com.example.android.popularmovies_stage1.utilities;

import android.net.Uri;

import com.example.android.popularmovies_stage1.MainActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    final static String MOVIEDB_POPULAR_BASE_URL = "https://api.themoviedb.org/3/movie/popular";
    final static String PARAM_API_KEY = "api_key";
    final static String PARAM_LANGUAGE = "language";
    final static String PARAM_PAGES = "page";

    final static String lang = "en-US";
    final static String pagesToDisplay = "1";


    public static URL getURL() {
        Uri builtUri = Uri.parse(MOVIEDB_POPULAR_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_API_KEY, MainActivity.API_KEY)
                .appendQueryParameter(PARAM_LANGUAGE, lang)
                .appendQueryParameter(PARAM_PAGES, pagesToDisplay)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("HTTP CONNNECTION ERRRRRRRRRRRRRRRRROR");
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return null;
    }
}
