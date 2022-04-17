package LabEstructuras;

import Menu.MenuItem;
import java.awt.Color;
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
 * @author nmartinez, tllach, dkaty
 * 
 */
public class EDTGui implements ItemListener, ActionListener{
    
    private Display display;
    private JLabel lblEDT, lblSeleccionEDT;
    private JButton btnimprimirReporte, btnBackMain;
    private JPanel panelHeader, panelMenu, panelScrollMenu;
    private JScrollPane scrollMenu, scrollPanelArchivo;
    private JComboBox selectorOption;
    
    private String opcion, paquetePadre, nombreArchivo, rutaPaEscribir;
    private boolean inPaquete, inEntregable;
    private JPanel panelArchivo;
    private JButton btnGuardarEntregable, btnGuardarPaquete, btnFileChooser;
    private JLabel lblNombreArchivo, lblUbicacion, lblNombrePaquete;
    private JComboBox selectorPaquete;
    private JTextField txtNombrePaqueteNuevo;
    private ListaEnlazada options;
    private ImageIcon iconPaquete, iconFile;
    private ListaEnlazada paquetes, subPaquetes;
    private File fileChoose;
    private MenuItem menuPH;
    private JTextArea areaArchivo;
    
    
    public EDTGui(Display display){
       this.display = display;
       declaration();
       addActionListener();
       addResources();
       setupVisualizarArchivo();
   }
    
    private void declaration(){
        lblEDT = new JLabel();
        lblSeleccionEDT = new JLabel();
        selectorOption = new JComboBox();
        btnimprimirReporte = new JButton();
        btnBackMain = new JButton();
        panelHeader = new JPanel();
        panelMenu = new JPanel();
        panelScrollMenu = new JPanel();
        scrollMenu = new JScrollPane();
        
        opcion = "";
        inEntregable = false;
        inPaquete = false;
        btnGuardarEntregable = new JButton();
        btnGuardarPaquete = new JButton();
        btnFileChooser = new JButton();
        lblNombreArchivo = new JLabel();
        lblUbicacion = new JLabel();
        lblNombrePaquete = new JLabel();
        selectorPaquete = new JComboBox();  
        txtNombrePaqueteNuevo = new JTextField();
        options = new ListaEnlazada();
        options.insertN("-");
        options.insertN(display.getNameProject());
        paquetes = new ListaEnlazada();
        subPaquetes = new ListaEnlazada();
        
        panelArchivo = new JPanel();
        areaArchivo = new JTextArea();
        scrollPanelArchivo = new JScrollPane();
        lblNombreArchivo = new JLabel();
        rutaPaEscribir = "c:src\\Files\\";
    }
    
    public void setupEDT(){
        display.getContainer().setBackground(new Color(41, 96, 137));
        
        btnimprimirReporte.setIcon(new ImageIcon(getClass().getResource("/Images/button_imprimir-reporte.png")));
        btnimprimirReporte.setContentAreaFilled(false);
        btnimprimirReporte.setBorder(BorderFactory.createEmptyBorder());
        btnimprimirReporte.setSize(300,100);
        btnimprimirReporte.setLocation(1150,750);
        
        panelHeader.setBounds(0,0, display.getxmax(), display.getymax() / 11);
        panelHeader.setBackground(new Color(99,38,38));
        
        panelMenu.setSize(new Dimension(display.getxmax() / 2 - (display.getxmax() / 7), display.getymax() - (panelHeader.getHeight() + 40)));
        panelMenu.setLocation(0, panelHeader.getHeight());
        
        scrollMenu.setBorder(null);
        
        panelScrollMenu.setLayout(new BoxLayout(panelScrollMenu, BoxLayout.Y_AXIS));
        
        scrollMenu.setViewportView(panelScrollMenu);
        
        GroupLayout panelMenuLayout = new GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(scrollMenu, GroupLayout.DEFAULT_SIZE, display.getxmax() / 2 - (display.getxmax() / 7), Short.MAX_VALUE)
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(scrollMenu, GroupLayout.DEFAULT_SIZE, display.getymax() - (panelHeader.getHeight() + 40), Short.MAX_VALUE)
        );
        
        
        lblEDT.setText(display.getNameProject());
        lblEDT.setFont(new Font("Monospaced", Font.CENTER_BASELINE, 60));
        lblEDT.setSize(110, 60);
        lblEDT.setForeground(Color.LIGHT_GRAY);
        lblEDT.setLocation((display.getxmax() - lblEDT.getWidth())/2 , 25);
        
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
        btnBackMain.setLocation(display.getxmax() - (btnBackMain.getWidth() + display.getxmax() / 20) , display.getymax() - (btnBackMain.getHeight() * 4));

        display.addToContainer(panelMenu, panelHeader, lblSeleccionEDT, selectorOption, btnBackMain,btnimprimirReporte);
        
        display.getContainer().validate();
        display.getContainer().repaint();
    }
    
    private void addActionListener(){
        btnimprimirReporte.addActionListener(this);
        selectorOption.addItemListener(this);
        selectorPaquete.addItemListener(this);
        btnBackMain.addActionListener(this);
        btnGuardarEntregable.addActionListener(this);
        btnGuardarPaquete.addActionListener(this);  
        btnFileChooser.addActionListener(this);
    }
    
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
        
        for(int i = 0; i < options.getTamañoN(); i++){
            selectorPaquete.addItem((String) options.getInfoNodo(i));
        }
        
        selectorPaquete.setBounds(selectorOption.getX(), lblUbicacion.getY() + 200, selectorOption.getWidth(), selectorOption.getHeight());
        
        lblNombrePaquete.setText("Ingrese Nombre Del Paquete:");
        lblNombrePaquete.setFont(new Font("Monospaced", Font.CENTER_BASELINE, 35));
        lblNombrePaquete.setForeground(Color.WHITE);
        lblNombrePaquete.setBounds(lblUbicacion.getX(), selectorPaquete.getY() + 20, 600, 170);
        
        txtNombrePaqueteNuevo.setVisible(true);
        txtNombrePaqueteNuevo.setBounds(selectorPaquete.getX(), lblNombrePaquete.getY() + 130, selectorPaquete.getWidth(), selectorPaquete.getHeight());
        
        btnGuardarPaquete.setVisible(true);
        btnGuardarPaquete.setText("Guardar");
        btnGuardarPaquete.setForeground(Color.BLUE);
        btnGuardarPaquete.setBounds(txtNombrePaqueteNuevo.getX() + 110,txtNombrePaqueteNuevo.getY() + 90, 180 , 40);
        
        btnFileChooser.setVisible(false);
        
        display.addToContainer(txtNombrePaqueteNuevo, lblUbicacion, selectorPaquete, lblNombrePaquete, btnGuardarPaquete,btnimprimirReporte);
        display.validate();
        display.repaint();
    }
    
    private void GUIAgregarEntregable() {
        inPaquete = false;
        inEntregable = true;
        btnGuardarPaquete.setVisible(false);
        txtNombrePaqueteNuevo.setVisible(false);
        
        lblUbicacion.setText("Seleccione Ubicacion:");
        lblUbicacion.setFont(new Font("Monospaced", Font.CENTER_BASELINE, 35));
        lblUbicacion.setForeground(Color.WHITE);
        lblUbicacion.setBounds(lblSeleccionEDT.getX(), lblSeleccionEDT.getY() + 20, 600, 300);
        
        selectorPaquete.setVisible(true);
        
        for(int i = 0; i < options.getTamañoN(); i++){
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
        
        display.addToContainer(btnGuardarEntregable, btnFileChooser, lblUbicacion, selectorPaquete, lblNombrePaquete);
        display.validate();
        display.repaint();
    }
    
    private void agregarAlArbolPaquete(){
        options.insertN(paquetePadre + "~>" + txtNombrePaqueteNuevo.getText());
        findPaquetePadre();
        display.getArbol().getRaiz().getHijos().InsertaEnPadreCorrecto(display.getArbol().getRaiz(), paquetePadre, txtNombrePaqueteNuevo.getText(),false);
        
        if(paquetePadre.equals(display.getNameProject())){
            MenuItem menuP = new MenuItem(iconPaquete, txtNombrePaqueteNuevo.getText(), null, false);
            paquetes.insertN(menuP);
            for(int i = 0; i < paquetes.getTamañoN(); i++){
                MenuItem item = (MenuItem) paquetes.getInfoNodo(i);
                addMenu(item);
            }
        }else{
            MenuItem menuP = getMenuItem(paquetePadre);
            if(menuP != null){
                MenuItem menuPH = new MenuItem(iconPaquete, txtNombrePaqueteNuevo.getText(), null, false);
                subPaquetes.insertN(menuPH);
                menuP.addSubMenuItem(menuPH);
                for(int i = 0; i < paquetes.getTamañoN(); i++){
                    MenuItem item = (MenuItem) paquetes.getInfoNodo(i);
                    addMenu(item);
                }
            }
        }
        
        selectorPaquete.removeAllItems();
        
        for(int i = 0; i < options.getTamañoN(); i++){
            selectorPaquete.addItem((String) options.getInfoNodo(i));
        }

        display.validate();
        display.repaint();
    }
    
    private void agregarAlArbolEntregable(){
        findPaquetePadre();
        display.getArbol().getRaiz().getHijos().InsertaEnPadreCorrecto(display.getArbol().getRaiz(), paquetePadre, nombreArchivo, true);
        
        MenuItem menuP = getMenuItem(paquetePadre);
        if(menuP != null){
            menuPH = new MenuItem(iconFile, nombreArchivo, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    visualizarArchivo();
                }
            } , true);
            menuP.addSubMenuItem(menuPH);
            for(int i = 0; i < paquetes.getTamañoN(); i++){
                addMenu((MenuItem) paquetes.getInfoNodo(i));
            }
        }
    }
    
    private void addMenu(MenuItem ...menu){
        for(MenuItem item: menu){
            panelScrollMenu.remove(item);
            panelScrollMenu.add(item);
            ListaEnlazada subMenu = item.getSubMenu();
            for(int i = 0; i < subMenu.getTamañoN(); i++){
                MenuItem m = (MenuItem) subMenu.getInfoNodo(i);
                addMenu(m);
            }
        }
        panelScrollMenu.revalidate(); 
    }

    private MenuItem getMenuItem(String padre){
        for(int i = 0; i < paquetes.getTamañoN(); i++){
            MenuItem item = (MenuItem) paquetes.getInfoNodo(i);
            if(item.getNombreMenu().equals(padre)){
                return item;
            }
        }
        for(int i = 0; i < subPaquetes.getTamañoN(); i++){
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
            /**if("null".equals(nombreArchivo)){
                JOptionPane.showMessageDialog(null, "Por favor seleccione un entregable");
                return true;
            }**/
        }
        return false;
    }    
   
    private void addResources(){
        iconPaquete = new ImageIcon(getClass().getResource("/Images/paquete.png"));
        iconFile = new ImageIcon(getClass().getResource("/Images/file.png"));
    }
    
    private void crearReporte(){
        int altura;
        display.getArbol().resetCadenas();
        
        display.getArbol().recorridoPreorden(display.getArbol().getRaiz());
        display.getArbol().recorridoInorden(display.getArbol().getRaiz());
        display.getArbol().recorridoPostOrden(display.getArbol().getRaiz());
        
        altura = display.getArbol().AlturaArbol(display.getArbol().getRaiz());
        display.getArbol().setCadenaAltura(String.valueOf(altura)); 
        display.getArbol().NodosTerminales(display.getArbol().getRaiz());
        display.getArbol().NodoSoloUnEntregable(display.getArbol().getRaiz());
    }
    
    private void showReporte(){
        System.out.println("La Altura Del Arbol es: " + display.getArbol().getCadenaAltura());
        System.out.println("Nodos Terminales " +  display.getArbol().getCadenaNodosTerminales());
        System.out.println("Nodos con solo un entregable: " + display.getArbol().getCadenaNodoSoloEntregables());
        
        JFrame frame = new JFrame("Reporte");
        frame.setSize(800, 500);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        
        frame.add(panel);
        
        panel.setLayout(null);
        panel.setSize(800, 500);
        panel.setBackground(Color.BLACK);
        
        JLabel lblTitle = new JLabel("REPORTE");
        JLabel lblTitlePre = new JLabel("Recorrido PreOrden:");
        JLabel lblPre = new JLabel(display.getArbol().getCadenaPreOrder());
        JLabel lblTitleIn = new JLabel("Recorrido InOrden:");
        JLabel lblIn = new JLabel(display.getArbol().getCadenaInorder());
        JLabel lblTitlePost = new JLabel("Recorrido PostOrden:");
        JLabel lblPost = new JLabel(display.getArbol().getCadenaPostOrder());
        
        lblTitle.setFont(new Font("Times", Font.BOLD, 30));
        lblTitle.setSize(200, 30);
        lblTitle.setForeground(Color.white);
        lblTitle.setLocation(300, 20);
        
        lblTitlePre.setFont(new Font("Times", Font.CENTER_BASELINE, 20));
        lblTitlePre.setSize(200, 30);
        lblTitlePre.setForeground(Color.ORANGE);
        lblTitlePre.setLocation(80, lblTitle.getY() + 70);
        
        lblPre.setFont(new Font("Times", Font.CENTER_BASELINE, 18));
        lblPre.setSize(400, 30);
        lblPre.setForeground(Color.LIGHT_GRAY);
        lblPre.setLocation(lblTitlePre.getX(), lblTitlePre.getY() + 40);
        
        lblTitleIn.setFont(new Font("Times", Font.CENTER_BASELINE, 20));
        lblTitleIn.setSize(200, 30);
        lblTitleIn.setForeground(Color.ORANGE);
        lblTitleIn.setLocation(lblPre.getX(), lblPre.getY() + 50);
        
        lblIn.setFont(new Font("Times", Font.CENTER_BASELINE, lblPre.getFont().getSize()));
        lblIn.setSize(400, 30);
        lblIn.setForeground(Color.LIGHT_GRAY);
        lblIn.setLocation(lblTitleIn.getX(), lblTitleIn.getY() + 40);
        
        lblTitlePost.setFont(new Font("Times", Font.CENTER_BASELINE, 20));
        lblTitlePost.setSize(280, 30);
        lblTitlePost.setForeground(Color.ORANGE);
        lblTitlePost.setLocation(lblIn.getX(), lblIn.getY() + 50);
        
        lblPost.setFont(new Font("Times", Font.CENTER_BASELINE, lblPre.getFont().getSize()));
        lblPost.setSize(400, 30);
        lblPost.setForeground(Color.LIGHT_GRAY);
        lblPost.setLocation(lblTitlePost.getX(), lblTitlePost.getY() + 40);
        
        panel.add(lblTitle);
        panel.add(lblTitlePre);
        panel.add(lblPre);
        panel.add(lblTitleIn);
        panel.add(lblIn);
        panel.add(lblTitlePost);
        panel.add(lblPost);
        try{
            display.add(frame);
        }catch(Exception e){
            
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
        panelArchivo.setSize(display.getxmax() - (display.getxmax() / 2 - (display.getxmax() / 7)), display.getymax() - (display.getymax() / 11));
        panelArchivo.setLocation(display.getxmax() / 2 - (display.getxmax() / 7), display.getymax() / 11);
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
        
        display.addToContainer(panelArchivo);
    }
    
    private void visualizarArchivo(){
        if(areaArchivo.getText().length() > 0){
            areaArchivo.setText("");
        }
        try{
           FileReader reader = new FileReader(rutaPaEscribir + display.getNombreObject());
           BufferedReader buffer = new BufferedReader(reader);
           
           areaArchivo.read(buffer, null);
           buffer.close();
           areaArchivo.requestFocus();
        }catch(Exception e){
            
        }
        
        panelArchivo.setVisible(!panelArchivo.isVisible());
    }
       

    @Override
    public void itemStateChanged(ItemEvent ie) {
        if (ie.getSource() == selectorOption){
            opcion = (String) selectorOption.getSelectedItem();
            selectorPaquete.removeAllItems();
            if (opcion.equals("Agregar Paquete")){ 
                GUIAgregarPaquete();
            }else if(opcion.equals("Agregar Entregable")){
                GUIAgregarEntregable(); 
            }
        }
        if(ie.getSource() == selectorPaquete){
            paquetePadre = (String) selectorPaquete.getSelectedItem();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == btnGuardarPaquete){
            //check si el paquete ya existe en el arbol
            if (display.getArbol().Existe(display.getArbol().getRaiz(), txtNombrePaqueteNuevo.getText())){
                JOptionPane.showMessageDialog(null, "Este paquete ya existe");
            }else{
                if(!isBadInput()){
                    agregarAlArbolPaquete();
                    txtNombrePaqueteNuevo.setText("");
                    JOptionPane.showMessageDialog(null, "Paquete Agregado!");
                }
            }
        }
        if (ae.getSource() == btnBackMain){
            display.getContainer().removeAll();
            display.getContainer().validate();
            display.getContainer().repaint();
            display.pantallaPrincipal();
          
        }
        
        if (ae.getSource() == btnimprimirReporte){
            crearReporte();
            showReporte();
        }
        if(ae.getSource() == btnFileChooser){
            try{
                JFileChooser file = new JFileChooser();
                file.setAcceptAllFileFilterUsed(false);
                file.setDialogTitle("Select a .txt file");
                FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files", "txt");
                file.addChoosableFileFilter(restrict);
                file.showOpenDialog(display);
                fileChoose = file.getSelectedFile();
                nombreArchivo = fileChoose.getName();
                lblNombreArchivo.setText(nombreArchivo);
                lblNombreArchivo.setLocation(selectorPaquete.getX() + 100, lblNombrePaquete.getY() + 135);
                lblNombreArchivo.setSize(nombreArchivo.length()*8, 30);
                lblNombreArchivo.setFont(new Font("Arial", Font.ITALIC, 20));
                lblNombreArchivo.setForeground(Color.DARK_GRAY);
                btnFileChooser.setSize(200 ,40);
                btnFileChooser.setLocation(selectorPaquete.getX() + 120 + lblNombreArchivo.getWidth(), lblNombrePaquete.getY() + 130);
                display.addToContainer(lblNombreArchivo);
            }catch(Exception ex){
                
            }
        }

        if(ae.getSource() == btnGuardarEntregable){
            if (display.getArbol().Existe(display.getArbol().getRaiz(), nombreArchivo)){
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
}
