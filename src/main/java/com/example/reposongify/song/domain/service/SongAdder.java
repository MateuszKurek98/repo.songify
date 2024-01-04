package com.example.reposongify.song.domain.service;

import com.example.reposongify.song.domain.model.Song;
import com.example.reposongify.song.domain.repository.SongRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class SongAdder {
    private final SongRepository songRepository;

    public SongAdder(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Song addSong(Song song) {
        //2. Wartstwa logiki biznesowej/serwisów domenowych: wyswitlamy informacje
        log.info("added new Song: " + song);
//        zapytanie do serwisu song.com/validate?songName=
        songRepository.saveToDatabase(song);
        return song;
    }
}
