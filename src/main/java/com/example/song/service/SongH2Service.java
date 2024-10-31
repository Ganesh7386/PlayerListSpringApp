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
}
