package com.example.FirstApp.ApiTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.parser.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.time.Duration;
import java.util.HashMap;

import static java.time.temporal.ChronoUnit.SECONDS;


public class HttpURLConnectionTest {

    public String url = "http://localhost:8080";
    public String endpoint = "/users/u1";
    public String baseEndpoint = "http://localhost:8080/users/u1";
    public String baseEndpoint2 = "https://httpbin.org/post";


    @Test
    public void ValidationStartWithJsonObject() throws IOException {

        URL obj = new URL(baseEndpoint2);
        HttpURLConnection response = (HttpURLConnection) obj.openConnection();
        response.setRequestMethod("POST");
        response.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = response.getResponseCode();

        System.out.println("Status code is : " + responseCode);

        JsonParser jsonParser = new JsonParser();
        JsonElement responseBody = jsonParser.parse(new InputStreamReader((InputStream) response.getContent()));

        JsonObject bodyStartObject = responseBody.getAsJsonObject();

        System.out.println(bodyStartObject.get("headers").getAsJsonObject().get("Accept"));

    }


    @Test
    public void ValidationStartWithJsonObject2() throws IOException {

        URL obj = new URL(baseEndpoint);
        URLConnection request = obj.openConnection();
        request.setRequestProperty("User-Agent", "Mozilla/5.0");
        request.connect();

        JsonParser jsonParser = new JsonParser();
        JsonElement responseBody = jsonParser.parse(new InputStreamReader((InputStream) request.getContent()));

        JsonObject bodyStartObject = responseBody.getAsJsonObject();

        System.out.println(bodyStartObject.get("id"));

    }


    @Test
    public void sample() throws IOException, InterruptedException, JSONException, URISyntaxException {


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(baseEndpoint))
                .headers("key1", "value1", "key2", "value2")
                .timeout(Duration.of(10, SECONDS))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        JsonParser jsonParser = new JsonParser();
        JsonElement responseBody = jsonParser.parse(response.body());

        JsonObject bodyStartObject = responseBody.getAsJsonObject();

        System.out.println(bodyStartObject.get("id"));


    }


    @Test
    public void sample2() throws IOException, InterruptedException, JSONException {

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseEndpoint))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request,
                HttpResponse.BodyHandlers.ofString());

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Headers    : " + response.headers().toString());
        System.out.println("Body       : " + response.body());

        JsonParser jsonParser = new JsonParser();
        JsonElement responseBody = jsonParser.parse(response.body());

        JsonObject bodyStartObject = responseBody.getAsJsonObject();

        System.out.println(bodyStartObject.get("id"));

    }


    @Test
    public void sample_Post_Request() throws IOException, InterruptedException, JSONException {
        HttpClient httpClient = HttpClient.newHttpClient();

        var values = new HashMap<String, String>() {{
            put("name", "John Doe");
            put("occupation", "gardener");
        }};

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writeValueAsString(values);


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/post"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = httpClient.send(request,
                HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

        System.out.println(response.body());

        JsonParser jsonParser = new JsonParser();
        JsonElement responseBody = jsonParser.parse(response.body());

        JsonObject bodyStartObject = responseBody.getAsJsonObject();

        System.out.println(bodyStartObject.get("headers").getAsJsonObject().get("Content-Length").toString());

    }


    @Test
    public void sample_2() throws IOException, InterruptedException, JSONException {

        URL url = new URL("https://api.github.com/users/google");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        JsonParser jsonParser = new JsonParser();
        JsonElement responseBody = jsonParser.parse(new InputStreamReader((InputStream) con.getInputStream()));


        JsonObject bodyStartObject = responseBody.getAsJsonObject();
        System.out.println(bodyStartObject);
        System.out.println(bodyStartObject.get("avatar_url"));


    }

    @Test
    public void sample_3() throws IOException, InterruptedException, JSONException {

        URL url = new URL("https://reqres.in/api/users?page=1");
        URLConnection con = url.openConnection();
        // HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // connection.setRequestMethod("GET");

        JsonParser jsonParser = new JsonParser();
        JsonElement responseBody = jsonParser.parse(new InputStreamReader((InputStream) con.getInputStream()));


        JsonObject bodyStartObject = responseBody.getAsJsonObject();
        System.out.println(bodyStartObject);
        System.out.println(bodyStartObject.get("data").getAsJsonArray().get(0).getAsJsonObject().get("email"));

    }

    @Test
    public void sample_4() throws JSONException, URISyntaxException, IOException {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpUriRequest httpget = RequestBuilder.get()
                .setUri(new URI("https://reqres.in/api/users"))
                .addParameter("page", "1")
                .build();
        CloseableHttpResponse response = httpclient.execute(httpget);

        JsonParser jsonParser = new JsonParser();
        JsonElement responseBody = jsonParser.parse( EntityUtils.toString(response.getEntity()));


        JsonObject bodyStartObject = responseBody.getAsJsonObject();
        System.out.println(bodyStartObject);
        System.out.println(bodyStartObject.get("data").getAsJsonArray().get(0).getAsJsonObject().get("email"));

    }



}