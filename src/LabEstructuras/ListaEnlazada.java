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
    int tam = 0;
    
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

    
    public void InsertaEnPadreCorrecto(NodoArbol raiz, String datoPadre, String datoHijo){
        NodoLista a = raiz.hijos.ptr;
        if (raiz.dato.equals(datoPadre)){
            System.out.println("El padre es: " + raiz.dato);
            raiz.hijos.insert(new NodoArbol(datoHijo));
        }else{
            while (a != null){
                if (a.nodoArbol.dato.equals(datoPadre)){
                    System.out.println("El padre es: " + a.nodoArbol.dato);
                    a.nodoArbol.hijos.insert(new NodoArbol(datoHijo));
                }else{
                    InsertaEnPadreCorrecto(a.nodoArbol, datoPadre, datoHijo);
                }
                a = a.link;
            }
        }
        }
    }

