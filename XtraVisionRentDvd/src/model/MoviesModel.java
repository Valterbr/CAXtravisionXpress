/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.Movies;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Valter
 */
public class MoviesModel {
 
// Attributes declaration     
   
private Connection con;
private PreparedStatement stmt;
private final String URL = "jdbc:mysql://apontejaj.com:3306/Valter_2019308?useSSL=false";
private final String USER = "Valter_2019308";
private final String PASS = "2019308";
private final String DRIVER = "com.mysql.jdbc.Driver";

// End of attributes declaration 

    public MoviesModel() {
    
        
        
    }


// Getting Connection with database
    public void getConnection() {
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Database Connection error: " + e);
        }
    }
 
 // inserting movies into the  database
    public void insertMovies(Movies movies){
        String sql = "INSERT INTO movies (name, directedby, releaseDate, language, gender, subtitle, audio, descpription, image) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
           // preparing the Statement to get movies to store into Database
            stmt = con.prepareStatement(sql);
            stmt.setString(1, movies.getName());
            stmt.setString(2, movies.getDirectedby());
            stmt.setString(3, movies.getReleaseDate());             
            stmt.setString(4, movies.getLanguage());
            stmt.setString(5, movies.getGender());
            stmt.setString(6, movies.getSubtitle());
            stmt.setString(7, movies.getAudio());
            stmt.setString(8 ,movies.getDescpription());
            stmt.setBytes(9,  movies.getImage());            
            stmt.execute();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            
        }
    
    }
    
     // Deletes movies from the  database
    public void deleteMovie(Movies movies){
    String sql = "DELETE FROM movies WHERE movieId = ?";
    
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, movies.getMovieId());
            stmt.execute();
        
        
        } catch (SQLException ex) {
            ex.printStackTrace();
    }
    }
    
     // Updates movies in the database

    public void UpdateAvaibilite(Movies movies){
       
         String sql = " UPDATE movies SET isAvailable = ? WHERE name = ? ";
  
    try {
        stmt = con.prepareStatement(sql);           
            stmt.setString(1, movies.getIsAvailable());  
            stmt.setString(2, movies.getName());
            stmt.execute();
        
        
    } catch (SQLException ex) {
            ex.printStackTrace();
    }
    
   }
    
    
    
    public void UpdateMovies(Movies movies){
        String sql = " UPDATE movies SET name = ?, directedby = ?, releaseDate = ? ,"
        + "language = ?, gender = ?, subtitle = ?, audio = ?, descpription = ?,"
        + " image = ? WHERE movieId = ? ";
  
    try {
        stmt = con.prepareStatement(sql);
           
            stmt.setString(1, movies.getName());
            stmt.setString(2, movies.getDirectedby());
            stmt.setString(3, movies.getReleaseDate());             
            stmt.setString(4, movies.getLanguage());
            stmt.setString(5, movies.getGender());
            stmt.setString(6, movies.getSubtitle());
            stmt.setString(7, movies.getAudio());
            stmt.setString(8 ,movies.getDescpription());
            stmt.setBytes(9,  movies.getImage());
            stmt.setString(10, movies.getIsAvailable());
            stmt.setInt(10, movies.getMovieId());
            
            stmt.execute();
        
        
    } catch (SQLException ex) {
            ex.printStackTrace();
    }
    
   }
    //Stores Movies in to a List
   
    public List <Movies> ListMovies() {
        String sql = "SELECT * FROM movies";
        List <Movies> moveList = new ArrayList<Movies>();
        
        try {
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Movies movies = new Movies();
                movies.setMovieId(rs.getInt("movieId"));
                movies.setName(rs.getString("name"));
                movies.setDirectedby(rs.getString("directedby"));
                movies.setReleaseDate(rs.getString("releaseDate"));
                movies.setLanguage(rs.getString("language"));
                movies.setGender(rs.getString("gender"));
                movies.setSubtitle(rs.getString("subtitle"));
                movies.setAudio(rs.getString("audio"));
                movies.setDescpription(rs.getString("descpription"));
                movies.setImage(rs.getBytes("image"));
                movies.setIsAvailable(rs.getString("isAvailable"));
                moveList.add(movies);
                
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return moveList;
    
}
    public void closeCon(){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}    
