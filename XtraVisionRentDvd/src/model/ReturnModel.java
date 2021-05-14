/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import dao.Return;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;


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
 
   public boolean existsRental(Return ret){
    getConnection();
    boolean existe = false;
     String sql = "SELECT * FROM rental where moveId = ?";
        try {
            
             stmt = con.prepareStatement(sql);            
             stmt.setInt(1, ret.getMovieId());
            
             stmt.execute(); 
             
            ResultSet rs = stmt.executeQuery();           
            if(rs.next()){
                existe = true;
                System.out.println(existe);
                            
        }stmt.close();
        }catch( SQLException ex){
          ex.printStackTrace();
        }
    return existe;
    
          
        
    }

    
     public void UpdateAvaibilite( Return ret){
       getConnection();
         String sql = " UPDATE movies SET isAvailable = ? WHERE name = ? ";
  
    try {
        stmt = con.prepareStatement(sql);             
            stmt.setString(1, ret.getIsAvalible());  
            stmt.setString(2, ret.getName());
            stmt.execute();
                  
    } catch (SQLException ex) {
            ex.printStackTrace();
    }
    
   }
    
    public void GetMovieDetails(Return ret, JLabel lbImg ){
   
    getConnection();
     String sql = "SELECT   movies.name, movies.image, movies.price, rental.rentalId, rental.rentaldate\n" +
       "from movies join rental on movies.movieId =  rental.moveId where moveId = ?;";
        try {
            
             stmt = con.prepareStatement(sql);            
             stmt.setInt(1, ret.getMovieId());
             stmt.execute(); 
             
             ResultSet rs = stmt.executeQuery();           
         while(rs.next()){
             ret.setName(rs.getString("name"));
             ret.setImage(rs.getBytes("image"));
             ret.setPrice(rs.getDouble("price"));
             ret.setRentalId(rs.getInt("rentalId"));
             ret.setRentedDate(rs.getString("rentaldate"));
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
    
    public boolean exists(Return ret){
    getConnection();
    boolean existe = false;
    String sql = "SELECT  user.emailAdd from user join rental on user.cardNumber "
            + "=  rental.userCard where moveId = ?;";
        try {
            
             stmt = con.prepareStatement(sql);            
             stmt.setInt(1, ret.getMovieId());
            
             stmt.execute(); 
             
            ResultSet rs = stmt.executeQuery();           
            if(rs.next()){
                if (!rs.getString("emailAdd").equals("")) { 
                   
                existe = true;
                System.out.println(existe);
               }              
        }stmt.close();
        }catch( SQLException ex){
          ex.printStackTrace();
        }
    return existe;
      }
    
    
     
     // Deletes movies from the  database
    public void deleteMovie(Return ret){
    getConnection();
    String sql = "DELETE FROM rental WHERE rentalId = ?";
    
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, ret.getRentalId());
            stmt.execute();
        
        
        } catch (SQLException ex) {
            ex.printStackTrace();
    }
    }
    
      
    public  void GetEmail(Return ret) {
       
         String sql = "SELECT  user.emailAdd from user join rental on user.cardNumber "
                 + "=  rental.userCard where moveId = ?;";
        try {
            
             stmt = con.prepareStatement(sql);            
             stmt.setLong(1, ret.getMovieId()); 
             stmt.execute();  
            ResultSet rs = stmt.executeQuery();
           
            while(rs.next()){
          
             
            ret.setEmailAdd(rs.getString("emailAdd"));
            System.out.println(ret.getEmailAdd());
             
            
        }}catch( SQLException ex){
          ex.printStackTrace();
        }
    }
    
    
    
      public void GenerateEmail( String emailAd  ){
        String emailAdd = "valterbrlopes@gmail.com"; //declares a variable to store the email address  
        String password = "valter123";  //declares a variable to store the password number  
        
        
        MultiPartEmail email = new  MultiPartEmail();//Creates a new object SimpleEmail from the libery commons.mail
        email.setHostName("smtp.gmail.com");//sets email's Host Name for googlemail.com
        email.setSmtpPort(465);//sets the host's  Smtp Port 465 
        email.setAuthenticator(new DefaultAuthenticator(emailAdd,password));//Does the Authentication of the email
        email.setSSLOnConnect(true);//Actiavte SSL for the protection of the system 
        
        try{
        
        email.setFrom(emailAdd);//Sets the mail thats is sending the msg
        email.setSubject("Teste Email");//Writes a Subject msg on the email
        email.setMsg("Thanks for choosing Xtra vision we are ussuem your receipt");////Writes a message on the email
        email.addTo(emailAd);//Sets the mail thats is receiving the msg
        EmailAttachment attach = new  EmailAttachment();
        attach.setPath("receipt.txt");
        attach.setName("recipt.txt");
        email.attach(attach);
        
        email.send();//this method sends the email
        System.out.println("message was sent");
        
        
        }catch( Exception e ) {
        e.printStackTrace();        
        
        }
       }
          
        
    }
    

