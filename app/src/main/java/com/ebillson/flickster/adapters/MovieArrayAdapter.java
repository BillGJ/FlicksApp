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

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by Ebillson GJ on 7/18/2017.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {


    // View lookup cache
    private static class ViewHolder {
        TextView tvOverview;
        TextView tvTitle;
        ImageView ivMovies;
    }


    public MovieArrayAdapter(Context context, List<Movie> movies){

        super(context, android.R.layout.simple_list_item_1, movies);
    }

    //@NonNull
    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {

        // get the data item for this position

        Movie movie = getItem(position);

        //check the existing view being reused
        ViewHolder viewHolder; // view lookup cache stored in tag

        if (convertView == null){

            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movies, parent, false);

            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);
            viewHolder.ivMovies = (ImageView)convertView.findViewById(R.id.ivMovieImage) ;
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);

        }else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }


        //Find image view

        ImageView ivImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);

        //clear out image from convertview

        ivImage.setImageResource(0);

        TextView tvTitle = (TextView)convertView.findViewById(R.id.tvTitle);
        TextView tvOverview = (TextView)convertView.findViewById(R.id.tvOverview);

        // Populate the data from the data object via the viewHolder object
        // into the template view.
        viewHolder.tvTitle.setText(movie.getOriginalTitle());
        viewHolder.tvOverview.setText(movie.getOverview());
        viewHolder.ivMovies.setImageResource(0);
       // viewHolder.ivMovies.setImageResource();
        // Return the completed view to render on screen
        //return convertView;





        //Populate the data

        //tvTitle.setText(movie.getOriginalTitle());
        //tvOverview.setText(movie.getOverview());
        Picasso.with(getContext()).load(movie.getPosterPath()).fit().centerCrop()
                .placeholder(R.drawable.movie_placeholder)
                .error(R.drawable.error_placeholder)
                .into(ivImage);
        Picasso.with(getContext()).load(movie.getPosterPath()).into(ivImage);

       //Picasso.with(getContext()).load(movie.getPosterPath()).resize(150, 0).into(ivImage);//recent comment
        Picasso.with(getContext()).load(movie.getPosterPath()).resize(250, 250).
                centerCrop().into(ivImage);
        Picasso.with(getContext()).load(movie.getPosterPath())
                .transform(new RoundedCornersTransformation(20, 20)).into((ivImage));
       //

        //return the view

       return convertView;

    }
}
