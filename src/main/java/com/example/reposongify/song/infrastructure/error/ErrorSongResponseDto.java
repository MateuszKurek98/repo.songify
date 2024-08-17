package com.example.reposongify.song.infrastructure.error;

import org.springframework.http.HttpStatus;

public record ErrorSongResponseDto(String message, HttpStatus status) {

}
