package LabEstructuras;



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
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author tllach, nmartinez, dkaty
 * 
 **/

public class Display extends JFrame implements ItemListener, ActionListener{
    
    private String opcion, paquetePadre, nombreArchivo, rutaPaEscribir, proyecto;
    private static String nombreObject;
    private int xmax, ymax, numpaquetes;
    private Boolean inEntregable, inPaquete;
    private Arbol arbol;
    private Container container;
    private JPanel panelMenu, panelHeader, panelScrollMenu, panelArchivo;
    private JButton btnBackMain;
    private JButton btnEDT, btnCronograma, btnGuardar, btnGuardarArchivo, btnFileChooser, btnGuardarEntregable, btnimprimirReporte;
    private JLabel lblPrincipal, lblEDT, lblNombrePaquete, lblSeleccionEDT, lblUbicacion, lblNombreArchivo, imagen,imagen2;
    private JComboBox selectorOption, selectorPaquete;
    private JTextField txtNombrePaqueteNuevo;
    private JFileChooser archivoEntregable;
    private JScrollPane scrollMenu, scrollPanelArchivo;
    private JTextArea areaArchivo;
    private ImageIcon iconPaquete, iconFile;
    private ListaEnlazada options;
    private ListaEnlazada paquetes, subPaquetes;
    private File fileChoose;
    private MenuItem menuPH;
    
    public Display(int xmax, int ymax, String proyecto){
        this.xmax = xmax;
        this.ymax = ymax;
        this.proyecto = proyecto;
        arbol = new Arbol();
        arbol.raiz = new NodoArbol(proyecto);
        options = new ListaEnlazada();
        options.insertN("-");
        options.insertN(proyecto);
        declaracion();
        pantallaPrincipal(); 
        addActionsListener();
        addResources();
        setupVisualizarArchivo();
    }
    
    private void declaracion(){
        container = this.getContentPane();
        lblPrincipal = new JLabel();
        btnEDT = new JButton();
        btnCronograma = new JButton();
        btnBackMain = new JButton();
        btnimprimirReporte = new JButton();
        lblEDT = new JLabel();
        imagen = new JLabel();
        imagen2 = new JLabel();
        selectorOption = new JComboBox();
        txtNombrePaqueteNuevo = new JTextField();
        lblNombrePaquete = new JLabel();
        lblSeleccionEDT = new JLabel();
        lblUbicacion = new JLabel();
        btnGuardar = new JButton();
        archivoEntregable = new JFileChooser();
        numpaquetes = 0;
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
        paquetes = new ListaEnlazada();
        subPaquetes = new ListaEnlazada();
        rutaPaEscribir = "c:src\\Files\\";
        panelArchivo = new JPanel();
        areaArchivo = new JTextArea();
        scrollPanelArchivo = new JScrollPane();
        lblNombreArchivo = new JLabel();
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
        btnimprimirReporte.addActionListener(this);
    }
    //CONFIGURAR BOTON DEL BACK TO MAIN
    
    //Pantallas
    private void pantallaPrincipal(){
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
    
    private void pantallaEDT(){
        container.setBackground(new Color(41, 96, 137));
        
        btnimprimirReporte.setIcon(new ImageIcon(getClass().getResource("/Images/button_imprimir-reporte.png")));
        btnimprimirReporte.setContentAreaFilled(false);
        btnimprimirReporte.setBorder(BorderFactory.createEmptyBorder());
        btnimprimirReporte.setSize(300,100);
        btnimprimirReporte.setLocation(1150,750);
        
        panelHeader.setBounds(0,0, xmax, ymax / 11);
        panelHeader.setBackground(new Color(99,38,38));
        
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
        
        
        lblEDT.setText(proyecto);
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
        
        btnBackMain.setIcon(new ImageIcon(getClass().getResource("/Images/button_regresar.png")));
        btnBackMain.setContentAreaFilled(false);
        btnBackMain.setBorder(BorderFactory.createEmptyBorder());
        btnBackMain.setSize(250, 80);
        btnBackMain.setLocation(xmax - (btnBackMain.getWidth() + xmax / 20) , ymax - (btnBackMain.getHeight() * 2));

        
        addToContainer(panelMenu, panelHeader, lblSeleccionEDT, selectorOption, btnBackMain,btnimprimirReporte); 
        
        container.validate();
        container.repaint();
    }

    private void addResources(){
        iconPaquete = new ImageIcon(getClass().getResource("/Images/paquete.png"));
        iconFile = new ImageIcon(getClass().getResource("/Images/file.png"));
    }
    
    private void addMenu(MenuItem ...menu){
        for(MenuItem item: menu){
            panelScrollMenu.remove(item);
            panelScrollMenu.add(item);
            ListaEnlazada subMenu = item.getSubMenu();
            for(int i = 0; i < subMenu.getTamaño(); i++){
                MenuItem m = (MenuItem) subMenu.getInfoNodo(i);
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
        lblNombreArchivo.setText("");
        
        lblUbicacion.setText("Seleccione Ubicacion:");
        lblUbicacion.setFont(new Font("Monospaced", Font.CENTER_BASELINE, 35));
        lblUbicacion.setForeground(Color.WHITE);
        lblUbicacion.setBounds(lblSeleccionEDT.getX(), lblSeleccionEDT.getY() + 20, 600, 300);
        
        selectorPaquete.setVisible(true);
        
        for(int i = 0; i < options.getTamaño(); i++){
            selectorPaquete.addItem((String) options.getInfoNodo(i));
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
        
        addToContainer(txtNombrePaqueteNuevo, lblUbicacion, selectorPaquete, lblNombrePaquete, btnGuardar,btnimprimirReporte);
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
        
        for(int i = 0; i < options.getTamaño(); i++){
            selectorPaquete.addItem((String) options.getInfoNodo(i));
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
            removeOfContainer(lblPrincipal, btnEDT, btnCronograma,imagen,imagen2);
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
        
        if (ae.getSource() == btnimprimirReporte){
            int altura;
            
            altura = arbol.AlturaArbol(arbol.raiz);
            System.out.println("Altura del árbol es: "+altura);
            
            System.out.print("Nodos Terminales: ");
            arbol.NodosTerminales(arbol.raiz);
            
            System.out.println("Recorrido en Inorden:");
            arbol.Inorden(arbol.raiz);
            
           
        }
        
        if(ae.getSource() == btnFileChooser){
            try{
                JFileChooser file = new JFileChooser();
                file.setAcceptAllFileFilterUsed(false);
                file.setDialogTitle("Select a .txt file");
                FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files", "txt");
                file.addChoosableFileFilter(restrict);
                file.showOpenDialog(this);
                fileChoose = file.getSelectedFile();
                nombreArchivo = fileChoose.getName();
                lblNombreArchivo.setText(nombreArchivo);
                lblNombreArchivo.setLocation(selectorPaquete.getX() + 100, lblNombrePaquete.getY() + 135);
                lblNombreArchivo.setSize(nombreArchivo.length()*8, 30);
                lblNombreArchivo.setFont(new Font("Arial", Font.ITALIC, 20));
                lblNombreArchivo.setForeground(Color.DARK_GRAY);
                btnFileChooser.setSize(200 ,40);
                btnFileChooser.setLocation(selectorPaquete.getX() + 120 + lblNombreArchivo.getWidth(), lblNombrePaquete.getY() + 130);
                addToContainer(lblNombreArchivo);
            }catch(Exception ex){
                
            }
        }

        if(ae.getSource() == btnGuardarEntregable){
            if (arbol.Existe(arbol.raiz, nombreArchivo)){
                JOptionPane.showMessageDialog(null, "Ya existe un entregable con ese nombre");
            }else{
                if(!isBadInput()){
                    agregarAlArbolEntregable();
                    JOptionPane.showMessageDialog(null, "Entregable Agregado!");
                    btnFileChooser.setBounds(selectorPaquete.getX(), lblNombrePaquete.getY() + 130, selectorPaquete.getWidth(), selectorPaquete.getHeight());
                    lblNombreArchivo.setText("");
                    selectorPaquete.setSelectedIndex(0);
                    guardarArchivo();
                }
            }
        }    
    }
    
    private void creacionArchivo(){
        File archivo = new File(rutaPaEscribir + nombreArchivo);
        try{
            archivo.createNewFile();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    
    private void guardarArchivo(){
        creacionArchivo();
        FileWriter writer = null;
        try{
            //encuentra el archivo
            writer = new FileWriter(rutaPaEscribir + nombreArchivo);
            BufferedWriter bfwriter = new BufferedWriter(writer);
            Scanner scanner = new Scanner(fileChoose);
            while(scanner.hasNext()){
                bfwriter.write(scanner.nextLine() + "\n");
            }
            bfwriter.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    private void setupVisualizarArchivo(){
        panelArchivo.setSize(xmax - (xmax / 2 - (xmax / 7)), ymax - (ymax / 11));
        panelArchivo.setLocation(xmax / 2 - (xmax / 7), ymax / 11);
        panelArchivo.setBackground(Color.DARK_GRAY);
        panelArchivo.setLayout(null);
        
        areaArchivo = new JTextArea();
        areaArchivo.setSize(panelArchivo.getWidth() - 10, panelArchivo.getHeight() - 42);
        areaArchivo.setLocation(panelArchivo.getX() - 533 , panelHeader.getHeight());
        
        scrollPanelArchivo = new JScrollPane(areaArchivo);
        scrollPanelArchivo.setEnabled(false);
        scrollPanelArchivo.setSize(panelArchivo.getWidth() - 10, panelArchivo.getHeight() - 42);
        scrollPanelArchivo.setLocation(panelArchivo.getX() - 533 , panelHeader.getHeight());
        
        panelArchivo.add(scrollPanelArchivo);
        panelArchivo.setVisible(false);
        
        addToContainer(panelArchivo);
    }
    
    private void visualizarArchivo(){
        if(areaArchivo.getText().length() > 0){
            areaArchivo.setText("");
        }
        try{
           FileReader reader = new FileReader(rutaPaEscribir + nombreObject);
           BufferedReader buffer = new BufferedReader(reader);
           
           areaArchivo.read(buffer, null);
           buffer.close();
           areaArchivo.requestFocus();
        }catch(Exception e){
            
        }
        
        panelArchivo.setVisible(!panelArchivo.isVisible());
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
        options.insertN(paquetePadre + "~>" + txtNombrePaqueteNuevo.getText());
        findPaquetePadre();
        arbol.raiz.hijos.InsertaEnPadreCorrecto(arbol.raiz, paquetePadre, txtNombrePaqueteNuevo.getText());
        numpaquetes++;
        
        if(paquetePadre.equals(proyecto)){
            MenuItem menuP = new MenuItem(iconPaquete, txtNombrePaqueteNuevo.getText(), null, false);
            paquetes.insertN(menuP);
            for(int i = 0; i < paquetes.getTamaño(); i++){
                MenuItem item = (MenuItem) paquetes.getInfoNodo(i);
                addMenu(item);
            }
        }else{
            MenuItem menuP = getMenuItem(paquetePadre);
            if(menuP != null){
                MenuItem menuPH = new MenuItem(iconPaquete, txtNombrePaqueteNuevo.getText(), null, false);
                subPaquetes.insertN(menuPH);
                menuP.addSubMenuItem(menuPH);
                for(int i = 0; i < paquetes.getTamaño(); i++){
                    MenuItem item = (MenuItem) paquetes.getInfoNodo(i);
                    addMenu(item);
                }
            }
        }
        
        selectorPaquete.removeAllItems();
        
        for(int i = 0; i < options.getTamaño(); i++){
            selectorPaquete.addItem((String) options.getInfoNodo(i));
        }

        validate();
        repaint();
    }
    
    private void agregarAlArbolEntregable(){
        findPaquetePadre();
        arbol.raiz.hijos.InsertaEnPadreCorrecto(arbol.raiz, paquetePadre, nombreArchivo);
        
        MenuItem menuP = getMenuItem(paquetePadre);
        if(menuP != null){
            menuPH = new MenuItem(iconFile, nombreArchivo, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    visualizarArchivo();
                }
            } , true);
            menuP.addSubMenuItem(menuPH);
            for(int i = 0; i < paquetes.getTamaño(); i++){
                addMenu((MenuItem) paquetes.getInfoNodo(i));
            }
        }
    }
    
    private MenuItem getMenuItem(String padre){
        for(int i = 0; i < paquetes.getTamaño(); i++){
            MenuItem item = (MenuItem) paquetes.getInfoNodo(i);
            if(item.getNombreMenu().equals(padre)){
                return item;
            }
        }
        for(int i = 0; i < subPaquetes.getTamaño(); i++){
            MenuItem item = (MenuItem) subPaquetes.getInfoNodo(i);
            if(item.getNombreMenu().equals(padre)){
                return item;
            }
        }
        return null;
    }
    
    private void findPaquetePadre(){
        if (paquetePadre.length() > 5){
            int i = paquetePadre.lastIndexOf(">");
            paquetePadre = paquetePadre.substring(i + 1, paquetePadre.length());
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
    
    public static void setNombreObject(String nombre){
        Display.nombreObject = nombre;
    }
    
}
