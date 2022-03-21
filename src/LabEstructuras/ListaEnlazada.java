/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LabEstructuras;

/**
 *
 * @author natymartinez04
 */
public class ListaEnlazada {
    
    NodoLista ptr;
    NodoLista link;
    
    public ListaEnlazada() {
        this.ptr = null;
    }
    
    public void insert(NodoArbol n){
        if (ptr == null){
            ptr = new NodoLista(n);
        }else{
            NodoLista p = ptr;
            System.out.println(n.dato);
            while (p.link != null){
                p = p.link;
            }
            p.link = new NodoLista(n);
        }
        
    }

}
