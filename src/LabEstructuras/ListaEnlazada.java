
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
            while (p.getLink() != null){
                p = p.getLink();
            }
            p.setLink(new NodoLista(n));
        } 
    }

    public void insertN(Object n){
        if (ptrN == null){
            ptrN = new Nodo(n);
        }else{
            Nodo p = ptrN;
            while (p.getLink() != null){
                p = p.getLink();
            }
            p.setLink(new Nodo(n)); 
        } 
    }
    
    //devuelve tamaño de la lista con nodos "Nodo"
    public int getTamañoN(){
        int c = 0;
        Nodo actual = ptrN;
        while(actual != null){
            c++;
            actual = actual.getLink();
        }
        return c;
    }
    
    //devuelve tamaño de la lista con nodos "NodoLista"
    public int getTamaño(){
        int i = 0;
        NodoLista p = ptr;
        while(p != null){
            i++;
            p = p.getLink();
        }
        return i;
    }
    
    public Object getInfoNodo(int index){
        Nodo p = ptrN;
        int i = 0;
        while(p != null){
            if(i == index){
                return p.getDato();
            }
            p = p.getLink();
            i++;
        }
        return null;
    }
    
    public void InsertaEnPadreCorrecto(NodoArbol raiz, String datoPadre, String datoHijo, Boolean tipo){
        NodoLista a = raiz.getHijos().getPtr();
        if (raiz.getDato().equals(datoPadre)){
            raiz.getHijos().insert(new NodoArbol(datoHijo, tipo));
        }else{
            while (a != null){
                if (a.getNodoArbol().getDato().equals(datoPadre)){
                    a.getNodoArbol().getHijos().insert(new NodoArbol(datoHijo, tipo));
                }else{
                    InsertaEnPadreCorrecto(a.getNodoArbol(), datoPadre, datoHijo, tipo);
                }
                a = a.getLink();
            }
        }
    }

    public NodoArbol getNodoArbol(int i) {
        NodoLista p = ptr;
        int index = 0;
        while(p != null){
            if(index == i){
                return p.getNodoArbol();
            }
            p = p.getLink();
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
