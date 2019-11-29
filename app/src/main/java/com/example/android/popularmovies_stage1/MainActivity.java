package com.example.android.popularmovies_stage1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    TextView mMainText;
    Button mRefreshButton;
    ArrayList<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainText = (TextView) findViewById(R.id.tv_main_text);
        mRefreshButton = (Button) findViewById(R.id.button_refresh);

        movieList = new ArrayList<Movie>();

        mRefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                URL url = NetworkUtils.buildURL("lol fake query info");
                new MovieQueryTask().execute(url);
            }
        });
    }

    public void setMovieData(String json) {
        try {
            JSONObject jsonData = new JSONObject(json);
            JSONArray jsonMovieList = jsonData.getJSONArray("results");
            movieList.clear();

            for (int i = 0; i < jsonMovieList.length(); i++) {
                movieList.add(JsonUtils.parseMovieJson(jsonMovieList.getJSONObject(i)));
            }

            String temp = "";
            for (Movie movie: movieList) {
                temp = temp + ", " + movie.getTitle();
            }
            mMainText.setText(temp);

        } catch(JSONException e) {
            e.printStackTrace();
        }
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
                mMainText.setText("Error occured!");
            } else {
                mMainText.setText(queryResults);
                setMovieData(queryResults);
            }
        }
    }
}