package com.example.schoolapi.controller;

import com.example.schoolapi.model.Enrollment;
import com.example.schoolapi.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    @PostMapping
    public Enrollment createEnrollment(@RequestBody Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable Long id) {
        return enrollmentRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enrollment> updateEnrollment(@PathVariable Long id, @RequestBody Enrollment enrollmentDetails) {
        return enrollmentRepository.findById(id).map(enrollment -> {
            if (enrollmentDetails.getStatus() != null) {
                enrollment.setStatus(enrollmentDetails.getStatus());
            }
            if (enrollmentDetails.getSemester() != null) {
                enrollment.setSemester(enrollmentDetails.getSemester());
            }
            return ResponseEntity.ok(enrollmentRepository.save(enrollment));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id) {
        return enrollmentRepository.findById(id).map(enrollment -> {
            enrollmentRepository.delete(enrollment);
            return ResponseEntity.ok().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/student/{studentId}")
    public List<Enrollment> getEnrollmentsByStudent(@PathVariable Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    @GetMapping("/course/{courseId}")
    public List<Enrollment> getEnrollmentsByCourse(@PathVariable Long courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }
}
