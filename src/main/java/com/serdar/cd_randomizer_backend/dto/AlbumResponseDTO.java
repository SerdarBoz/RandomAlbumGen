package com.serdar.cd_randomizer_backend.dto;

import lombok.Data;

@Data
public class AlbumResponseDTO {
    private Long id;
    private String title;
    private String artist;
    private String genre;
    private Integer year;
    private String mood;
    private String coverUrl;
}
