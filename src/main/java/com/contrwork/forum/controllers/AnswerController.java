package com.contrwork.forum.controllers;

import com.contrwork.forum.models.Answer;
import com.contrwork.forum.models.Theme;
import com.contrwork.forum.models.User;
import com.contrwork.forum.services.AnswerService;
import com.contrwork.forum.services.ThemeService;
import com.contrwork.forum.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/answers")
@AllArgsConstructor
public class AnswerController {
    private final AnswerService answerService;
    private final ThemeService themeService;
    private final UserService userService;

    @PostMapping("/add")
    public Answer saveAnswer(@RequestParam Long themeId, String message, Principal principal) {
        Optional<User> user = userService.getUserByEmail(principal.getName());
        Optional<Theme> theme = themeService.getThemeById(themeId);
        Answer answer = Answer.builder()
                .message(message)
                .theme(theme.get())
                .user(user.get())
                .build();
        answerService.saveAnswer(answer);

        theme.get().setAnswerCount(theme.get().getAnswerCount() + 1);
        themeService.saveTheme(theme.get());
        return answer;
    }
}
