package com.serdar.cd_randomizer_backend.controller;

import com.serdar.cd_randomizer_backend.dto.AlbumRequestDTO;
import com.serdar.cd_randomizer_backend.dto.AlbumResponseDTO;
import com.serdar.cd_randomizer_backend.service.AlbumService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumService albumService;

    @PostMapping
    public ResponseEntity<AlbumResponseDTO> createAlbum(
            @Valid @RequestBody AlbumRequestDTO requestDTO) {

        AlbumResponseDTO created = albumService.createAlbum(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<AlbumResponseDTO>> getAllAlbums() {
        return ResponseEntity.ok(albumService.getAllAlbums());
    }

    @GetMapping("/random")
    public ResponseEntity<AlbumResponseDTO> getRandomAlbum() {
        return ResponseEntity.ok(albumService.getRandomAlbum());
    }

    @GetMapping("/search")
    public ResponseEntity<List<AlbumResponseDTO>> searchAlbums(
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String mood) {

        return ResponseEntity.ok(albumService.searchAlbums(genre, mood));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlbumResponseDTO> updateAlbum(
            @PathVariable Long id,
            @Valid @RequestBody AlbumRequestDTO requestDTO) {

        return ResponseEntity.ok(albumService.updateAlbum(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {
        albumService.deleteAlbum(id);
        return ResponseEntity.noContent().build();
    }

}
