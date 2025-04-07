package com.system.app.presentation.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.app.application.dtos.PromptRequest;
import com.system.app.application.usecases.GenerateTextUseCase;
import com.system.app.domain.model.TextRequest;
import com.system.app.domain.model.TextResponse;
import com.system.app.infrastructure.api.GeminiApiService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<TextResponse> generateText(@Valid @RequestBody TextRequest request) throws Exception {
        String prompt = sanitize(request.getPrompt());
       // System.out.println("api " + request.getPrompt());
        String response = generateTextUseCase.execute(prompt);
        return ResponseEntity.ok(new TextResponse(response));
    }

    @PostMapping("/generate-image")
    public String generateImage(@RequestBody PromptRequest request) {
        try {
            return geminiApiService.generateImage(request.getPrompt());
        } catch (Exception e) {
            return "Error generando la imagen: " + e.getMessage();
        }
    }

    private String sanitize(String input) {
        if (input == null) return "";
        return input
                .replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;")
                .replaceAll("\"", "&quot;")
                .replaceAll("'", "&#x27;");
    }
}