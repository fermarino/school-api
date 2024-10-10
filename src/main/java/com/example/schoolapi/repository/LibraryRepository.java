package com.example.schoolapi.repository;

import com.example.schoolapi.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibraryRepository extends JpaRepository<Library, Long> {
    // Função extra para buscar bibliotecas por localização
    List<Library> findByLocationContaining(String location);
}
