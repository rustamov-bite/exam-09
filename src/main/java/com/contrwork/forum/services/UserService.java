package com.contrwork.forum.services;

import com.contrwork.forum.configs.SecurityConfig;
import com.contrwork.forum.forms.RegisterForm;
import com.contrwork.forum.models.User;
import com.contrwork.forum.repos.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public void registerUser(RegisterForm registerForm) {
        User user = User.builder()
                .email(registerForm.getEmail())
                .name(registerForm.getName())
                .password(SecurityConfig.encoder().encode(registerForm.getPassword()))
                .build();
        userRepo.save(user);
    }
}
