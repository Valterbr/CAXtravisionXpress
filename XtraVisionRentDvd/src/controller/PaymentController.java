/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;
import dao.Movies;
import model.MoviesModel;
import dao.Payment;
import model.PaymentModel;
import model.RentalModel;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JList;
import dao.Rental;
import java.io.File;
import view.MovieView;
import view.PaymentView;


/**
 *
 * @author Valter
 */
public class PaymentController extends PlainDocument{
    private PaymentModel pModel = new PaymentModel();
    private MoviesModel mCon = new MoviesModel();
    private Movies movies = new Movies();
    

    public PaymentController() {
        
      
      
    }
    
    //store rented in isavailble move
    public void getModel(MovieView view, DefaultListModel model ){
        System.out.println(model);
        }
    
    
    
     public String getdate(){
        
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        return strDate;
        }
     
    
    
     
     
     public void setReceipt( JList basket,JLabel price){  
         
         FileWriter arquivo;
	
	try {
       arquivo = new FileWriter(new File("receipt.txt"));
            System.out.println("");  
		
        int selectedIx = basket.getModel().getSize();
        Movies selectedMovie;
    // Get all the selected items using the indices
       for (int i = 0; i < selectedIx; i++) {
        
        selectedMovie  = (Movies) basket.getModel().getElementAt(i);
        
        arquivo.write("Movie "+i+": "+ selectedMovie.getName()+"\n");
        System.out.println(selectedMovie.getName());    
    }
                arquivo.write("Total Price "+price.getText()+"\n");
                arquivo.write("Date " + getdate() +"\n");
               	arquivo.close();
	} catch (IOException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}
        
     }
     
   
   
    public void insertEmail(PaymentView pView, String mail ,long CardNun, JLabel 
        emailAlert, JLabel cardAlert, JTextField email, JTextField card,JList basket){
       Movies selectedMovie;     
       Rental rental = new Rental();  
       RentalModel rentalModel = new RentalModel();
     
         
       
        MovieView movie =  new MovieView();
        DefaultListModel model= new DefaultListModel();
        getModel( movie,  model );
        pModel.getConnection();        
        Payment pay  = new Payment();        
        pay.setEmail(mail);
        
       pay.setCardNumber(CardNun);         
       String cardNum = Long.toString(pay.getCardNumber()); 
       String emailAd = pay.getEmail();
       
         pModel.existsRental(cardNum);
        
        if(!pModel.existsRental(cardNum)){ 
            
            pModel.existsCardNum(cardNum);
        
        if(!pModel.existsCardNum(cardNum)){//if cardNumner doesnt exist 
          
            
            if ( cardNum.length()== 16 ) {
                 if(basket.getModel().getSize()<=2){
                
                cardAlert.setText("");//rremove invalid notification
                    emailAlert.setText("");//rremove invalid notification
                    System.out.println(cardNum);
                    pModel.insertEmail(pay);//insert a carNumber an email
                    pModel.GetEmail(pay);
                    
                     //set movies as rented      
                      for (int i =0 ; i < basket.getModel().getSize();i++){
           
                          selectedMovie  = (Movies) basket.getModel().getElementAt(i);
                          rental.setMovieName(selectedMovie.getName());
                          rental.setMoveId(selectedMovie.getMovieId());
                          rental.setDate(getdate());
                          rental.setCreditCard(CardNun);  
                          rentalModel.insertRental(rental);
                          }
         
                     movies.setIsAvailable("rented");
                     for (int i =0 ; i < basket.getModel().getSize();i++){
         
                          selectedMovie  = (Movies) basket.getModel().getElementAt(i);
                           movies.setName(selectedMovie.getName());
       
                           pModel.UpdateAvaibilite(movies);
                           }
                    JOptionPane.showMessageDialog(pView,"transaction approved");
                    
                    if(!emailAd.equals("")){// its going to send email only if Email fiel is no Null
                        
                        pModel.GenerateEmail(emailAd);
                    }
                   
            }else{JOptionPane.showMessageDialog(null,"you cant rent more than 2 item in your first rent");
               System.out.println("you cant rent more than 2 item in your first rent");}
        }else{
                cardAlert.setText("invalid card Number");
                
            }
            
        
       
        
        }else{

            pModel.GetEmail(pay);               
            emailAd = pay.getEmail();  
              //set movies as rented  
           for (int i =0 ; i < basket.getModel().getSize();i++){
           
                selectedMovie  = (Movies) basket.getModel().getElementAt(i);
                rental.setMovieName(selectedMovie.getName());
                rental.setMoveId(selectedMovie.getMovieId());
                rental.setDate(getdate());
                rental.setCreditCard(CardNun);  
                rentalModel.insertRental(rental);
                }
           
             movies.setIsAvailable("rented");
            for (int i =0 ; i < basket.getModel().getSize();i++){
         
                     selectedMovie  = (Movies) basket.getModel().getElementAt(i);
                     movies.setName(selectedMovie.getName());
       
                    pModel.UpdateAvaibilite(movies);
                     }
                      JOptionPane.showMessageDialog(pView,"transaction approved");        
                
            if(!emailAd.equals("")){  
                pModel.GenerateEmail(emailAd);
              
        }  
            
       }
      }else{
            
            JOptionPane.showMessageDialog(pView ,"You can´t rent a movie looks you alredy have a rental with this card");
        }
       }
    }

 

    
     

