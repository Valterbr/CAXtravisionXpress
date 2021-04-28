/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author Leandro
 */
public class Movies {
     
   // Attributes declaration 
    private int movieId;
    private String name;
    private String directedby;
    private String releaseDate;
    private String language;
    private String gender;
    private String subtitle;
    private String audio;
    private String descpription;
    private byte[] image;
    private double price;
    private String isAvailable;
     // End of attributes declaration 
    //valter Change  vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv       
   

    
    //Getters and setters generating
    public Movies() {
        
    }
    
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirectedby() {
        return directedby;
    }

    public void setDirectedby(String directedby) {
        this.directedby = directedby;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getDescpription() {
        return descpription;
    }

    public void setDescpription(String descpription) {
        this.descpription = descpription;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    
    public double getPrice(){
    return price;
    }
    
    public void setPrice(double price){
    this.price = price;
    } 
    
    public String getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }
    //End getters and setters generating

   

  
}

