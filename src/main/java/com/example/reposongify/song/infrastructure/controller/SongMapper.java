package com.example.reposongify.song.infrastructure.controller;

import com.example.reposongify.song.domain.model.Song;
import com.example.reposongify.song.infrastructure.dto.request.CreateSongRequestDto;
import com.example.reposongify.song.infrastructure.dto.request.PartiallyUpdateSongRequestDto;
import com.example.reposongify.song.infrastructure.dto.request.UpdateSongRequestDto;
import com.example.reposongify.song.infrastructure.dto.response.*;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class SongMapper {public static Song mapFromCreateSongRequestDtoToSong(CreateSongRequestDto dto) {
    return new Song(dto.songName(), dto.artist());
}

    public static Song mapFromUpdateSongRequestDtoToSong(UpdateSongRequestDto dto) {
        return new Song(dto.songName(), dto.artist());
    }

    public static Song mapFromPartiallyUpdateSongRequestDtoToSong(PartiallyUpdateSongRequestDto dto) {
        return new Song(dto.songName(), dto.artist());
    }

    public static CreateSongResponseDto mapFromSongToCreateSongResponseDto(Song song) {
        return new CreateSongResponseDto(song);
    }

    public static DeleteSongResponseDto mapFromSongToDeleteSongResponseDto(Integer id) {
        return new DeleteSongResponseDto("You deleted song with id: " + id, HttpStatus.OK);
    }

    public static UpdateSongResponseDto mapFromSongToUpdateSongResponseDto(Song newSong) {
        return new UpdateSongResponseDto(newSong.name(), newSong.artist());
    }

    public static PartiallyUpdateSongResponseDto mapFromSongToPartiallyUpdateSongResponseDto(Song updatedSong) {
        return new PartiallyUpdateSongResponseDto(updatedSong);
    }

    public static GetSongResponseDto mapFromSongToGetSongResponseDto(Song song) {
        return new GetSongResponseDto(song);
    }

    public static GetAllSongsResponseDto mapFromSongToGetAllSongsResponseDto(Map<Integer, Song> database) {
        return new GetAllSongsResponseDto(database);
    }
}
