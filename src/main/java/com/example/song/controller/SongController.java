package com.example.song.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.*;

import com.example.song.model.Song;
import com.example.song.service.SongH2Service;

@RestController
public class SongController {
    @Autowired
    public SongH2Service myH2SongService;

    @GetMapping("/")
    public String greet() {
        return "hello , from h2 ";
    }

    @GetMapping("/songs/{songId}")
    public Song getSongByGivenId(@PathVariable int songId) {
        Song existingSong = myH2SongService.getSongById(songId);
        if (existingSong == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return existingSong;
    }

    @GetMapping("/songs")
    public ArrayList<Song> getListOfSongs() {
        ArrayList<Song> listOfSongs = myH2SongService.getListOfSongs();
        return listOfSongs;
        
    }
}