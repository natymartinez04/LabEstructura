
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
    private int dias,horas,minutos;
    private Date fechai;
    private String paquetePadre;
    private Vertice arista;
    private ListaEnlazada verticesAdyacentes;
    
    public Vertice(String nombre, int dias,int horas,int minutos, double costo){
        this.nombre = nombre;
        this.dias = dias;
        this.horas = horas;
        this.minutos = minutos;
        this.paquetePadre = paquetePadre;
        verticesAdyacentes = new ListaEnlazada();
    }

    

    
    public String getNombre(){
        return nombre;
    }
    
    
    public ListaEnlazada getVerticesAdyacentes(){
        return verticesAdyacentes;
    }
    
}
