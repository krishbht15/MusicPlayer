package com.example.myapplication;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {
    Context context; ArrayList<SongPojo> songs;
    MediaPlayer mediaPlayer=new MediaPlayer();
    private static final String TAG = "SongAdapter";

    public SongAdapter(Context context, ArrayList<SongPojo> songs) {
        this.context=context;
        this.songs=songs;
    }


    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.song_layout,parent,false);
        view.findViewById(R.id.playButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: bolbaphsohpahpa");
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: view hua click");
            }
        });
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SongViewHolder holder, int position) {
        final SongPojo current=songs.get(position);
        holder.artistName.setText(current.getArtistName());
        holder.songName.setText(current.getSongName());
        holder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                if (holder.play.getText().equals("stop")) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                    mediaPlayer.release();
                    holder.play.setText("Play");
                    mediaPlayer=null;
                } else {
                    try {
                        mediaPlayer=new MediaPlayer();
                        Log.d(TAG, "onClick: sss" + current.getSongUrl());
                        mediaPlayer.setDataSource(current.getSongUrl());
                        mediaPlayer.prepareAsync();
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();

                                holder.play.setText("stop");
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class SongViewHolder extends RecyclerView.ViewHolder {
        TextView songName,artistName;
        Button play;

        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            songName=itemView.findViewById(R.id.songText);
            artistName=itemView.findViewById(R.id.artistText);
            play=itemView.findViewById(R.id.playButton);
        }
    }
}
