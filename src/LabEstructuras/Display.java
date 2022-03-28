/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LabEstructuras;



import Menu.MenuItem;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
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
    
    private String opcion, paquetePadre, nombreArchivo;
    private int xmax, ymax, numpaquetes, verificaerrores;
    private Boolean inEntregable, inPaquete, isFileAdded;
    private Container container;
    private JPanel panelMenu, panelHeader, panelScrollMenu;
    private JButton btnBackMain;
    private JButton btnEDT, btnCronograma, btnGuardar, btnGuardarArchivo, btnEntregable, btnFileChooser, btnGuardarEntregable;
    private JLabel lblPrincipal, lblEDT, lblNombrePaquete, lblSeleccionEDT, lblUbicacion, lblEscriba;
    private JComboBox selectorOption, selectorPaquete;
    private JTextField txtNombrePaqueteNuevo;
    private JTextArea txtArea;
    private Arbol arbol;
    private JFileChooser archivoEntregable;
    private ArrayList<String> options;
    private JScrollPane scrollMenu;
    
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
        btnBackMain = new JButton();
        lblEDT = new JLabel();
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
        panelHeader = new JPanel();
        panelMenu = new JPanel();
        scrollMenu = new JScrollPane();
        panelScrollMenu = new JPanel();
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
        btnBackMain.addActionListener(this);
    }
   //CONFIGURAR BOTON DEL BACK TO MAIN
    //PREGUNTAR SI SE PUEDE CAMBIAR EL DISEÑO -> Katy y Natalia
    
    
    //Pantallas
    private void pantallaPrincipal(){
        container.setLayout(null);
        container.setBackground(Color.getHSBColor(450, 345, 706));
        
        lblPrincipal.setText("Proyect Management");
        lblPrincipal.setFont(new Font("Monospaced",Font.CENTER_BASELINE,60));
        lblPrincipal.setSize(670, 300);
        lblPrincipal.setForeground(Color.WHITE);
        lblPrincipal.setLocation((xmax - 670)/2, 20);
            
        btnEDT.setText("EDT");
        btnEDT.setSize(300,100);
        btnEDT.setLocation(((xmax - 670)/2)-50,400);
            
        btnCronograma.setText("Cronograma");
        btnCronograma.setSize(300,100);
        btnCronograma.setLocation((xmax/2)+50,400);
            
        addToContainer(lblPrincipal, btnEDT, btnCronograma);
    }
    
    private void pantallaEDT(){
        container.setBackground(Color.getHSBColor(480, 345, 706));
        
        panelHeader.setBounds(0,0, xmax, ymax / 11);
        panelHeader.setBackground(new Color(42, 132, 184));
        
        panelMenu.setSize(new Dimension(xmax / 2 - (xmax / 7), ymax - (panelHeader.getHeight() + 40)));
        panelMenu.setLocation(0, panelHeader.getHeight());
        
        //scrollMenu.setBounds(panelMenu.getX(), panelMenu.getY(), panelMenu.getWidth(), panelMenu.getHeight());
        scrollMenu.setBorder(null);
        
        //panelScrollMenu.setBounds(scrollMenu.getX(), scrollMenu.getY(), scrollMenu.getWidth(), scrollMenu.getHeight());
        panelScrollMenu.setLayout(new BoxLayout(panelScrollMenu, BoxLayout.Y_AXIS));
        
        scrollMenu.setViewportView(panelScrollMenu);
        
        GroupLayout panelMenuLayout = new GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(scrollMenu, GroupLayout.DEFAULT_SIZE, xmax / 2 - (xmax / 7), Short.MAX_VALUE)
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(scrollMenu, GroupLayout.DEFAULT_SIZE, ymax - (panelHeader.getHeight() + 40), Short.MAX_VALUE)
        );
        
        execute();
        
        lblEDT.setText("EDT");
        lblEDT.setFont(new Font("Monospaced", Font.CENTER_BASELINE, 60));
        lblEDT.setSize(110, 60);
        lblEDT.setForeground(Color.LIGHT_GRAY);
        lblEDT.setLocation((xmax - lblEDT.getWidth())/2 , 25);
        
        panelHeader.add(lblEDT);
        
        lblSeleccionEDT.setText("Seleccione:");
        lblSeleccionEDT.setFont(new Font("Monospaced",Font.CENTER_BASELINE,40));
        lblSeleccionEDT.setForeground(Color.WHITE);
        lblSeleccionEDT.setBounds(lblEDT.getX() - 105 , lblEDT.getY() + 245, 350, 40);
        
        selectorOption.setVisible(true);
        selectorOption.addItem("-");
        selectorOption.addItem("Agregar Paquete");
        selectorOption.addItem("Agregar Entregable");
        selectorOption.setBounds(lblSeleccionEDT.getX() + 60, lblSeleccionEDT.getY() + 60, 400, 40);
        
        btnBackMain.setSize(250, 80);
        btnBackMain.setLocation(xmax - (btnBackMain.getWidth() + xmax / 18) , ymax - (btnBackMain.getHeight() * 2));
        btnBackMain.setText("Regresar");
        
        addToContainer(panelMenu, panelHeader, lblSeleccionEDT, selectorOption, btnBackMain); 
        
        container.validate();
        container.repaint();
    }

    
    //no terminado
    //falta revisar el tamaño
    private void execute(){
        ImageIcon iconPaquete = new ImageIcon(getClass().getResource("/Menu/paquete.png"));
        ImageIcon iconFile = new ImageIcon(getClass().getResource("/Menu/file.png"));
               
        //Create Submenu
        MenuItem menuS1 = new MenuItem(iconPaquete, "subpaquete1", null);
        MenuItem menuS2 = new MenuItem(iconFile, "file1", null);
        MenuItem menuS3 = new MenuItem(iconFile, "file2", null);
        
        //Paquete
        MenuItem menuP1 = new MenuItem(iconPaquete, "example1", null, menuS1, menuS2, menuS3);
        MenuItem menuP2 = new MenuItem(iconPaquete, "example2", null);
        MenuItem menuP3 = new MenuItem(iconPaquete, "example3", null);
        
        addMenu(menuP1, menuP2, menuP3);
    }
    
    private void addMenu(MenuItem ...menu){
        for(MenuItem item: menu){
            panelScrollMenu.add(item);
            ArrayList<MenuItem> subMenu = item.getSubMenu();
            for(MenuItem m: subMenu){
                addMenu(m);
            }
        }
        panelScrollMenu.revalidate(); 
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
        btnGuardar.setForeground(Color.BLUE);
        btnGuardar.setBounds(txtNombrePaqueteNuevo.getX() + 110,txtNombrePaqueteNuevo.getY() + 90, 180 , 40);
        
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
        findPaquetePadre();
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
        findPaquetePadre();
        arbol.raiz.hijos.InsertaEnPadreCorrecto(arbol.raiz, paquetePadre, nombreArchivo);
    }
    
    private void findPaquetePadre(){
        if (paquetePadre.length()>5){
                int i;
                i = paquetePadre.lastIndexOf(">");
                paquetePadre = paquetePadre.substring(i+1, paquetePadre.length());
        }
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