package com.contrwork.forum.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "themes")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Builder.Default
    private Long answerCount = (long) 0;

    @Builder.Default
    private LocalDate date = LocalDate.now();

    @ManyToOne
    private User user;
}
