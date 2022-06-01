package com.company.hcj;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.http.client.utils.URIBuilder;

import com.company.hcj.exceptions.APIClientException;
import com.google.gson.Gson;

public class APIClient {

	public static <T> List<T> getList(String path, Class<T[]> clazz) {
		return getList(path, clazz, null);
	}

	public static <T> List<T> getList(String path, Class<T[]> clazz, Map<String, String> urlParameters) {
		List<T> objects = null;

		try {
			HttpResponse<String> response = sendHttpGetRequest(buildURI(path, urlParameters));

			int statusCode = response.statusCode();
			if (statusCode >= 200 && statusCode <= 299) {
				objects = Arrays.asList(new Gson().fromJson(response.body(), clazz));
			}
		} catch (Exception e) {
			throw new APIClientException(e);
		}

		return objects;
	}

	public static <T> T get(String path, Class<T> clazz) {
		return get(path, clazz, null);
	}

	public static <T> T get(String path, Class<T> clazz, Map<String, String> urlParameters) {
		T object = null;

		try {
			HttpResponse<String> response = sendHttpGetRequest(buildURI(path, urlParameters));

			int statusCode = response.statusCode();
			if (statusCode >= 200 && statusCode <= 299) {
				object = new Gson().fromJson(response.body(), clazz);
			}
		} catch (Exception e) {
			throw new APIClientException(e);
		}

		return object;
	}

	private static HttpResponse<String> sendHttpGetRequest(URI uri) throws IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder().GET().header("accept", "application/json").uri(uri).build();
		return HttpClient.newHttpClient().send(request, BodyHandlers.ofString());
	}

	public static URI buildURI(String path, Map<String, String> urlParameters) throws URISyntaxException {
		URIBuilder builder = new URIBuilder(path);

		if (urlParameters != null) {
			urlParameters.forEach((key, value) -> builder.setParameter(key, value));
		}

		return builder.build();
	}

	public static <T> boolean post(String path, T object) {

		try {
			BodyPublisher bodyPublisher = BodyPublishers.ofString(new Gson().toJson(object).toString());
			HttpRequest request = HttpRequest.newBuilder().POST(bodyPublisher).header("accept", "application/json")
					.uri(buildURI(path, null)).build();

			HttpResponse<String> response = HttpClient.newHttpClient().send(request, BodyHandlers.ofString());

			int statusCode = response.statusCode();
			if (statusCode >= 200 && statusCode <= 299) {
				return true;
			}
		} catch (Exception e) {
			throw new APIClientException(e);
		}

		return false;
	}

}
