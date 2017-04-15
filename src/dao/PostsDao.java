package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import models.Post;
import models.User;
import util.Database;

public class PostsDao {
	private Connection connection;

	PostsDao() {
		connection = Database.getConnection();
	}

	public void addPost(Post post) {

	}

	public void deletePost(String postId) {

	}

	public void updatePost(Post post) {

	}

}
