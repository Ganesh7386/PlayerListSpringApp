package com.example.song.repository;

import com.example.song.model.Song;

import java.util.*;

public interface SongRepository {
    public Song getSongById(int songId);

    public ArrayList<Song> getListOfSongs();
}