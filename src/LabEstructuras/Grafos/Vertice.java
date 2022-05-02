
package LabEstructuras.Grafos;

import LabEstructuras.ListaEnlazada;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Date;

/**
 *
 * @author dkaty, nmartinez, tllach 
 * 
 */
public class Vertice {
    
    private String nombre;
    private int x, y;
    private int tam;
    private int dias, horas, costo;
    private Date fechai;
    public Boolean infoCompleta;
    private ListaEnlazada verticesAdyacentes;
    
    public Vertice(String nombre){
        this.nombre = nombre;
        infoCompleta = false;
        verticesAdyacentes = new ListaEnlazada();
    }

    public Vertice(String nombre, int dias, int horas , int costo){
        this.nombre = nombre;
        this.dias = dias;
        this.horas = horas;
        this.costo = costo;
        infoCompleta = false;
        verticesAdyacentes = new ListaEnlazada();
    }
    
    public void addVerticeAdyacente(Vertice v){
        verticesAdyacentes.insertN(v);
    }
    
    public void showVerticesAdyecente(){
        //System.out.println("Vertices Adyacentes de " + nombre);
        for(int i = 0; i <= verticesAdyacentes.getTamañoN(); i++){
            Vertice v = (Vertice)verticesAdyacentes.getInfoNodo(i);
            if(v != null){
                //System.out.print(v.getNombre() + " --> ");
            }else{
                //System.out.print(" null ");
            }
        }
    }
    
    public void setXPosition(int x){
        this.x = x;
    }
    
    public void setYPosition(int y){
        this.y = y;
    }
    
    public void setTamaño(int tam){
        this.tam = tam;
    }
    
    public void draw(Graphics g){
        g.setColor(Color.RED);
        g.drawOval(this.x - this.tam/2, this.y - this.tam/2, this.tam, this.tam);
        g.drawString(this.nombre, this.x + 5, this.y + 10);
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
        setTamaño(nombre.length() * 15);
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
    
    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }
    
    public Date getFechai() {
        return fechai;
    }

    public void setFechai(Date fechai) {
        this.fechai = fechai;
    }
    
    public Boolean isInfoCompleted(){
        return infoCompleta;
    }
    
    public void setInfoCompleta(){
        this.infoCompleta = true;
    }
    
    public ListaEnlazada getVerticesAdyacentes(){
        return verticesAdyacentes;
    }
    
}
