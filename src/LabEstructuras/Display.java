/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LabEstructuras;

import java.awt.Color;
import java.awt.Font;
import static java.awt.Frame.MAXIMIZED_BOTH;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author tllach, nmartinez, dkaty
 */
public class Display {
    
    JFrame frame;
    int xmax,ymax;
    JPanel panel;
    JButton parte1B;
    JLabel titulo;
    
    public Display(){
        frame = new JFrame();
        panel = new JPanel();
        parte1B = new JButton();
        titulo = new JLabel();
        visual(frame,panel,parte1B); 
    }
    
    
    private void visual(JFrame frame, JPanel panel,JButton parte1B){
        frame.setVisible(true);
        frame.setBackground(Color.RED);
        frame.setExtendedState(MAXIMIZED_BOTH);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        xmax = frame.getWidth();
        ymax = frame.getHeight();
        frame.add(panel);
        panel.setVisible(true);
        panel.setBackground(Color.gray);
        panel.setLayout(null);
        panel.add(titulo);
        
        titulo.setVisible(true);
        titulo.setText("Proyect Manager");
        titulo.setFont(new Font("Arial",Font.BOLD,40));
        
        titulo.setBounds(630,5, 400, 200);
        

        panel.add(parte1B);
        parte1B.setVisible(true);
        parte1B.setBounds(300,500,100,50);
        parte1B.setText("EDT");
        
    }

    
}
