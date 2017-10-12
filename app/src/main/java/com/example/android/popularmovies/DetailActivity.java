package com.example.android.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Manages the display of detailed movie content.
 * Created by Gary on 9/27/2017.
 */

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra(getString(R.string.extra_movie_data))) {
                Bundle bundle = intentThatStartedThisActivity.getExtras();
                MovieData movieData = bundle.getParcelable(getString(R.string.extra_movie_data));
                if (movieData != null) {
                    setView(movieData);
                }
            }
        }
    }

    private void setView(MovieData movieData) {
        TextView titleDisplay = (TextView) findViewById(R.id.tv_movie_title);
        titleDisplay.setText(movieData.getOriginalTitle());

        ImageView imageView = (ImageView) findViewById(R.id.im_movie_detail);
        Picasso.with(this).load(movieData.getPosterImage()).into(imageView);

        TextView releaseDateDisplay = (TextView) findViewById(R.id.tv_release_date);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy", Locale.US);
        releaseDateDisplay.setText(formatter.format(movieData.getReleaseDate()));

        TextView ratingDisplay = (TextView) findViewById(R.id.tv_rating);
        String rating = getString(R.string.user_rating, movieData.getUserRating());
        ratingDisplay.setText(rating);

        TextView synopsisDisplay = (TextView) findViewById(R.id.tv_synopsis);
        synopsisDisplay.setText(movieData.getPlotSynopsis());
    }
}
