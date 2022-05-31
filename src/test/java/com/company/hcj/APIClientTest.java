package com.company.hcj;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.company.hcj.domains.tos.Post;

class APIClientTest {

	@Test
	void get() {
		APIClient client = new APIClient();

		String path = "https://jsonplaceholder.typicode.com/posts";
		List<Post> posts = client.<Post>get(path, Post[].class);

		assertNotNull(posts);
		assertNotNull(posts.get(0).getTitle());
	}
}
