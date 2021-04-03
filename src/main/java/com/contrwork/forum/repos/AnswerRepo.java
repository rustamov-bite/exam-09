package com.contrwork.forum.repos;

import com.contrwork.forum.models.Answer;
import com.contrwork.forum.models.Theme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepo extends JpaRepository<Answer, Long> {
    Page<Answer> findAnswersByTheme(Theme theme, Pageable pageable);
    List<Answer> findAnswersByTheme(Theme theme);
}
