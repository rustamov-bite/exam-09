package com.contrwork.forum.services;

import com.contrwork.forum.models.Theme;
import com.contrwork.forum.models.User;
import com.contrwork.forum.repos.ThemeRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ThemeService {
    private final ThemeRepo themeRepo;

    public Optional<Theme> getThemeById(Long id) {
        return themeRepo.findById(id);
    }

    public Page<Theme> getThemes(Pageable pageable) {
        return themeRepo.findAll(pageable);
    }

    public Theme saveTheme(User user, String title) {
        Theme theme = Theme.builder()
                .title(title)
                .user(user)
                .build();
        return themeRepo.save(theme);
    }

    public void saveTheme(Theme theme) {
        themeRepo.save(theme);
    }
}
