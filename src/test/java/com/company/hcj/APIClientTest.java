package com.company.hcj;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.company.hcj.builders.CommentBuilder;
import com.company.hcj.domains.tos.Comment;
import com.company.hcj.domains.tos.ContactsResult;
import com.company.hcj.domains.tos.Post;

class APIClientTest {

	@Test
	void getListHappyDay() {
		String path = "https://jsonplaceholder.typicode.com/posts";

		List<Post> posts = APIClient.<Post>getList(path, Post[].class);

		assertNotNull(posts);
		assertEquals("sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
				posts.get(0).getTitle());
	}

	@Test
	void buildURI() throws URISyntaxException, MalformedURLException {
		String path = "https://jsonplaceholder.typicode.com/comments";

		Map<String, String> urlParameters = new HashMap<>();
		urlParameters.put("postId", "1");
		urlParameters.put("email", "email@company.com");

		assertEquals("https://jsonplaceholder.typicode.com/comments?postId=1&email=email%40company.com",
				APIClient.buildURI(path, urlParameters).toURL().toString());
	}

	@Test
	void getListWithEmptyUrlParameters() {

		String path = "https://jsonplaceholder.typicode.com/comments";

		Map<String, String> urlParameters = new HashMap<>();

		List<Comment> comments = APIClient.<Comment>getList(path, Comment[].class, urlParameters);

		assertNotNull(comments);
		assertEquals("Eliseo@gardner.biz", comments.get(0).getEmail());
	}

	@Test
	void getHappyDay() {

		String path = "https://jsonplaceholder.typicode.com/comments/1";

		Comment comment = APIClient.<Comment>get(path, Comment.class);

		assertNotNull(comment);
		assertEquals("Eliseo@gardner.biz", comment.getEmail());
	}

	@Test
	void postHappyDay() {

		String path = "https://jsonplaceholder.typicode.com/comments/";

		Comment comment = CommentBuilder.newComment().withName("Name").withPostId(1).withEmail("email@company.com")
				.withBody("body").now();

		assertTrue(APIClient.<Comment>post(path, comment));
	}

	@Test
	void apisGet() {

		String path = "https://api.hubapi.com/crm/v3/objects/contacts";

		Map<String, String> parameters = new HashMap<>();
		parameters.put("limit", "10");
		parameters.put("archived", "false");
		parameters.put("hapikey", "eu1-d2c4-4bae-46e7-88a3-ab000fe2fe32");

		ContactsResult contactsResult = APIClient.<ContactsResult>get(path, ContactsResult.class, parameters);

		assertNotNull(contactsResult);

		assertNotNull(contactsResult.getResults().get(0).getCreatedAt());

		contactsResult.getResults().forEach(System.out::println);

	}

}
