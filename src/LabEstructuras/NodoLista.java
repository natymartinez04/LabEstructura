/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LabEstructuras;

import LabEstructuras.Arbol.NodoArbol;

/**
 *
 * @author tllach, nmartinez, dkaty
 */
public class NodoLista {
    
    NodoLista link;
    NodoArbol nodoArbol;
    
    public NodoLista(NodoArbol dato){
        this.nodoArbol = dato;
    }

    public NodoArbol getNodoArbol(){
        return nodoArbol;
    }
    
    public NodoLista getLink(){
        return link;
    }
}
