package com.example.reposongify.song.infrastructure.dto.response;
import com.example.reposongify.song.domain.model.Song;
import java.util.Map;

public record GetAllSongsResponseDto(Map<Integer, Song> songs) {
}
