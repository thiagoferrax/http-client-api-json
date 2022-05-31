package com.company.hcj;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

public class APIClient {

	public <T> List<T> get(String path, Class<T[]> clazz) {
		List<T> objects = null;

		try {
			URI uri = URI.create(path);
			HttpRequest request = HttpRequest.newBuilder().GET().header("accept", "application/json").uri(uri).build();

			HttpClient client = HttpClient.newHttpClient();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

			int statusCode = response.statusCode();

			if (statusCode >= 200 && statusCode <= 299) {
				objects = Arrays.asList(new Gson().fromJson(response.body(), clazz));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objects;
	}

}
