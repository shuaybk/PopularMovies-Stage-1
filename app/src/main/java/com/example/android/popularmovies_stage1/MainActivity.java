package com.example.android.popularmovies_stage1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import com.example.android.popularmovies_stage1.utilities.JsonUtils;
import com.example.android.popularmovies_stage1.utilities.NetworkUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String API_KEY = "";  //themoviedb.org API Key, replace with your own to make this app work
    public static final int IMAGE_WIDTH = 342; //Choices are 92, 154, 185, 342, 500, 780

    ArrayList<Movie> movieList;

    RecyclerView mRecyclerViewMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieList = new ArrayList<Movie>();

        mRecyclerViewMovies = (RecyclerView) findViewById(R.id.recyclerview_movies);

        URL url = NetworkUtils.buildURL("lol fake query info");
        new MovieQueryTask().execute(url);
    }

    //Called when the query to themoviedb returns a result
    public void setMovieData(String json) {
        try {
            JSONObject jsonData = new JSONObject(json);
            JSONArray jsonMovieList = jsonData.getJSONArray("results");
            movieList.clear();

            for (int i = 0; i < jsonMovieList.length(); i++) {
                movieList.add(JsonUtils.parseMovieJson(jsonMovieList.getJSONObject(i)));
            }

            initRecyclerViewMovies();
        } catch(JSONException e) {
            e.printStackTrace();
        }
    }

    public void initRecyclerViewMovies() {
        MovieAdapter adapter = new MovieAdapter(this, movieList);
        mRecyclerViewMovies.setAdapter(adapter);
        int numColumns = getNumColumns();
        mRecyclerViewMovies.setLayoutManager(new GridLayoutManager(this, numColumns));
    }

    //Calculate the number of columns required for the grid layout based on screen width and image width
    public int getNumColumns() {
        int imageWidth = 342;
        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        return screenWidth/imageWidth;
    }


    public class MovieQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];
            String queryResults = null;
            try {
                queryResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return queryResults;
        }

        @Override
        protected void onPostExecute(String queryResults) {
            if (queryResults == null) {
                //error happened
            } else {
                setMovieData(queryResults);
            }
        }
    }
}