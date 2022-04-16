
package LabEstructuras.Grafos;

import LabEstructuras.ListaEnlazada;
import java.util.Date;

/**
 *
 * @author dkaty, nmartinez, tllach 
 * 
 */
public class Vertice {
    
    private String nombre;
    private int duracion;
    private Date fechai;
    private String paquetePadre;
    private Vertice arista;
    private ListaEnlazada verticesAdyacentes;
    
    public Vertice(String nombre, int duracion, String paquetePadre){
        this.nombre = nombre;
        this.duracion = duracion;
        this.paquetePadre = paquetePadre;
        verticesAdyacentes = new ListaEnlazada();
    }
    
    public void insertVerticeAdyancente(){
        
    }
    
    public void setFechaI(Date fechai){
        this.fechai = fechai;
    }
    
    public Date getFechaI(){
        return fechai;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public int getDuracion(){
        return duracion;
    }
    
    public String getPaquetePadre(){
        return paquetePadre;
    }
    
    public ListaEnlazada getVerticesAdyacentes(){
        return verticesAdyacentes;
    }
    
}
