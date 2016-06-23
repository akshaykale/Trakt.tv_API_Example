package com.akshaykale.popularmovies;

import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by Akshay on 6/23/2016.
 */
public class LoadMoviesFromUrlTask extends AsyncTask<String,String,String>{


    Context mContext;
    String mUrl;

    public LoadMoviesFromUrlTask(Context mContext, String mUrl) {
        this.mContext = mContext;
        this.mUrl = mUrl;
    }

    @Override
    protected String doInBackground(String... params) {
        return null;
    }
}

