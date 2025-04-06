package com.system.app.presentation.controllers;

import com.system.app.application.dtos.PromptRequest;
import com.system.app.application.usecases.GenerateTextUseCase;
import com.system.app.domain.model.TextRequest;
import com.system.app.domain.model.TextResponse;
import com.system.app.infrastructure.api.GeminiApiService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gemini")
public class GeminiController {
    private final GenerateTextUseCase generateTextUseCase;
    private final GeminiApiService geminiApiService;

    public GeminiController(GenerateTextUseCase generateTextUseCase, GeminiApiService geminiApiService) {
        this.generateTextUseCase = generateTextUseCase;
        this.geminiApiService = geminiApiService;
    }

    @PostMapping("/generate")
    public TextResponse generateText(@RequestBody TextRequest request) throws Exception {
        String response = generateTextUseCase.execute(request.getPrompt());
        return new TextResponse(response);
    }
    @PostMapping("/generate-image")
    public String generateImage(@RequestBody PromptRequest request) {
        try {
            return geminiApiService.generateImage(request.getPrompt());
        } catch (Exception e) {
            return "Error generando la imagen: " + e.getMessage();
        }
    }
}