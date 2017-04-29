package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Group;
import util.Database;

public class GroupsDao {
	private Connection connection;
	UserDao userDao;
	public GroupsDao(){
		connection = Database.getConnection();
		userDao = new UserDao();
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
				group.setId(rs.getInt("id"));
                group.setTitle(rs.getString("title"));
                group.setDescription(rs.getString("description"));
                group.setCreator_id(rs.getInt("creator_id"));
				group.setCreatorName(userDao.getById(rs.getInt("creator_id")).getUname());
                groups.add(group);
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return groups;
	}

	public Group getGroupById(int groupId) {
		// TODO Auto-generated method stub
		Group group = new Group();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from groups where id = "+groupId);
			while (rs.next()) {
				group.setId(rs.getInt("id"));
	            group.setTitle(rs.getString("title"));
	            group.setDescription(rs.getString("description"));
	            group.setCreator_id(rs.getInt("creator_id"));
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return group;

	}
}
