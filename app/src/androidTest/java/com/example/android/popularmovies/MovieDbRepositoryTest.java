package com.example.android.popularmovies;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Verifies repository functionality.
 * Created by Gary on 9/21/2017.
 */
@RunWith(AndroidJUnit4.class)
public class MovieDbRepositoryTest {
    @Before
    public void setup(){
        MovieDbRepository.initialize(
                new MovieDbRepositorySettings(InstrumentationRegistry.getTargetContext())
        );
    }

    @Test
    public void canInitializeRepository()throws Exception{
        assertNotNull(MovieDbRepository.getInstance());
    }
    @Test
    public void canLoadPopularMovies() throws Exception {
        MovieDbRepository.getInstance().loadPopularMovies();
        assertNotNull("popular movies not loaded", MovieDbRepository.getInstance().getMovies());
    }

    @Test
    public void canLoadTopRatedMovies() throws Exception {
        MovieDbRepository.getInstance().loadTopRatedMovies();
        assertNotNull("popular movies not loaded", MovieDbRepository.getInstance().getMovies());
    }

    @Test
    public void canGetNumberOfMovies() throws Exception {
        assertTrue("number of movies is incorrect", MovieDbRepository.getInstance().getNumberOfMovies() > 0);
    }

    @Test
    public void canGetMovie() throws Exception {
        // Get the first element and verify all of the fields are present.
        MovieData movieData = MovieDbRepository.getInstance().getMovie(0);
        assertNotNull("movie not retrieved", movieData);
        assertNotNull("movie name not set", movieData.getOriginalTitle());
        assertNotNull("movie poster image not set", movieData.getPosterImage());
        assertNotNull("movie plot synopsis not set", movieData.getPlotSynopsis());
        assertTrue("invalid user rating set", movieData.getUserRating() > 0.0);
        assertNotNull("movie release date not set", movieData.getReleaseDate());
    }
}
