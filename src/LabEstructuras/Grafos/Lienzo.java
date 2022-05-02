/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LabEstructuras.Grafos;

import LabEstructuras.ListaEnlazada;
import LabEstructuras.Nodo;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 *
 * @author tllach, nmartinez, dkaty
 */
public class Lienzo extends JPanel implements MouseListener{

    private ListaEnlazada listaVerticesE, listaVerticesI;
    private int i;
    
    public Lienzo(ListaEnlazada listaVertice){
        i = 0;
        this.listaVerticesE = listaVertice;
        this.listaVerticesI = new ListaEnlazada();
        this.addMouseListener(this);
    }
    
    public void paint(Graphics g){
        Nodo n = listaVerticesI.getPtrN();
        while(n != null){
            Vertice v = (Vertice) n.getDato();
            v.draw(g);
            n = n.getLink();
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        if(me.getButton() == MouseEvent.BUTTON1){
            Vertice v = (Vertice) listaVerticesE.getInfoNodo(i);
            //v.setXPosition(me.getX());
            //v.setYPosition(me.getY());
            listaVerticesI.insertN(v);
            repaint();
            i++;
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }
    
    
    
    
    
    
}
