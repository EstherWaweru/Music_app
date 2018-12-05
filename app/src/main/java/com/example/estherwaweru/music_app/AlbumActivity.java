package com.example.estherwaweru.music_app;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AlbumActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST=1;
    private ListView mListView;
    private SongAdapter mAdapter;
    private ArrayList<Song> mSong;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        mSong=new ArrayList<Song>();
        mListView=(ListView) findViewById(R.id.song_list);
        mAdapter=new SongAdapter(this,mSong);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
               Song mSongData = mSong.get(position);
                Intent mediaIntent=new Intent(AlbumActivity.this,MediaActivity.class);
                mediaIntent.setData(Uri.parse(mSongData.getSongUri()));
                startActivity(mediaIntent);


            }
        });
        checkPermission();

    }
    //This checks if the user has granted permission to use the external storage
    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest
                    .permission.READ_EXTERNAL_STORAGE)){
                ActivityCompat.requestPermissions(AlbumActivity.this,new String[]
                        {Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST);

            }
            else{
                ActivityCompat.requestPermissions(AlbumActivity.this,new String[]
                        {Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST);

            }
        }
        else{
            getMusic();
        }
    }
    //This displays the results of the check permission result method
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getMusic();
                }
                else{
                    Toast toast=Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT);
                    toast.show();
                    checkPermission();}

                break;}
            default:{
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }}


    }

//This queries the external storage for the album and songUri
    public void getMusic() {
        ContentResolver contentResolver=getContentResolver();
        Uri songUri= MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor songCursor=contentResolver.query(songUri,null,null,null,null);
        if(songCursor!=null && songCursor.moveToFirst()){
            do{
                String title=songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                String album=songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                String artist=songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String url=songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                Song s=new Song(null,title,url,album);
                mSong.add(s);
            }while (songCursor.moveToNext());
            songCursor.close();
            mAdapter=new SongAdapter(this,mSong);
        }
    }
}
