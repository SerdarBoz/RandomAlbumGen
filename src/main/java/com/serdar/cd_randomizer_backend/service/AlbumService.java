package com.serdar.cd_randomizer_backend.service;

import com.serdar.cd_randomizer_backend.dto.AlbumRequestDTO;
import com.serdar.cd_randomizer_backend.dto.AlbumResponseDTO;

import java.util.List;

public interface AlbumService {
    AlbumResponseDTO createAlbum(AlbumRequestDTO requestDTO);

    List<AlbumResponseDTO> getAllAlbums();

    AlbumResponseDTO getRandomAlbum();

    List<AlbumResponseDTO> searchAlbums(String genre, String mood);

    AlbumResponseDTO updateAlbum(Long id, AlbumRequestDTO requestDTO);

    void deleteAlbum(Long id);
}
