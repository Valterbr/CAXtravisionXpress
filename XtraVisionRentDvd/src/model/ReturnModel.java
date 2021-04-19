/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.Return;
import dao.Rental;
import java.awt.Image;
import java.awt.Label;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import view.ReturnView;

/**
 *
 * @author Valter
 */
public class ReturnModel {
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
 
    public void insertEmail(Rental rental){
        
        String sql = "INSERT INTO user ( emailAdd, cardNumber ) VALUES( ?, ?)";
        try {
           // preparing the Statement to get movies to store into Database
            stmt = con.prepareStatement(sql);
            stmt.setString(1, rental.getEmail());
            stmt.setInt(2, rental.getCardNumber());
            stmt.execute();
           
        }catch( SQLException ex){
          ex.printStackTrace();
        }
      }
    
    
    public void GetMovieDetails(Return ret, JLabel lbImg ){
   
    getConnection();
     String sql = "SELECT * FROM movies WHERE movieId  = ?";
        try {
            
             stmt = con.prepareStatement(sql);            
             stmt.setInt(1, ret.getMovieId());
             System.out.println(ret.getMovieId());
             stmt.execute(); 
             
             ResultSet rs = stmt.executeQuery();           
         while(rs.next()){
             ret.setName(rs.getString("name"));
             ret.setImage(rs.getBytes("image"));              
             ImageIcon image = new ImageIcon(ret.getImage());
             Image img = image.getImage();
             Image movieImg = img.getScaledInstance(lbImg.getWidth(), lbImg.getHeight(),Image.SCALE_SMOOTH);
             ImageIcon NewImage = new ImageIcon(movieImg);
             lbImg.setIcon(NewImage);
             
        }stmt.close();
        }catch( SQLException ex){
          ex.printStackTrace();
        }
    }
    
          
    public  void GetEmail(Rental rental) {
       
         String sql = "SELECT emailAdd FROM user WHERE cardNumber  = ?";
        try {
            
             stmt = con.prepareStatement(sql);            
             stmt.setInt(1, rental.getCardNumber()); 
             stmt.execute();  
            ResultSet rs = stmt.executeQuery();
           
            while(rs.next()){
          
             
            rental.setEmail(rs.getString("emailAdd"));
            System.out.println(rental.getEmail());
             
            
        }}catch( SQLException ex){
          ex.printStackTrace();
        }
    
          
        
    }
    
}
