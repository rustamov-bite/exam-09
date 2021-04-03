package com.contrwork.forum.services;

import com.contrwork.forum.repos.ThemeRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ThemeService {
    private final ThemeRepo themeRepo;
}
