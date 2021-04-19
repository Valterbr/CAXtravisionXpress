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
import view.ReturnView;

/**
 *
 * @author Valter
 */
public class ReturnController {
    
    private ReturnModel retModel = new ReturnModel();
    private Return ret = new Return();
  
    public void getRentalDetail(ReturnView aThis, JTextField idMovie, JLabel tete, JLabel lbImage) {
  
    ret.setMovieId(Integer.parseInt(idMovie.getText()));
    System.out.println(ret.getMovieId());
    System.out.println(ret.getName());
    retModel.GetMovieDetails(ret, lbImage );
    
    tete.setText(ret.getName());
     System.out.println(ret.getName());
    }
    
}
