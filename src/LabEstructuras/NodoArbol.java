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

    public NodoArbol(Object dato) {
        this.dato = dato;
        this.hijos = new ListaEnlazada();
    }
    
    
}
