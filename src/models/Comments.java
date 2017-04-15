package models;

public class Comments {
	User author;
	Posts post;
	String title,content;
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public Posts getPost() {
		return post;
	}
	public void setPost(Posts post) {
		this.post = post;
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
	
	
}
