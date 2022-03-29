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
    private int xmax, ymax, numpaquetes;
    private Boolean inEntregable, inPaquete;
    private Container container;
    private JPanel panelMenu, panelHeader, panelScrollMenu;
    private JButton btnBackMain;
    private JButton btnEDT, btnCronograma, btnGuardar, btnGuardarArchivo, btnFileChooser, btnGuardarEntregable;
    private JLabel lblPrincipal, lblEDT, lblNombrePaquete, lblSeleccionEDT, lblUbicacion;
    private JComboBox selectorOption, selectorPaquete;
    private JTextField txtNombrePaqueteNuevo;
    private Arbol arbol;
    private JFileChooser archivoEntregable;
    private ArrayList<String> options;
    private JScrollPane scrollMenu;
    private ImageIcon iconPaquete, iconFile;
    private ArrayList<MenuItem> paquetes;
    private ArrayList<MenuItem> subPaquetes;
    
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
        btnFileChooser = new JButton();
        btnGuardarEntregable = new JButton();
        nombreArchivo = "null";
        inEntregable = false;
        inPaquete = false;
        btnGuardarArchivo = new JButton();
        panelHeader = new JPanel();
        panelMenu = new JPanel();
        scrollMenu = new JScrollPane();
        panelScrollMenu = new JPanel();
        paquetes = new ArrayList<MenuItem>();
        subPaquetes = new ArrayList<MenuItem>();
    }
    
    private void addActionsListener(){
        btnEDT.addActionListener(this);
        btnGuardar.addActionListener(this);  
        selectorOption.addItemListener(this);
        selectorPaquete.addItemListener(this);
        btnFileChooser.addActionListener(this);
        btnGuardarArchivo.addActionListener(this);
        btnGuardarEntregable.addActionListener(this);
        btnBackMain.addActionListener(this);
    }
   //CONFIGURAR BOTON DEL BACK TO MAIN
    
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
        
        scrollMenu.setBorder(null);
        
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
        
        addPaqueteToMenu();
        
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

    
    private void addPaqueteToMenu(){
        iconPaquete = new ImageIcon(getClass().getResource("/Images/paquete.png"));
        iconFile = new ImageIcon(getClass().getResource("/Images/file.png"));
        
        /*//create Submenu del submenu
        MenuItem menuSS1 = new MenuItem(iconPaquete, "subpaquete1.1", null);
        MenuItem menuSS2 = new MenuItem(iconFile, "file1.1", null);
        MenuItem menuSS3 = new MenuItem(iconFile, "file2.1", null);
        
        //Create Submenu
        MenuItem menuS1 = new MenuItem(iconPaquete, "subpaquete1", null, menuSS1, menuSS2, menuSS3);
        MenuItem menuS2 = new MenuItem(iconFile, "file1", null);
        MenuItem menuS3 = new MenuItem(iconFile, "file2", null);
        
        //Paquete
        MenuItem menuP1 = new MenuItem(iconPaquete, "example1", null);
        MenuItem menuP2 = new MenuItem(iconPaquete, "example2", null);
        MenuItem menuP3 = new MenuItem(iconPaquete, "example3", null);
        
        menuP1.addSubMenu(menuS1, menuS2, menuS3);
        
        addMenu(menuP1, menuP2, menuP3);
        */
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
                if(!isBadInput()){
                    agregarAlArbolPaquete();
                    txtNombrePaqueteNuevo.setText("");
                    JOptionPane.showMessageDialog(null, "Paquete Agregado!");
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
                JOptionPane.showMessageDialog(null, "Entregable Agregado!");
            }catch(Exception ex){
                
            }
        }

        if(ae.getSource() == btnGuardarEntregable){
            if (arbol.Existe(arbol.raiz, nombreArchivo)){
                JOptionPane.showMessageDialog(null, "Ya existe un entregable con ese nombre");
            }else{
                if(!isBadInput()){
                    System.out.println("Entre");
                    agregarAlArbolEntregable();
                }
            }
        }    
    }
    
    private boolean isBadInput(){
        if(inPaquete){
            if (txtNombrePaqueteNuevo.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Por favor ingrese el nombre del paquete que desee agregar");
                return true;
            }
            if ("-".equals(paquetePadre)){
                JOptionPane.showMessageDialog(null, "Por favor seleccione el paquete padre");
                return true;
            }      
        }else if (inEntregable){
            if ("-".equals(paquetePadre)){
                JOptionPane.showMessageDialog(null, "Por favor seleccione el paquete del entregable");
                return true;
            }
            if("null".equals(nombreArchivo)){
                JOptionPane.showMessageDialog(null, "Por favor seleccione un entregable");
                return true;
            }
        }
        return false;
    }    
        
    private void agregarAlArbolPaquete(){
        options.add(paquetePadre + "~>" + txtNombrePaqueteNuevo.getText());
        findPaquetePadre();
        arbol.raiz.hijos.InsertaEnPadreCorrecto(arbol.raiz, paquetePadre, txtNombrePaqueteNuevo.getText());
        numpaquetes++;
        
        if(paquetePadre.equals("EDT")){
            MenuItem menuP = new MenuItem(iconPaquete, txtNombrePaqueteNuevo.getText(), null);
            paquetes.add(menuP);
            addMenu(menuP);
            for(MenuItem item: paquetes){
                addMenu(item);
            }
        }else{
            MenuItem menuP = getMenuItem(paquetePadre);
            if(menuP != null){
                MenuItem menuPH = new MenuItem(iconPaquete, txtNombrePaqueteNuevo.getText(), null);
                subPaquetes.add(menuPH);
                menuP.addSubMenuItem(menuPH);
                for(MenuItem item: paquetes){
                    addMenu(item);
                }
            }
        }
        
        selectorPaquete.removeAllItems();
        for(String op: options){
            selectorPaquete.addItem(op);
        }
        validate();
        repaint();
    }
    
    private MenuItem getMenuItem(String padre){
        for(MenuItem item: paquetes){
            if(item.getNombreMenu().equals(padre)){
                return item;
            }
        }
        for(MenuItem item: subPaquetes){
            if(item.getNombreMenu().equals(padre)){
                return item;
            }
        }
        return null;
    }
    
    
    private void agregarAlArbolEntregable(){
        findPaquetePadre();
        arbol.raiz.hijos.InsertaEnPadreCorrecto(arbol.raiz, paquetePadre, nombreArchivo);
        
        MenuItem menuP = getMenuItem(paquetePadre);
        if(menuP != null){
            MenuItem menuPH = new MenuItem(iconFile, nombreArchivo, null);
            menuP.addSubMenuItem(menuPH);
            for(MenuItem item: paquetes){
                 addMenu(item);
            }
        }
    }
    
    private void findPaquetePadre(){
        if (paquetePadre.length()>5){
                int i;
                i = paquetePadre.lastIndexOf(">");
                paquetePadre = paquetePadre.substring(i+1, paquetePadre.length());
        }
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