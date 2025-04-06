package com.system.app.application.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PromptRequest {
    @NotBlank(message = "El prompt no puede estar vacío")
    private String prompt;

}