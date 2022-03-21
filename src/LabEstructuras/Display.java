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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author tllach, nmartinez, dkaty
 */
public class Display extends JFrame implements ItemListener{
    
    JFrame frame;
    int xmax,ymax;
    JPanel panel;
    JButton parte1B,parte2B;
    JButton AgregarEntre,AgregarPaquete;
    JLabel titulo,titulo2,nombrepaquete;
    Container pane;
    JComboBox Selector;
    String opcion;
    JTextField paquete;
    
    public Display(int xmax,int ymax){
        this.xmax = xmax;
        this.ymax = ymax;
        parte1B = new JButton();
        parte2B = new JButton();
        AgregarEntre = new JButton();
        AgregarPaquete = new JButton();
        titulo = new JLabel();
        titulo2 = new JLabel();;
        pane = this.getContentPane();
        Selector = new JComboBox();
        paquete = new JTextField();
        nombrepaquete = new JLabel();
        visual(); 
        botones();
    }
    
    private void botones(){
        ActionListener hundeEDT = new hundeEDT();
        parte1B.addActionListener(hundeEDT);
    }
    
    
    //Pantallas
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
    private void EDTVisual(){
        titulo2.setText("EDT");
        titulo2.setFont(new Font("Monospaced",Font.CENTER_BASELINE,60));
        titulo2.setSize(670, 300);
        titulo2.setForeground(Color.BLACK);
        titulo2.setLocation(xmax-300, ymax-(ymax-2));
        Selector.setVisible(true);
        Selector.addItem("Agregar Paquete");
        Selector.addItem("Agregar Entregable");
        Selector.addItem("Visualizar Proyecto");
        Selector.addItemListener(this);
        Selector.setBounds(30,40,500, 500);
        
        pane.add(Selector);
        pane.add(titulo2);
        pane.validate();
        pane.repaint();
        
    }

    //Opciones
    private void AgregarPaquete(){
        paquete.setBounds(xmax-500,200,100,150);
        nombrepaquete.setText("Ingrese el nombre del paquete que desea agregar");
        nombrepaquete.setFont(new Font("Monospaced",Font.CENTER_BASELINE,30));
        nombrepaquete.setBounds(xmax-400,50,400,300);
        pane.add(paquete);
        pane.add(nombrepaquete);
    }
    
    //Action Listeners  
    private class hundeEDT implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
           pane.remove(titulo);
           pane.remove(parte1B);
           pane.remove(parte2B);
           pane.validate();
           pane.repaint();
           pane.setBackground(Color.getHSBColor(570, 745, 300));
           EDTVisual();
        } 
        
    }
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == Selector){
            opcion = (String) Selector.getSelectedItem();
            if (opcion.equals("Agregar Paquete")){
                AgregarPaquete();
            }else if(opcion.equals("Agregar Entregable")){
                
            }else if (opcion.equals("Visualizar Proyecto")){
                
            }
        }
    }
    
   
    
    
}
