package com.example.estherwaweru.music_app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SongAdapter extends ArrayAdapter<Song> {
    public SongAdapter(Context context, ArrayList<Song> song) {
        super(context, 0, song);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Song} object located at this position in the list
        Song currentSong = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID song_title
        TextView songTextView = (TextView) listItemView.findViewById(R.id.song_title);
        // Get the song tilte from the currentsong object and set this text on
        // the songTextView.
        songTextView.setText(currentSong.getSongTitle());

        // Find the TextView in the list_item.xml layout with the ID song_artist.
        TextView artistTextView = (TextView) listItemView.findViewById(R.id.song_artist);
        // Get the artist from the currentSong object and set this text on
        // the artistTextView.
        artistTextView.setText(currentSong.getArtistName());

        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }
}
