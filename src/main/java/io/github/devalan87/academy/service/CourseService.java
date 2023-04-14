package io.github.devalan87.academy.service;

import io.github.devalan87.academy.api.controller.dto.CourseDto;
import io.github.devalan87.academy.domain.entity.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
    Course saveCourse(CourseDto dto);
    Course saveCourse(CourseDto dto, Integer id);
    void deleteCourse(Integer id);
    Course getCourse(Integer id);
    Course getCourseByCode(String code);
    List<Course> listCourses();
    List<Course> listCoursesByFullname(String fullName);
    List<Course> listCoursesByShortname(String shortName);
}