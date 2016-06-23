package com.akshaykale.popularmovies;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Akshay on 6/23/2016.
 */
public class LoadMoviesFromUrlTask extends AsyncTask<String,String,String>{


    private IMoviesLoadedListener iMoviesLoadedListener;
    Context mContext;
    String mUrl;

    private final String USER_AGENT = "Android";

    public LoadMoviesFromUrlTask(Context mContext, String mUrl, IMoviesLoadedListener iMoviesLoadedListener) {
        this.mContext = mContext;
        this.mUrl = mUrl;
        this.iMoviesLoadedListener = iMoviesLoadedListener;

    }

    @Override
    protected String doInBackground(String... params) {
        try {
            URL obj = new URL(this.mUrl);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add request header
            /**
             Content-Type:application/json
             trakt-api-version:2
             trakt-api-key:[client_id]
             * */
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("trakt-api-version", "2");
            con.setRequestProperty("trakt-api-key", Constants.CLIENT_ID);

            int responseCode = con.getResponseCode();

            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        ArrayList<Movie> movieList = new ArrayList<>();
        movieList = parseResponse(s);
        iMoviesLoadedListener.onPopularMoviesLoaded(movieList);

    }

    private ArrayList<Movie> parseResponse(String s) {
        ArrayList<Movie> movieList = new ArrayList<>();
        /**
         *
         [
            {
             "title": "The Dark Knight",
             "year": 2008,
             "ids": {
                 "trakt": 16,
                 "slug": "the-dark-knight-2008",
                 "imdb": "tt0468569",
                 "tmdb": 155
                }
             }
         ]
         * */
        try {
            JSONArray jsonArray = new JSONArray(s);
            for (int i=0;i<jsonArray.length();i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Movie movie = new Movie();
                if (!jsonObject.isNull("title"))
                    movie.setTitle(jsonObject.getString("title"));
                if (!jsonObject.isNull("year"))
                    movie.setYear(jsonObject.getInt("year"));
                if (!jsonObject.isNull("ids")){
                    JSONObject joID = jsonObject.getJSONObject("ids");
                    if (!joID.isNull("imdb"))
                        movie.setId_IMDB(joID.getString("imdb"));
                    if (!joID.isNull("tmdb"))
                        movie.setId_TMDB(joID.getString("tmdb"));
                    if (!joID.isNull("trakt"))
                        movie.setTrakt(joID.getInt("trakt"));
                    if (!joID.isNull("slug"))
                        movie.setSlug(joID.getString("slug"));
                }

                movieList.add(movie);
            }
            return movieList;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}

