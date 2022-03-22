/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LabEstructuras;

import javax.swing.JOptionPane;

/**
 *
 * @author natymartinez04
 */
public class ListaEnlazada {
    
    NodoLista ptr;
    NodoLista link;
    int tam;
    
    public ListaEnlazada() {
        this.ptr = null;
    }
    
    public void insert(NodoArbol n){
        if (ptr == null){
            ptr = new NodoLista(n);
        }else{
            NodoLista p = ptr;
            while (p.link != null){
                p = p.link;
            }
            p.link = new NodoLista(n);
        } 
    }
    
    public NodoArbol BuscaPaquetePadre(NodoArbol raiz,String datoPadre,String datoHijo){
        Boolean encontroPadre = false;
        if (raiz != null){
            if (raiz.dato.equals(datoPadre)){
                return raiz;
            }else{
                if (raiz.hijos.ptr != null){
                    NodoLista Hijo = raiz.hijos.ptr;
                    encontroPadre = revisaHijos(raiz,datoPadre,Hijo);
                    Hijo = Hijo.link;
                    if (encontroPadre == false && Hijo.link != null){
                        Hijo = Hijo.link;
                        BuscaPaquetePadre(Hijo.nodoArbol,datoPadre,datoHijo);
                    }else if (encontroPadre==true){
                        Hijo.nodoArbol.hijos.insert(new NodoArbol(datoHijo));
                    }
                }  
            }    
        }
        return raiz;
    }
    
    public Boolean revisaHijos(NodoArbol p,String dato,NodoLista hijo){
        boolean encontroPadre = false;
        if (p.hijos.ptr != null){
            if (dato.equals(hijo.nodoArbol.dato)){
                encontroPadre = true;
                return encontroPadre;
            }else{
                hijo = hijo.link;
                revisaHijos(p,dato,hijo);
            }
        }
        return encontroPadre;
    }

}
