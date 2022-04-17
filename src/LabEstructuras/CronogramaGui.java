/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LabEstructuras;

import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

/**
 *
 * @author natymartinez04
 */
public class CronogramaGui implements ItemListener, ActionListener{
    
    private Display display;
    private JComboBox Entregables;
    private JButton btnBackMain;
    ListaEnlazada listaEntregables;
    
    
    public CronogramaGui(Display display){
        this.display = display;
        declaration();
        AddActionListener(); 
    }

    private void declaration(){
        Entregables = new JComboBox();
        btnBackMain = new JButton();
    }
    
    private void AddActionListener(){
        btnBackMain.addActionListener(this);
    }
    
    public void setUpCronograma(ListaEnlazada listaEntregables){
        this.listaEntregables = listaEntregables;
        
        Entregables.addItem("-");
        AddEntregables();
        Entregables.setVisible(true);
        Entregables.setBounds(140, 130, 400, 40);
        
        
        btnBackMain.setIcon(new ImageIcon(getClass().getResource("/Images/button_regresar.png")));
        btnBackMain.setContentAreaFilled(false);
        btnBackMain.setBorder(BorderFactory.createEmptyBorder());
        btnBackMain.setSize(250, 80);
        btnBackMain.setLocation(display.getxmax() - (btnBackMain.getWidth() + display.getxmax() / 20) , display.getymax() - (btnBackMain.getHeight() * 4));
 
        
        if (listaEntregables.getPtr() == null){
            System.out.println("No hay entregables");
        }else{
            System.out.println("Si hay");
        }
        
        display.getContainer().add(Entregables,btnBackMain);
        display.getContainer().validate();
        display.getContainer().repaint();
    }
    private void AddEntregables(){
        NodoLista p = listaEntregables.getPtr();
        while (p != null){
            System.out.println("hola");
            Entregables.addItem(p.nodoArbol.getDato());
            p = p.link;
        }
        
    }
    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnBackMain){
            display.getContainer().removeAll();
            display.getContainer().validate();
            display.getContainer().repaint();
            display.pantallaPrincipal();
        }
    }


    
}
