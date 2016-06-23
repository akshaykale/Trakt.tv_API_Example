package com.akshaykale.popularmovies;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        IMoviesLoadedListener {

    private static final String TAG = "MainActivity";
    private ListView listView;
    private ProgressDialog dialog;
    private MoviesListAdapter adapter;
    public static ArrayList<Movie> mPopularMovieList;

    int page_count = 1;
    int max_pages = 100;  //initially


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

        if(Utils.isOnline(getApplicationContext())) {
            getDataFromUrl(Constants.getMoviesURL("popular", page_count));
            setListViewAdapter();
            listView.setOnScrollListener(onScrollListener());
        }else {
            Toast.makeText(this,"No Internet connectivity", Toast.LENGTH_SHORT).show();
        }
    }

    private void setListViewAdapter() {
        adapter = new MoviesListAdapter(this, mPopularMovieList);
        listView.setAdapter(adapter);
    }

    // calling asynctask to get json data from internet
    private void getDataFromUrl(String url) {
        new LoadMoviesFromUrlTask(this, url, this).execute();
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

    @Override
    public void onPopularMoviesLoaded(ArrayList<Movie> movies) {
        mPopularMovieList.addAll( movies);
        adapter.notifyDataSetChanged();
    }
}
