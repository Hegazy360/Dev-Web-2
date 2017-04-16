package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Group;
import models.Post;
import util.Database;

public class PostsDao {
	private Connection connection;

	public PostsDao() {
		connection = Database.getConnection();
	}

	public boolean createPost(Post post) {
		try {
			PreparedStatement ps = connection.prepareStatement("insert into posts(group_id,author_id,title,description,content) values(?,?,?,?,?)");
      		ps.setInt(1, post.getGroup_id());
            ps.setInt(2, post.getAuthor_id());
            ps.setString(3, post.getTitle()); 
            ps.setString(4, post.getDescription());
            ps.setString(5, post.getContent());
            int rows = ps.executeUpdate();
            if(rows > 0){
            	return true;
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("false");
		return false;		
	}

	public void deletePost(String postId) {

	}

	public void updatePost(Post post) {

	}
	
	public List<Post> getAllPosts(int id){
		List<Post> posts = new ArrayList<Post>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from posts where group_id = "+id);
			
			while (rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setGroup_id(rs.getInt("group_id"));
                post.setAuthor_id(rs.getInt("author_id"));
                post.setTitle(rs.getString("title"));
                post.setDescription(rs.getString("description"));
                post.setContent(rs.getString("content"));
                posts.add(post);
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return posts;
	}

	public Post getPostById(int groupId,int postId) {
		Post post = new Post();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from posts where id = "+postId+" and group_id = " + groupId);
			while (rs.next()) {
				post.setId(rs.getInt("id"));
				post.setGroup_id(rs.getInt("id"));
				post.setAuthor_id(rs.getInt("author_id"));
				post.setTitle(rs.getString("title"));
	            post.setDescription(rs.getString("description"));
	            post.setContent(rs.getString("content"));
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return post;
	}



}
