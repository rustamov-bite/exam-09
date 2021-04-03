package com.contrwork.forum.forms;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ThemeForm {
    @NotBlank
    @NotNull
    private String title = "";
}
