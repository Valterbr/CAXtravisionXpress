/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.JLabel;
import javax.swing.JList;
import model.MoviesModel;
import view.MovieView;

/**
 *
 * @author Valter
 */
public class MoviesController {
    
    
    public void ListMovies(MovieView view, JList list){
    
     MoviesModel md = new MoviesModel();        
     md.ListMovie(list);
    }
    public void ShowtMoviesDetails(MovieView view, JList list, JLabel lb){
    
     MoviesModel md = new MoviesModel();        
     md.ShowMovieDetails(list,lb);
    }
    
}
