package com.example.reposongify.song.domain.model;

import lombok.Builder;

@Builder
public record Song (String name, String artist){
}
