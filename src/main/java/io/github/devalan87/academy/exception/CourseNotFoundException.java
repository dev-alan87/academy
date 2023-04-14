package io.github.devalan87.academy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CourseNotFoundException
        extends ResponseStatusException {

    public CourseNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Course not found.");
    }

    public CourseNotFoundException(String msg) {
        super(HttpStatus.NOT_FOUND, "Course not found.\n"+msg);
    }

}
