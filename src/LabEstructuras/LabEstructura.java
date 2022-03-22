/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LabEstructuras;

import java.awt.Toolkit;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author tllach, nmartinez, dkaty
 */
public class LabEstructura {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Toolkit tk = Toolkit.getDefaultToolkit(); 
        int xmax = (int) tk.getScreenSize().getWidth();  
        int ymax = (int) tk.getScreenSize().getHeight(); 
        JFrame display = new Display(xmax,ymax);
        display.setDefaultCloseOperation(EXIT_ON_CLOSE);
        display.setSize(xmax,ymax);
        display.setResizable(false);
        display.setVisible(true);
    }
    
}
