package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bcrypt.BCrypt;
import beans.User;
import util.Database;

public class UserDao {
    private Connection connection;
    
    public UserDao() {
        connection = Database.getConnection();
    }
 
    public boolean checkUser(String email) {
    	try {
			PreparedStatement ps = connection.prepareStatement("Select email from users where uname = ?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
		 System.out.println("Error in checkUser() -->" + e.getMessage());
		}
		return false;
    }
    public void addUser(User user) {
    	if(checkUser(user.getEmail())){
    		System.out.println("User already exists");
    	}
    	else {
        	try {
        		PreparedStatement ps = connection.prepareStatement("insert into users(uname, password, email) values (?, ?, ?)");
        		ps.setString(1, user.getUname());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getEmail());            
                ps.executeUpdate();
                System.out.println("User added");
        	} catch (SQLException e) {
       		 System.out.println("Error in addUser() -->" + e.getMessage());
        	}
    	}
    }
 
    public void deleteUser(String email) {
    	try {
			PreparedStatement ps = connection.prepareStatement("delete from users where email=?");
			ps.setString(1, email);
			ps.executeUpdate();
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
	   		 System.out.println("Error in deleteUser() -->" + e.getMessage());
		}
    }
 
    public void updateUser(User user) {
    	
    }

	public User signIn(String email, String password) {
		System.out.println("In SignIn");
		
		try {
			PreparedStatement ps = connection.prepareStatement("select * from users where email=?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				if (BCrypt.checkpw(password, rs.getString("password"))){
					System.out.println("Sign In Success");
					System.out.println("It matches");
					User user = new User();
					user.setEmail(rs.getString("email"));
					user.setUname(rs.getString("uname"));
					user.setId(rs.getInt("id"));
					return user;
				}
				else
					System.out.println("It does not match");
				
			}
			else {
				System.out.println("Sign In Failed");
			}
		} catch (SQLException e) {
			// TODO: handle exception
	   		 System.out.println("Error in signIn() -->" + e.getMessage());

		}
		return null;
	}
	
	public User getById(int id){
		User user = new User();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from users where id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				user.setEmail(rs.getString("email"));
				user.setUname(rs.getString("uname"));
				user.setId(rs.getInt("id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	public boolean friendsCheck(int id1,int id2){
		try {
			PreparedStatement ps = connection.prepareStatement("select * from friends where id_1 IN (?,?) and id_2 IN (?,?)");
			ps.setInt(1, id1);
			ps.setInt(2, id2);
			ps.setInt(3, id1);
			ps.setInt(4, id2);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				System.out.println("Friends");
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("not friends");
		return false;
	}
	
	public boolean addFriend(int id1, int id2) {
		try {
			if(!friendsCheck(id1,id2)){
				PreparedStatement ps = connection.prepareStatement("insert into friends(id_1,id_2) values (?,?)");
				ps.setInt(1, id1);
				ps.setInt(2, id2);
                int rows = ps.executeUpdate();
                if(rows > 0){
                	System.out.println("friend added");
                	return true;
                }
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
 

}