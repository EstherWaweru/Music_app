package com.example.estherwaweru.music_app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class Song {
    private String artistName;
    private String songTitle;
    private String songUri;
    private String songAlbum;
    static final String SONGURI_KEY="SongUri";
    static final String SONGTITLE_KEY="SongTitle";

    public Song(String artistName, String songTitle, String songUri,String songAlbum) {
        this.artistName = artistName;
        this.songTitle = songTitle;
        this.songUri = songUri;
        this.songAlbum=songAlbum;
    }

    public String getArtistName() {
        return artistName;
    }
    public String getSongAlbum(){return songAlbum;}

    public String getSongTitle() {
        return songTitle;
    }

    public String getSongUri() {
        return songUri;
    }
    //passes the songUri and title to the currentPlaying activity
    static Intent starter(Context context, String songUri, String songTitle){
        Intent mediaIntent=new Intent(context,currently_playing.class);
        mediaIntent.setData(Uri.parse( songUri));
        mediaIntent.putExtra(SONGTITLE_KEY,songTitle);
        return mediaIntent;
    }

}
