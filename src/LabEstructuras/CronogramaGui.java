
package LabEstructuras;

import LabEstructuras.Grafos.Grafo;
import LabEstructuras.Grafos.Lienzo;
import LabEstructuras.Grafos.Vertice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BorderFactory;
import javax.swing.*;

/**
 *
 * @author nmartinez, dkaty, tllach
 */
public class CronogramaGui implements ItemListener, ActionListener{
    
    private Display display;
    private JPanel menuPanel;
    
    private int id = 0;
    private JPanel panelHeader, panelBody;
    private JLabel lblCronograma, lblEntregables, lblDuracion, lblCosto, lblDependencia, lblPrecedentes;
    private JComboBox selectorEntregables, selectorDependencia, selectorEntregablesPrecedencia;
    private JLabel lblDias, lblHrs;
    private JTextField txtDuracionDias, txtDuracionHrs, txtCostoField;
    private JButton btnBackMain, btnMenu, btnGuardar, btnAgregar, btnIrAEDT, btnVisualizar, btnModificarInfo;
    private ListaEnlazada listaEntregables, listaEntregablesPrecedencia;
    private Grafo grafo;
    private Boolean hayEntregableInicial;
    private Lienzo lienzo;
    int menu;
    
    public CronogramaGui(Display display){
        this.display = display;
        declaration();
        addActionListener(); 
    }

    private void declaration(){
        menuPanel = new JPanel();
        selectorEntregables = new JComboBox();
        selectorDependencia = new JComboBox();
        selectorEntregablesPrecedencia = new JComboBox();
        lblCronograma = new JLabel();
        lblEntregables = new JLabel();
        lblDuracion = new JLabel();
        lblCosto = new JLabel();
        lblDependencia = new JLabel();
        lblPrecedentes = new JLabel();
        lblDias = new JLabel();
        lblHrs = new JLabel();
        txtDuracionDias = new JTextField();
        txtDuracionHrs = new JTextField();  
        txtCostoField = new JTextField();
        btnMenu = new JButton();
        btnGuardar = new JButton();
        btnBackMain = new JButton();
        btnAgregar = new JButton();
        btnIrAEDT = new JButton();
        btnModificarInfo = new JButton();
        btnVisualizar = new JButton();
        panelHeader = new JPanel();
        panelBody = new JPanel();
        grafo = new Grafo();
        hayEntregableInicial = false;
    }
    
    private void addActionListener(){
        btnBackMain.addActionListener(this);
        btnMenu.addActionListener(this);
        btnGuardar.addActionListener(this);
        selectorEntregables.addItemListener(this);
        selectorDependencia.addItemListener(this);
        selectorEntregablesPrecedencia.addActionListener(this);
        btnIrAEDT.addActionListener(this);
        btnVisualizar.addActionListener(this);
        btnModificarInfo.addActionListener(this);
    }
    
    public void setUpCronograma(ListaEnlazada listaEntregables){
        menu = 0;
        this.listaEntregables = listaEntregables;
        this.listaEntregablesPrecedencia = listaEntregables;
        
        Boolean accion = HayEntregables(listaEntregables);
        if (accion){
            addVerticesAGrafo();
            display.getContainer().setBackground(new Color(200, 227, 250));

            lblCronograma.setText("Cronograma");
            lblCronograma.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 57));
            lblCronograma.setSize(350, 60);
            lblCronograma.setForeground(Color.BLACK);
            lblCronograma.setLocation((display.getXmax() - lblCronograma.getWidth())/2 , 22);

            btnMenu.setIcon(new ImageIcon(getClass().getResource("/Images/menu.png")));
            btnMenu.setBounds(30, 30, 33, 33);
            
            panelHeader.setBounds(0,0, display.getXmax(), display.getYmax() / 11);
            panelHeader.setBackground(new Color(255, 122, 202));
            panelHeader.setLayout(null);

            setupBtns(btnMenu);
            addObjectToPanel(panelHeader, lblCronograma, btnMenu);

            panelBody.setBounds(0, panelHeader.getHeight(), display.getXmax(), display.getYmax());
            panelBody.setBackground(new Color(200, 227, 250));
            panelBody.setLayout(null);
            
            lienzo = new Lienzo(listaEntregables);
            lienzo.setBounds(0, 0, 200, 200);
            lienzo.setBackground(Color.BLACK);
            lienzo.setVisible(true);
            //panelBody.add(lienzo);
            
            display.addToContainer(lienzo, panelHeader, panelBody);
            
            display.getContainer().revalidate();
            display.getContainer().repaint();
            
        }else{
            display.getContainer().removeAll();
            display.getContainer().validate();
            display.getContainer().repaint();
            display.pantallaPrincipal(); 
        }
    }
    
    private void ListaPrecedencia(String EntregableSeleccionado){
        NodoLista p = listaEntregables.getPtr();
        selectorEntregablesPrecedencia.removeAllItems();
        selectorEntregablesPrecedencia.addItem("-");
        for(int i = 0; i < listaEntregables.getTamaño(); i++){
            if (p.getNodoArbol().getDato() != EntregableSeleccionado){
                selectorEntregablesPrecedencia.addItem(p.getNodoArbol().getDato());
            }
            p = p.getLink();
        }
    }
    
    private void setPanelBody(){

        lblEntregables.setText("Entregables:");
        lblEntregables.setBounds(120, panelHeader.getHeight() + 30, 350, 45);
        
        selectorEntregables.addItem("-");
        AddEntregables();
        
        selectorEntregables.setVisible(true);
        selectorEntregables.setBounds(lblEntregables.getX() + 50, 
                    lblEntregables.getY() + lblEntregables.getHeight() + 25, 
                    400, 
                    40);
        if (selectorDependencia.getItemCount() < 3){
            selectorDependencia.addItem("-");
            selectorDependencia.addItem("Si");
            selectorDependencia.addItem("No");
        }
        
        selectorDependencia.setVisible(true);
        selectorDependencia.setBounds(750, 190, 400, 40);
        
        selectorEntregablesPrecedencia.addItem("-");
        
        btnGuardar.setText("Guardar");
        btnGuardar.setForeground(Color.BLUE);
        btnGuardar.setBackground(Color.WHITE);
        btnGuardar.setBounds(620 ,600, 180, 40);
        
        lblDuracion.setText("Duracion:");
        lblDuracion.setBounds(lblEntregables.getX(), 
                selectorEntregables.getY() + selectorEntregables.getHeight() + 50, 350, 45);
        
        txtDuracionDias.setBounds(selectorEntregables.getX() + 40, lblDuracion.getY()+ lblDuracion.getHeight() + 25, 40, 30);
        lblDias.setText(": Dias");
        lblDias.setBounds(txtDuracionDias.getX() + txtDuracionDias.getWidth() + 2, txtDuracionDias.getY() + 5, 60, 20);
        
        txtDuracionHrs.setBounds(lblDias.getX() + lblDias.getWidth() + 4, txtDuracionDias.getY(), txtDuracionDias.getWidth(), txtDuracionDias.getHeight());
        lblHrs.setText(": Horas");
        lblHrs.setBounds(txtDuracionHrs.getX() + txtDuracionHrs.getWidth() + 2, lblDias.getY(), 80, 20);        
        
        lblCosto.setText("Costo:");
        lblCosto.setBounds(lblDuracion.getX(), 
                txtDuracionDias.getY() + txtDuracionDias.getHeight() + 50, 300, 45);
        txtCostoField.setBounds(lblCosto.getX() + 50, 
                lblCosto.getY() + lblCosto.getHeight() + 25, 300, 40);
        
        
        lblDependencia.setText("Tiene precedentes?");
        lblDependencia.setBounds(lblDuracion.getX()*6, lblEntregables.getY(), 330, 45);
        
        lblPrecedentes.setBounds(730, 250, 700, 45);
        selectorEntregablesPrecedencia.setBounds(760, 320, 400, 40);
        
        btnBackMain.setIcon(new ImageIcon(getClass().getResource("/Images/button_regresar.png")));
        btnBackMain.setSize(250, 80);
        btnBackMain.setLocation(display.getXmax() - (btnBackMain.getWidth() + display.getXmax() / 20), 
                display.getYmax() - (btnBackMain.getHeight() * 4));
        
        
        setupBtns(btnBackMain);
        txtSetBorder(txtDuracionDias, txtDuracionHrs, txtCostoField);
        setFontRobotoToLabel(lblDias, lblHrs);
        setFontTimesToLabels(lblEntregables, lblDuracion, lblCosto,lblDependencia,lblPrecedentes);
        setColorGrayToLabels(lblEntregables, lblDuracion, lblCosto,lblDependencia,lblPrecedentes);
        
        addObjectToPanel(panelBody, menuPanel,selectorEntregables,selectorDependencia,btnBackMain, lblEntregables, 
                lblDuracion, txtDuracionDias, lblDias, txtDuracionHrs, lblHrs, 
                lblCosto, txtCostoField,lblDependencia,btnGuardar,lblPrecedentes,selectorEntregablesPrecedencia);
        
    }

    private boolean isBadInput() {
        if(!verificacionEmpty()){
            try{
                int m = Integer.parseInt(txtDuracionDias.getText());
                m = Integer.parseInt(txtDuracionHrs.getText());
                m = Integer.parseInt(txtCostoField.getText());
            }catch(Exception e){ 
                JOptionPane.showMessageDialog(null, "Verifique los datos introducidos");
                return true;
            }
            if(selectorDependencia.getSelectedItem().equals("Si") && selectorEntregablesPrecedencia.getSelectedItem().equals("-")){
                JOptionPane.showMessageDialog(null, "Seleccione un entregable");
            }
        }else{
            return true;
        }
        return false;
    }
    
    private boolean verificacionEmpty(){
        if (txtDuracionDias.getText().isEmpty() || txtDuracionHrs.getText().isEmpty() || txtCostoField.getText().isEmpty() 
                || selectorEntregables.getSelectedItem().equals("-") || selectorDependencia.getSelectedItem().equals("-")){
            JOptionPane.showMessageDialog(null, "Por favor llene toda la información");
            return true;
        }
        return false;
    }
    
    private Boolean HayEntregables(ListaEnlazada listaEntregables){
        Boolean accion = true;
        NodoLista p = listaEntregables.getPtr();
        if (p == null){
            JOptionPane.showMessageDialog(null, "Usted no tiene entregables agregados a su proyecto, vaya a la sección de EDT para agregar sus entregables");
            accion = false;
        }
        return accion;
    }

    private void addVerticesAGrafo() {
        NodoLista p = listaEntregables.getPtr();
        for(int i = 0; i < listaEntregables.getTamaño(); i++){
            Vertice v = new Vertice((String) p.getNodoArbol().getDato());
            grafo.addVertice(v);
            p = p.getLink();
        }
    }
    
    private void AddEntregables(){
        NodoLista p = listaEntregables.getPtr();
        selectorEntregables.removeAllItems();
        selectorEntregables.addItem("-");
        for(int i = 0; i < listaEntregables.getTamaño(); i++){
            Vertice v = grafo.getVertice((String) p.getNodoArbol().getDato());
            if(v != null){
                if(!v.isInfoCompleted()){
                    selectorEntregables.addItem(p.getNodoArbol().getDato());
                }
            }
            p = p.getLink();
        }
        
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == selectorDependencia){
            if (selectorDependencia.getSelectedItem().equals("Si")){
                lblPrecedentes.setText("Seleccione un entregable precedente:");
                selectorEntregablesPrecedencia.setVisible(true);
                String entregable;
                entregable = selectorEntregables.getSelectedItem().toString();
                ListaPrecedencia(entregable);
                
            }else{
                selectorEntregablesPrecedencia.setVisible(false);
                lblPrecedentes.setText("");
            }
            if(selectorDependencia.getSelectedItem().equals("No")){
                hayEntregableInicial = true;
            }
            
        }
        if (e.getSource() == selectorEntregables){
            selectorEntregablesPrecedencia.removeAllItems();
            selectorDependencia.setSelectedItem("-");
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnBackMain){
            display.getContainer().removeAll();
            display.getContainer().validate();
            display.getContainer().repaint();
            display.pantallaPrincipal();
        }
        
        if (ae.getSource() == btnGuardar){
            if(!isBadInput()){
                Vertice verticeEntregable;
                String nombre = selectorEntregables.getSelectedItem().toString();

                verticeEntregable = grafo.getVertice(nombre);

                verticeEntregable.setDias(Integer.parseInt(txtDuracionDias.getText()));
                verticeEntregable.setHoras(Integer.parseInt(txtDuracionHrs.getText()));
                verticeEntregable.setCosto(Integer.parseInt(txtCostoField.getText()));
                verticeEntregable.setInfoCompleta();
 
                if (selectorDependencia.getSelectedItem().equals("Si")){
                    String nombreEntregableDepe = selectorEntregablesPrecedencia.getSelectedItem().toString();
                    Vertice dependiente;

                    dependiente = grafo.getVertice(nombreEntregableDepe);

                    grafo.conectar(verticeEntregable.getNombre(), dependiente.getNombre());
                    dependiente.showVerticesAdyecente();
                }

                grafo.mostrarGrafo(); 
                resetTxts(txtDuracionDias ,txtDuracionHrs, txtCostoField);
                selectorEntregables.removeItem(selectorEntregables.getSelectedItem());
            }
        }
        if (ae.getSource() == btnMenu){
            if (menu == 0){
                menuPanel.setBounds(0, 0, display.getContainer().getWidth()/5, display.getContainer().getHeight());
                menuPanel.setBackground(Color.DARK_GRAY);
                menuPanel.setVisible(true);
                MoveItemsRight(selectorEntregables,selectorDependencia, lblEntregables, 
                    lblDuracion, txtDuracionDias, lblDias, txtDuracionHrs, lblHrs, lblCosto, 
                    txtCostoField,lblDependencia, btnGuardar, lblPrecedentes, selectorEntregablesPrecedencia);
                addObjectToPanel(menuPanel, btnIrAEDT, btnVisualizar, btnModificarInfo);
                menuPanel.setLayout(null);
                SetBotones();
                menu = 1;
            }else{
                menuPanel.setVisible(false);
                menu = 0;
                MoveItemsLeft(selectorEntregables,selectorDependencia, lblEntregables, 
                    lblDuracion, txtDuracionDias, lblDias, txtDuracionHrs, lblHrs, lblCosto, 
                    txtCostoField, lblDependencia, btnGuardar, lblPrecedentes, selectorEntregablesPrecedencia);
            } 
        }
        
        if(ae.getSource() == btnVisualizar){
            lienzo.setVisible(true);
            panelBody.setVisible(false);
            
            display.getContainer().validate();
            display.getContainer().repaint();
        }
        
        if (ae.getSource() == btnIrAEDT){
            display.getContainer().removeAll();
            display.getContainer().validate();
            display.getContainer().repaint();
            display.pantallaPrincipal();
            display.getBtnEDT().doClick();
        }
    }
    
    private void SetBotones(){
        btnIrAEDT.setBounds(0,250,menuPanel.getWidth(),50);
        btnIrAEDT.setText("Ir a EDT");
        btnVisualizar.setBounds(0,150,menuPanel.getWidth(),50);
        btnVisualizar.setText("Visualizar");
        btnModificarInfo.setBounds(0,50,menuPanel.getWidth(),50);
        btnModificarInfo.setText("Modificar Información");
    }
    
    private void MoveItemsRight(JComponent...object){
        if (menu == 0){
            for(JComponent obj: object){
                obj.setBounds(obj.getX()+200, obj.getY(), obj.getWidth(), obj.getHeight());
            }
        }  
    }
    
    private void MoveItemsLeft(JComponent...object){
        if (menu == 0){
            for(JComponent obj: object){
                obj.setBounds(obj.getX()-200, obj.getY(), obj.getWidth(), obj.getHeight());
            }
        }  
    }

    public void resetTxts(JTextField ...txts){
        for(JTextField txt: txts){
            txt.setText("");
        }
    }
     
    public void addObjectToPanel(JPanel panel, JComponent ...objs){
        for(JComponent obj: objs){
            panel.add(obj);
        }
    }
    
    public void setFontTimesToLabels(JLabel ...lbls){
        for(JLabel lbl: lbls){
            lbl.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 40));
        }
    }
    
    public void setFontRobotoToLabel(JLabel ...lbls){
        for(JLabel lbl: lbls){
            lbl.setFont(new Font("Roboto", Font.CENTER_BASELINE, 20));
        }
    }
    
    public void setColorGrayToLabels(JLabel ...lbls){
        for(JLabel lbl: lbls){
            lbl.setForeground(Color.DARK_GRAY);
        }
    }
    
    public void setupBtns(JButton ...btns){
        for(JButton btn: btns){
            btn.setContentAreaFilled(false);
            btn.setBorder(BorderFactory.createEmptyBorder());
        }
    }
    
    public void txtSetBorder(JTextField ...txts){
        for(JTextField txt: txts){
            txt.setBorder(null);
        }
    }
 
    public Grafo getGrafo(){
        return grafo;
    }

}
