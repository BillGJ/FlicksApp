package com.ebillson.flickster;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ebillson.flickster.models.Movie;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //String ImageView = getIntent().getStringExtra("ivMovieImage");
       // String TextView = getIntent().getStringExtra("tvTitle");
       // String TextView2= getIntent().getStringExtra("tvOverview");
       setContentView(R.layout.activity_movie_detail);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);



        Movie movie = (Movie) getIntent().getSerializableExtra("movie");

        // Get the views
        TextView title = (TextView) findViewById(R.id.tvTitle);
        TextView tvReleaseDate = (TextView) findViewById(R.id.tvReleaseDate);
        TextView overview = (TextView) findViewById(R.id.tvOverview);
       TextView voteCount = (TextView) findViewById(R.id.tvVoteCount);
        ImageView imageView = (ImageView) findViewById(R.id.ivMovieImage);
       RatingBar ratingBar = (RatingBar) findViewById(R.id.rbMovie);

        // Set value to the Views
        title.setText(movie.getOriginalTitle());
        tvReleaseDate.append(movie.getReleaseDate().toString());
        overview.setText(movie.getOverview());
        voteCount.append(movie.getVoteCount().toString());

        ratingBar.setRating(movie.getVoteAverage());
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

        Picasso.with(this).load(movie.getBackdropPath())
                .transform(new RoundedCornersTransformation(15, 15))
                .placeholder(R.drawable.movie_placeholder2)
                .into(imageView);



    }

    public void onBack(View view) {
        finish();
    }





        }




