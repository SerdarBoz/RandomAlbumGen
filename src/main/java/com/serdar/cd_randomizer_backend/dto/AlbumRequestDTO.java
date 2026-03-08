package com.serdar.cd_randomizer_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AlbumRequestDTO {
    @NotBlank(message = "Titel is verplicht")
    private String title;

    @NotBlank(message = "Artiest is verplicht")
    private String artist;

    private String genre;

    @NotNull(message = "Jaar is verplicht")
    private Integer year;

    private String mood;

    private String coverUrl;
}
