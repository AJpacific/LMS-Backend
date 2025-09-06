package com.example.elearning_platform.repository;

import com.example.elearning_platform.model.Enrollment;
import com.example.elearning_platform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByStudent(User student);
}
