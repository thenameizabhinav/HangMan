package com.josh.hangman;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class FetchWordFromAPI{
    private static HttpResponse response;
    private static String[] words = new String[10];
    public static String fetchWord() {
        HttpRequest request = HttpRequest.newBuilder().GET()
                .uri(URI.create("https://random-word-api.herokuapp.com/word?number=10")).build();
        HttpClient client= HttpClient.newBuilder().build();
        try {
            response =client.send(request, HttpResponse.BodyHandlers.ofString());

        }
        catch (IOException| InterruptedException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            words = objectMapper.readValue(response.body().toString(), String[].class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return words[(int)(Math.random() * words.length)];
    }

}
