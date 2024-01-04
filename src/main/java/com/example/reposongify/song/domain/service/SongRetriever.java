package com.example.reposongify.song.domain.service;

import com.example.reposongify.song.domain.model.Song;
import com.example.reposongify.song.domain.repository.SongRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;
@Service
@Log4j2
public class SongRetriever {
    // klasa wyciągająca piosenki
    private final SongRepository songRepository;

    SongRetriever(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Map<Integer, Song> findAll() {
        //2. Wartstwa logiki biznesowej/serwisów domenowych: wyswitlamy informacje
        log.info("Retreiving all songs: " );
        return songRepository.findAll();
    }
    public Map<Integer, Song> findAllLimitedBy(Integer limit) {

        return songRepository.findAll().entrySet()
                .stream()
                .limit(limit)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
