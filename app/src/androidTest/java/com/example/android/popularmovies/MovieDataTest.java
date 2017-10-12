package com.example.android.popularmovies;

import android.os.Parcel;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import static junit.framework.Assert.assertEquals;

/**
 * Test MovieData object
 * Created by Gary on 10/9/2017.
 */
@RunWith(AndroidJUnit4.class)
public class MovieDataTest {
    private static final String ORIGINAL_TITLE = "Original Title";
    private static final String POSTER_IMAGE = "Poster Image";
    private static final String PLOT_SYNOPSIS = "Plot Synopsis";
    private static final double USER_RATING = 9.9;
    private static final Date RELEASE_DATE = new Date();
    private MovieData mMovieData;

    @Before
    public void setup(){
        mMovieData = new MovieData();
        mMovieData.setOriginalTitle(ORIGINAL_TITLE);
        mMovieData.setPosterImage(POSTER_IMAGE);
        mMovieData.setPlotSynopsis(PLOT_SYNOPSIS);
        mMovieData.setUserRating(USER_RATING);
        mMovieData.setReleaseDate(RELEASE_DATE);
    }
    @Test
    public void canCreateFromParameter()throws Exception{
        assertEquals("incorrect original title", mMovieData.getOriginalTitle(), ORIGINAL_TITLE);
        assertEquals("incorrect poster image", mMovieData.getPosterImage(), POSTER_IMAGE);
        assertEquals("incorrect plot synopsis", mMovieData.getPlotSynopsis(), PLOT_SYNOPSIS);
        assertEquals("incorrect user rating", mMovieData.getUserRating(), USER_RATING);
        assertEquals("incorrect release data", mMovieData.getReleaseDate(), RELEASE_DATE);
    }
    @Test
    public void canParcelMovieData()throws Exception{
        Parcel parcel = Parcel.obtain();
        mMovieData.writeToParcel(parcel, mMovieData.describeContents());
        parcel.setDataPosition(0);
        MovieData createdFromParcel = MovieData.CREATOR.createFromParcel(parcel);
        assertEquals("incorrect original title", mMovieData.getOriginalTitle(), createdFromParcel.getOriginalTitle());
        assertEquals("incorrect poster image", mMovieData.getPosterImage(), createdFromParcel.getPosterImage());
        assertEquals("incorrect plot synopsis", mMovieData.getPlotSynopsis(), createdFromParcel.getPlotSynopsis());
        assertEquals("incorrect user rating", mMovieData.getUserRating(), createdFromParcel.getUserRating());
        assertEquals("incorrect release data", mMovieData.getReleaseDate(), createdFromParcel.getReleaseDate());
//        assertEquals("parceled object is not equal to the original", mMovieData, createdFromParcel);
    }
}
