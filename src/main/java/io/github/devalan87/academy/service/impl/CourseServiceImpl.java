package io.github.devalan87.academy.service.impl;

import io.github.devalan87.academy.api.controller.dto.CourseDto;
import io.github.devalan87.academy.domain.entity.Course;
import io.github.devalan87.academy.domain.repository.CourseRepository;
import io.github.devalan87.academy.exception.CourseNotFoundException;
import io.github.devalan87.academy.service.CourseService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl
        implements CourseService {

    @Autowired
    CourseRepository repository;

    @Override
    @Transactional
    public Course saveCourse(CourseDto dto) {
        return saveCourse(dto, null);
    }

    @Override
    @Transactional
    public Course saveCourse(CourseDto dto, Integer id) {
        Course course = new Course();
        if(id != null) {
            course = getCourse(id);
            dto.setId(course.getId());
        }
        course.map(dto);

        return repository.save(course);
    }

    @Override
    @Transactional
    public void deleteCourse(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Course getCourse(Integer id) {
        return repository.findById(id)
                .orElseThrow(CourseNotFoundException::new);
    }

    @Override
    public Course getCourseByCode(String code) {
        return repository.findByCode(code)
                .orElseThrow(CourseNotFoundException::new);
    }

    @Override
    public List<Course> listCourses() {
        return repository.findAll();
    }

    @Override
    public List<Course> listCoursesByFullname(String fullName) {
        return repository.findByFullnameContainingIgnoreCase(fullName);
    }

    @Override
    public List<Course> listCoursesByShortname(String shortName) {
        return repository.findByShortnameContainingIgnoreCase(shortName);
    }
}
