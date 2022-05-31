package com.company.hcj;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class APIClient {

	public <T> List<T> get(String path) {
		List<T> objects = null;

		try {
			HttpRequest request = HttpRequest.newBuilder().GET().header("accept", "application/json")
					.uri(URI.create(path)).build();

			HttpClient client = HttpClient.newHttpClient();
			HttpResponse<String> reponse = client.send(request, BodyHandlers.ofString());

			int statusCode = reponse.statusCode();

			if (statusCode >= 200 && statusCode <= 299) {

				ObjectMapper mapper = new ObjectMapper();
				objects = mapper.readValue(reponse.body(), new TypeReference<List<T>>() {
				});

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objects;
	}

}
