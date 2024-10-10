package com.example.schoolapi.repository;

import com.example.schoolapi.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    // Função extra para buscar departamentos por nome
    List<Department> findByNameContaining(String name);
}
