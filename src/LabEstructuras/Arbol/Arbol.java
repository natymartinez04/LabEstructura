/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LabEstructuras.Arbol;

import LabEstructuras.ListaEnlazada;
import LabEstructuras.NodoLista;

/**
 *
 * @author tllach, nmartinez, dkaty
 */
public class Arbol {
    
    private NodoArbol raiz;
    private String cadenaInorder;
    private String cadenaPreOrder;
    private String cadenaPostOrder;
    private String cadenaNodosTerminales;
    private String cadenaAltura;
    private String cadenaNodoSoloEntregables;

    public Arbol(){
        this.raiz = null;
        cadenaInorder = "";
    }
    
    public Boolean Existe(NodoArbol raiz, String dato){
        Boolean encontrado = false;
        if (raiz.getDato().equals(dato)){
            encontrado = true;
        }else{
            NodoLista p = raiz.getHijos().getPtr();
            while(p != null){
                
                if (dato.equals(p.getNodoArbol().getDato())){
                    encontrado = true;
                }
                Existe(p.getNodoArbol(), dato);
                p = p.getLink();
            }
        }
        return encontrado;
    }
    
    public int AlturaArbol(NodoArbol raiz){
        if (raiz == null){
            return -1;
        }
        int altura = -1;
        NodoLista p = raiz.getHijos().getPtr();
        while (p != null){
            altura = Math.max(altura, AlturaArbol(p.getNodoArbol()));
            p = p.getLink();
        }    
        return altura +1;
    }
    
    public void NodosTerminales (NodoArbol raiz){
        if (raiz.getHijos().getPtr() == null){
            cadenaNodosTerminales = cadenaNodosTerminales.concat(raiz.getDato() + " ");
        }else{
            NodoLista p = raiz.getHijos().getPtr();
            while(p != null){
                if (p.getNodoArbol().getHijos() == null){
                    cadenaNodosTerminales = cadenaNodosTerminales.concat(p.getNodoArbol().getDato() + " ");
                }
                NodosTerminales(p.getNodoArbol());
                p = p.getLink();
            }
        }
    }
    
    public void NodoSoloUnEntregable(NodoArbol raiz){
        int conta = 0;

        if (raiz != null){
            if (raiz.getHijos().getPtr() != null){
                NodoLista p = raiz.getHijos().getPtr();
                NodoLista k = p;
                while(k != null){
                    if (k.getNodoArbol().getTipo() == true){
                        conta ++;
                    }
                    k = k.getLink();
                }
                if (conta == 1){
                    cadenaNodoSoloEntregables = cadenaNodoSoloEntregables.concat(raiz.getDato() + " ");
                }
                conta = 0;
                NodoSoloUnEntregable(p.getNodoArbol());
                p = p.getLink();
            }
        }
    }
    
    public void recorridoPreorden(NodoArbol raiz){
        cadenaPreOrder = cadenaPreOrder.concat((String) raiz.getDato() + " ");
        if(raiz.getHijos().getPtr() != null){
            for(int i = 0; i < raiz.getHijos().getTamaño(); i++){
                recorridoPreorden((NodoArbol) raiz.getHijos().getNodoArbol(i));
            }
        }
    }
    
    public void recorridoInorden(NodoArbol raiz){
        if(raiz.getHijos() != null){
            
            ListaEnlazada izq = dividir(raiz.getHijos())[0];
            ListaEnlazada der = dividir(raiz.getHijos())[1];
            
            if(izq.getTamaño() != 0){
                for(int i = 0; i < izq.getTamaño(); i++){
                    recorridoInorden((NodoArbol) izq.getNodoArbol(i));
                }
                cadenaInorder = cadenaInorder.concat((String) raiz.getDato() + " ");
                if(der.getTamaño() != 0){
                    for(int i = 0; i < der.getTamaño(); i++){
                        recorridoInorden((NodoArbol) der.getNodoArbol(i));
                    }
                } 
            } 
        }
        if(!cadenaInorder.contains((String) raiz.getDato())){
            cadenaInorder = cadenaInorder.concat((String) raiz.getDato() + " ");
        }
    }
    
    public ListaEnlazada[] dividir(ListaEnlazada lista){
        int tam = lista.getTamaño();
        int tamL = Math.round(tam / 2);
        if(tamL == 0){
            tamL = 1;
        }
        ListaEnlazada a = new ListaEnlazada();
        ListaEnlazada b = new ListaEnlazada();
        NodoLista p = lista.getPtr();
        int ite = 0;
        if(tam % 2 == 0){
            while(p != null){
                if(ite < tamL){
                    a.insert(p.getNodoArbol());
                }else{
                    b.insert(p.getNodoArbol());
                }
                p = p.getLink();
                ite++;
            }
        }else{
            while(p != null){
                if(ite < tamL){
                    a.insert(p.getNodoArbol());
                }else{
                    b.insert(p.getNodoArbol());
                }
                p = p.getLink();
                ite++;
            }
        }
        return new ListaEnlazada[]{a,b};
    }
    
    public void recorridoPostOrden(NodoArbol raiz){
        if(raiz.getHijos().getPtr() != null){
            for(int i = 0; i < raiz.getHijos().getTamaño(); i++){
                recorridoPostOrden((NodoArbol) raiz.getHijos().getNodoArbol(i));
            }  
        }
        cadenaPostOrder = cadenaPostOrder.concat((String) raiz.getDato() +  " ");
    }
    
    public void setCadenaAltura(String cadenaAltura) {
        this.cadenaAltura = cadenaAltura;
    }
    
    public void resetCadenas(){
        cadenaInorder = "";
        cadenaPreOrder = "";
        cadenaPostOrder = "";
        cadenaNodosTerminales = "";
        cadenaAltura = "";
        cadenaNodoSoloEntregables = "";
    }
    
    public String getCadenaInorder() {
        return cadenaInorder;
    }

    public String getCadenaPreOrder() {
        return cadenaPreOrder;
    }

    public String getCadenaPostOrder() {
        return cadenaPostOrder;
    }

    public String getCadenaNodosTerminales() {
        return cadenaNodosTerminales;
    }

    public String getCadenaAltura() {
        return cadenaAltura;
    }

    public String getCadenaNodoSoloEntregables() {
        return cadenaNodoSoloEntregables;
    }
    
    public NodoArbol getRaiz(){
        return raiz;
    }
    
    public void setRaiz(NodoArbol raiz){
        this.raiz = raiz;
    }
    
}

