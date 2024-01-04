package com.example.reposongify.song.infrastructure.error;

import com.example.reposongify.song.domain.model.SongNotFoundException;
import com.example.reposongify.song.infrastructure.controller.SongRestController;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
@ControllerAdvice(assignableTypes = SongRestController.class)
@Log4j2
public class SongErrorHandler {
    @ExceptionHandler(SongNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDeleteSongResponseDto handleException(SongNotFoundException exception){
        log.warn("SongNotFoundException while accesing song");
        return new ErrorDeleteSongResponseDto(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
