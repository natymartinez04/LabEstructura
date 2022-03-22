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
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 *
 * @author tllach, nmartinez, dkaty
 */
public class Display extends JFrame implements ItemListener, ActionListener{
    
    int xmax, ymax, numpaquetes, verificaerrores;
    Container container;
    JButton btnEDT, btnCronograma, btnGuardar;
    JLabel lblPrincipal, lblPEDT, lblNombrePaquete, lblSeleccionEDT, lblUbicacion;
    JComboBox selectorOption, selectorPaquete;
    String opcion, paquetePadre;
    JTextField txtNombrePaqueteNuevo;
    Arbol arbol;
    JFileChooser archivoEntregable;
    ArrayList<String> options;

    
    public Display(int xmax,int ymax){
        this.xmax = xmax;
        this.ymax = ymax;
        declaracion();
        pantallaPrincipal(); 
        addActionsListener();
    }
    
    private void declaracion(){
        arbol = new Arbol();
        arbol.raiz = new NodoArbol("EDT");
        container = this.getContentPane();
        lblPrincipal = new JLabel();
        btnEDT = new JButton();
        btnCronograma = new JButton();
        lblPEDT = new JLabel();
        selectorOption = new JComboBox();
        txtNombrePaqueteNuevo = new JTextField();
        lblNombrePaquete = new JLabel();
        lblSeleccionEDT = new JLabel();
        lblUbicacion = new JLabel();
        btnGuardar = new JButton();
        archivoEntregable = new JFileChooser();
        numpaquetes = 0;
        options = new ArrayList<String>();
        options.add("-");
        options.add("EDT");
        selectorPaquete = new JComboBox();
    }
    
    private void addActionsListener(){
        btnEDT.addActionListener(this);
        btnGuardar.addActionListener(this);  
        selectorOption.addItemListener(this);
        selectorPaquete.addItemListener(this);
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
        
        selectorOption.setVisible(true);
        selectorOption.addItem("-");
        selectorOption.addItem("Agregar Paquete");
        selectorOption.addItem("Agregar Entregable");
        selectorOption.setBounds(lblPEDT.getX() + 40, lblPEDT.getY() + 290, 400, 40);
        
        addToContainer(selectorOption, lblPEDT, lblSeleccionEDT); 
        container.validate();
        container.repaint();
        
    }

    //Opciones
    private void GUIAgregarPaquete(){
        lblUbicacion.setText("Seleccione Ubicacion:");
        lblUbicacion.setFont(new Font("Monospaced", Font.CENTER_BASELINE, 35));
        lblUbicacion.setForeground(Color.WHITE);
        lblUbicacion.setBounds(lblSeleccionEDT.getX(), lblSeleccionEDT.getY() + 20, 600, 300);
        
        selectorPaquete.setVisible(true);
        for(String op: options){
            selectorPaquete.addItem(op);
        }
        selectorPaquete.setBounds(selectorOption.getX(), lblUbicacion.getY() + 200, selectorOption.getWidth(), selectorOption.getHeight());
        
        lblNombrePaquete.setText("Ingrese Nombre Del Paquete:");
        lblNombrePaquete.setFont(new Font("Monospaced", Font.CENTER_BASELINE, 35));
        lblNombrePaquete.setForeground(Color.WHITE);
        lblNombrePaquete.setBounds(lblUbicacion.getX(), selectorPaquete.getY() + 20, 600, 170);
        
        txtNombrePaqueteNuevo.setBounds(selectorPaquete.getX(), lblNombrePaquete.getY() + 130, selectorPaquete.getWidth(), selectorPaquete.getHeight());
        
        btnGuardar.setText("Guardar");
        btnGuardar.setForeground(Color.GREEN);
        btnGuardar.setBounds(txtNombrePaqueteNuevo.getX() + 20,txtNombrePaqueteNuevo.getY() + 70, 100 ,40 );
        
        
        addToContainer(txtNombrePaqueteNuevo, lblUbicacion, selectorPaquete, lblNombrePaquete, btnGuardar);
        validate();
        repaint();
    }

    private void GUIAgregarEntregable() {
        lblUbicacion.setText("Seleccione Ubicacion:");
        lblUbicacion.setFont(new Font("Monospaced", Font.CENTER_BASELINE, 35));
        lblUbicacion.setForeground(Color.WHITE);
        lblUbicacion.setBounds(lblSeleccionEDT.getX(), lblSeleccionEDT.getY() + 20, 600, 300);
        
        selectorPaquete.setVisible(true);
        for(String op: options){
            selectorPaquete.addItem(op);
        }
        selectorPaquete.setBounds(selectorOption.getX(), lblUbicacion.getY() + 200, selectorOption.getWidth(), selectorOption.getHeight());
        
        lblNombrePaquete.setText("Adjunte Entregable");
        lblNombrePaquete.setFont(new Font("Monospaced", Font.CENTER_BASELINE, 35));
        lblNombrePaquete.setForeground(Color.WHITE);
        lblNombrePaquete.setBounds(lblUbicacion.getX(), selectorPaquete.getY() + 20, 600, 170);
        
        txtNombrePaqueteNuevo.setBounds(selectorPaquete.getX(), lblNombrePaquete.getY() + 130, selectorPaquete.getWidth(), selectorPaquete.getHeight());
        
        btnGuardar.setText("Guardar");
        btnGuardar.setForeground(Color.GREEN);
        btnGuardar.setBounds(txtNombrePaqueteNuevo.getX() + 20,txtNombrePaqueteNuevo.getY() + 70, 100 ,40 );
        
        
        addToContainer(txtNombrePaqueteNuevo, lblUbicacion, selectorPaquete, lblNombrePaquete, btnGuardar);
        validate();
        repaint();
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
            verificaerrores = 0;
            Boolean seRepite = false;
            seRepite = arbol.Existe(arbol.raiz, txtNombrePaqueteNuevo.getText());
            if (seRepite == true){
                JOptionPane.showMessageDialog(null, "Este paquete ya existe");
                verificaerrores ++;
            }
            verificarInput();
            if (verificaerrores == 0){
                AgregarAlArbolPaquete();
            }
        } 
    }
    
    private void verificarInput(){
        if (txtNombrePaqueteNuevo.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Por favor ingrese el nombre del paquete que desee agregar");
            verificaerrores++;
        }
        if ("-".equals(paquetePadre)){
            JOptionPane.showMessageDialog(null, "Por favor seleccione el paquete padre");
            verificaerrores++;
        }
    }
    
    private void AgregarAlArbolPaquete(){
        arbol.raiz.hijos.InsertaEnPadreCorrecto(arbol.raiz, paquetePadre, txtNombrePaqueteNuevo.getText());
        numpaquetes++;
        options.add(paquetePadre + "~>" + txtNombrePaqueteNuevo.getText());
        selectorPaquete.removeAllItems();
        for(String op: options){
            selectorPaquete.addItem(op);
        }
        validate();
        repaint();
        //selectorPaquete.addItem(paquetePadre + "~>" + txtNombrePaqueteNuevo.getText());
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == selectorOption){
            opcion = (String) selectorOption.getSelectedItem();
            selectorPaquete.removeAllItems();
            if (opcion.equals("Agregar Paquete")){ 
                GUIAgregarPaquete();
            }else if(opcion.equals("Agregar Entregable")){
                //arbol.raiz.hijos.printHijos(arbol.raiz);
                GUIAgregarEntregable(); 
            }
        }
        if(e.getSource() == selectorPaquete){
            paquetePadre = (String) selectorPaquete.getSelectedItem();
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