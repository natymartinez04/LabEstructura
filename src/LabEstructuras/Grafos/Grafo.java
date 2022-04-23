
package LabEstructuras.Grafos;

import LabEstructuras.ListaEnlazada;
import LabEstructuras.Nodo;

/**
 *
 * @author dkaty, nmartinez, tllach
 * 
 */
public class Grafo {
    
    private ListaEnlazada vertices;
    
    public Grafo(){
        this.vertices = new ListaEnlazada();
    }
    
    public void addVertice(Vertice v){
        if(!existeVertice(v.getNombre())){
            System.out.println("Vertice agregada");
            vertices.insertN(v);
        }else{
            System.out.println("Esa vertice ya existe");
        }
        
    }
    
    public void conectar(String a, String b){
        Vertice n = getVertice(a);
        if(n != null){
            System.out.println("Conectar A getNombre()= " + n.getNombre());
            Vertice m = getVertice(b);
            System.out.println("Conectar B getNombre()= " + m.getNombre());
            m.addVerticeAdyacente(n); 
        }else{
            System.out.println("El nodo no existe");
        }
    }
    
    
    public Vertice getVertice(String text){
        Nodo a = vertices.getPtrN();
        while(a != null){
            Vertice m = (Vertice) a.getDato();
            if(m.getNombre().equals(text)){
                System.out.println("getVertice getDato()= " + a.getDato());
                return m;
            }
            a = a.getLink();
        }
        return null;
    }
    
    public void mostrarGrafo(){
        System.out.println("\n Mostrar Vertices Grafo");
        Nodo p = vertices.getPtrN();
        while(p != null){
            Vertice i =(Vertice) p.getDato();
            System.out.print(i.getNombre() + " --> ");
            p = p.getLink();
        }
        
    }
    
    public int numVertices(){
        return 0;
    }
    
    public int numAristas(){
        return 0;
    }
    
    public boolean existeVertice(String dato){
        Nodo p = vertices.getPtrN();
        while(p != null){
            Vertice v = (Vertice) p.getDato();
            if(v.getNombre().equals(dato)){
                return true;
            }
            p = p.getLink();
        }
        return false;
    }
}
