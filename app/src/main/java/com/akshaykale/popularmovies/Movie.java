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

    public int getTrakt() {
        return trakt;
    }

    public void setTrakt(int trakt) {
        this.trakt = trakt;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
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

    String title,tagline,overview,trailer,homepage,banner;
    int year,trakt,runtime;
    double rating;
    String id_IMDB;
    String id_TMDB;
    String slug;



    String genres;



}
