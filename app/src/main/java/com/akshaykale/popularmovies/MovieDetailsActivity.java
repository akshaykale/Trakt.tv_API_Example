package com.akshaykale.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


/**
 * Created by Akshay on 6/24/2016.
 */
public class MovieDetailsActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener, View.OnClickListener {

    private static final int RECOVERY_DIALOG_REQUEST = 1;
    private YouTubePlayerView youTubeView;

    private TextView tv_tagline,tv_overview,tv_rating,tv_runtime,tv_year;
    private ImageView iv_homepage,iv_imdb,iv_tmdb;

    Movie movie;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getIntentData();

        init();


        setData();
    }

    private void init() {

        youTubeView = (YouTubePlayerView) findViewById(R.id.player_view);
        youTubeView.initialize(Constants.YOUTUBE_API_KEY, this);

        tv_overview = (TextView) findViewById(R.id.tv_det_overview);
        tv_rating = (TextView) findViewById(R.id.tv_det_rating);
        tv_runtime = (TextView) findViewById(R.id.tv_det_time);
        tv_tagline = (TextView) findViewById(R.id.tv_det_tagline);
        tv_year = (TextView) findViewById(R.id.tv_det_year);

        iv_homepage = (ImageView) findViewById(R.id.iv_homepage); iv_homepage.setOnClickListener(this);
        iv_imdb = (ImageView) findViewById(R.id.iv_imdb); iv_imdb.setOnClickListener(this);
        iv_tmdb = (ImageView) findViewById(R.id.iv_tmdb); iv_tmdb.setOnClickListener(this);

    }

    private void setData() {

        tv_overview.setText(movie.getOverview());
        tv_year.setText(movie.getYear()+"");
        tv_tagline.setText(movie.getTagline());
        tv_runtime.setText(movie.getRuntime()+" min");
        tv_rating.setText(String.valueOf(movie.getRating()).substring(0,4));

        TextView tv_action,tv_comedy,tv_drama,tv_adventure,tv_sience_fiction,tv_romance,tv_fantancy;
        tv_action = (TextView) findViewById(R.id.tag_det_action);
        tv_comedy = (TextView) findViewById(R.id.tag_det_comedy);
        tv_drama = (TextView) findViewById(R.id.tag_det_drama);tv_adventure = (TextView) findViewById(R.id.tag_det_adventure);
        tv_sience_fiction = (TextView) findViewById(R.id.tag_det_sience_fiction);
        tv_romance = (TextView) findViewById(R.id.tag_det_romance);
        tv_fantancy = (TextView) findViewById(R.id.tag_det_fantasy);

        String gener = movie.getGenres();
        if(gener!=null && !gener.equals("")){
            if (gener.contains("action"))
                 tv_action.setVisibility(View.VISIBLE);
            if (gener.contains("adventure"))
                 tv_adventure.setVisibility(View.VISIBLE);
            if (gener.contains("fantasy"))
                 tv_fantancy.setVisibility(View.VISIBLE);
            if (gener.contains("science-fiction"))
                 tv_sience_fiction.setVisibility(View.VISIBLE);
            if (gener.contains("drama"))
                 tv_drama.setVisibility(View.VISIBLE);
            if (gener.contains("comedy"))
                 tv_comedy.setVisibility(View.VISIBLE);
            if (gener.contains("romance"))
                 tv_romance.setVisibility(View.VISIBLE);
        }
    }

    private void getIntentData() {

        movie = new Movie();

        movie.setTitle(getIntent().getStringExtra("title"));
        movie.setYear(getIntent().getIntExtra("year",2013));
        movie.setTagline(getIntent().getStringExtra("tagline"));
        movie.setOverview(getIntent().getStringExtra("overview"));
        movie.setGenres(getIntent().getStringExtra("genres"));
        movie.setHomepage(getIntent().getStringExtra("homepage"));
        movie.setId_IMDB(getIntent().getStringExtra("imdb"));
        movie.setId_TMDB(getIntent().getStringExtra("tmdb"));
        movie.setTrailer(getIntent().getStringExtra("trailer"));
        movie.setRating(getIntent().getDoubleExtra("rating",8.3));
        movie.setRuntime(getIntent().getIntExtra("runtime",152));
    }

    @Override   
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                        boolean wasRestored) {
        if (!wasRestored) {
            player.cueVideo(movie.getTrailer().split("=")[1]);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format("There was an error initializing the YouTubePlayer", errorReason.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.iv_homepage:
                Intent intent_homepage = new Intent(MovieDetailsActivity.this,WebViewActivity.class);
                intent_homepage.putExtra("webview_url", movie.getHomepage());
                startActivity(intent_homepage);
                break;
            case R.id.iv_imdb:
                Intent intent_imdb = new Intent(MovieDetailsActivity.this,WebViewActivity.class);
                intent_imdb.putExtra("webview_url", "http://www.imdb.com/title/"+movie.getId_IMDB());
                startActivity(intent_imdb);
                break;
            case R.id.iv_tmdb:
                Intent intent_tmdb = new Intent(MovieDetailsActivity.this,WebViewActivity.class);
                intent_tmdb.putExtra("webview_url", "https://www.themoviedb.org/movie/"+movie.getId_TMDB());
                startActivity(intent_tmdb);
                break;
        }
    }
}
