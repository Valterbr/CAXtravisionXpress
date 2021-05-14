/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
//valter change
import dao.Movies;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import model.MoviesModel;
import javax.swing.JFrame;
import view.MovieView;
import view.RentalView;

/**
 *
 * @author Valter
 */
public class MoviesController {
    
    
    public void ListMovies(MovieView view, JList list){
   
     MoviesModel md = new MoviesModel();  
     JFrame frame = new JFrame();
     md.ListMovie(list,frame);
    }
    
    public void ShowtMoviesDetails(MovieView view, JList list, JLabel lb, JList 
    list2,DefaultListModel dm,JLabel price){
    
    Movies m = new Movies();
    MovieView mv = new MovieView();
        
    RentalView   moviesDetailView = new RentalView(mv,list,dm,list2,price);
   moviesDetailView.setVisible(true);      
    MoviesModel md = new MoviesModel();
    md.ShowMovieDetails(m,list) ;   
  
   
    
    
    moviesDetailView.getMoviesDetails(m.getMovieId(),m.getName(), m.getDirectedby(),
     m.getGender(),m.getAudio(),m.getLanguage(), m.getSubtitle(),m.getDescpription(),
     m.getPrice(),m.getImage(),m.getIsAvailable());
   
    
    
} 
}
