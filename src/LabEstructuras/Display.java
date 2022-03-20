/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LabEstructuras;


import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author tllach, nmartinez, dkaty
 */
public class Display extends JFrame{
    
    JFrame frame;
    int xmax,ymax;
    JPanel panel;
    JButton parte1B,parte2B;
    JLabel titulo;
    Container pane;
    
    public Display(int xmax,int ymax){
        this.xmax = xmax;
        this.ymax = ymax;
        parte1B = new JButton();
        parte2B = new JButton();
        titulo = new JLabel();
        pane = this.getContentPane();
        visual(); 
        botones();
    }
    private void botones(){
        ActionListener hunde = new hunde();
        parte1B.addActionListener(hunde);
    }
    
    private void visual(){
            pane.setLayout(null);
            pane.setBackground(Color.getHSBColor(450, 345, 706));
            titulo.setText("Proyect Management");
            System.out.println(titulo.getSize());
            titulo.setFont(new Font("Monospaced",Font.CENTER_BASELINE,60));
            titulo.setSize(670, 300);
            titulo.setForeground(Color.WHITE);
            titulo.setLocation((xmax-670)/2, 20);
            parte1B.setText("EDT");
            parte1B.setSize(300,100);
            parte1B.setLocation(((xmax-670)/2)-50,400);
            parte1B.setBackground(Color.LIGHT_GRAY);
            parte2B.setText("Cronograma");
            parte2B.setSize(300,100);
            parte2B.setLocation((xmax/2)+50,400);
            pane.add(titulo);
            pane.add(parte1B);
            pane.add(parte2B);
    }
        
    public class hunde implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
           pane.setVisible(false);
        }
    }
    
}
