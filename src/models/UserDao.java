package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bcrypt.BCrypt;
import beans.User;
import util.Database;

public class UserDao {
	private Connection connection;

	public UserDao() {
		connection = Database.getConnection();
	}

	public boolean checkUser(String email) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement("Select email from users where uname = ?");
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Error in checkUser() -->" + e.getMessage());
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				ps.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				connection.close();
			} catch (Exception e) {
				/* ignored */ }
		}
		return false;
	}

	public int addUser(User user) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		if (checkUser(user.getEmail())) {
			System.out.println("User already exists");
		} else {
			try {
				ps = connection.prepareStatement("insert into users(uname, password, email,image) values (?, ?, ?,?)",
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, user.getUname());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getEmail());
				ps.setString(4, user.getImage());
				ps.executeUpdate();
				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					return rs.getInt(1);
				}
				System.out.println("User added");
			} catch (SQLException e) {
				System.out.println("Error in addUser() -->" + e.getMessage());
			} finally {
				try {
					rs.close();
				} catch (Exception e) {
					/* ignored */ }
				try {
					ps.close();
				} catch (Exception e) {
					/* ignored */ }
				try {
					connection.close();
				} catch (Exception e) {
					/* ignored */ }
			}
		}
		return 0;
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

	public boolean updateUser(User user) {
		try {
			String query = "update users set uname = \"" + user.getUname() + "\"";
			if (user.getPassword() != null) {
				query += " , password = \"" + user.getPassword() + "\"";
			}
			if (!user.getEmail().equals("")) {
				query += " , email = \"" + user.getEmail() + "\"";
			}
			if (!user.getImage().equals(".")) {
				query += ", image = \"" + user.getImage() + "\"";
			}
			query += " where id = " + user.getId();
			System.out.println(query);
			PreparedStatement ps = connection.prepareStatement(query);
			int rows = ps.executeUpdate();
			if (rows > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("false");
		return false;
	}

	public User signIn(String email, String password) {
		System.out.println("In SignIn");

		try {
			PreparedStatement ps = connection.prepareStatement("select * from users where email=?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				if (BCrypt.checkpw(password, rs.getString("password"))) {
					updateIP(email);
					System.out.println("Sign In Success");
					System.out.println("It matches");
					User user = new User();
					user.setEmail(rs.getString("email"));
					user.setUname(rs.getString("uname"));
					user.setId(rs.getInt("id"));
					user.setImage(rs.getString("image"));
					return user;
				} else
					System.out.println("It does not match");

			} else {
				System.out.println("Sign In Failed");
			}
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error in signIn() -->" + e.getMessage());

		}
		return null;
	}

	public void updateIP(String email) {

		String ip = null;
		try {
			URL whatismyip;
			whatismyip = new URL("http://checkip.amazonaws.com");
			BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
			ip = in.readLine();
			System.out.println(ip);
			System.out.println(StringIPToLong(ip));
			System.out.println(LongIPToString(StringIPToLong(ip)));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		PreparedStatement ps;
		try {
			ps = connection.prepareStatement("update users set ip = ? where email =?");
			ps.setLong(1, StringIPToLong(ip));
			ps.setString(2, email);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public User getById(int id) {
		User user = new User();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from users where id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user.setEmail(rs.getString("email"));
				user.setUname(rs.getString("uname"));
				user.setId(rs.getInt("id"));
				user.setImage(rs.getString("image"));
				user.setIp(LongIPToString(rs.getLong("ip")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	public boolean friendsCheck(int id1, int id2) {
		try {
			PreparedStatement ps = connection
					.prepareStatement("select * from friends where id_1 IN (?,?) and id_2 IN (?,?)");
			ps.setInt(1, id1);
			ps.setInt(2, id2);
			ps.setInt(3, id1);
			ps.setInt(4, id2);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
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
			if (!friendsCheck(id1, id2)) {
				PreparedStatement ps = connection.prepareStatement("insert into friends(id_1,id_2) values (?,?)");
				ps.setInt(1, id1);
				ps.setInt(2, id2);
				int rows = ps.executeUpdate();
				if (rows > 0) {
					System.out.println("friend added");
					return true;
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public List<User> getAllFriends(int id) {
		List<User> friends = new ArrayList<User>();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from friends where id_1 = ? or id_2 = ?");
			ps.setInt(1, id);
			ps.setInt(2, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User friend = new User();
				if (rs.getInt("id_1") == id) {
					friend = getById(rs.getInt("id_2"));
				} else {
					friend = getById(rs.getInt("id_1"));
				}
				friends.add(friend);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return friends;
	}

	public Long StringIPToLong(String IP) {
		String[] addressArray = IP.split("\\.");

		long result = 0;

		for (int i = 0; i < addressArray.length; i++) {
			int power = 3 - i;

			result += ((Integer.parseInt(addressArray[i]) % 256 * Math.pow(256, power)));
		}

		return result;
	}

	public String LongIPToString(long IP) {
		return ((IP >> 24) & 0xFF) + "." + ((IP >> 16) & 0xFF) + "." + ((IP >> 8) & 0xFF) + "." + (IP & 0xFF);
	}

}
