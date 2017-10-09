package com.example.android.popularmovies;

import android.os.AsyncTask;

import com.example.android.utilities.AsyncTaskListener;

import java.util.List;

/**
 * Loads movies as determined by the user selected action, then retrieves the movies from the
 * repository.
 * Created by Gary on 10/9/2017.
 */

class FetchMoviesTask extends AsyncTask<Integer,Void,List<MovieData>> {
    private final AsyncTaskListener<List<MovieData>> mListener;

    FetchMoviesTask(AsyncTaskListener<List<MovieData>> listener) {
        mListener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mListener.onTaskPreExecute();
    }

    @Override
    protected List<MovieData> doInBackground(Integer... params) {
        if (params.length == 0){
            return null;
        }

        if (params[0] == R.id.action_sort_popular){
            MovieDbRepository.getInstance().loadPopularMovies();
            return MovieDbRepository.getInstance().getMovies();
        }

        if (params[0] == R.id.action_sort_rating){
            MovieDbRepository.getInstance().loadTopRatedMovies();
            return MovieDbRepository.getInstance().getMovies();
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<MovieData> movieDataList) {
        super.onPostExecute(movieDataList);
        mListener.onTaskPostExecute(movieDataList);
    }
}
