package com.example.android.popularmovies.utilities;

import android.support.test.InstrumentationRegistry;

import com.example.android.popularmovies.MovieDbRepository;
import com.example.android.popularmovies.MovieDbRepositorySettings;
import com.example.android.utilities.NetworkUtils;

import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.util.StringTokenizer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Verifies the ability to build urls and retrieve data from the built urls.
 * Created by Gary on 9/19/2017.
 */
public class NetworkUtilsTest {
    private URL mUrl = null;
    private MovieDbRepository.Settings mSettings;

    @Before
    public void setUp() throws Exception {
        mSettings = new MovieDbRepositorySettings(InstrumentationRegistry.getTargetContext());
        String fullUrl = mSettings.getMoviesDbBaseUrl() + mSettings.getPopularMoviesUrl();
        mUrl = NetworkUtils.buildUrl(fullUrl, mSettings.getApiKey(), mSettings.getApiKeyParameter());
    }

    @Test
    public void buildUrl() throws Exception {
        assertNotNull("url not set", mUrl);
    }

    @Test
    public void getResponseFromHttpUrl() throws Exception {
        String response = NetworkUtils.getResponseFromHttpUrl(mUrl, mSettings.getResponseDelimiter());
        assertNotNull(response);
        // get the characters to the first  "," to ensure it is what we expect
        StringTokenizer tokenizer = new StringTokenizer(response, ",");
        assertEquals("Unexpected response", "{\"page\":1", tokenizer.nextElement());
    }

}