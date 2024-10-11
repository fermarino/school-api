package com.example.schoolapi.controller;

import com.example.schoolapi.model.Department;
import com.example.schoolapi.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        return departmentRepository.save(department);
    }

    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        return departmentRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department departmentDetails) {
        Optional<Department> existingDepartment = departmentRepository.findById(id);
        if (!existingDepartment.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Department departmentToUpdate = existingDepartment.get();
        // Atualiza os campos conforme necessário
        if (departmentDetails.getName() != null) {
            departmentToUpdate.setName(departmentDetails.getName());
        }
        if (departmentDetails.getHead() != null) {
            departmentToUpdate.setHead(departmentDetails.getHead());
        }
        if (departmentDetails.getLocation() != null) {
            departmentToUpdate.setLocation(departmentDetails.getLocation());
        }
        // Salva e retorna o departamento atualizado
        return ResponseEntity.ok(departmentRepository.save(departmentToUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        return departmentRepository.findById(id).map(department -> {
            departmentRepository.delete(department);
            return ResponseEntity.ok().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
