package com.example.schoolapi.repository;

import com.example.schoolapi.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByDepartmentId(Long departmentId);
    List<Course> findByTeacherId(Long teacherId);
}
