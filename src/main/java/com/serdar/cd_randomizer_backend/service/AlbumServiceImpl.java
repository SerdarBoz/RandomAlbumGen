package com.serdar.cd_randomizer_backend.service;

import com.serdar.cd_randomizer_backend.dto.AlbumRequestDTO;
import com.serdar.cd_randomizer_backend.dto.AlbumResponseDTO;
import com.serdar.cd_randomizer_backend.model.Album;
import com.serdar.cd_randomizer_backend.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class  AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albumRepository;

    @Override
    public AlbumResponseDTO createAlbum(AlbumRequestDTO requestDTO) {
        Album album = mapToEntity(requestDTO);
        Album saved = albumRepository.save(album);
        return mapToResponseDTO(saved);
    }

    @Override
    public List<AlbumResponseDTO> getAllAlbums() {
        return albumRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    @Override
    public AlbumResponseDTO getRandomAlbum() {
        List<Album> albums = albumRepository.findAll();

        if (albums.isEmpty()) {
            throw new RuntimeException("Geen albums gevonden");
        }

        Random random = new Random();
        Album randomAlbum = albums.get(random.nextInt(albums.size()));
        return mapToResponseDTO(randomAlbum);
    }

    @Override
    public List<AlbumResponseDTO> searchAlbums(String genre, String mood) {
        List<Album> results;

        if (genre != null && mood != null) {
            results = albumRepository.findByGenreAndMood(genre, mood);
        } else if (genre != null) {
            results = albumRepository.findByGenre(genre);
        } else if (mood != null) {
            results = albumRepository.findByMood(mood);
        } else {
            results = albumRepository.findAll();
        }

        return results.stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    @Override
    public AlbumResponseDTO updateAlbum(Long id, AlbumRequestDTO requestDTO) {
        Album existing = albumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Album niet gevonden met id: " + id));

        existing.setTitle(requestDTO.getTitle());
        existing.setArtist(requestDTO.getArtist());
        existing.setGenre(requestDTO.getGenre());
        existing.setYear(requestDTO.getYear());
        existing.setMood(requestDTO.getMood());
        existing.setCoverUrl(requestDTO.getCoverUrl());

        return mapToResponseDTO(albumRepository.save(existing));
    }

    @Override
    public void deleteAlbum(Long id) {
        if (!albumRepository.existsById(id)) {
            throw new RuntimeException("Album niet gevonden met id: " + id);
        }
        albumRepository.deleteById(id);
    }

    private Album mapToEntity(AlbumRequestDTO dto) {
        Album album = new Album();
        album.setTitle(dto.getTitle());
        album.setArtist(dto.getArtist());
        album.setGenre(dto.getGenre());
        album.setYear(dto.getYear());
        album.setMood(dto.getMood());
        album.setCoverUrl(dto.getCoverUrl());
        return album;
    }

    private AlbumResponseDTO mapToResponseDTO(Album album) {
        AlbumResponseDTO dto = new AlbumResponseDTO();
        dto.setId(album.getId());
        dto.setTitle(album.getTitle());
        dto.setArtist(album.getArtist());
        dto.setGenre(album.getGenre());
        dto.setYear(album.getYear());
        dto.setMood(album.getMood());
        dto.setCoverUrl(album.getCoverUrl());
        return dto;
    }

}
