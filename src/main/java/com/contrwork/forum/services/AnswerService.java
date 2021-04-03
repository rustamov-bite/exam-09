package com.contrwork.forum.services;

import com.contrwork.forum.repos.AnswerRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AnswerService {
    private final AnswerRepo answerRepo;
}
