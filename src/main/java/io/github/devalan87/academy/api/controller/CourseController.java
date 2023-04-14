package io.github.devalan87.academy.api.controller;

import io.github.devalan87.academy.api.controller.dto.CourseDto;
import io.github.devalan87.academy.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController @RequestMapping("/api/course/")
public class CourseController {

    @Autowired
    private CourseService service;

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public CourseDto addCourse(@RequestBody CourseDto course) {
        return service.saveCourse(course).dto();
    }

    @PutMapping("/{id}") @ResponseStatus(HttpStatus.OK)
    public CourseDto editCourse(@PathVariable Integer id, @RequestBody CourseDto course) {
        return service.saveCourse(course, id).dto();
    }

    @PutMapping("/deprecate/{id}") @ResponseStatus(HttpStatus.OK)
    public CourseDto deprecateCourse(@PathVariable Integer id) {
        CourseDto dto = service.getCourse(id).dto();
        dto.setDeprecated(true);
        return service.saveCourse(dto, id).dto();
    }

    @PutMapping("/accede/{id}") @ResponseStatus(HttpStatus.OK)
    public CourseDto accedeCourse(@PathVariable Integer id) {
        CourseDto dto = service.getCourse(id).dto();
        dto.setDeprecated(false);
        return service.saveCourse(dto, id).dto();
    }

    @PutMapping("/disable/{id}") @ResponseStatus(HttpStatus.OK)
    public CourseDto disableCourse(@PathVariable Integer id) {
        CourseDto dto = service.getCourse(id).dto();
        dto.setDisabled(true);
        return service.saveCourse(dto, id).dto();
    }

    @PutMapping("/enable/{id}") @ResponseStatus(HttpStatus.OK)
    public CourseDto enableCourse(@PathVariable Integer id) {
        CourseDto dto = service.getCourse(id).dto();
        dto.setDisabled(false);
        return service.saveCourse(dto, id).dto();
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.OK)
    public void deleteCourse(@PathVariable Integer id) {
        service.deleteCourse(id);
    }

    @GetMapping @ResponseStatus(HttpStatus.FOUND)
    public List<CourseDto> listCourses() {
        List<CourseDto> list = new ArrayList<>();
        service.listCourses().forEach(
                course -> {
                    list.add(course.dto());
                }
        );
        return list;
    }

    @GetMapping("/{id}") @ResponseStatus(HttpStatus.FOUND)
    public CourseDto getCourse(@PathVariable Integer id) {
        return service.getCourse(id).dto();
    }

    @GetMapping("/code/{code}") @ResponseStatus(HttpStatus.FOUND)
    public CourseDto getCourseByCode(@PathVariable String code) {
        return service.getCourseByCode(code).dto();
    }

    @GetMapping("/fullname/{fullname}") @ResponseStatus(HttpStatus.FOUND)
    public List<CourseDto> listByFullname(@PathVariable(name = "fullname") String fullName) {
        List<CourseDto> list = new ArrayList<>();
        service.listCoursesByFullname(fullName).forEach(
                course -> {
                    list.add(course.dto());
                }
        );
        return list;
    }

    @GetMapping("/shortname/{shortname}") @ResponseStatus(HttpStatus.FOUND)
    public List<CourseDto> listByShortname(@PathVariable(name = "shortname") String shortName) {
        List<CourseDto> list = new ArrayList<>();
        service.listCoursesByShortname(shortName).forEach(
                course -> {
                    list.add(course.dto());
                }
        );
        return list;
    }

}
