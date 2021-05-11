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
public class Payment {
   private  String email;   
   private int id;
   private long cardNumber;
   private String isAvalible;
   
    public Payment() {
     
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getIsAvalible() {
        return isAvalible;
    }

    public void setIsAvalible(String isAvalible) {
        this.isAvalible = isAvalible;
    }
    
    
   
}
