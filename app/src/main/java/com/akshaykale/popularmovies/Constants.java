package com.akshaykale.popularmovies;

/**
 * Created by Akshay on 6/22/2016.
 */
public class Constants {

    public static final String BASE_URL = "https://api-v2launch.trakt.tv/";

    public static final String CLIENT_ID = "ad005b8c117cdeee58a1bdb7089ea31386cd489b21e14b19818c91511f12a086";

    public static String getMoviesURL(String category,int page){
        StringBuilder url = new StringBuilder();
        url.append(BASE_URL)
                .append("movies")
                .append("/")
                .append(category)
                .append("?page="+page);
        return url.toString();
    }
}
