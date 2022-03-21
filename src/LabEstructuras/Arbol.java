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
public class Arbol {
    NodoArbol raiz;
    
    public Arbol(){
        this.raiz = null;
    }
    public void imprimirArbol(NodoArbol raiz){
        NodoLista p = raiz.hijos.ptr;
        String raizstring = (String)raiz.dato;
        String nombre;
        System.out.println(raizstring);
        for (int i = 0; i<=3;i++){
            nombre = (String) p.nodoArbol.dato; 
            System.out.println(nombre);
            p = p.link;
        }
    }
}
