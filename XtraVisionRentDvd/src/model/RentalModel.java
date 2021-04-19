/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.Rental;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;

/**
 *
 * @author Valter
 */
public class RentalModel {
    
Rental rental = new Rental();
// Attributes declaration     
   
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
    
    
    public boolean exists(String test){
    boolean existe = false;
     String sql = "SELECT * FROM user WHERE cardNumber =?";
        try {
            
             stmt = con.prepareStatement(sql);            
             stmt.setString(1, test);
             System.out.println(test);
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

