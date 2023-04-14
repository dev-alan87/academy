package io.github.devalan87.academy.domain.entity;

import io.github.devalan87.academy.api.controller.dto.CourseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "tb_course")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Course {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "fullname", nullable = false)
    private String fullname;

    @Column(name = "shortname")
    private String shortname;

    @Column(unique = true)
    private String code;

    private String description;

    private Integer hours;

    @Column(columnDefinition = "boolean default false")
    @Builder.Default
    private Boolean deprecated = false;

    @Column(columnDefinition = "boolean default false")
    @Builder.Default
    private Boolean disabled = false;

    public void map(CourseDto dto) {
        this.id = dto.getId() != null ? dto.getId() : this.id;
        this.fullname = dto.getFullname() != null ? dto.getFullname() : this.fullname;
        this.shortname = dto.getShortname() != null ? dto.getShortname() : this.shortname;
        this.code = dto.getCode() != null ? dto.getCode() : this.code;
        this.description = dto.getDescription() != null ? dto.getDescription() : this.description;
        this.hours = dto.getHours() != null ? dto.getHours() : this.getHours();
        this.deprecated = dto.getDeprecated() != null ? dto.getDeprecated() : this.deprecated;
        this.disabled = dto.getDisabled() != null ? dto.getDisabled() : this.disabled;
    }

    public CourseDto dto() {
        return new CourseDto(this.id,
                this.fullname,
                this.shortname,
                this.code,
                this.description,
                this.hours,
                this.deprecated,
                this.disabled
        );
    }

    private List<CourseDto> dtoList(List<Course> courses) {
        List<CourseDto> dtoList = new ArrayList<>();
        if(courses != null) {
            courses.forEach(
                    course -> {
                        dtoList.add(course.dto());
                    }
            );
        }
        return dtoList;
    }

    private List<Course> list(List<CourseDto> dtoList) {
        List<Course> list = new ArrayList<>();
        if(dtoList != null) {
            dtoList.forEach(
                    dto -> {
                        list.add(Course.builder()
                                .id(dto.getId())
                                .fullname(dto.getFullname())
                                .shortname(dto.getShortname())
                                .code(dto.getCode())
                                .description(dto.getDescription())
                                .hours(dto.getHours())
                                .deprecated(dto.getDeprecated())
                                .disabled(dto.getDisabled())
                                .build());
                    }
            );
        }
        return list;
    }
}
