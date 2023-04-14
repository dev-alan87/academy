package io.github.devalan87.academy.service;

import io.github.devalan87.academy.domain.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService
        extends UserDetailsService {

    User saveUser(User user);
    User findUser(String login);
    UserDetails authenticate(User user);

}
