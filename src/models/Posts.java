package models;

import java.util.Date;

public class Posts {
	User author;
	String title, content;
	Date datePost, dateLastModify;

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDatePost() {
		return datePost;
	}

	public void setDatePost(Date datePost) {
		this.datePost = datePost;
	}

	public Date getDateLastModify() {
		return dateLastModify;
	}

	public void setDateLastModify(Date dateLastModify) {
		this.dateLastModify = dateLastModify;
	}

}
