package com.kpt.tienminh.youtubeapi0305;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MainActivity extends YouTubeBaseActivity
    implements YouTubePlayer.OnInitializedListener{

    String API_KEY = "AIzaSyAkW5lqi7v6i18_aaxESmm2_5Q9nT0sH5w";
    EditText edtIdVideo;
    Button btnPlay;
    YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtIdVideo = (EditText) findViewById(R.id.editTextIDVideo);
        btnPlay = (Button) findViewById(R.id.buttonPlay);
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtubeView);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youTubePlayerView.initialize(API_KEY, MainActivity.this);
            }
        });
    }
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer youTubePlayer, boolean b) {

        youTubePlayer.cueVideo(edtIdVideo.getText().toString());
    }
    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult youTubeInitializationResult) {
        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this, 123);
        }else {
            Toast.makeText(MainActivity.this,"Lỗi Video", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 123){
            youTubePlayerView.initialize(API_KEY, this);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
