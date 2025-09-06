package com.example.elearning_platform.repository;

import com.example.elearning_platform.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByStatus(String status);
}
