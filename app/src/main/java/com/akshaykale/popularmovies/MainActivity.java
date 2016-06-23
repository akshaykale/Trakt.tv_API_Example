package com.akshaykale.popularmovies;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ListView listView;
    private ProgressDialog dialog;
    private MoviesListAdapter adapter;
    ArrayList<Movie> mPopularMovieList;

    int page_count = 1;
    int max_pages = 1;  //initially


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    private void init() {

        //data
        mPopularMovieList = new ArrayList<>();

        //view
        listView = (ListView) findViewById(R.id.list);

        setListViewAdapter();
        getDataFromUrl(Constants.getMoviesURL("popular",page_count));
        listView.setOnScrollListener(onScrollListener());
    }

    private void setListViewAdapter() {
        adapter = new MoviesListAdapter(this, mPopularMovieList);
        listView.setAdapter(adapter);
    }

    // calling asynctask to get json data from internet
    private void getDataFromUrl(String url) {
        new LoadMoviesFromUrlTask(this, url).execute();
    }


    private AbsListView.OnScrollListener onScrollListener() {
        return new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                int threshold = 1;
                int count = listView.getCount();

                if (scrollState == SCROLL_STATE_IDLE) {
                    if (listView.getLastVisiblePosition() >= count - threshold && page_count < max_pages) {
                        Log.i(TAG, "loading more data");
                        page_count++;
                        // Execute LoadMoreDataTask AsyncTask
                        getDataFromUrl(Constants.getMoviesURL("popular",page_count));
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
            }

        };
    }
}
