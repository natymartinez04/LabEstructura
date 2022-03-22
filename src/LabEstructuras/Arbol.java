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
    int conta;
    public Arbol(){
        this.raiz = null;
    }
    public void imprimirArbol(NodoArbol raiz,int n,int conta){
        if (raiz != null){
            
            
        }
        
    }
    public Boolean Existe(NodoArbol raiz, String dato){
        Boolean encontrado = false;
        if (raiz.dato.equals(dato)){
            encontrado = true;
        }else{
            NodoLista p = raiz.hijos.ptr;
            while(p != null){
                if (dato.equals(p.nodoArbol.dato)){
                    encontrado = true;
                }
                Existe(p.nodoArbol, dato);
                p = p.link;
            }
            System.out.println(encontrado);
        }
        return encontrado;
    }
   }

