package edu.neu.cs5200.jdbc_assignment.model;
import java.sql.Date;

public class Comment {
	private String commentId;
	private String comment;
	private Date date;
	private User username;
	private Movie movieId;
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public User getUsername() {
		return username;
	}
	public void setUsername(User username) {
		this.username = username;
	}
	public Movie getMovieId() {
		return movieId;
	}
	public void setMovieId(Movie movieId) {
		this.movieId = movieId;
	}
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	
	public Comment(String commentId, String comment, Date date, User username, Movie movieId) {
		super();
		this.commentId = commentId;
		this.comment = comment;
		this.date = date;
		this.username = username;
		this.movieId = movieId;
	}
	public Comment() {
		super();
		
	}
	

}
