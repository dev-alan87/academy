package io.github.devalan87.academy.domain.repository;

import io.github.devalan87.academy.domain.entity.Course;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository
        extends JpaRepository<Course, Integer> {

    Optional<Course> findByCode(String code);

    List<Course> findByFullnameContainingIgnoreCase(String fullname);

    List<Course> findByShortnameContainingIgnoreCase(String shortname);

}
