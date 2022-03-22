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
    
    
    public Boolean Existe(NodoArbol raiz,int n,String dato){
        Boolean encontrado=false;
        if (raiz.dato.equals(dato)){
            encontrado = true;
        }else{
            NodoLista p = raiz.hijos.ptr;
            for (int i = 0; i<n;i++){
                if (dato.equals(p.nodoArbol.dato)){
                    encontrado = true;
                }
            p = p.link;
        }
        System.out.println(encontrado);
        
        }
        return encontrado;
    }
}

    

