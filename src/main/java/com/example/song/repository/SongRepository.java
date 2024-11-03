package com.example.song.repository;

import com.example.song.model.Song;

import java.util.*;

public interface SongRepository {
    public Song getSongById(int songId);

    public ArrayList<Song> getListOfSongs();

    public Song addGivenSong(Song newGivenSong);

    public Song getSongBasedOnGivenId(int id);

    public Song updateSongBasedOnGivenId(int id, Song patchSong);

    public Song deleteSongBasedOnGivenId(int id);
}