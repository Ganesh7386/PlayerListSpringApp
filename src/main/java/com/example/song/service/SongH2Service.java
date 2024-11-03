package com.example.song.service;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
// import org.springframework.web.server.ResponseStatusException;
// import java.util.ArrayList;

import com.example.song.repository.SongRepository;
import com.example.song.model.Song;
import com.example.song.model.SongRowMapper;
import java.util.*;

// import javax.validation.OverridesAttribute;

@Service
public class SongH2Service implements SongRepository {
    @Autowired
    private JdbcTemplate myDb;

    @Override
    public Song getSongById(int id) {
        Song existingSong = myDb.queryForObject("select * from playlist where songId = ?", new SongRowMapper(), id);
        if (existingSong == null) {
            return null;
        }
        return existingSong;
    }

    @Override
    public ArrayList<Song> getListOfSongs() {
        List<Song> songsList = myDb.query("select * from playlist", new SongRowMapper());
        ArrayList<Song> listOfSongs = new ArrayList<>(songsList);
        return listOfSongs;
    }

    @Override
    public Song addGivenSong(Song newGivenSong) {
        String myQuery = "INSERT INTO playlist(songName , lyricist ,singer , musicDirector) values(? , ? , ? , ?)";
        myDb.update(myQuery, newGivenSong.getSongName(), newGivenSong.getLyricist(), newGivenSong.getSinger(),
                newGivenSong.getMusicDirector());
        Song newAddedSong = myDb.queryForObject(
                "SELECT * FROM playlist where songName=? and lyricist=? and singer=? and musicDirector=?",
                new SongRowMapper(), newGivenSong.getSongName(), newGivenSong.getLyricist(), newGivenSong.getSinger(),
                newGivenSong.getMusicDirector());
        return newAddedSong;

    }

    @Override
    public Song getSongBasedOnGivenId(int songId) {
        String myQuery = "SELECT * FROM playlist where songId = ?";
        Song existingSong = myDb.queryForObject(myQuery, new SongRowMapper(), songId);
        if (existingSong == null) {
            return null;
        }
        return existingSong;
    }

    @Override
    public Song updateSongBasedOnGivenId(int songId, Song patchSong) {
        Song existingSong = myDb.queryForObject("SELECT * FROM playlist where songId = ?", new SongRowMapper(), songId);
        if (existingSong == null) {
            return null;
        }
        if (patchSong.getSongName() != null) {
            myDb.update("UPDATE playlist SET songName=? where songId = ?", patchSong.getSongName(),
                    songId);
        }
        if (patchSong.getLyricist() != null) {
            myDb.update("UPDATE playlist SET lyricist = ? WHERE songId = ?",
                    patchSong.getLyricist(), songId);
        }
        if (patchSong.getSinger() != null) {
            myDb.update("UPDATE playlist SET singer = ? WHERE songId = ?",
                    patchSong.getSinger(), songId);
        }
        if (patchSong.getMusicDirector() != null) {
            myDb.update("UPDATE playlist SET musicDirector = ? WHERE songId = ?",
                    patchSong.getMusicDirector(), songId);
        }

        Song newUpdatedSong = myDb.queryForObject("SELECT * FROM playlist where songId = ?", new SongRowMapper(),
                songId);

        return newUpdatedSong;
    }

    @Override
    public Song deleteSongBasedOnGivenId(int songId) {
        Song existingSong = myDb.queryForObject("SELECT * FROM playlist where songId = ?", new SongRowMapper(), songId);
        if (existingSong == null) {
            return null;
        }
        myDb.update("DELETE FROM playlist where songId = ?", songId);
        return existingSong;
    }

}
