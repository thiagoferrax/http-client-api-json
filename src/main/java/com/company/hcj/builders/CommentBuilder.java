package com.company.hcj.builders;

import com.company.hcj.domains.tos.Comment;

public class CommentBuilder {
	private Comment comment;

	private CommentBuilder() {
	}

	public static CommentBuilder newComment() {
		CommentBuilder builder = new CommentBuilder();
		builder.comment = new Comment();
		return builder;
	}

	public CommentBuilder withId(Integer id) {
		comment.setId(id);
		return this;
	}

	public CommentBuilder withPostId(Integer postId) {
		comment.setPostId(postId);
		return this;
	}

	public CommentBuilder withEmail(String email) {
		comment.setEmail(email);
		return this;
	}

	public CommentBuilder withName(String name) {
		comment.setName(name);
		return this;
	}

	public CommentBuilder withBody(String body) {
		comment.setBody(body);
		return this;
	}

	public Comment now() {
		return comment;
	}
}
