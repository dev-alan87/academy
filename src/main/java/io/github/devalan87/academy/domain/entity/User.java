package io.github.devalan87.academy.domain.entity;

import io.github.devalan87.academy.api.controller.dto.UserDto;
import io.github.devalan87.academy.domain.entity.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

@Entity @Table(name = "tb_user")
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserType type;

    @Column(columnDefinition = "boolean default true")
    @Builder.Default
    private boolean enabled = true;

    public UserDto dto() {
        return UserDto.builder()
                .id(id)
                .username(username)
                .email(email)
                .type(type)
                .enabled(enabled)
                .build();
    }

    public UserDetails getDetails() {
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(username)
                .password(password)
                .roles(type.name())
                .disabled(!enabled)
                .build();
    }
}
