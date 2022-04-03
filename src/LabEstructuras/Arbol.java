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
public class Arbol {
    NodoArbol raiz;

    
    public Arbol(){
        this.raiz = null;
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
        }
        return encontrado;
    }
    
    public int AlturaArbol(NodoArbol raiz){
        if (raiz == null){
            return -1;
        }
        int altura = -1;
        NodoLista p = raiz.hijos.ptr;
        while (p != null){
            altura = Math.max(altura, AlturaArbol(p.nodoArbol));
            p = p.link;
        }    
        return altura +1;
    }
    
    public void NodosTerminales (NodoArbol raiz){
        if (raiz.hijos.ptr == null){
            System.out.print(raiz.dato+" ");
        }else{
            NodoLista p = raiz.hijos.ptr;
            while(p != null){
                if (p.nodoArbol.hijos == null){
                    System.out.print(p.nodoArbol.dato+" ");
                    
                }
                NodosTerminales(p.nodoArbol);
                p = p.link;
            }
        }
    }
    
    public void Inorden(NodoArbol raiz){
        
    }
    
    public void preorden(NodoArbol raiz){
        
    }
    public void postorden(NodoArbol raiz){
        
    }
    
    public void NodoSoloUnEntregable(NodoArbol raiz){
        int conta = 0;

        if (raiz != null){
            if (raiz.hijos.ptr != null){
                NodoLista p = raiz.hijos.ptr;
                NodoLista k = p;
                while(k != null){
                    if (k.nodoArbol.tipo == true){
                        conta ++;
                    }
                    k = k.link;
                }
                if (conta == 1){
                    System.out.print(raiz.dato+" ");
                    
                }
                conta = 0;
                NodoSoloUnEntregable(p.nodoArbol);
                p = p.link;
            }
        }
    }
    

   
    
    
    
}

