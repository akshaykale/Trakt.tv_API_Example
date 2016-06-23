package com.akshaykale.popularmovies;

/**
 * Created by Akshay on 6/23/2016.
 */
public class Movie {

    public Movie() {
        title = "";
        year = 0;
        id_IMDB = "";
        id_TMDB = "";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getId_IMDB() {
        return id_IMDB;
    }

    public void setId_IMDB(String id_IMDB) {
        this.id_IMDB = id_IMDB;
    }

    public String getId_TMDB() {
        return id_TMDB;
    }

    public void setId_TMDB(String id_TMDB) {
        this.id_TMDB = id_TMDB;
    }

    /**
     * {
     "title":"Batman Begins",
     "year":2005,
     "ids":{
         "trakt":1,
         "slug":"batman-begins-2005",
         "imdb":"tt0372784",
         "tmdb":272
        }
     }
     * */

    String title;
    int year;
    String id_IMDB,id_TMDB;
}
