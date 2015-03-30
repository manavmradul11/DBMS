package edu.neu.cs5200.jdbc_assignment.model;

public class Cast {
	private String castId;
	private String characterName;
	private Movie movieId;
	private Actor actorId;
	
	
	public String getCastId() {
		return castId;
	}
	public void setCastId(String castId) {
		this.castId = castId;
	}
	public String getCharacterName() {
		return characterName;
	}
	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}
	public Movie getMovieId() {
		return movieId;
	}
	public void setMovieId(Movie movieId) {
		this.movieId = movieId;
	}
	public Actor getActorId() {
		return actorId;
	}
	public void setActorId(Actor actorId) {
		this.actorId = actorId;
	}
	public Cast(String castId, String characterName, Movie movieId, Actor actorId) {
		super();
		this.castId=castId;
		this.characterName = characterName;
		this.movieId = movieId;
		this.actorId = actorId;
	}
	public Cast() {
		super();
		
	}
	

}
