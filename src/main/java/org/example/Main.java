package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class Call {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.punkapi.com/v2/beers"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        // Print the response body
        System.out.println(response.body());

        // Save the response body to a JSON file
        saveResponseToJsonFile(response.body(), "response.json");
    }

    private static void saveResponseToJsonFile(String responseBody, String filePath) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(responseBody);
            System.out.println("Response saved to: " + filePath);
        }
    }
}
