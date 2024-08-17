package com.example.reposongify.song.infrastructure.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;
import java.util.UUID;
@Log4j2
@Component
@RequestScope
public class ArtistsSaver {
    List<String> artists;
    String saveName = UUID.randomUUID().toString();

    ArtistsSaver(List<String> artist){
        this.artists = artist;
    }
    void addArtist(String artist){
        artists.add(artist);
    }
    void printArtistsSize(){
        log.info("actual size is: " + artists.size());
    }
    void printSaverName() {
        log.info("actual saver name is: " + saveName);
    }
    public void printArtists() {
        artists.forEach(log::info);
    }
}
