package com.example.estherwaweru.music_app;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView songs = (TextView) findViewById(R.id.song_activity);

        // Set a click listener on that View
        songs.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the media category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link mediaActivity}
                Intent mediaIntent = new Intent(MainActivity.this, MediaActivity.class);

                // Start the new activity
                startActivity(mediaIntent);
            }
        });

        // Find the View that shows the artist category
        TextView artists = (TextView) findViewById(R.id.artist_activity);

        // Set a click listener on that View
        artists.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the artist category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link ArtistActivity}
                Intent artistIntent = new Intent(MainActivity.this, ArtistActivity.class);

                // Start the new activity
                startActivity(artistIntent);
            }
        });

        // Find the View that shows the album category
        TextView albums = (TextView) findViewById(R.id.album_activity);

        // Set a click listener on that View
        albums.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the albums category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link AlbumsActivity}
                Intent albumsIntent = new Intent(MainActivity.this, AlbumActivity.class);

                // Start the new activity
                startActivity(albumsIntent);
            }
        });


    }
}
