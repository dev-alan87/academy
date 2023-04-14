package io.github.devalan87.academy.api.controller;

import io.github.devalan87.academy.api.controller.dto.CredentialsDto;
import io.github.devalan87.academy.api.controller.dto.TokenDto;
import io.github.devalan87.academy.api.controller.dto.UserDto;
import io.github.devalan87.academy.domain.entity.User;
import io.github.devalan87.academy.exception.InvalidPasswordException;
import io.github.devalan87.academy.exception.UserAlreadyRegistered;
import io.github.devalan87.academy.security.jwt.service.JwtService;
import io.github.devalan87.academy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping(value = "/sign-up", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto signUp(@RequestBody User user) {
        try {
            return userService.saveUser(user).dto();
        } catch (Exception ex) {
            throw new UserAlreadyRegistered();
        }
    }

    @PostMapping(value = "/sign-in", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public TokenDto signIn(@RequestBody CredentialsDto credentials) {
        User requester = User.builder()
                .username(credentials.getLogin())
                .password(credentials.getPassword())
                .build();
        try {
            UserDetails authenticated = userService.authenticate(requester);
            String token = jwtService.generateToken(authenticated.getUsername());
            return new TokenDto(authenticated.getUsername(), token);
        } catch (Exception ex) {
            throw new InvalidPasswordException();
        }
    }

}
