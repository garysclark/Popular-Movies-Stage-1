package com.example.android.popularmovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Adapter used to show the retrieved movies.
 * Created by Gary on 9/26/2017.
 */

class MovieAdapter extends ArrayAdapter<MovieData> implements View.OnClickListener {

    private final MovieAdapterOnClickHandler mClickHandler;

    public interface MovieAdapterOnClickHandler{
        void onClick(int position);
    }

    public MovieAdapter(@NonNull Context context, @NonNull List<MovieData> movieDataList, MovieAdapterOnClickHandler clickHandler) {
        super(context, 0, movieDataList);
        mClickHandler = clickHandler;
    }

    /**
     * Shows the movie image in the assigned cell on the screen.
     *
     * @param position    The AdapterView position that is requesting a view
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.movie_item, parent, false);
        }
        MovieData movieData = getItem(position);
        if (movieData != null) {
            ImageView imageView = convertView.findViewById(R.id.movie_image);
            Picasso.with(getContext()).load(movieData.getPosterImage()).into(imageView);
        }

        convertView.setTag(position);
        convertView.setOnClickListener(this);

        return convertView;
    }

    /**
     * Handles when a movie view is clicked.  The index of the movie is maintained in the 'tag'
     * information.
     * @param view selected view.
     */
    @Override
    public void onClick(View view) {
        mClickHandler.onClick((int) view.getTag());
    }
}
