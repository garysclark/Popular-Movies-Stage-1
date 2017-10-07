package com.example.android.popularmovies;

import android.content.Context;

/**
 * Contains the settings required to interact with the web based MovieDB api.
 * Created by Gary on 10/5/2017.
 */

public class MovieDbRepositorySettings implements MovieDbRepository.Settings{
    private final String mPopularMoviesUrl;
    private final String mTopRatedMoviesUrl;
    private final String mMoviesJsonArrayNameTag;
    private final String mOriginalTitleTag;
    private final String mImageBaseUrl;
    private final String mImageSizeTag;
    private final String mPosterPathTag;
    private final String mOverviewTag;
    private final String mVoteAverageTag;
    private final String mReleaseDateTag;
    private final String mDateFormat;
    private final String mMovieBaseUrl;
    private final String mApiKey;
    private final String mApiKeyParameter;
    private final String mResponseDelimiter;

    /**
     * Constructs the settings object.
     * @param context object used to retrieve resource settings.
     */
    public MovieDbRepositorySettings(Context context) {
        mPopularMoviesUrl = context.getString(R.string.popular_movies_url);
        mTopRatedMoviesUrl = context.getString(R.string.top_rated_movies_url);
        mMoviesJsonArrayNameTag = context.getString(R.string.movies_json_array_name);
        mOriginalTitleTag = context.getString(R.string.original_title);
        mImageBaseUrl = context.getString(R.string.image_base_url);
        mImageSizeTag = context.getString(R.string.image_size);
        mPosterPathTag = context.getString(R.string.poster_path);
        mOverviewTag = context.getString(R.string.overview);
        mVoteAverageTag = context.getString(R.string.vote_average);
        mReleaseDateTag = context.getString(R.string.release_date);
        mDateFormat = context.getString(R.string.date_format);
        mMovieBaseUrl = context.getString(R.string.movies_base_url);
        mApiKey = context.getString(R.string.api_key);
        mApiKeyParameter = context.getString(R.string.api_key_parameter);
        mResponseDelimiter = context.getString(R.string.response_delimiter);
    }

    @Override
    public String getPopularMoviesUrl() {
        return mPopularMoviesUrl;
    }

    @Override
    public String getTopRatedMoviesUrl() {
        return mTopRatedMoviesUrl;
    }

    @Override
    public String getMoviesJsonArrayNameTag() {
        return mMoviesJsonArrayNameTag;
    }

    @Override
    public String getOriginalTitleTag() {
        return mOriginalTitleTag;
    }

    @Override
    public String getImageBaseUrl() {
        return mImageBaseUrl;
    }

    @Override
    public String getImageSizeTag() {
        return mImageSizeTag;
    }

    @Override
    public String getPosterPathTag() {
        return mPosterPathTag;
    }

    @Override
    public String getOverviewTag() {
        return mOverviewTag;
    }

    @Override
    public String getVoteAverageTag() {
        return mVoteAverageTag;
    }

    @Override
    public String getMoviesDbBaseUrl() {
        return mMovieBaseUrl;
    }

    @Override
    public String getApiKey() {
        return mApiKey;
    }

    @Override
    public String getApiKeyParameter() {
        return mApiKeyParameter;
    }

    @Override
    public String getResponseDelimiter() {
        return mResponseDelimiter;
    }

    @Override
    public String getReleaseDateTag() {
        return mReleaseDateTag;
    }

    @Override
    public String getDateFormat() {
        return mDateFormat;
    }
}
