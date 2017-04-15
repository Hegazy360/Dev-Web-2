package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import models.Group;
import util.Database;

public class GroupsDao {
	private Connection connection;

	public GroupsDao(){
		connection = Database.getConnection();
	}
	
	public boolean createGroup(Group group){
		try {
			PreparedStatement ps = connection.prepareStatement("insert into groups(title,description,creator_id) values(?,?,?)");
      		ps.setString(1, group.getTitle());
            ps.setString(2, group.getDescription());
            ps.setInt(3, group.getCreator_id());            
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
	
	public List<Group> getAllGroups(){
		List<Group> groups = new ArrayList<Group>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from groups");
			
			while (rs.next()) {
                Group group = new Group();
                group.setTitle(rs.getString("title"));
                group.setDescription(rs.getString("description"));
                group.setCreator_id(rs.getInt("creator_id"));
                groups.add(group);
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return groups;
	}
}
