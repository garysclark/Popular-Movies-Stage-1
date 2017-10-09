package com.example.android.utilities;

/**
 * From: http://www.jameselsey.co.uk/blogs/techblog
 *      /extracting-out-your-asynctasks-into-separate-classes-makes-your-code-cleaner/
 * This is a useful callback mechanism so we can abstract our AsyncTasks out into separate,
 * re-usable and testable classes yet still retain a hook back into the calling activity.
 * Basically, it'll make classes cleaner and easier to unit test.
 * @param <T> class of result object
 * Created by Gary on 10/9/2017.
 */

public interface AsyncTaskListener<T> {
    /**
     * Invoked just prior to the execution of the background task
     */
    void onTaskPreExecute();
    /**
     * Invoked when the AsyncTask has completed its execution.
     * @param result The resulting object from the AsyncTask.
     */
    void onTaskPostExecute(T result);
}
