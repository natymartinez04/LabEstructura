
package LabEstructuras.Grafos;

import LabEstructuras.ListaEnlazada;

/**
 *
 * @author dkaty, nmartinez, tllach
 * 
 */
public class Grafo {
    
    private ListaEnlazada aristas;
    private ListaEnlazada vertices;
    
    public Grafo(){
        this.aristas = new ListaEnlazada();
        this.vertices = new ListaEnlazada();
    }
    
    public int numVertices(){
        
        return 0;
    }
    
    public int numAristas(){
        return 0;
    }
    
    public boolean existeVertice(){
        return false;
    }
}
