package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import models.Posts;
import models.User;
import util.Database;

public class PostsDao {
	private Connection connection;

	PostsDao() {
		connection = Database.getConnection();
	}

	public void addPost(Posts post) {

	}

	public void deletePost(String postId) {

	}

	public void updatePost(Posts post) {

	}

}
