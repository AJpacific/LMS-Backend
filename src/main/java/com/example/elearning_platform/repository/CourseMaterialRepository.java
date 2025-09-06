package com.example.elearning_platform.repository;

import com.example.elearning_platform.model.CourseMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourseMaterialRepository extends JpaRepository<CourseMaterial, Long> {
    List<CourseMaterial> findByCourseId(Long courseId);
}
