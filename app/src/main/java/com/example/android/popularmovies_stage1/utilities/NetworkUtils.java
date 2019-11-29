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

    final static String BASE_URL = "https://api.themoviedb.org/3/movie/550?api_key=" + MainActivity.API_KEY;

    public static URL buildURL(String searchQuery) {
        Uri builtUri = Uri.parse(BASE_URL);

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
