package com.akshaykale.popularmovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Akshay on 6/26/2016.
 */
public class SearchActivity extends AppCompatActivity implements IMoviesLoadedListener, AdapterView.OnItemClickListener, TextWatcher {

    private static final String TAG = "SearchActivity";
    EditText et_search;
    ListView listView;
    private SearchListAdapter adapter;
    public static ArrayList<Movie> mMovieList;

    int page_count = 1;
    int max_pages = 100;  //initially

    LoadMoviesFromUrlTask loadMoviesFromUrlTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        et_search = (EditText) findViewById(R.id.et_serch_box);
        mMovieList = new  ArrayList<Movie>();

        listView = (ListView) findViewById(R.id.list_search);
        setListViewAdapter();
        listView.setOnScrollListener(onScrollListener());
        listView.setOnItemClickListener(this);





        et_search.addTextChangedListener(this);

    }

    private void setListViewAdapter() {
        adapter = new SearchListAdapter(this);
        listView.setAdapter(adapter);
    }

    private LoadMoviesFromUrlTask getDataFromUrl(String url,int scroll) {
         return (LoadMoviesFromUrlTask) new LoadMoviesFromUrlTask(this, url, scroll, this).execute();
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
                        searchLogic(et_search.getText().toString(),1);
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
    public void onPopularMoviesLoaded(ArrayList<Movie> movies , int scroll) {
        if (scroll == 1){
            mMovieList.addAll(movies);
        }else {
            mMovieList = movies;
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Movie movie = mMovieList.get(position);
/*
        Intent intent = new Intent(SearchActivity.this,MovieDetailsActivity.class);
        intent.putExtra("title",movie.getTitle());
        intent.putExtra("year",movie.getYear());
        intent.putExtra("tagline",movie.getTagline());
        intent.putExtra("overview",movie.getOverview());
        intent.putExtra("genres",movie.getGenres());
        intent.putExtra("homepage",movie.getHomepage());
        intent.putExtra("imdb",movie.getId_IMDB());
        intent.putExtra("tmdb",movie.getId_TMDB());
        intent.putExtra("trailer",movie.getTrailer());
        intent.putExtra("rating",movie.getRating());
        intent.putExtra("runtime",movie.getRuntime());

        startActivity(intent);*/
    }





    /////edittext
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        Log.d(TAG,"beforeTextChanged--->> "+s.toString());
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Log.d(TAG,"onTextChanged--->> "+s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {
        Log.d(TAG,"afterTextChanged--->> "+s.toString());
        searchLogic(s.toString(),0);

    }


    public void searchLogic(String s, int scroll){
        String query = s.toString().replace(" ","%20");

        if(s.length()>=3){
            String url = Constants.getSearchURL(query,page_count);

            if (loadMoviesFromUrlTask!=null && loadMoviesFromUrlTask.getStatus() == AsyncTask.Status.RUNNING){
                loadMoviesFromUrlTask.cancel(true);
            }
            loadMoviesFromUrlTask = getDataFromUrl(url,scroll);

        }
    }
}
