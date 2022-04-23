/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LabEstructuras;

import LabEstructuras.Grafos.Grafo;
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
    private int id = 0;
    private JPanel panelHeader, panelBody;
    private JLabel lblCronograma, lblEntregables, lblDuracion, lblCosto, lblDependencia,lblPrecedentes;
    private JComboBox selectorEntregables,selectorDependencia,selectorEntregablesPrecedencia;
    private JLabel lblDias, lblHrs, lblMin;
    private JTextField duracionDias, duracionHrs, duracionMin, costoField;
    private JButton btnBackMain, btnMenu, btnGuardar,btnAgregar;
    private ListaEnlazada listaEntregables;
    private Grafo grafo;
    
    public CronogramaGui(Display display){
        this.display = display;
        declaration();
        addActionListener(); 
    }

    private void declaration(){
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
        lblMin = new JLabel();
        duracionDias = new JTextField();
        duracionHrs = new JTextField(); 
        duracionMin = new JTextField(); 
        costoField = new JTextField();
        btnMenu = new JButton();
        btnGuardar = new JButton();
        btnBackMain = new JButton();
        btnAgregar = new JButton();
        panelHeader = new JPanel();
        panelBody = new JPanel();
        grafo = new Grafo();
    }
    
    private void addActionListener(){
        btnBackMain.addActionListener(this);
        btnMenu.addActionListener(this);
        btnGuardar.addActionListener(this);
        selectorEntregables.addItemListener(this);
        selectorDependencia.addItemListener(this);
        selectorEntregablesPrecedencia.addActionListener(this);
    }
    
    public void setUpCronograma(ListaEnlazada listaEntregables){
        this.listaEntregables = listaEntregables;
        Boolean accion = HayEntregables(listaEntregables);
        if (accion){
            display.getContainer().setBackground(new Color(200, 227, 250));

            lblCronograma.setText("Cronograma");
            lblCronograma.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 57));
            lblCronograma.setSize(350, 60);
            lblCronograma.setForeground(Color.BLACK);
            lblCronograma.setLocation((display.getXmax() - lblCronograma.getWidth())/2 , 22);

            btnMenu.setIcon(new ImageIcon(getClass().getResource("/Images/menu.png")));
            btnMenu.setBounds(30, 30, 33, 33);
            btnMenu.setEnabled(false);

            panelHeader.setBounds(0,0, display.getXmax(), display.getYmax() / 11);
            panelHeader.setBackground(new Color(255, 122, 202));
            panelHeader.setLayout(null);

            setupBtns(btnMenu);
            addObjectToPanel(panelHeader, lblCronograma, btnMenu);

            panelBody.setBounds(0, panelHeader.getHeight(), display.getXmax(), display.getYmax());
            panelBody.setBackground(new Color(200, 227, 250));
            panelBody.setLayout(null);

            setPanelBody();

            display.addToContainer(panelHeader, panelBody);
            display.getContainer().validate();
            display.getContainer().repaint();
        }else{
            display.getContainer().removeAll();
            display.getContainer().validate();
            display.getContainer().repaint();
            display.pantallaPrincipal();
            
        }
        
    }
    
    private void ListaPrecedencia(ListaEnlazada listaEntregables, String EntregableSeleccionado){
        NodoLista p = listaEntregables.getPtr();
        while (p != null){
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
        if (selectorDependencia.getItemCount()<3){
            selectorDependencia.addItem("-");
            selectorDependencia.addItem("Si");
            selectorDependencia.addItem("No");
        }
        
        selectorDependencia.setVisible(true);
        selectorDependencia.setBounds(750, 190, 400, 40);
        
        btnGuardar.setText("Guardar");
        btnGuardar.setForeground(Color.BLUE);
        btnGuardar.setBackground(Color.WHITE);
        btnGuardar.setBounds(620 ,600, 180, 40);
        
        lblDuracion.setText("Duracion:");
        lblDuracion.setBounds(lblEntregables.getX(), 
                selectorEntregables.getY() + selectorEntregables.getHeight() + 50, 350, 45);
        
        duracionDias.setBounds(selectorEntregables.getX() + 40, lblDuracion.getY()+ lblDuracion.getHeight() + 25, 40, 30);
        lblDias.setText(": Dias");
        lblDias.setBounds(duracionDias.getX() + duracionDias.getWidth() + 2, duracionDias.getY() + 5, 60, 20);
        
        duracionHrs.setBounds(lblDias.getX() + lblDias.getWidth() + 4, duracionDias.getY(), duracionDias.getWidth(), duracionDias.getHeight());
        lblHrs.setText(": Horas");
        lblHrs.setBounds(duracionHrs.getX() + duracionHrs.getWidth() + 2, lblDias.getY(), 80, 20);
        
        duracionMin.setBounds(lblHrs.getX() + lblHrs.getWidth() + 4, duracionHrs.getY(), duracionHrs.getWidth(), duracionHrs.getHeight());
        lblMin.setText(": Min");
        lblMin.setBounds(duracionMin.getX() + duracionMin.getWidth() + 2, lblHrs.getY(), 60, 20);

        lblCosto.setText("Costo:");
        lblCosto.setBounds(lblDuracion.getX(), 
                duracionDias.getY() + duracionDias.getHeight() + 50, 300, 45);
        costoField.setBounds(lblCosto.getX() + 50, 
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
        txtSetBorder(duracionDias, duracionHrs, duracionMin, costoField);
        setFontRobotoToLabel(lblDias, lblHrs, lblMin);
        setFontTimesToLabels(lblEntregables, lblDuracion, lblCosto,lblDependencia,lblPrecedentes);
        setColorGrayToLabels(lblEntregables, lblDuracion, lblCosto,lblDependencia,lblPrecedentes);
        
        addObjectToPanel(panelBody, selectorEntregables,selectorDependencia,btnBackMain, lblEntregables, 
                lblDuracion, duracionDias, lblDias, duracionHrs, lblHrs, duracionMin, lblMin, 
                lblCosto, costoField,lblDependencia,btnGuardar,lblPrecedentes,selectorEntregablesPrecedencia);
        
    }

    private void VerificacionEmpty(){
        if (duracionDias.getText().isEmpty() == true ||
              duracionHrs.getText().isEmpty() == true || duracionMin.getText().isEmpty() == true || 
              costoField.getText().isEmpty() == true || selectorEntregables.getSelectedItem().equals("-")){
          JOptionPane.showMessageDialog(null, "Por favor llene toda la información");
        }
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

    private void AddEntregables(){
        NodoLista p = listaEntregables.getPtr();
        selectorEntregables.removeAllItems();
        selectorEntregables.addItem("-");
        for(int i = 0; i < listaEntregables.getTamaño(); i++){
            System.out.println(i);
            selectorEntregables.addItem(p.getNodoArbol().getDato());
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
                ListaPrecedencia(listaEntregables, entregable);
                
            }else{
                selectorEntregablesPrecedencia.setVisible(false);
                lblPrecedentes.setText("");
            }
            
        }
        if (e.getSource() == selectorEntregables){
            selectorEntregablesPrecedencia.removeAllItems();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        //acción botón regresar
        if (ae.getSource() == btnBackMain){
            display.getContainer().removeAll();
            display.getContainer().validate();
            display.getContainer().repaint();
            display.pantallaPrincipal();
        }
        
        //acción botón guardar
        if (ae.getSource() == btnGuardar){
            VerificacionEmpty();
            String nombre = selectorEntregables.getSelectedItem().toString();
            int dias = Integer.parseInt(duracionDias.getText());
            int horas = Integer.parseInt(duracionHrs.getText());
            int minutos = Integer.parseInt(duracionMin.getText());
            double costo = Integer.parseInt(costoField.getText());
            Vertice vertice = new Vertice(nombre,dias,horas,minutos,costo);
            grafo.addVertice(vertice);
            
            if (selectorDependencia.getSelectedItem().equals("Si")){
                Vertice dependiente = new Vertice(selectorEntregablesPrecedencia.getSelectedItem().toString(),0,0,0,0);
                grafo.addVertice(dependiente);
                grafo.conectar(vertice.getNombre(), dependiente.getNombre());
                dependiente.showVerticesAdyecente();
            }
            grafo.mostrarGrafo();
            
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
    
    
}
