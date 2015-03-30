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


public class CastManager {

	DataSource ds;
	
	public CastManager()
	{
	  try {
		Context ctx = new InitialContext();
		ds = (DataSource)ctx.lookup("java:comp/env/jdbc/JDBCASSIGNMENT");
		System.out.println(ds);
	  } catch (NamingException e) {
		e.printStackTrace();
	  }
	}
	
	
	public Cast createCast(Cast cast)
	{
		String sql = "insert into cast values (null,?,?,?)";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, cast.getCharacterName());
			statement.setString(2, cast.getMovieId().getId());
			statement.setString(3, cast.getActorId().getId());
			
			
			statement.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Cast> readAllCasts()
	{
		List<Cast> castList = new ArrayList<Cast>();
		String sql = "select * from cast";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Cast cast = new Cast();
				Movie movie = new Movie();
				Actor actor = new Actor();
				cast.setCastId(results.getString("castId"));
				cast.setCharacterName(results.getString("characterName"));
				movie.setId((results.getString("movieId")));
				actor.setId((results.getString("actorId")));
				
				cast.setMovieId(movie);
				cast.setActorId(actor);
				
				
				
				castList.add(cast);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return castList;
	}
	
	public List<Cast> readAllCastsForActor(String actorId)
	{
		List<Cast> castList = new ArrayList<Cast>();
		String sql = "select * from cast where actorId = ?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, actorId);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Cast cast = new Cast();
				Movie movie = new Movie();
				Actor actor = new Actor();
				cast.setCastId(results.getString("castId"));
				cast.setCharacterName(results.getString("characterName"));
				movie.setId((results.getString("movieId")));
				actor.setId((results.getString("actorId")));
				
				cast.setMovieId(movie);
				cast.setActorId(actor);
				
				
				
				castList.add(cast);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return castList;
	}
	
	public List<Cast> readAllCastsForMovie(String movieId)
	{
		List<Cast> castList = new ArrayList<Cast>();
		String sql = "select * from cast where movieId = ?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, movieId);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Cast cast = new Cast();
				Movie movie = new Movie();
				Actor actor = new Actor();
				cast.setCastId(results.getString("castId"));
				cast.setCharacterName(results.getString("characterName"));
				movie.setId((results.getString("movieId")));
				actor.setId((results.getString("actorId")));
				
				cast.setMovieId(movie);
				cast.setActorId(actor);
				
				
				
				castList.add(cast);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return castList;
	}
	
	
	public Cast readCastForId(String castId)
	{
		Cast cast = new Cast();
		Movie movie = new Movie();
		Actor actor = new Actor();
		String sql = "select * from cast where castId = ?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, castId);
			ResultSet results = statement.executeQuery();
			if(results.next())
			{
				
				cast.setCastId(results.getString("castId"));
				cast.setCharacterName(results.getString("characterName"));
				movie.setId((results.getString("movieId")));
				actor.setId((results.getString("actorId")));
				
				cast.setMovieId(movie);
				cast.setActorId(actor);
					
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}		
		return cast;
	}
	
	
	public Cast updateCast(String castId, Cast cast)
	{
		String sql = "update cast set characterName=?, movieId=?, actorId=? where castId=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, cast.getCharacterName());
			statement.setString(2, cast.getMovieId().getId());
			statement.setString(3, cast.getActorId().getId());
			statement.setString(4, castId);
			
			statement.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}		
		return cast;
	}
	
	public int deleteCast(String castId)
	{
		String sql = "delete from cast where id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, castId);
			return statement.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return 0;
	}
}