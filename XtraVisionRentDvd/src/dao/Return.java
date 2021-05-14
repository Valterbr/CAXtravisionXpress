/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author Valter
 */
public class Return {
    private String name;
    private int rentalId;
    private int movieId;    
    private byte[] image;
    private double price;
    private String rentedDate;
    private String Date;
    private double latePrice;
    private String isAvalible;
    private String emailAdd;

    public String getIsAvalible() {
        return isAvalible;
    }

    public void setIsAvalible(String isAvalible) {
        this.isAvalible = isAvalible;
    }

    

    public Return() {
       // this.name = "titanic";
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public double getLatePrice() {
        return latePrice;
    }

    public void setLatePrice(double latePrice) {
        this.latePrice = latePrice;
    }

    public String getEmailAdd() {
        return emailAdd;
    }

    public void setEmailAdd(String emailAdd) {
        this.emailAdd = emailAdd;
    }

    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public String getRentedDate() {
        return rentedDate;
    }

    public void setRentedDate(String rentedDate) {
        this.rentedDate = rentedDate;
    }
    
       
}
