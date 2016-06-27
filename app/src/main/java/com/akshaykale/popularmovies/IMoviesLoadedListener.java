package com.akshaykale.popularmovies;

import java.util.ArrayList;

/**
 * Created by Akshay on 6/23/2016.
 */
public interface IMoviesLoadedListener {

    void onPopularMoviesLoaded(ArrayList<Movie> movies, int scroll);

}
