/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.JLabel;
import javax.swing.JTextField;
import dao.Return;
import model.ReturnModel;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import view.ReturnView;

/**
 *
 * @author Valter
 */
public class ReturnController {
    
    private ReturnModel retModel = new ReturnModel();
    private Return ret = new Return();
  
    public void getRentalDetail(ReturnView rView,JLabel lbPrice, JTextField idMovie, JLabel name, 
            JLabel lbImage, JLabel price, JLabel rentalId,JLabel rentedDate,JLabel lbRentalID,
    JLabel lbRentalDate, JLabel lbMovieName,JButton returnButton) {
   
   ret.setMovieId(Integer.parseInt(idMovie.getText()));//Stores movie Id from Text Field   
   retModel.existsRental(ret);//check if rental exists
   if(retModel.existsRental(ret)){
    retModel.GetMovieDetails(ret, lbImage );
    lbRentalID.setText("Rental Id");
    lbMovieName.setText("Movie Name");
    lbRentalDate.setText("Rental date");
    lbPrice.setText("Price");
    name.setText(ret.getName());
    rentalId.setText(Integer.toString(ret.getRentalId()));
    rentedDate.setText(ret.getRentedDate());
    price.setText(Double.toString( ret.getPrice()));
    returnButton.setVisible(true);
    }else{
   name.setText("");
   rentalId.setText("");
   lbImage.setIcon(null);
   lbRentalID.setText("");
   lbMovieName.setText("");
   lbRentalDate.setText("");
   lbPrice.setText("");
   price.setText("");
   returnButton.setVisible(false);
     JOptionPane.showMessageDialog(rView, "couldn't recognize the disc, Please make sure you inserted the correct into the slot");
    }
    }
    public void returMovie(ReturnView rView, JTextField idMovie, JLabel name,
          JLabel lbImage, JLabel price, JLabel rentalId,JLabel rentedDate){
    ret.setIsAvalible("Avalible");
    ret.setName(name.getText());
    retModel.UpdateAvaibilite(ret);    
    setReceipt(rView, name, price );
    retModel.GetEmail(ret);
    String emailAd =ret.getEmailAdd();
    retModel.exists(ret);
    JOptionPane.showMessageDialog(rView, "Move was returned");
    if(retModel.exists(ret)){
    retModel.GenerateEmail(emailAd);
    }
    ret.setRentalId(Integer.parseInt(rentalId.getText()) );
    retModel.deleteMovie( ret);
    }
   
   public void setReceipt(ReturnView rView,  JLabel name, JLabel price){
     
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        System.out.println(strDate);
        
         FileWriter file;
	
	try {
		file = new FileWriter(new File("receipt.txt"));
           
	        file.write("Name: "+ name.getText()+"\n" );
                file.write("Total Price "+price.getText()+"\n");
                file.write("Date " + strDate +"\n");
                file.close();
	} catch (IOException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}
        
        
        }
     
     }