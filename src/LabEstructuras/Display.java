package LabEstructuras;



import LabEstructuras.Arbol.NodoArbol;
import LabEstructuras.Arbol.Arbol;
import Menu.MenuItem;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author tllach, nmartinez, dkaty
 * 
 **/

public class Display extends JFrame implements ActionListener{
    
    private EDTGui edtGUI;
    private CronogramaGui cronogramaGUI;
    
    private String nombreProyecto;
    private static String nombreObject;
    private int xmax, ymax;
    private Arbol arbol;
    private Container container;
    private JButton btnEDT, btnCronograma;
    private JLabel lblPrincipal,  imagen, imagen2;
    private ListaEnlazada listaEntregables;
    
    
    public Display(int xmax, int ymax, String proyecto, ListaEnlazada listaEntregables){
        this.xmax = xmax;
        this.ymax = ymax;
        this.nombreProyecto = proyecto;
        this.listaEntregables = listaEntregables;
        
        declaracion();
        pantallaPrincipal(); 
    }
    
    private void declaracion(){
        arbol = new Arbol();
        arbol.setRaiz(new NodoArbol(this.nombreProyecto, false));
        container = this.getContentPane();
        lblPrincipal = new JLabel();
        btnEDT = new JButton();
        btnCronograma = new JButton();
        edtGUI = new EDTGui(this);
        cronogramaGUI = new CronogramaGui(this);
        imagen = new JLabel();
        imagen2 = new JLabel();
        btnEDT.addActionListener(this);
        btnCronograma.addActionListener(this);
    }
    
    public void pantallaPrincipal(){
       
       
        container.setLayout(null);
        container.setBackground(new Color(41, 96, 137));
        
        lblPrincipal.setText("Project Management");
        lblPrincipal.setFont(new Font("Monospaced",Font.CENTER_BASELINE,60));
        lblPrincipal.setSize(670, 300);
        lblPrincipal.setForeground(Color.WHITE);
        lblPrincipal.setLocation((xmax - 670)/2, 20);
        
        btnEDT.setIcon(new ImageIcon(getClass().getResource("/Images/button_edt.png")));
        btnEDT.setSize(300,90);
        btnEDT.setLocation(((xmax - 670)/2)-50,400);
        btnEDT.setContentAreaFilled(false);
        btnEDT.setBorder(BorderFactory.createEmptyBorder());
    
        imagen.setBounds(-20, -25, 500, 350);
        imagen.setIcon(new ImageIcon(getClass().getResource("/Images/fondo3.gif")));
        
        imagen2.setBounds(1050, 300, 500, 500);
        imagen2.setIcon(new ImageIcon(getClass().getResource("/Images/persi.gif")));
        
        btnCronograma.setIcon(new ImageIcon(getClass().getResource("/Images/button_cronograma.png")));
        btnCronograma.setSize(300,90);
        btnCronograma.setLocation((xmax/2)+50,400);
        btnCronograma.setContentAreaFilled(false);
        btnCronograma.setBorder(BorderFactory.createEmptyBorder());
            
        addToContainer(lblPrincipal, btnEDT, btnCronograma,imagen,imagen2);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == btnEDT){
            removeOfContainer(lblPrincipal, btnEDT, btnCronograma, imagen, imagen2);
            container.validate();
            container.repaint();
            edtGUI.setupEDT();
        }
        if (ae.getSource() == btnCronograma){
            removeOfContainer(lblPrincipal, btnEDT, btnCronograma, imagen, imagen2);
            container.validate();
            container.repaint();
            listaEntregables = arbol.NodosEntregables(arbol.getRaiz());
            cronogramaGUI.setUpCronograma(listaEntregables);
            
        }
    }

    public void addToContainer(JComponent ...objs) {
        for(JComponent obj : objs) {
            container.add(obj);
        }
    }   
    
    public void removeOfContainer(JComponent ...objs) {
        for(JComponent obj : objs) {
            container.remove(obj);
        }
    } 
    
    public static void setNombreObject(String nombre){
        Display.nombreObject = nombre;
    }
    
    public Container getContainer(){
        return container;
    }
    
    public int getXmax(){
        return xmax;
    }
    
    public int getYmax(){
        return ymax;
    }
    
    public String getNameProject(){
        return nombreProyecto;
    }
    
    public Arbol getArbol(){
        return arbol;
    }
    
    public String getNombreObject(){
        return nombreObject;
    }
}

