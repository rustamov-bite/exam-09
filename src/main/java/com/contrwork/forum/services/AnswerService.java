package com.contrwork.forum.services;

import com.contrwork.forum.models.Answer;
import com.contrwork.forum.models.Theme;
import com.contrwork.forum.repos.AnswerRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AnswerService {
    private final AnswerRepo answerRepo;

    public Page<Answer> getAnswersByTheme(Theme theme, Pageable pageable) {
        return answerRepo.findAnswersByTheme(theme, pageable);
    }

    public List<Answer> getAnswerByTheme(Theme theme) {
        return answerRepo.findAnswersByTheme(theme);
    }

    public Answer saveAnswer(Answer answer) {
        return answerRepo.save(answer);
    }
}
