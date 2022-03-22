/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LabEstructuras;



import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author tllach, nmartinez, dkaty
 */
public class Display extends JFrame implements ItemListener, ActionListener{
    
    String opcion, paquetePadre, nombreArchivo;
    int xmax, ymax, numpaquetes, verificaerrores;
    Boolean inEntregable, inPaquete, isFileAdded;
    Container container;
    JButton btnEDT, btnCronograma, btnGuardar, btnGuardarArchivo, btnEntregable, btnFileChooser, btnGuardarEntregable;
    JLabel lblPrincipal, lblPEDT, lblNombrePaquete, lblSeleccionEDT, lblUbicacion, lblEscriba;
    JComboBox selectorOption, selectorPaquete;
    JTextField txtNombrePaqueteNuevo;
    JTextArea txtArea;
    Arbol arbol;
    JFileChooser archivoEntregable;
    ArrayList<String> options;

    public Display(int xmax, int ymax){
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
        btnEntregable = new JButton();
        btnFileChooser = new JButton();
        btnGuardarEntregable = new JButton();
        nombreArchivo = "null";
        inEntregable = false;
        inPaquete = false;
        isFileAdded = false;
        lblEscriba = new JLabel();
        txtArea = new JTextArea();
        btnGuardarArchivo = new JButton();
    }
    
    private void addActionsListener(){
        btnEDT.addActionListener(this);
        btnGuardar.addActionListener(this);  
        selectorOption.addItemListener(this);
        selectorPaquete.addItemListener(this);
        btnFileChooser.addActionListener(this);
        btnEntregable.addActionListener(this);
        btnGuardarArchivo.addActionListener(this);
        btnGuardarEntregable.addActionListener(this);
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
        inPaquete = true;
        inEntregable = false;
        btnEntregable.setVisible(false);
        btnGuardarEntregable.setVisible(false);
        
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
        
        txtNombrePaqueteNuevo.setVisible(true);
        txtNombrePaqueteNuevo.setBounds(selectorPaquete.getX(), lblNombrePaquete.getY() + 130, selectorPaquete.getWidth(), selectorPaquete.getHeight());
        
        btnGuardar.setVisible(true);
        btnGuardar.setText("Guardar");
        btnGuardar.setForeground(Color.GREEN);
        btnGuardar.setBounds(txtNombrePaqueteNuevo.getX() + 20,txtNombrePaqueteNuevo.getY() + 70, 100 ,40 );
        
        btnFileChooser.setVisible(false);
        
        addToContainer(txtNombrePaqueteNuevo, lblUbicacion, selectorPaquete, lblNombrePaquete, btnGuardar);
        validate();
        repaint();
    }

    private void GUIAgregarEntregable() {
        inPaquete = false;
        inEntregable = true;
        btnGuardar.setVisible(false);
        txtNombrePaqueteNuevo.setVisible(false);
        
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
        /**
        btnEntregable.setVisible(true);
        btnEntregable.setText("Crear Entregable");
        btnEntregable.setForeground(Color.RED);
        btnEntregable.setBounds(selectorPaquete.getX(), lblNombrePaquete.getY() + 130, selectorPaquete.getWidth(), selectorPaquete.getHeight());
        **/
        btnFileChooser.setVisible(true);
        btnFileChooser.setText("Importar Entregable");
        btnFileChooser.setForeground(Color.RED);
        btnFileChooser.setBounds(selectorPaquete.getX(), lblNombrePaquete.getY() + 130, selectorPaquete.getWidth(), selectorPaquete.getHeight());
        
        btnGuardarEntregable.setVisible(true);        
        btnGuardarEntregable.setText("Guardar");
        btnGuardarEntregable.setForeground(Color.ORANGE);
        btnGuardarEntregable.setBounds(selectorPaquete.getX() + 110, btnFileChooser.getY() + 90, 180 ,40 );
        
        addToContainer(btnGuardarEntregable, btnFileChooser, lblUbicacion, selectorPaquete, lblNombrePaquete);
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
            //check si el paquete ya existe en el arbol
            if (arbol.Existe(arbol.raiz, txtNombrePaqueteNuevo.getText())){
                JOptionPane.showMessageDialog(null, "Este paquete ya existe");
            }else{
                if(!verificarBadInput()){
                    agregarAlArbolPaquete();
                    txtNombrePaqueteNuevo.setText("");
                }
            }
        } 
        
        if(ae.getSource() == btnFileChooser){
            try{
                JFileChooser file = new JFileChooser();
                file.setAcceptAllFileFilterUsed(false);
                file.setDialogTitle("Select a .txt file");
                FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files", "txt");
                file.addChoosableFileFilter(restrict);
                file.showOpenDialog(this);
                File fileChoose = file.getSelectedFile();
                nombreArchivo = fileChoose.getName();
                System.out.println(nombreArchivo);
                isFileAdded = true;  
            }catch(Exception ex){
                
            }
        }
        
        if(ae.getSource() == btnEntregable){
            escribirArchivo();
        }
        
        if(ae.getSource() == btnGuardarEntregable){
            if (arbol.Existe(arbol.raiz, nombreArchivo)){
                JOptionPane.showMessageDialog(null, "Ya existe un entregable con ese nombre");
            }else{
                if(!verificarBadInput()){
                    agregarAlArbolEntregable();
                }
            }
        }    
    }
    
    private boolean verificarBadInput(){
        if(inPaquete){
            if (txtNombrePaqueteNuevo.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Por favor ingrese el nombre del paquete que desee agregar");
                verificaerrores++;
                return true;
            }
            if ("-".equals(paquetePadre)){
                JOptionPane.showMessageDialog(null, "Por favor seleccione el paquete padre");
                verificaerrores++;
                return true;
            }      
        }else if (inEntregable){
            if ("-".equals(paquetePadre)){
                JOptionPane.showMessageDialog(null, "Por favor seleccione el paquete del entregable");
                verificaerrores++;
                return true;
            }
            if("null".equals(nombreArchivo)){
                JOptionPane.showMessageDialog(null, "Por favor seleccione un entregable");
            }
        }
        return false;
    }    
        
    private void agregarAlArbolPaquete(){
        options.add(paquetePadre + "~>" + txtNombrePaqueteNuevo.getText());
        if (paquetePadre.length()>5){
                int i;
                i = paquetePadre.lastIndexOf(">");
                paquetePadre = paquetePadre.substring(i+1, paquetePadre.length());
        }
        arbol.raiz.hijos.InsertaEnPadreCorrecto(arbol.raiz, paquetePadre, txtNombrePaqueteNuevo.getText());
        numpaquetes++;
        selectorPaquete.removeAllItems();
        for(String op: options){
            selectorPaquete.addItem(op);
        }
        validate();
        repaint();
    }

    private void agregarAlArbolEntregable(){
        if (paquetePadre.length()>5){
                int i;
                i = paquetePadre.lastIndexOf(">");
                paquetePadre = paquetePadre.substring(i+1, paquetePadre.length());
        }
        arbol.raiz.hijos.InsertaEnPadreCorrecto(arbol.raiz, paquetePadre, nombreArchivo);
    }
    
    private void escribirArchivo(){
        container.setBackground(Color.getHSBColor(480, 345, 706));
        
        lblEscriba.setText("Escriba los datos a ingresar en su archivo");
        lblEscriba.setFont(new Font("Monospaced",Font.CENTER_BASELINE,60));
        lblEscriba.setSize(110, 40);
        lblEscriba.setForeground(Color.BLACK);
        lblEscriba.setLocation((xmax - lblEscriba.getWidth()) / 2  , 60);
        
        txtArea.setSize(500,100);
        txtArea.setLocation((xmax/2)+50,400);
        
        btnGuardarArchivo.setText("Guardar Archivo");
        btnGuardarArchivo.setSize(70,40);
        btnGuardarArchivo.setLocation((xmax/2)+80,300);
        
        addToContainer(lblEscriba, txtArea, btnGuardarArchivo);
        container.validate();
        container.repaint();
    }

    
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == selectorOption){
            opcion = (String) selectorOption.getSelectedItem();
            selectorPaquete.removeAllItems();
            if (opcion.equals("Agregar Paquete")){ 
                GUIAgregarPaquete();
            }else if(opcion.equals("Agregar Entregable")){
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