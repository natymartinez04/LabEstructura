
package LabEstructuras.Grafos;

import LabEstructuras.ListaEnlazada;
import LabEstructuras.Nodo;
import java.util.Date;

/**
 *
 * @author dkaty, nmartinez, tllach 
 * 
 */
public class Vertice {
    
    private String nombre;
    private int dias, horas, minutos;
    private Date fechai;
    private ListaEnlazada verticesAdyacentes;
    
    public Vertice(String nombre, int dias, int horas, int minutos, double costo){
        this.nombre = nombre;
        this.dias = dias;
        this.horas = horas;
        this.minutos = minutos;
        verticesAdyacentes = new ListaEnlazada();
    }
    
    public void addVerticeAdyacente(Vertice v){
        verticesAdyacentes.insertN(v);
    }
    
    public void showVerticesAdyecente(){
        System.out.println("Vertices Adyacentes de " + nombre);
        for(int i = 0; i <= verticesAdyacentes.getTamañoN(); i++){
            Vertice v = (Vertice)verticesAdyacentes.getInfoNodo(i);
            if(v != null){
                System.out.println(v.getNombre() + " --> ");
            }else{
                System.out.print(" null ");
            }
        }
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public Date getFechai() {
        return fechai;
    }

    public void setFechai(Date fechai) {
        this.fechai = fechai;
    }
    
    public ListaEnlazada getVerticesAdyacentes(){
        return verticesAdyacentes;
    }
    
}
