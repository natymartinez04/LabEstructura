
package LabEstructuras;

import LabEstructuras.Arbol.NodoArbol;

/**
 *
 * @author tllach, nmartinez, dkaty
 */
public class ListaEnlazada {
    
    private NodoLista ptr;
    private Nodo ptrN;
    private NodoLista link;
    
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

    public void insertN(Object n){
        if (ptrN == null){
            ptrN = new Nodo(n);
        }else{
            Nodo p = ptrN;
            while (p.link != null){
                p = p.link;
            }
            p.link = new Nodo(n); 
        } 
    }
    
    //devuelve tamaño de la lista con nodos "Nodo"
    public int getTamañoN(){
        int c = 0;
        Nodo actual = ptrN;
        while(actual != null){
            c++;
            actual = actual.link;
        }
        return c;
    }
    
    //devuelve tamaño de la lista con nodos "NodoLista"
    public int getTamaño(){
        int i = 0;
        NodoLista p = ptr;
        while(p != null){
            i++;
            p = p.link;
        }
        return i;
    }
    
    public Object getInfoNodo(int i){
        Nodo p = ptrN;
        int index = 0;
        while(p != null){
            if(index == i){
                return p.dato;
            }
            p = p.link;
            index++;
        }
        return null;
    }
    
    public void InsertaEnPadreCorrecto(NodoArbol raiz, String datoPadre, String datoHijo, Boolean tipo){
        NodoLista a = raiz.getHijos().getPtr();
        if (raiz.getDato().equals(datoPadre)){
            raiz.getHijos().insert(new NodoArbol(datoHijo, tipo));
        }else{
            while (a != null){
                if (a.nodoArbol.getDato().equals(datoPadre)){
                    a.nodoArbol.getHijos().insert(new NodoArbol(datoHijo, tipo));
                }else{
                    InsertaEnPadreCorrecto(a.nodoArbol, datoPadre, datoHijo, tipo);
                }
                a = a.link;
            }
        }
    }

    public NodoArbol getNodoArbol(int i) {
        NodoLista p = ptr;
        int index = 0;
        while(p != null){
            if(index == i){
                return p.nodoArbol;
            }
            p = p.link;
            index++;
        }
        return null;
    }
    
    public NodoLista getPtr(){
        return ptr;
    }
    
    public Nodo getPtrN(){
        return ptrN;
    }
    
}
