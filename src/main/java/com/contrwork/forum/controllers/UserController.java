package com.contrwork.forum.controllers;

import com.contrwork.forum.exception.UserAlreadyRegisteredException;
import com.contrwork.forum.exception.UserNotFoundException;
import com.contrwork.forum.forms.LoginForm;
import com.contrwork.forum.forms.RegisterForm;
import com.contrwork.forum.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false, defaultValue = "false") Boolean error, Model model) {
        model.addAttribute("error", error);
        model.addAttribute("form", new LoginForm());
        return "login";
    }

    @GetMapping("/registration")
    public String addUser(Model model) {
        if (!model.containsAttribute("form")) {
            model.addAttribute("form", new RegisterForm());
        }
        return "register";
    }

    @PostMapping("/registration")
    public String registerUser(@Validated RegisterForm form, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasFieldErrors()) {
            throw new BindException(bindingResult);
        }
        if (userService.getUserByEmail(form.getEmail()).isPresent()) {
            throw new UserAlreadyRegisteredException();
        }
        userService.registerUser(form);
        return "redirect:/users/login";
    }

    @ExceptionHandler(UserNotFoundException.class)
    private String userNotFound(UserNotFoundException uex) {
        return uex.getMessage();
    }
}
