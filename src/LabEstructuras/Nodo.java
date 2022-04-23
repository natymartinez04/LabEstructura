
package LabEstructuras;

/**
 *
 * @author dkaty, nmartinez, tllach 
 */
public class Nodo {
    
    private Object dato;
    private Nodo link;
    
    public Nodo(Object dato){
        this.dato = dato;
    }
    
    public Object getDato(){
        return dato;
    }
    
    public Nodo getLink(){
        return link;
    }
    
    public void setLink(Nodo a){
        this.link = a;
    }
}
