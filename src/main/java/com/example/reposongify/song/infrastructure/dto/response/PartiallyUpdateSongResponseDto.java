package com.example.reposongify.song.infrastructure.dto.response;

import com.example.reposongify.song.domain.model.Song;

public record PartiallyUpdateSongResponseDto(Song updatedSong) {
}
