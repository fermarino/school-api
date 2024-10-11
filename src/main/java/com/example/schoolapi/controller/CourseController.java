package com.example.schoolapi.controller;

import com.example.schoolapi.model.Course;
import com.example.schoolapi.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        return courseRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course courseDetails) {
        return courseRepository.findById(id).map(course -> {
            if (courseDetails.getName() != null) {
                course.setName(courseDetails.getName());
            }
            if (courseDetails.getDuration() != null) {
                course.setDuration(courseDetails.getDuration());
            }
            if (courseDetails.getDescription() != null) {
                course.setDescription(courseDetails.getDescription());
            }
            return ResponseEntity.ok(courseRepository.save(course));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        return courseRepository.findById(id).map(course -> {
            courseRepository.delete(course);
            return ResponseEntity.ok().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/department/{departmentId}")
    public List<Course> getCoursesByDepartment(@PathVariable Long departmentId) {
        return courseRepository.findByDepartmentId(departmentId);
    }

    @GetMapping("/teacher/{teacherId}")
    public List<Course> getCoursesByTeacher(@PathVariable Long teacherId) {
        return courseRepository.findByTeacherId(teacherId);
    }
}
