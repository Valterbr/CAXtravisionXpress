/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.Movies;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Valter
 */
public class Renderer extends DefaultListCellRenderer implements ListCellRenderer<Object>{

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
       Movies iT = (Movies) value;
        setText(iT.getName());
        setIcon(iT.getIcon());
        setVerticalTextPosition(BOTTOM);
        setHorizontalTextPosition(CENTER);
        setEnabled(true);
        setFont(list.getFont());
        return this;
        
        
            }
  
}
