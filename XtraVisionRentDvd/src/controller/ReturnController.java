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
import javax.swing.JList;
import view.PaymentView;
import view.ReturnView;

/**
 *
 * @author Valter
 */
public class ReturnController {
    
    private ReturnModel retModel = new ReturnModel();
    private Return ret = new Return();
  
    public void getRentalDetail(ReturnView rView, JTextField idMovie, JLabel name, JLabel lbImage, JLabel price) {
       
    ret.setMovieId(Integer.parseInt(idMovie.getText()));
    System.out.println(ret.getMovieId());
    System.out.println(ret.getName());
    retModel.GetMovieDetails(ret, lbImage );
   
    name.setText(ret.getName());
    price.setText(Double.toString( ret.getPrice()));
    System.out.println(ret.getName());
    ret.setIsAvalible("Avalible");
    ret.setName(name.getText());
    retModel.UpdateAvaibilite(ret);    
    setReceipt(rView, name, price );
    retModel.GetEmail(ret);
    String emailAd =ret.getEmailAdd();
    retModel.exists(ret);
    System.out.println(retModel.exists(ret));
    if(retModel.exists(ret)){
    retModel.GenerateEmail(emailAd);
    }
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