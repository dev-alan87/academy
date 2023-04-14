package io.github.devalan87.academy.api.controller.dto;

import io.github.devalan87.academy.domain.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class CourseDto {
    private Integer id;
    private String fullname;
    private String shortname;
    private String code;
    private String description;
    private Integer hours;
    private Boolean deprecated;
    private Boolean disabled;
}
