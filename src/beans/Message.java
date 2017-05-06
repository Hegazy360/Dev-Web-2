package beans;

import java.util.Date;

public class Message {

	String content = "";
	String name = "";
	Date date;
	public String getContent() {
		return content;
	}

	public void setContent(String message) {
		this.content = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
