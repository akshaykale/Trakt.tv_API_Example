package com.akshaykale.popularmovies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


/**
 * Created by Akshay on 6/24/2016.
 */
public class MovieDetailsActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final int RECOVERY_DIALOG_REQUEST = 1;
    private YouTubePlayerView youTubeView;

    Movie movie;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getIntentData();

        youTubeView = (YouTubePlayerView) findViewById(R.id.player_view);
        youTubeView.initialize(Constants.YOUTUBE_API_KEY, this);




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
}
