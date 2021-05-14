/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Color;
import dao.Movies;
import dao.Renderer;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;

/**
 *
 * @author Valter
 */
public class MoviesModel {
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
 
    // Stores movies in Jlist
     public void ListMovie( JList list,JFrame frame) {
        DefaultListModel dm = new DefaultListModel();
        getConnection();
        dm.clear();
        String sql = "SELECT * FROM movies";        
        try {
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
               byte[] img = rs.getBytes("image");
               img = scaleImage(img, 150, 140, frame);
               dm.addElement(new Movies(rs.getString("name"),rs.getInt("movieId"), new ImageIcon(img)));
                          
            } 
        }catch (SQLException ex){
            ex.printStackTrace();
        }
          list.setCellRenderer(new Renderer());
          list.setModel(dm);
 
     }

     public  byte[] scaleImage( byte[] img, int higth , int width, JFrame frame ){
         ByteArrayInputStream imgbyte = new  ByteArrayInputStream(img);
         try {
             BufferedImage image = ImageIO.read(imgbyte);
             if (higth ==0) {
                 higth =(width*image.getHeight())/image.getWidth();
             }if (width ==0) {
                 width =(higth*image.getWidth())/image.getHeight();
             }
             Image ScaledImage = image.getScaledInstance(width, higth, image.SCALE_SMOOTH);
             BufferedImage imagebuffered = new BufferedImage(width, width, BufferedImage.TYPE_INT_RGB);
             imagebuffered.getGraphics().drawImage(ScaledImage, 0, 0, new Color(0, 0, 0), null);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ImageIO.write(imagebuffered ,"png", byteArrayOutputStream);
             return byteArrayOutputStream.toByteArray();
         } catch (Exception e) {
         }

    return null;



    }
     
     public void ShowMovieDetails(Movies movies,JList list) {
         getConnection();
         Movies it = (Movies) list.getSelectedValue();
         
         String sql = "SELECT * FROM movies where name = ?";        
        try {
            
            
            stmt = con.prepareStatement(sql);
            stmt.setString(1, it.getName());
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
        
    
}
    
    

