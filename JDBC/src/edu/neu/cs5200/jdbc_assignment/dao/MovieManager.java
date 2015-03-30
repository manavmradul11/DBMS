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


public class MovieManager {

	DataSource ds;
	
	public MovieManager()
	{
	  try {
		Context ctx = new InitialContext();
		ds = (DataSource)ctx.lookup("java:comp/env/jdbc/JDBCASSIGNMENT");
		System.out.println(ds);
	  } catch (NamingException e) {
		e.printStackTrace();
	  }
	}
	
	
	public Movie createMovie(Movie movie)
	{
		String sql = "insert into movie values (null,?,?,?)";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, movie.getTitle());
			statement.setString(2, movie.getPosterImage());
			statement.setDate(3, movie.getReleaseDate());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Movie> readAllMovies()
	{
		List<Movie> movieList = new ArrayList<Movie>();
		String sql = "select * from movie";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Movie movie = new Movie();
				movie.setId((results.getString("id")));
				movie.setTitle((results.getString("title")));
				movie.setPosterImage((results.getString("posterImage")));
				movie.setReleaseDate((results.getDate("releaseDate")));
				
				movieList.add(movie);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return movieList;
	}
	
	
	public Movie readMovie(String id)
	{
		Movie movie = new Movie();
		
		String sql = "select * from movie where id = ?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet results = statement.executeQuery();
			if(results.next())
			{
				movie.setId((results.getString("id")));
				movie.setTitle((results.getString("title")));
				movie.setPosterImage((results.getString("posterImage")));
				movie.setReleaseDate((results.getDate("releaseDate")));
				
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}		
		return movie;
	}
	
	
	public Movie updateMovie(String id, Movie movie)
	{
		String sql = "update movie set title=?, posterImage=?, releaseDate=? where id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, movie.getTitle());
			statement.setString(2, movie.getPosterImage());
			statement.setDate(3, movie.getReleaseDate());
			statement.setString(4, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}		
		return movie;
	}
	
	public int deleteMovie(String id)
	{
		String sql = "delete from movie where id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, id);
			return statement.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return 0;
	}
}