package com.contrwork.forum.controllers;

import com.contrwork.forum.exception.UserNotFoundException;
import com.contrwork.forum.forms.ThemeForm;
import com.contrwork.forum.models.Answer;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/themes")
@AllArgsConstructor
public class ThemeController {
    private final ThemeService themeService;
    private final UserService userService;
    private final AnswerService answerService;

    private final Integer size = 2;

    @GetMapping("/create")
    public String createTheme(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/users/login";
        }
        Optional<User> user = userService.getUserByEmail(principal.getName());
        if (user.isEmpty()) {
            throw new UserNotFoundException("User was not found.");
        }
        model.addAttribute("user", user.get());
        if (!model.containsAttribute("themeForm")) {
            model.addAttribute("themeForm", new ThemeForm());
        }
        return "createTheme";
    }

    @PostMapping("/create")
    public String saveTheme(Principal principal, @RequestParam String title) {
        if (principal == null) {
            return "redirect:/users/login";
        }
        Optional<User> user = userService.getUserByEmail(principal.getName());
        if (user.isEmpty()) {
            throw new UserNotFoundException("User was not found.");
        }
        Theme theme = themeService.saveTheme(user.get(), title);
        user.get().getThemes().add(theme);
        return "redirect:/";
    }

    @GetMapping("/precise")
    public String preciseTheme(Model model, Principal principal, @RequestParam Long themeId,
                               @RequestParam(defaultValue = "1") Integer page) {
        Optional<Theme> theme = themeService.getThemeById(themeId);
        Optional<User> user = userService.getUserByEmail(principal.getName());
        if (theme.isEmpty() || user.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (theme.get().getAnswerCount() > 0) {
            Page<Answer> answers = answerService.getAnswersByTheme(theme.get(),
                    PageRequest.of(page - 1, size, Sort.by(Sort.Direction.ASC, "answerDate")));
            model.addAttribute("answers", answers.getContent());
            model.addAttribute("pages", answers.getTotalPages());
        } else {
            model.addAttribute("answers", new ArrayList<>());
            model.addAttribute("pages", 1);
        }
        model.addAttribute("theme", theme.get());
        model.addAttribute("user", user.get());
        model.addAttribute("currentPage", page);
        return "preciseTheme";
    }
}
