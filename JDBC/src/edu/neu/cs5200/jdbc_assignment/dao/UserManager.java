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


public class UserManager {

	DataSource ds;
	
	public UserManager()
	{
	  try {
		Context ctx = new InitialContext();
		ds = (DataSource)ctx.lookup("java:comp/env/jdbc/JDBCASSIGNMENT");
		System.out.println(ds);
	  } catch (NamingException e) {
		e.printStackTrace();
	  }
	}
	
	
	public User createUser(User user)
	{
		String sql = "insert into user values (?,?,?,?,?,?)";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFirstName());
			statement.setString(4, user.getLastName());
			statement.setString(5, user.getEmail());
			statement.setDate(6, user.getDateOfBirth());
			statement.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	public List<User> readAllUsers()
	{
		List<User> userList = new ArrayList<User>();
		String sql = "select * from user";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				User user = new User();
				user.setUsername(results.getString("username"));
				user.setPassword(results.getString("password"));
				user.setFirstName(results.getString("firstName"));
				user.setLastName(results.getString("lastName"));
				user.setEmail(results.getString("email"));
				user.setDateOfBirth(results.getDate("dateOfBirth"));
				userList.add(user);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return userList;
	}
	
	
	public User readUser(String username)
	{
		User user = new User();
		
		String sql = "select * from user where username = ?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();
			if(result.next())
			{
				user.setUsername(result.getString("username"));
				user.setPassword(result.getString("password"));
				user.setEmail(result.getString("email"));
				user.setLastName(result.getString("lastName"));
				user.setFirstName(result.getString("firstName"));
				user.setDateOfBirth(result.getDate("dateOfBirth"));
				
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}		
		return user;
	}
	
	
	public User updateUser(String username, User user)
	{
		String sql = "update user set username=?, password=?, firstName=?, lastName=?, email=?, dateOfBirth=? where username=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFirstName());
			statement.setString(4, user.getLastName());
			statement.setString(5, user.getEmail());
			statement.setDate(6, user.getDateOfBirth());
			statement.setString(7, username);
			statement.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}		
		return user;
	}
	
	public int deleteUser(String username)
	{
		String sql = "delete from user where username=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			return statement.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return 0;
	}
}