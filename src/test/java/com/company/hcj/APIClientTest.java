package com.company.hcj;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.company.hcj.domains.tos.Comment;
import com.company.hcj.domains.tos.Post;

class APIClientTest {

	@Test
	void getListHappyDay() {
		APIClient client = new APIClient();

		String path = "https://jsonplaceholder.typicode.com/posts";

		List<Post> posts = client.<Post>getList(path, Post[].class);

		assertNotNull(posts);
		assertNotNull(posts.get(0).getTitle());
	}

	@Test
	void buildURI() throws URISyntaxException, MalformedURLException {
		String path = "https://jsonplaceholder.typicode.com/comments";

		Map<String, String> urlParameters = new HashMap<>();
		urlParameters.put("postId", "1");

		assertEquals("https://jsonplaceholder.typicode.com/comments?postId=1",
				APIClient.buildURI(path, urlParameters).toURL().toString());
	}

	@Test
	void getListWithEmptyUrlParameters() {

		String path = "https://jsonplaceholder.typicode.com/comments";

		Map<String, String> urlParameters = new HashMap<>();

		APIClient client = new APIClient();
		List<Comment> comments = client.<Comment>getList(path, Comment[].class, urlParameters);

		assertNotNull(comments);
		assertNotNull(comments.get(0).getEmail());
	}
	
	@Test
	void getHappyDay() {

		String path = "https://jsonplaceholder.typicode.com/comments/1";

		APIClient client = new APIClient();
		Comment comment = client.<Comment>get(path, Comment.class);

		assertNotNull(comment);
		assertNotNull(comment.getEmail());
	}
}
