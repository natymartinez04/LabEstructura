/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LabEstructuras;

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
    private int id=0;
    private JPanel panelHeader, panelBody;
    private JLabel lblCronograma, lblEntregables, lblDuracion, lblCosto, lblDependencia;
    private JComboBox selectorEntregables,selectorDependencia,selectorEntregablesPrecedencia;
    private JLabel lblDias, lblHrs, lblMin;
    private JTextField duracionDias, duracionHrs, duracionMin, costoField;
    private JButton btnBackMain, btnMenu, btnGuardar,btnAgregar;
    private ListaEnlazada listaEntregables;
    
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
        
        selectorDependencia.addItem("-");
        selectorDependencia.addItem("Si");
        selectorDependencia.addItem("No");
        selectorDependencia.setVisible(true);
        selectorDependencia.setBounds(750, 190,400,40);
        
        
        btnGuardar.setText("Guardar");
        btnGuardar.setForeground(Color.BLUE);
        btnGuardar.setBackground(Color.WHITE);
        btnGuardar.setBounds(620 ,600, 180 , 40);
        
        
        
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
        lblDependencia.setBounds(lblDuracion.getX()*6,lblEntregables.getY(),330,45);
        
        btnBackMain.setIcon(new ImageIcon(getClass().getResource("/Images/button_regresar.png")));
        btnBackMain.setSize(250, 80);
        btnBackMain.setLocation(display.getXmax() - (btnBackMain.getWidth() + display.getXmax() / 20), 
                display.getYmax() - (btnBackMain.getHeight() * 4));
        
        
        //crear y configiurar boton pa guardaar.
        //falta comprobacion de bad input
        
        setupBtns(btnBackMain);
        txtSetBorder(duracionDias, duracionHrs, duracionMin, costoField);
        setFontRobotoToLabel(lblDias, lblHrs, lblMin);
        setFontTimesToLabels(lblEntregables, lblDuracion, lblCosto,lblDependencia);
        setColorGrayToLabels(lblEntregables, lblDuracion, lblCosto,lblDependencia);
        
        addObjectToPanel(panelBody, selectorEntregables,selectorDependencia,btnBackMain, lblEntregables, 
                lblDuracion, duracionDias, lblDias, duracionHrs, lblHrs, duracionMin, lblMin, 
                lblCosto, costoField,lblDependencia,btnGuardar);
    }
    
    
    private void AddEntregables(){
        NodoLista p = listaEntregables.getPtr();
        while (p != null){
            System.out.println("hola");
            selectorEntregables.addItem(p.getNodoArbol().getDato());
            p = p.getLink();
        }
        
    }
    private void ListaPrecedencia(){
        
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        //acción botón regresar
        if (ae.getSource() == btnBackMain){
            display.getContainer().removeAll();
            selectorDependencia.removeAllItems();
            selectorEntregables.removeAllItems();
            display.getContainer().validate();
            display.getContainer().repaint();
            display.pantallaPrincipal();
        }
        
        //acción botón guardar
        if (ae.getSource() == btnGuardar){
            String nombre = selectorDependencia.getSelectedItem().toString();
            int dias = Integer.parseInt(duracionDias.getText());
            int horas = Integer.parseInt(duracionHrs.getText());
            int minutos = Integer.parseInt(duracionMin.getText());
            double costo = Integer.parseInt(duracionMin.getText());
            Vertice vertice = new Vertice(nombre,dias,horas,minutos,costo);
        }
        
        if (ae.getSource() == selectorDependencia){
            if (selectorDependencia.getSelectedItem() == "Si"){
                
            }else{
                
            }
            
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
