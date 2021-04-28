/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;
import dao.Movies;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import model.MoviesModel;
import view.MovieView;
import view.MoviesDetailView;

/**
 *
 * @author Valter
 */
public class MoviesController {
    
    
    public void ListMovies(MovieView view, JList list){
    
     MoviesModel md = new MoviesModel();        
     md.ListMovie(list);
    }
    public void ShowtMoviesDetails(MovieView view, JList list, JLabel lb, JList list2,DefaultListModel dm){
    MovieView mv = new MovieView();
    new MoviesDetailView(mv,list,dm,list2).setVisible(true);      
    MoviesModel md = new MoviesModel();
    md.ShowMovieDetails(list,lb) ;
     
     
      // lbNmae.setText(mv.getName());
     
      System.out.println(mv.getName());
      md.ShowMovieDetails(list,lb);
     
    }
    
}
