package com.example.schoolapi.controller;

import com.example.schoolapi.model.Library;
import com.example.schoolapi.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libraries")
public class LibraryController {

    @Autowired
    private LibraryRepository libraryRepository;

    @GetMapping
    public List<Library> getAllLibraries() {
        return libraryRepository.findAll();
    }

    @PostMapping
    public Library createLibrary(@RequestBody Library library) {
        return libraryRepository.save(library);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Library> getLibraryById(@PathVariable Long id) {
        return libraryRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Library> updateLibrary(@PathVariable Long id, @RequestBody Library libraryDetails) {
        return libraryRepository.findById(id).map(library -> {
            if (libraryDetails.getName() != null) {
                library.setName(libraryDetails.getName());
            }
            if (libraryDetails.getLocation() != null) {
                library.setLocation(libraryDetails.getLocation());
            }
            return ResponseEntity.ok(libraryRepository.save(library));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibrary(@PathVariable Long id) {
        return libraryRepository.findById(id).map(library -> {
            libraryRepository.delete(library);
            return ResponseEntity.ok().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
