package com.example.reposongify.song.domain.repository;

import com.example.reposongify.song.domain.model.Song;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
@Repository
public class SongRepository {
    Map<Integer, Song> database = new HashMap<>(Map.of(
            1, new Song("In The End", "Linkin Park"),
            2, new Song("Hate you", "Three Days Grace"),
            3, new Song("Papercut", "Linkin Park"),
            4, new Song("Smallow","Linkin Park")
    ));
    public Song saveToDatabase(Song song) {
        //3. warstwa bazodanowa: zapisujemy do bazy danych
        database.put(database.size() + 1, song);
        return song;
    }
    public Map<Integer, Song> findAll() {
        return database;
    }
}
