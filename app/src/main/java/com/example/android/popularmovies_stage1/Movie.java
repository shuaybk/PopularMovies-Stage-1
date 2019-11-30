package com.example.android.popularmovies_stage1;

public class Movie {



    private String title;
    private String poster;
    private String description;
    private String releaseDate;

    public Movie() {

    }

    public Movie(String mTitle, String mPoster, String mDescription, String mReleaseDate) {
        this.title = mTitle;
        this.poster = mPoster;
        this.description = mDescription;
        this.releaseDate = mReleaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }


}
