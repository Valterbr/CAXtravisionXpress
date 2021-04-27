/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.swing.Icon;

/**
 *
 * @author Valter
 */
public class Movies {
    private String name;
    private Icon icon;

    public Movies() {
    }
    
    
   public Movies(String name,Icon icon) {
        this.name = name;
        this.icon = icon;
    }

     
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    
    
    
    
}
