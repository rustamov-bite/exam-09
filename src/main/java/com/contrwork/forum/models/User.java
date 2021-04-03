package com.contrwork.forum.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    @ToString.Exclude
    @OneToMany
    private List<Theme> themes;

    @Builder.Default
    private boolean enabled = true;

    @Builder.Default
    private String role = "USER";
}
