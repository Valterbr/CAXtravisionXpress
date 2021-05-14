/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.MoviesModel;

import javax.swing.JLabel;
import javax.swing.JList;
import model.PaymentModel;
import model.RentalModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import dao.Movies;
import view.RentalView;

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
      
      public void addMovieToBasket(RentalView detView, JLabel price, 
              DefaultListModel add, JList list, String name, byte [] image, 
              String isRented,int id )  {
              
          
        if(!isRented.equals("rented")){ 
            if(add.size() < 4){
              
            RentalModel rentaModel = new RentalModel();
            Movies movies = new Movies();
            rentaModel.getMovieDetails(movies);
            double MovePrice = movies.getPrice();
            price.setText(Double.toString( MovePrice * (add.getSize()+1 )));
            MoviesModel model = new MoviesModel();  
            JFrame frame = new JFrame();
            image = model.scaleImage(image , 70, 70, frame); 
            
            if (add.contains(id)){ 
              JOptionPane.showMessageDialog(detView, "Move was alredy add");
            }else{
            
            add.addElement(new Movies(name ,id ,new ImageIcon(image)));           
            list.setModel(add);       
        
            detView.dispose();
            }
            
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
