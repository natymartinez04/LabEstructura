/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LabEstructuras.Arbol;

import LabEstructuras.ListaEnlazada;

/**
 *
 * @author tllach, nmartinez, dkaty
 */
public class NodoArbol {
    
    private Object dato;
    private ListaEnlazada hijos;
    private Boolean tipo;
    
    public NodoArbol(Object dato, Boolean tipo) {
        this.dato = dato;
        this.tipo = tipo;
        this.hijos = new ListaEnlazada();
    }
    
    public Object getDato(){
        return dato;
    }
    
    public ListaEnlazada getHijos(){
        return hijos;
    }
    
    public Boolean getTipo(){
        return tipo;
    }
    

}

