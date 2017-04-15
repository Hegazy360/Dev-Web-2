package models;

import java.util.Date;

public class User {
	String uname, password, email;
	boolean admin;
	Date registeredon;

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegisteredon() {
		return registeredon;
	}

	public void setRegisteredon(Date registeredon) {
		this.registeredon = registeredon;
	}

}
