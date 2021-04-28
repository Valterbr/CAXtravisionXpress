/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;
import dao.Movies;
import model.MoviesModel;
import dao.Rental;
import model.RentalModel;
import view.MovieView;
import view.PaymentView;

/**
 *
 * @author Valter
 */
public class PaymentController extends PlainDocument{
    private RentalModel rCon = new RentalModel();
    private MoviesModel mCon = new MoviesModel();
    private Movies movies = new Movies();
    

    public PaymentController() {
        

      
    }
    
    public List getModel(MovieView mView , DefaultListModel mModel){
    List  list = new ArrayList(mModel.getSize());
    for (int i = 0; i < mModel.getSize(); i++) {
    list.add(mModel.getElementAt(i));
    
    }
        System.out.println(list.size());
        return list;
    }

       
    public void insertEmail(PaymentView pView, String mail ,int CardNun, JLabel emailAlert, JLabel cardAlert, JTextField email){
        
        rCon.getConnection();        
        Rental rental  = new Rental();        
        rental.setEmail(mail);
        
       rental.setCardNumber(CardNun);         
       String cardNum = Integer.toString(rental.getCardNumber()); 
       String emailAd = rental.getEmail();
       
         rCon.exists(cardNum);
        if(!rCon.exists(cardNum)){//if cardNumner doesnt exist 
            System.out.println(rCon.exists(cardNum));
            if ( cardNum.length()== 9 ) {
                
                cardAlert.setText("");//rremove invalid notification
                if(emailAd.contains("@")&& emailAd.contains(".")){
                    
                    emailAlert.setText("");//rremove invalid notification
                    System.out.println(cardNum);
                    rCon.insertEmail(rental);//insert a carNumber an email
                    rCon.GetEmail(rental);
                    JOptionPane.showMessageDialog(pView,"transaction approved");
                    if(!emailAd.equals("")){// its going to send email only if Email fiel is no Null
                        rCon.GenerateEmail(emailAd);
                    }
                    
                }else {
                    emailAlert.setText("Please type a valid email Address ");
                }
                
            }else{
                cardAlert.setText("invalid card Number");
                
            }
        
        
        }else{

            rCon.GetEmail(rental);               
            emailAd = rental.getEmail();
            JOptionPane.showMessageDialog(pView,"transaction approved");        
            // System.out.println(emailAd);      
            if(!emailAd.equals("")){  
                
                rCon.GenerateEmail(emailAd);
              
        }
       }
      }
    }

 

    
     

