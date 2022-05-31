package com.company.hcj;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.company.hcj.domains.tos.Post;

class APIClientTest {

	@Test
	void get() {
		APIClient client = new APIClient();
		
		List<Post> posts = client.<Post>get("https://jsonplaceholder.typicode.com/posts");
			
		assertNotNull(posts);
	}

}
