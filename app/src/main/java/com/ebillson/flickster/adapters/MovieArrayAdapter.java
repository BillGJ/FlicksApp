package com.ebillson.flickster.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebillson.flickster.R;
import com.ebillson.flickster.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Ebillson GJ on 7/18/2017.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    public MovieArrayAdapter(Context context, List<Movie> movies){

        super(context, android.R.layout.simple_list_item_1, movies);
    }

    //@NonNull
    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {

        // get the data item for this position

        Movie movie = getItem(position);

        //check the existing view being reused
        if (convertView == null){

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movies, parent, false);
        }

        //Find image view

        ImageView ivImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);

        //clear out image from convertview

        ivImage.setImageResource(0);

        TextView tvTitle = (TextView)convertView.findViewById(R.id.tvTitle);
        TextView tvOverview = (TextView)convertView.findViewById(R.id.tvOverview);

        //Populate the data

        tvTitle.setText(movie.getOriginalTitle());
        tvOverview.setText(movie.getOverview());

        Picasso.with(getContext()).load(movie.getPosterPath()).into(ivImage);
        //Picasso.with(getContext()).load(movie.getPosterPath()).resize(250, 0).into(ivImage);
        Picasso.with(getContext()).load(movie.getPosterPath()).resize(250, 250).
                centerCrop().into(ivImage);

        //return the view

        return convertView;

    }
}
