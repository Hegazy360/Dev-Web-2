package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.User;
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
			else {
				return false;
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
        		PreparedStatement ps = connection.prepareStatement("insert into users(uname, password, email, registeredon) values (?, ?, ?, ? )");
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
			PreparedStatement ps = connection.prepareStatement("select * from users where email=? and password=?");
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				System.out.println("Sign In Success");
				User user = new User();
				user.setEmail(rs.getString("email"));
				user.setUname(rs.getString("uname"));
				return user;
			}
			else {
				System.out.println("Sign In Failed");
				return null;
			}
		} catch (SQLException e) {
			// TODO: handle exception
	   		 System.out.println("Error in signIn() -->" + e.getMessage());

		}
		return null;
	}
 

}
