package com.example.schoolapi.repository;

import com.example.schoolapi.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    // Função extra para buscar matrículas por estudante
    List<Enrollment> findByStudentId(Long studentId);
}
