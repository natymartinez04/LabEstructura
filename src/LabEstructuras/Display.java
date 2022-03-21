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
import javax.swing.JComponent;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author tllach, nmartinez, dkaty
 */
public class Display extends JFrame implements ItemListener, ActionListener{
    
    int xmax,ymax;
    Container container;
    JButton btnEDT, btnCronograma, btnGuardar;
    JLabel lblPrincipal, lblPEDT, lblNombrePaquete, lblSeleccionEDT, lblUbicacion;
    JComboBox Selector;
    String opcion;
    JTextField txtSeleccion, txtPaquetePadre;
    
    public Display(int xmax,int ymax){
        this.xmax = xmax;
        this.ymax = ymax;
        declaracion();
        pantallaPrincipal(); 
        addActionsListener();
    }
    
    private void declaracion(){
        container = this.getContentPane();
        lblPrincipal = new JLabel();
        btnEDT = new JButton();
        btnCronograma = new JButton();
        lblPEDT = new JLabel();
        Selector = new JComboBox();
        txtSeleccion = new JTextField();
        lblNombrePaquete = new JLabel();
        lblSeleccionEDT = new JLabel();
        lblUbicacion = new JLabel();
        txtPaquetePadre = new JTextField();
        btnGuardar = new JButton();
    }
    
    
    private void addActionsListener(){
        btnEDT.addActionListener(this);
        btnGuardar.addActionListener(this);
        
    }
    
    //Pantallas
    private void pantallaPrincipal(){
            container.setLayout(null);
            container.setBackground(Color.getHSBColor(450, 345, 706));
            
            lblPrincipal.setText("Proyect Management");
            lblPrincipal.setFont(new Font("Monospaced",Font.CENTER_BASELINE,60));
            lblPrincipal.setSize(670, 300);
            lblPrincipal.setForeground(Color.WHITE);
            lblPrincipal.setLocation((xmax-670)/2, 20);
            
            btnEDT.setText("EDT");
            btnEDT.setSize(300,100);
            btnEDT.setLocation(((xmax-670)/2)-50,400);
            
            btnCronograma.setText("Cronograma");
            btnCronograma.setSize(300,100);
            btnCronograma.setLocation((xmax/2)+50,400);
            
            addToContainer(lblPrincipal, btnEDT, btnCronograma);
    }
    
    private void pantallaEDT(){
        container.setBackground(Color.getHSBColor(480, 345, 706));
        
        lblPEDT.setText("EDT");
        lblPEDT.setFont(new Font("Monospaced",Font.CENTER_BASELINE,60));
        lblPEDT.setSize(110, 60);
        lblPEDT.setForeground(Color.BLACK);
        lblPEDT.setLocation((xmax - lblPEDT.getWidth()) / 2  , 60);
        
        lblSeleccionEDT.setText("Seleccione:");
        lblSeleccionEDT.setFont(new Font("Monospaced",Font.CENTER_BASELINE,40));
        lblSeleccionEDT.setForeground(Color.WHITE);
        lblSeleccionEDT.setBounds(lblPEDT.getX() - 15 , lblPEDT.getY() + 225, 350, 40);
        
        Selector.setVisible(true);
        Selector.addItem("-");
        Selector.addItem("Agregar Paquete");
        Selector.addItem("Agregar Entregable");
        Selector.addItemListener(this);
        Selector.setBounds(lblPEDT.getX() + 40, lblPEDT.getY() + 290, 400, 40);
        
        addToContainer(Selector, lblPEDT, lblSeleccionEDT); 
        container.validate();
        container.repaint();
        
    }

    //Opciones
    private void AgregarPaquete(){
        lblUbicacion.setText("Ingrese Ubicacion:");
        lblUbicacion.setFont(new Font("Monospaced", Font.CENTER_BASELINE, 35));
        lblUbicacion.setForeground(Color.WHITE);
        lblUbicacion.setBounds(lblSeleccionEDT.getX(), lblSeleccionEDT.getY() + 20, 600, 300);
        
        txtPaquetePadre.setBounds(Selector.getX(), lblUbicacion.getY() + 200, Selector.getWidth(), Selector.getHeight());
        
        lblNombrePaquete.setText("Ingrese Nombre Del Paquete:");
        lblNombrePaquete.setFont(new Font("Monospaced", Font.CENTER_BASELINE, 35));
        lblNombrePaquete.setForeground(Color.WHITE);
        lblNombrePaquete.setBounds(lblUbicacion.getX(), txtPaquetePadre.getY() + 20, 600, 170);
        
        txtSeleccion.setBounds(txtPaquetePadre.getX(), lblNombrePaquete.getY() + 130, txtPaquetePadre.getWidth(), txtPaquetePadre.getHeight());
        
        btnGuardar.setText("Guardar");
        btnGuardar.setBounds(txtSeleccion.getX() +20,txtSeleccion.getY() + 80, 100,100 );
        
        
        addToContainer(txtSeleccion, lblUbicacion, txtPaquetePadre, lblNombrePaquete, btnGuardar);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == btnEDT){
            removeOfContainer(lblPrincipal, btnEDT, btnCronograma);
            container.validate();
            container.repaint();
            pantallaEDT();
        }
        if(ae.getSource() == btnGuardar){
            System.out.println("Guardado");
        }
        
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == Selector){
            opcion = (String) Selector.getSelectedItem();
            if (opcion.equals("Agregar Paquete")){
                AgregarPaquete();
            }else if(opcion.equals("Agregar Entregable")){
                
            }
        }
    }
    
    private void addToContainer(JComponent ...objs) {
        for(JComponent obj : objs) {
            container.add(obj);
        }
    }   
    
    private void removeOfContainer(JComponent ...objs) {
        for(JComponent obj : objs) {
            container.remove(obj);
        }
    } 
    
}
