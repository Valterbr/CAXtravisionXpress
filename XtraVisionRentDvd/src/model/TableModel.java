/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.Movies;
import java.util.List;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author Valter
 */
public class TableModel extends AbstractTableModel{
    private List<Movies> movieList;
    private String [] column ={"Id","Nome"/*, "Directedby", "ReleaseDate","Language",
              "gender", "subtitle", "audio", "descpription"*/};

    public TableModel(List<Movies> movielist) {
        this.movieList = movielist;
    }

    @Override
    public int getColumnCount() {
        return column.length;
    }
    
    @Override
    public int getRowCount() {
        return movieList.size();
    }

    @Override
    public String getColumnName( int columnIndex){
        return column[columnIndex];    
    }
    
    @Override
    public Class<?> getColumnClass( int columnIndex){
        return String.class;
    }
    
    @Override
    public void setValueAt(Object avalue, int rowIndex, int columnIndex ){
        Movies movies = movieList.get(rowIndex);
        switch(columnIndex){
            case 0: 
                  movies.setMovieId(Integer.parseInt(avalue.toString()));
                  break;
             case 1:       
                  movies.setName(avalue.toString());
                  break;
            case 2:       
                  movies.setDirectedby(avalue.toString());
                  break; 
            case 3:       
                  movies.setReleaseDate(avalue.toString());
                  break;
            case 4:       
                  movies.setLanguage(avalue.toString());
                  break;
            case 5:       
                  movies.setGender(avalue.toString());
                  break;
            case 6:       
                  movies.setSubtitle(avalue.toString());
                  break;
            case 7:       
                  movies.setAudio(avalue.toString());
                   break;
            case 8:       
                  movies.setDescpription(avalue.toString());
                  break;
            default:
                  System.out.println("Invalid Column Index ");
        }
        fireTableCellUpdated(rowIndex, columnIndex);
      
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Movies selectedObject = movieList.get(rowIndex);
        Object objectValue = null;
        
        switch(columnIndex){
            case 0: 
                  objectValue = selectedObject.getMovieId();
                  break;
             case 1:       
                  objectValue = selectedObject.getName();
                  break;
            case 2:       
                  objectValue = selectedObject.getDirectedby();
                  break; 
            case 3:       
                  objectValue = selectedObject.getReleaseDate();
                  break;
            case 4:       
                  objectValue = selectedObject.getLanguage();
                  break;
            case 5:       
                  objectValue = selectedObject.getLanguage();
                  break;
            case 6:       
                  objectValue = selectedObject.getSubtitle();
                  break;
            case 7:       
                  objectValue = selectedObject.getAudio();
                   break;
            case 8:       
                  objectValue = selectedObject.getDescpription();
                  break;
            default:
                  
        }
        
        return objectValue;
        
    }
   
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return false;
    }
    

	
    public Object getSelected(int rowIndex ){
        return movieList.get(rowIndex);
    
    }
    
    public void refreshData(List<Movies> newMovieList ){
        this.movieList =  newMovieList;
        fireTableDataChanged();
    }
   
     public void clearData(){
        movieList.clear();
        fireTableDataChanged();
    }
    public boolean isEnpity(){          
        return movieList.isEmpty();
     
    }
    
}
