package com.system.app.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TextRequest {
    @NotBlank(message = "El prompt no puede estar vacío")
    @Size(max = 3000, message = "El prompt es demasiado largo")
    private String prompt;

    public TextRequest() {
    }

    public TextRequest(String prompt) {
        this.prompt = prompt;
    }

}