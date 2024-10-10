package com.example.schoolapi.repository;

import com.example.schoolapi.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    // Função extra para buscar professores por nome
    List<Teacher> findByNameContaining(String name);
}
