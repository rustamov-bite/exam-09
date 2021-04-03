package com.contrwork.forum.repos;

import com.contrwork.forum.models.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepo extends JpaRepository<Theme, Long> {
}
