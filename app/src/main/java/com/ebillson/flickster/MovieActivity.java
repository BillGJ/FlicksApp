package com.ebillson.flickster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ebillson.flickster.adapters.MovieArrayAdapter;
import com.ebillson.flickster.adapters.MovieArrayAdapterLand;
import com.ebillson.flickster.models.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


import cz.msebera.android.httpclient.Header;

import static com.loopj.android.http.AsyncHttpClient.log;

public class MovieActivity extends AppCompatActivity {

    AsyncHttpClient client;
    ArrayList<Movie> movies;
    MovieArrayAdapter movieAdapter;
    MovieArrayAdapterLand movieAdapterLand;
    ListView lvItems;
    int orientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        lvItems = (ListView) findViewById(R.id.lvMovies);
        movies = new ArrayList<Movie>();
        movieAdapter = new MovieArrayAdapter(this, movies);
        movieAdapterLand = new MovieArrayAdapterLand(this, movies);


        orientation = getResources().getConfiguration().orientation;


        if(orientation == 1){

            lvItems.setAdapter(movieAdapter);

        }
        if(orientation == 2){

            lvItems.setAdapter(movieAdapterLand);
           // lvItems.setAdapter(movieAdapter);
        }





        //////////////////////////


        lvItems.setOnItemClickListener(new ListView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = (Movie) lvItems.getItemAtPosition(position);
                //Toast.makeText(MoviesActivity.this, movie.getOriginalTitle(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MovieActivity.this, MovieDetailActivity.class);
                i.putExtra("movie", movie);
                startActivity(i);
            }
        });

        //Fetch Movie list on Create
        fetchMoviesAsync();


    }


    ////////////

    public void fetchMoviesAsync() {

        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";


        client = new AsyncHttpClient();

        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieJSONResults = null;

                try {
                    movieJSONResults = response.getJSONArray("results");
                    movies.addAll(Movie.fromJSONArray(movieJSONResults));
                    movieAdapter.notifyDataSetChanged();
                    movieAdapterLand.notifyDataSetChanged();

                    log.d("DEBUG", movies.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }



            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode,headers, responseString, throwable);
            }


        });


    }


}
