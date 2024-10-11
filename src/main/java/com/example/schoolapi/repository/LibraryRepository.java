package com.example.schoolapi.repository;

import com.example.schoolapi.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Long> {}
