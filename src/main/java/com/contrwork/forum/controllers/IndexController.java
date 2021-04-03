package com.contrwork.forum.controllers;

import com.contrwork.forum.exception.UserNotFoundException;
import com.contrwork.forum.models.Theme;
import com.contrwork.forum.models.User;
import com.contrwork.forum.services.AnswerService;
import com.contrwork.forum.services.ThemeService;
import com.contrwork.forum.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class IndexController {
    private final UserService userService;
    private final ThemeService themeService;
    private final AnswerService answerService;
    private final Integer size = 2;

    @GetMapping("/")
    public String mainPage(Model model, Principal principal,
                           @RequestParam(defaultValue = "1") Integer page) {
        if (principal == null) {
            return "redirect:/users/login";
        }
        Optional<User> user = userService.getUserByEmail(principal.getName());
        Page<Theme> themePage = themeService
                .getThemes(PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "date")));
        if (user.isEmpty()) {
            throw new UserNotFoundException("User was not found.");
        }
        model.addAttribute("user", user.get());
        model.addAttribute("themes", themePage.getContent());
        model.addAttribute("pages", themePage.getTotalPages());
        return "mainPage";
    }
}
