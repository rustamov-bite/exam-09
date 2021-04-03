package com.contrwork.forum.configs;

import com.contrwork.forum.models.Answer;
import com.contrwork.forum.models.Theme;
import com.contrwork.forum.models.User;
import com.contrwork.forum.repos.AnswerRepo;
import com.contrwork.forum.repos.ThemeRepo;
import com.contrwork.forum.repos.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class LoadDB {
    private final UserRepo userRepo;
    private final ThemeRepo themeRepo;
    private final AnswerRepo answerRepo;

    @Bean
    public CommandLineRunner initDB() {
        return (args) -> {
            answerRepo.deleteAll();
            themeRepo.deleteAll();
            userRepo.deleteAll();
            for (int i = 0; i < 20; i++) {
                User user = User.builder()
                        .name("Person" + i)
                        .email("person" + i +"@mail.ru")
                        .password(SecurityConfig.encoder().encode(String.valueOf((i + i + i + i))))
                        .build();
                userRepo.save(user);
                Theme theme = Theme.builder()
                        .user(user)
                        .title("Template " + i)
                        .build();
                themeRepo.save(theme);
                for (int j = 0; j < 5; j++) {
                    Answer answer = Answer.builder()
                            .user(user)
                            .theme(theme)
                            .message("Answer " + i)
                            .build();
                    answerRepo.save(answer);
                    theme.setAnswerCount(theme.getAnswerCount() + 1);
                    themeRepo.save(theme);
                }
            }
        };
    }
}
