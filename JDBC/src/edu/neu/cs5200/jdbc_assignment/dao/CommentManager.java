package edu.neu.cs5200.jdbc_assignment.dao;
import edu.neu.cs5200.jdbc_assignment.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class CommentManager {

	DataSource ds;
	
	public CommentManager()
	{
	  try {
		Context ctx = new InitialContext();
		ds = (DataSource)ctx.lookup("java:comp/env/jdbc/JDBCASSIGNMENT");
		System.out.println(ds);
	  } catch (NamingException e) {
		e.printStackTrace();
	  }
	}
	
	
	public Comment createComment(Comment comment)
	{
		String sql = "insert into comment values (null,?,?,?,?)";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, comment.getComment());
			statement.setDate(2, comment.getDate());
			statement.setString(3, comment.getUsername().getUsername());
			statement.setString(4, comment.getMovieId().getId());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Comment> readAllComments()
	{
		List<Comment> commentList = new ArrayList<Comment>();
		String sql = "select * from comment";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Comment comment = new Comment();
				Movie movie = new Movie();
				User user = new User();
				comment.setCommentId(results.getString("commentId"));
				comment.setComment((results.getString("comment")));
				comment.setDate((results.getDate("date")));
				user.setUsername((results.getString("username")));
				movie.setId((results.getString("movieId")));
				
				comment.setUsername(user);
				comment.setMovieId(movie);
				
				commentList.add(comment);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return commentList;
	}
	
	public List<Comment> readAllCommentsForUsername(String username)
	{
		List<Comment> commentList = new ArrayList<Comment>();
		String sql = "select * from comment where username = ?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Comment comment = new Comment();
				Movie movie = new Movie();
				User user = new User();
				comment.setCommentId(results.getString("commentId"));
				comment.setComment((results.getString("comment")));
				comment.setDate((results.getDate("date")));
				user.setUsername((results.getString("username")));
				movie.setId((results.getString("movieId")));
				
				comment.setUsername(user);
				comment.setMovieId(movie);
				
				commentList.add(comment);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return commentList;
	}
	
	public List<Comment> readAllCommentsForMovie(String movieId)
	{
		List<Comment> commentList = new ArrayList<Comment>();
		String sql = "select * from comment where movieId = ?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, movieId);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Comment comment = new Comment();
				Movie movie = new Movie();
				User user = new User();
				comment.setCommentId(results.getString("commentId"));
				comment.setComment((results.getString("comment")));
				comment.setDate((results.getDate("date")));
				user.setUsername((results.getString("username")));
				movie.setId((results.getString("movieId")));
				
				comment.setUsername(user);
				comment.setMovieId(movie);
				
				commentList.add(comment);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return commentList;
	}
	
	
	public Comment readCommentForId(String commentId)
	{
		Comment comment = new Comment();
		Movie movie = new Movie();
		User user = new User();
		String sql = "select * from comment where commentId = ?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, commentId);
			ResultSet results = statement.executeQuery();
			if(results.next())
			{
				
				
				comment.setCommentId(results.getString("commentId"));
				comment.setComment((results.getString("comment")));
				comment.setDate((results.getDate("date")));
				user.setUsername((results.getString("username")));
				movie.setId((results.getString("movieId")));
				
				comment.setUsername(user);
				comment.setMovieId(movie);
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}		
		return comment;
	}
	
	
	public Comment updateComment(String commentId, Comment comment)
	{
		String sql = "update comment set comment=?, date=?, username=?, movieId=? where commentId=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, comment.getComment());
			statement.setDate(2, comment.getDate());
			statement.setString(3, comment.getUsername().getUsername());
			statement.setString(4, comment.getMovieId().getId());
			statement.setString(5, commentId);
			
			statement.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}		
		return comment;
	}
	
	public int deleteComment(String commentId)
	{
		String sql = "delete from comment where id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, commentId);
			return statement.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return 0;
	}
}