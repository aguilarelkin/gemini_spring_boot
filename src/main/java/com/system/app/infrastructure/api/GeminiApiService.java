package com.system.app.infrastructure.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.app.infrastructure.config.EnvConfig;
import okhttp3.*;
import org.springframework.stereotype.Service;
@Service
public class GeminiApiService {
    private final String apiKey = EnvConfig.get("API_KEY");
    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String generateText(String prompt) throws Exception {

        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + apiKey;

        String jsonRequest = "{ \"contents\": [{ \"parts\": [{ \"text\": \"" + prompt + "\" }] }] }";
        RequestBody body = RequestBody.create(jsonRequest, MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            System.out.println("API Response: " + responseBody);  // 🔍 Agregar este log

            if (!response.isSuccessful()) {
                throw new RuntimeException("Error en la API: " + responseBody);
            }


            JsonNode jsonResponse = objectMapper.readTree(responseBody);
            JsonNode firstCandidate = jsonResponse.get("candidates").get(0);
            JsonNode content = firstCandidate.get("content");
            JsonNode parts = content.get("parts");
            JsonNode textNode = parts.get(0).get("text");

            return textNode.asText();
        }
    }

    public String generateImage(String prompt) throws Exception {
         String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash-exp-image-generation:generateContent?key=" + apiKey;

         String jsonPayload = "{ \"contents\": [ { \"parts\": [ { \"text\": \"Genera una imagen de un bosque encantado.\" } ] } ] }";


         RequestBody body = RequestBody.create(jsonPayload, MediaType.get("application/json"));
         Request request = new Request.Builder()
                 .url(url)
                 .post(body)
                 .addHeader("Content-Type", "application/json")
                 .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            System.out.println("API Response: " + responseBody);  // 🔍 Agregar este log
            if (!response.isSuccessful()) {
                throw new RuntimeException("Error en la API: " + response.body().string());
            }
            // Convertimos la respuesta en JSON
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonResponse = objectMapper.readTree(responseBody);
            // Extraemos la URL de la imagen generada
            return jsonResponse.toString(); // Debes revisar qué campo contiene la imagen (URL o Base64)
        }
    }
}