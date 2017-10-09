package com.example.android.popularmovies;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.utilities.AsyncTaskListener;
import com.example.android.utilities.NetworkUtils;

import java.util.List;

/**
 * A fragment used to display the movie poster thumbnails for the retrieved movies.
 * Created by Gary on 9/26/2017.
 */

public class MainActivityFragment extends Fragment implements MovieAdapter.MovieAdapterOnClickHandler {
    private GridView mGridView;
    private MenuItem mMenuItem;
    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;
    private int mMenuItemId;

    /**
     * Initializes the popular movies application when the main activity is created.
     * @param savedInstanceState state information from prior instance
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MovieDbRepository.initialize(new MovieDbRepositorySettings(getActivity()));
        setHasOptionsMenu(true);
    }

    /**
     * Creates the movie poster view.
     * @param inflater  inflater used to inflate the fragment's layout.
     * @param container container used to inflate the fragment's layout
     * @param savedInstanceState state to restore view to when recovered (unused)
     * @return the newly created view
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, Bundle savedInstanceState) {
        if(savedInstanceState == null || !savedInstanceState.containsKey("MenuItemId")) {
            mMenuItemId = R.id.action_sort_popular;
        }
        else {
            mMenuItemId = savedInstanceState.getInt("MenuItemId");
        }
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mGridView = rootView.findViewById(R.id.movies_grid);
        mErrorMessageDisplay = rootView.findViewById(R.id.tv_error_message_display);
        mLoadingIndicator = rootView.findViewById(R.id.pb_loading_indicator);
        return rootView;
    }

    /**
     * Creates the options menu and initializes the application display based on the currently
     * selected option.
     * @param menu options menu reference
     * @param inflater inflater used to create the menu
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.sort_order, menu);
        int menuIndex = 0;
        while(menuIndex < menu.size() && menu.getItem(menuIndex).getItemId() != mMenuItemId){
            menuIndex++;
        }
        selectMenuItem(menu.getItem(menuIndex));
    }

    /**
     * Updates the display if either sort option is selected.
     * @param item menu item selected
     * @return true if either sort item is selected, deferred to 'super' otherwise
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_sort_popular || id == R.id.action_sort_rating){
            selectMenuItem(item);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Launches the detail activity when a movie is selected in the view.
     * @param position position of the movie selected. This can be used as an index to retrieve
     *                 the movie in the detail view.
     */
    @Override
    public void onClick(int position) {
        Context context = getActivity();
        Class destinationClass = DetailActivity.class;
        Intent intentToStartDetailActivity = new Intent(context, destinationClass);
        intentToStartDetailActivity.putExtra(Intent.EXTRA_TEXT, position);
        startActivity(intentToStartDetailActivity);
    }

    /**
     * Saves the state of the fragment.  This can be used to restore the fragment in the future.
     * @param outState current fragment state.
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("MenuItemId", mMenuItem.getItemId());
    }

    private void selectMenuItem(MenuItem item) {
        if (mMenuItem != null){
            mMenuItem.setChecked(false);
        }

        mMenuItem = item;
        mMenuItem.setChecked(true);
        loadMovies(mMenuItem.getItemId());
    }

    private void loadMovies(int itemId) {
        if(NetworkUtils.isOnline(getActivity())){
            showMovieGridView();
            new FetchMoviesTask(new FetchMoviesTaskListener()).execute(itemId);
        }else{
            showErrorMessage();
        }
    }

    private class FetchMoviesTaskListener implements AsyncTaskListener<List<MovieData>> {
        @Override
        public void onTaskPreExecute() {
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        public void onTaskPostExecute(List<MovieData> movieDataList) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (movieDataList != null){
                mGridView.setAdapter(new MovieAdapter(getActivity(), movieDataList, MainActivityFragment.this));
            }else{
                showErrorMessage();
            }
        }
    }

    private void showMovieGridView() {
        /* First, make sure the error is invisible */
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        /* Then, make sure the movie grid is visible */
        mGridView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        /* First, hide the currently visible movie grid */
        mGridView.setVisibility(View.INVISIBLE);
        /* Then, show the error */
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }
}
