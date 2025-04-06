package com.system.app.domain.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TextRequest {
    private String prompt;

    public TextRequest() {
    }

    public TextRequest(String prompt) {
        this.prompt = prompt;
    }

}