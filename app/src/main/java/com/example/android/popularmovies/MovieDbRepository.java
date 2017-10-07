package com.example.android.popularmovies;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.android.utilities.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Singleton that manages the retrieval of movie data from the MovieDb website.
 * Created by Gary on 9/21/2017.
 */

public class MovieDbRepository{
    private static final String TAG = MovieDbRepository.class.getSimpleName();
    private static MovieDbRepository mInstance;
    private final Settings mSettings;
    private ArrayList<MovieData> mMovieDataList;

    private MovieDbRepository(Settings settings){
        mSettings = settings;
    }

    /**
     * Interface representing the settings required for repository operations.
     */
    public interface Settings {
        String getDateFormat();
        String getImageBaseUrl();
        String getImageSizeTag();
        String getMoviesJsonArrayNameTag();
        String getOriginalTitleTag();
        String getOverviewTag();
        String getPopularMoviesUrl();
        String getPosterPathTag();
        String getReleaseDateTag();
        String getTopRatedMoviesUrl();
        String getVoteAverageTag();
        String getMoviesDbBaseUrl();
        String getApiKey();
        String getApiKeyParameter();
        String getResponseDelimiter();
    }

    /**
     * Initializes the repository using the passed in settings object.
     * @param settings object containing the required settings to support repository operations
     */
    public synchronized static void initialize(Settings settings) {
        if(mInstance == null){
            mInstance = new MovieDbRepository(settings);
        }
    }

    /**
     * Returns the instance of the repository.
     * @return repository instance
     */
    public static synchronized MovieDbRepository getInstance() {
        return mInstance;
    }

    /**
     * Loads 'popular' movies from the web api.
     */
    public synchronized void loadPopularMovies() {
        loadMovies(mSettings.getPopularMoviesUrl());
    }

    /**
     * Loads the 'top rated' movies from the web api.
     */
    public synchronized void loadTopRatedMovies() {
        loadMovies(mSettings.getTopRatedMoviesUrl());
    }

    /**
     * Retrieves the information for the movies currently loaded into the repository.
     * @return list of movie records
     */
    public synchronized ArrayList<MovieData> getMovies() {
        return mMovieDataList;
    }

    /**
     * Retrieves the number of movies currently loaded into the repository.
     * @return count of movie records
     */
    public int getNumberOfMovies() {
        return mMovieDataList.size();
    }
    /**
     * Retrieves the specific movie record at the passed in index.
     * @param index index of movie record in the repository
     * @return movie record
     */
    public MovieData getMovie(int index) {
        return mMovieDataList.get(index);
    }

    private void loadMovies(String url){
        String moviesJson;
        try {
            moviesJson = getJson(url);
            Log.d(TAG, "Movies JSON : " + moviesJson);
            mMovieDataList = createArrayListFromJson(moviesJson);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<MovieData> createArrayListFromJson(String moviesJson) throws JSONException {
        JSONObject jsonObject = new JSONObject(moviesJson);
        JSONArray jsonArray = jsonObject.getJSONArray(mSettings.getMoviesJsonArrayNameTag());
        ArrayList<MovieData> movieDataArrayList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject movie = jsonArray.getJSONObject(i);
            MovieData movieData = getMovieData(movie);
            movieDataArrayList.add(movieData);
        }
        return movieDataArrayList;
    }

    @NonNull
    private MovieData getMovieData(JSONObject movie) throws JSONException {
        MovieData movieData = new MovieData();
        movieData.setOriginalTitle(movie.getString(mSettings.getOriginalTitleTag()));
        movieData.setPosterImage(getPosterUrl(movie.getString(mSettings.getPosterPathTag())));
        movieData.setPlotSynopsis(movie.getString(mSettings.getOverviewTag()));
        movieData.setUserRating(movie.getDouble(mSettings.getVoteAverageTag()));
        String dateString = movie.getString(mSettings.getReleaseDateTag());
        DateFormat formatter = new SimpleDateFormat(mSettings.getDateFormat(), Locale.US);
        Date date = null;
        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        movieData.setReleaseDate(date);
        return movieData;
    }

    private String getPosterUrl(String relativePath) {
        return mSettings.getImageBaseUrl() + mSettings.getImageSizeTag() + relativePath;
    }

    private String getJson(String moviesUrl) throws IOException {
        String baseUrl = mSettings.getMoviesDbBaseUrl() + moviesUrl;
        URL queryUrl = NetworkUtils.buildUrl(baseUrl, mSettings.getApiKey(), mSettings.getApiKeyParameter());
        Log.d(TAG, "Query URL : " + queryUrl);
        return NetworkUtils.getResponseFromHttpUrl(queryUrl, mSettings.getResponseDelimiter());
    }
}
