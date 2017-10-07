package com.example.android.popularmovies;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Verifies the initialization of the settings object and the ability to retrieve settings.
 * Created by Gary on 10/6/2017.
 */
@RunWith(AndroidJUnit4.class)
public class MovieDbRepositorySettingsTest {
    @Test
    public void canCreateSettingsObject()throws Exception{
        MovieDbRepository.Settings repositorySettings =
                new MovieDbRepositorySettings(InstrumentationRegistry.getTargetContext());
        assertEquals("setting incorrect",
                repositorySettings.getDateFormat(),
                getContextString(R.string.date_format));
        assertEquals("setting incorrect",
                repositorySettings.getImageBaseUrl(),
                getContextString(R.string.image_base_url));
        assertEquals("setting incorrect",
                repositorySettings.getImageSizeTag(),
                getContextString(R.string.image_size));
        assertEquals("setting incorrect",
                repositorySettings.getMoviesJsonArrayNameTag(),
                getContextString(R.string.movies_json_array_name));
        assertEquals("setting incorrect",
                repositorySettings.getOriginalTitleTag(),
                getContextString(R.string.original_title));
        assertEquals("setting incorrect",
                repositorySettings.getOverviewTag(),
                getContextString(R.string.overview));
        assertEquals("setting incorrect",
                repositorySettings.getPopularMoviesUrl(),
                getContextString(R.string.popular_movies_url));
        assertEquals("setting incorrect",
                repositorySettings.getPosterPathTag(),
                getContextString(R.string.poster_path));
        assertEquals("setting incorrect",
                repositorySettings.getReleaseDateTag(),
                getContextString(R.string.release_date));
        assertEquals("setting incorrect",
                repositorySettings.getTopRatedMoviesUrl(),
                getContextString(R.string.top_rated_movies_url));
        assertEquals("setting incorrect",
                repositorySettings.getVoteAverageTag(),
                getContextString(R.string.vote_average));
        assertEquals("setting incorrect",
                repositorySettings.getMoviesDbBaseUrl(),
                getContextString(R.string.movies_base_url));
        assertEquals("setting incorrect",
                repositorySettings.getApiKey(),
                getContextString(R.string.api_key));
        assertEquals("setting incorrect",
                repositorySettings.getApiKeyParameter(),
                getContextString(R.string.api_key_parameter));
        assertEquals("setting incorrect",
                repositorySettings.getResponseDelimiter(),
                getContextString(R.string.response_delimiter));
    }

    private String getContextString(int id) {
        return InstrumentationRegistry.getTargetContext().getString(id);
    }
}