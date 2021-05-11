/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.MoviesModel;
import java.awt.Label;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JList;
import model.PaymentModel;
import model.RentalModel;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import dao.Movies;
import dao.Rental;
import view.MovieView;
import view.MoviesDetailView;
import view.PaymentView;



/**
 *
 * @author Valter
 */
public class RentalController {
   private PaymentModel con = new PaymentModel();
    public RentalController() {
        
        
       
    }
      public void insertRental(){
        


      }
      
      public void addMovieToBasket(MoviesDetailView detView, DefaultListModel basket,
              JLabel price, DefaultListModel add, JList list, String name, byte [] image, String isRented,int id )  {
        
    
        if(!isRented.equals("rented")){ 
            if(add.size() < 4){
               
            RentalModel rentaModel = new RentalModel();
            Movies movies = new Movies();
            rentaModel.getMovieDetails(movies);
            double MovePrice = movies.getPrice();
            price.setText(Double.toString( MovePrice * (basket.getSize()+1 )));
            MoviesModel model = new MoviesModel();  
            JFrame frame = new JFrame();
            image = model.scaleImage(image , 70, 70, frame);        
       
            add.addElement(new Movies(name ,id ,new ImageIcon(image)));           
            list.setModel(add);       
        
            detView.dispose();
            }else{
            JOptionPane.showMessageDialog(detView,"You cant rented more than for movies" ); 
            
                detView.dispose();
            }
         }else{
        
                JOptionPane.showMessageDialog(detView,"Movies Already rented" );           
                detView.dispose();
        }
   
   
      } 
     
    
    
}
