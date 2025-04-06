package com.system.app.application.usecases;

import com.system.app.infrastructure.api.GeminiApiService;
import org.springframework.stereotype.Service;

@Service
public class GenerateTextUseCase {

    private final GeminiApiService geminiApiService;

    public GenerateTextUseCase(GeminiApiService geminiApiService) {
        this.geminiApiService = geminiApiService;
    }

    public String execute(String prompt) throws Exception {
        return geminiApiService.generateText(prompt);
    }
}