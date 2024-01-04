package com.example.reposongify.song.infrastructure.controller;

import com.example.reposongify.song.domain.model.Song;
import com.example.reposongify.song.domain.model.SongNotFoundException;
import com.example.reposongify.song.domain.service.SongAdder;
import com.example.reposongify.song.domain.service.SongRetriever;
import com.example.reposongify.song.infrastructure.dto.request.CreateSongRequestDto;
import com.example.reposongify.song.infrastructure.dto.request.PartiallyUpdateSongRequestDto;
import com.example.reposongify.song.infrastructure.dto.request.UpdateSongRequestDto;
import com.example.reposongify.song.infrastructure.dto.response.*;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RestController
@Log4j2
@RequestMapping("/songs")

public class SongRestController {
    private final SongAdder songAdder;
    private final SongRetriever songRetriever;


    public SongRestController(SongAdder songAdder, SongRetriever songRetriever) {
        this.songAdder = songAdder;
        this.songRetriever = songRetriever;
    }

    @GetMapping()
    public ResponseEntity<GetAllSongResponseDto> getAllSongs(@RequestParam(required = false) Integer limit){
        Map<Integer, Song> allSongs = songRetriever.findAll();
        if(limit != null){
            Map<Integer, Song> limitedMap = songRetriever.findAllLimitedBy(limit);
            GetAllSongResponseDto response = new GetAllSongResponseDto(limitedMap);
            return ResponseEntity.ok(response);
        }
        GetAllSongResponseDto response = new GetAllSongResponseDto(allSongs);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GetSongResponseDto> getSongById(@PathVariable Integer id,
                                                          @RequestHeader(required = false) String requestId){
        log.info(requestId);
        Map<Integer, Song> allSongs = songRetriever.findAll();
        if(!allSongs.containsKey(id)){
            throw new SongNotFoundException("Song with id " + id + " not found");
        }
        Song song = allSongs().get(id);
        GetSongResponseDto response = new GetSongResponseDto(song);
        return ResponseEntity.ok(response);
    }
    @PostMapping()
    public ResponseEntity<CreateSongResponseDto> postSong(@RequestBody @Valid CreateSongRequestDto request){
        //1. Mapowanie z CreateSongResponseDto na Obiekt Domenowy (Song)
        Song song = SongMapper.mapFromCreateSongRequestDtoToSong(request);
        songAdder.addSong(song);
        //4. mapowanie z obiektu Domenowego (Song) na DTO CreateSongResponseDto
        CreateSongResponseDto body = SongMapper.mapFromSongToCreateSongResponseDto(song);
        return ResponseEntity.ok(body);
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteSongResponseDto> deleteSongByIdUsingPathVariable(@PathVariable  Integer id){
        if(!allSongs().containsKey(id)){
            throw new SongNotFoundException("Song with id " + id + " not found");
        }
        allSongs().remove(id);
        return ResponseEntity.ok(new DeleteSongResponseDto("You deleted song by id: " + id, HttpStatus.OK));
    }
    @PutMapping("/{id}")
    public ResponseEntity<UpdateSongResponseDto> update(@PathVariable Integer id,
                                                        @RequestBody @Valid UpdateSongRequestDto request) {
        if (!allSongs().containsKey(id)) {
            throw new SongNotFoundException("Song with id " + id + " not found");
        }
        String newSongName = request.songName();
        String newArtist = request.artist();
        Song newSong = new Song(newSongName, newArtist);
        Song oldSong = allSongs().put(id, newSong);
        log.info("Update song with id: " + id +
                " with oldSongName: " + oldSong.name() +
                " to new song: " + newSong.name() +
                " old artist: " + oldSong.artist() +
                " to new artist: " + newSong.artist());
        return ResponseEntity.ok(new UpdateSongResponseDto(newSong.name(), newSong.artist()));
    }

    private Map<Integer, Song> allSongs() {
        return songRetriever.findAll();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PartiallyUpdateSongResponseDto> partiallyUpdateSong(@PathVariable Integer id,
                                                                              @RequestBody PartiallyUpdateSongRequestDto request){
        Map<Integer, Song> allSongs = songRetriever.findAll();
        if(!allSongs.containsKey(id)){
            throw new RuntimeException("song with id: " + id + " not found");
        }
        Song songFromDatabase = allSongs.get(id);
        Song.SongBuilder biulder = Song.builder();
        if(request.songName() != null){
            biulder.name(request.songName());
            log.info("Partially update song name");
        } else {
            biulder.name(songFromDatabase.name());
        }
        if(request.artist() != null){
            biulder.artist(request.artist());
            log.info("Partially update artist");
        } else {
            biulder.artist(songFromDatabase.artist());
        }
        Song updatedSong = biulder.build();
        allSongs.put(id, updatedSong);
        return ResponseEntity.ok(new PartiallyUpdateSongResponseDto(updatedSong));
    }
}
