
package LabEstructuras.Arbol;

import LabEstructuras.ListaEnlazada;

/**
 *
 * @author tllach, nmartinez, dkaty
 */
public class NodoArbol {
    
    private Object dato;
    private ListaEnlazada hijos;
    private Boolean tipo;
    
    public NodoArbol(Object dato, Boolean tipo) {
        this.dato = dato;
        this.tipo = tipo;
        this.hijos = new ListaEnlazada();
    }
    
    public Object getDato(){
        return dato;
    }
    
    public ListaEnlazada getHijos(){
        return hijos;
    }
    
    public Boolean getTipo(){
        return tipo;
    }
    

}

