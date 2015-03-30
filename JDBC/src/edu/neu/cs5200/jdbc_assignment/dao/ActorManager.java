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


public class ActorManager {

	DataSource ds;
	
	public ActorManager()
	{
	  try {
		Context ctx = new InitialContext();
		ds = (DataSource)ctx.lookup("java:comp/env/jdbc/JDBCASSIGNMENT");
		System.out.println(ds);
	  } catch (NamingException e) {
		e.printStackTrace();
	  }
	}
	
	
	public Actor createActor(Actor actor)
	{
		String sql = "insert into actor values (null,?,?,?)";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, actor.getFirstName());
			statement.setString(2, actor.getLastName());
			statement.setDate(3, actor.getDateOfBirth());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Actor> readAllActors()
	{
		List<Actor> actorList = new ArrayList<Actor>();
		String sql = "select * from actor";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				Actor actor = new Actor();
				actor.setId((results.getString("id")));
				actor.setFirstName((results.getString("firstName")));
				actor.setLastName((results.getString("lastName")));
				actor.setDateOfBirth((results.getDate("dateOfBirth")));
				
				actorList.add(actor);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return actorList;
	}
	
	
	public Actor readActor(String id)
	{
		Actor actor = new Actor();
		
		String sql = "select * from actor where id = ?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet results = statement.executeQuery();
			if(results.next())
			{
				actor.setId((results.getString("id")));
				actor.setFirstName((results.getString("firstName")));
				actor.setLastName((results.getString("lastName")));
				actor.setDateOfBirth((results.getDate("dateOfBirth")));
				
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}		
		return actor;
	}
	
	
	public Actor updateActor(String id, Actor actor)
	{
		String sql = "update actor set firstName=?, lastName=?, dateOfBirth=? where id=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, actor.getFirstName());
			statement.setString(2, actor.getLastName());
			statement.setDate(3, actor.getDateOfBirth());
			statement.setString(4, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}		
		return actor;
	}
	
	public int deleteActor(String id)
	{
		String sql = "delete from actor where id=?";
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