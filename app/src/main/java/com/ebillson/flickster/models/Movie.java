package com.ebillson.flickster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by Ebillson GJ on 7/18/2017.
 */

public class Movie implements Serializable {

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",posterPath);
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }


    public String getBackdropPath() {
        //get width 500 (w500) for backdrop image
        return String.format("https://image.tmdb.org/t/p/w1280/%s", backdropPath);
    }


    public Float getVoteAverage() {
        return this.voteAverage;
    }

    public Boolean getAdult() {
        return adult;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public Float getPopularity() {
        return popularity;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public Boolean getVideo() {
        return video;
    }











    String posterPath;
    String originalTitle;
    String overview;


    String backdropPath;
    Float voteAverage;
    Boolean adult;
    Date releaseDate;
    String originalLanguage;
    Float popularity;
    Integer voteCount;
    Boolean video;



    public Movie (JSONObject jsonObject) throws JSONException{

        this.posterPath = jsonObject.getString("poster_path");
        this.originalTitle = jsonObject.getString("original_title");
        this.overview = jsonObject.getString("overview");


        this.backdropPath = jsonObject.getString("backdrop_path");
        this.voteAverage = Float.parseFloat(jsonObject.getString("vote_average"));
        this.originalLanguage = jsonObject.getString("original_language");
        this.overview = jsonObject.getString("overview");
        this.adult = Boolean.parseBoolean(jsonObject.getString("adult"));
        this.releaseDate = Date.valueOf(jsonObject.getString("release_date"));
        this.popularity = Float.parseFloat(jsonObject.getString("popularity"));
        this.voteCount = Integer.parseInt(jsonObject.getString("vote_count"));
        this.video = Boolean.parseBoolean(jsonObject.getString("video"));
    }








    public static ArrayList<Movie> fromJSONArray(JSONArray array){

        ArrayList<Movie> results = new ArrayList<>();

        for (int x = 0; x<array.length();x++){

            try {
                results.add(new Movie(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

}
