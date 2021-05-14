/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dao.Movies;

import dao.Rental;

/**
 *
 * @author Valter
 */
public class RentalModel {
private Connection con;
private PreparedStatement stmt;
private final String URL = "jdbc:mysql://apontejaj.com:3306/Valter_2019308?useSSL=false";
private final String USER = "Valter_2019308";
private final String PASS = "2019308";
private final String DRIVER = "com.mysql.jdbc.Driver";

// End of attributes declaration 

    

// Getting Connection with database
    public void getConnection() {
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Database Connection error: " + e);
        }
    }
 
    
     
     public void getMovieDetails(Movies movies) {
         getConnection();
         
         String sql = "SELECT * FROM movies";        
        try {
            
            
            stmt = con.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
           
            if(rs.next()){
                movies.setName(rs.getString("name")); 
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
                movies.setPrice(rs.getDouble("price"));
            
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        
        }
     }
     
     public void getMovieId(Movies movies) {
         getConnection();
         
         String sql = "SELECT * FROM movies";        
        try {
                       
            stmt = con.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
           
            if(rs.next()){
                
                movies.setMovieId(rs.getInt("movieId"));
            }
            }catch (SQLException ex){
            ex.printStackTrace();
        
            }
     
     }
     
        public void insertRental(Rental  rent){
            
         getConnection();
        
        String sql = "INSERT INTO rental ( userCard, moveId , moveName, rentaldate ) VALUES( ?, ?,?,?)";
        try {
           // preparing the Statement to get movies to store into Database
            stmt = con.prepareStatement(sql);
            stmt.setString(1,Long.toString(rent.getCreditCard()));
            stmt.setInt(2, rent.getMoveId());
            stmt.setString(3, rent.getMovieName());            
            stmt.setString(4, rent.getDate() );
            stmt.execute();
           
        }catch( SQLException ex){
          ex.printStackTrace();
        }
      }
     }
     
    

     
        

