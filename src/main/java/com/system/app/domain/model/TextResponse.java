package com.system.app.domain.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TextResponse {
    private String response;

    public TextResponse(String response) {
        this.response = response;
    }

}