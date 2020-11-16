package com.example.FirstApp.ApiTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class RequestWithHttpClient {

    @Test
    public void getResponse() throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/1"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());

        JsonParser jsonParser = new JsonParser();
        JsonElement responseBody = jsonParser.parse(response.body());

        JsonObject bodyStartObject = responseBody.getAsJsonObject();

        System.out.println(bodyStartObject.get("address").getAsJsonObject().get("street"));

    }

    @Test
    public void postRequest() throws IOException, InterruptedException {

        var values = new HashMap<String, String>() {{
            put("name", "John Doe");
            put ("occupation", "gardener");
        }};

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writeValueAsString(values);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/post"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());

        JsonParser jsonParser = new JsonParser();

        JsonElement responseBody = jsonParser.parse(response.body());

        JsonObject bodyStartObject = responseBody.getAsJsonObject();

        System.out.println(bodyStartObject.get("json").getAsJsonObject().get("name"));

    }


}
