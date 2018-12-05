package com.example.estherwaweru.music_app;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
//This activity is for displaying details of a specific song and handles the mediaPlayer class

public class currently_playing extends AppCompatActivity {
   private MediaPlayer mediaPlayer;
   private Handler mHandler=new Handler();
    private double finalTime=0;
    private double startTime=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currently_playing);
        TextView textView = (TextView) findViewById(R.id.song_text);
        final ImageButton button = (ImageButton) findViewById(R.id.play_btn);
        final ImageButton nextbtn = (ImageButton) findViewById(R.id.next_btn);
        final ImageButton prevbtn = (ImageButton) findViewById(R.id.previous_btn);
        final ImageButton pausebtn = (ImageButton) findViewById(R.id.pause_btn);
        Uri songUri = getIntent().getData();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), songUri);
        textView.setText(getIntent().getStringExtra(Song.SONGTITLE_KEY));
        //This pauses the song
        pausebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
                pausebtn.setEnabled(false);
                button.setEnabled(true);
            }
        });
        //This makes the song to play from its  starts time
        prevbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.seekTo((int) startTime);
                prevbtn.setEnabled(true);
            }
        });
        //This displays a toast message that a song is last in the playlist
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(), " This is the last song of the playlist", Toast.LENGTH_SHORT);
                toast.show();
                nextbtn.setEnabled(false);
            }
        });
        //This starts the mediaPlayer class
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
                button.setEnabled(false);
                pausebtn.setEnabled(true);
                finalTime = mediaPlayer.getDuration();
                startTime = mediaPlayer.getCurrentPosition();



            }
        });


    }}
