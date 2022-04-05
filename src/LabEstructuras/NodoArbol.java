/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LabEstructuras;

/**
 *
 * @author tllach, nmartinez, dkaty
 */
public class NodoArbol {
    Object dato;
    ListaEnlazada hijos;
    Boolean tipo;
    
    public NodoArbol(Object dato, Boolean tipo) {
        this.dato = dato;
        this.tipo = tipo;
        this.hijos = new ListaEnlazada();
    }
    
    
}

