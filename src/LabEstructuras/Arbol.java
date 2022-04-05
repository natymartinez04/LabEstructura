/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LabEstructuras;

/**
 *
 * @author tllach, nmartinez, dkaty
 */
public class Arbol {
    
    NodoArbol raiz;
    String cadenaInorder;
    String cadenaPreOrder;
    String cadenaPostOrder;
    String cadenaNodosTerminales;
    String cadenaAltura;
    String cadenaNodoSoloEntregables;

    public Arbol(){
        this.raiz = null;
        cadenaInorder = "";
    }
    
    public Boolean Existe(NodoArbol raiz, String dato){
        Boolean encontrado = false;
        if (raiz.dato.equals(dato)){
            encontrado = true;
        }else{
            NodoLista p = raiz.hijos.ptr;
            while(p != null){
                if (dato.equals(p.nodoArbol.dato)){
                    encontrado = true;
                }
                Existe(p.nodoArbol, dato);
                p = p.link;
            }
        }
        return encontrado;
    }
    
    public int AlturaArbol(NodoArbol raiz){
        if (raiz == null){
            return -1;
        }
        int altura = -1;
        NodoLista p = raiz.hijos.ptr;
        while (p != null){
            altura = Math.max(altura, AlturaArbol(p.nodoArbol));
            p = p.link;
        }    
        return altura +1;
    }
    
    public void NodosTerminales (NodoArbol raiz){
        if (raiz.hijos.ptr == null){
            cadenaNodosTerminales = cadenaNodosTerminales.concat(raiz.dato + " ");
        }else{
            NodoLista p = raiz.hijos.ptr;
            while(p != null){
                if (p.nodoArbol.hijos == null){
                    cadenaNodosTerminales = cadenaNodosTerminales.concat(p.nodoArbol.dato + " ");
                }
                NodosTerminales(p.nodoArbol);
                p = p.link;
            }
        }
    }
    
    public void NodoSoloUnEntregable(NodoArbol raiz){
        int conta = 0;

        if (raiz != null){
            if (raiz.hijos.ptr != null){
                NodoLista p = raiz.hijos.ptr;
                NodoLista k = p;
                while(k != null){
                    if (k.nodoArbol.tipo == true){
                        conta ++;
                    }
                    k = k.link;
                }
                if (conta == 1){
                    cadenaNodoSoloEntregables = cadenaNodoSoloEntregables.concat(raiz.dato + " ");
                }
                conta = 0;
                NodoSoloUnEntregable(p.nodoArbol);
                p = p.link;
            }
        }
    }
    
    public void recorridoPreorden(NodoArbol raiz){
        cadenaPreOrder = cadenaPreOrder.concat((String) raiz.dato + " ");
        if(raiz.hijos.ptr != null){
            for(int i = 0; i < raiz.hijos.getTamaño(); i++){
                recorridoPreorden((NodoArbol) raiz.hijos.getNodoArbol(i));
            }
        }
    }
    
    public void recorridoInorden(NodoArbol raiz){
        if(raiz.hijos != null){
            
            ListaEnlazada izq = dividir(raiz.hijos)[0];
            ListaEnlazada der = dividir(raiz.hijos)[1];
            
            if(izq.getTamaño() != 0){
                for(int i = 0; i < izq.getTamaño(); i++){
                    recorridoInorden((NodoArbol) izq.getNodoArbol(i));
                }
                cadenaInorder = cadenaInorder.concat((String) raiz.dato + " ");
                if(der.getTamaño() != 0){
                    for(int i = 0; i < der.getTamaño(); i++){
                        recorridoInorden((NodoArbol) der.getNodoArbol(i));
                    }
                } 
            } 
        }
        if(!cadenaInorder.contains((String) raiz.dato)){
            cadenaInorder = cadenaInorder.concat((String) raiz.dato + " ");
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
        NodoLista p = lista.ptr;
        int ite = 0;
        if(tam % 2 == 0){
            while(p != null){
                if(ite < tamL){
                    a.insert(p.nodoArbol);
                }else{
                    b.insert(p.nodoArbol);
                }
                p = p.link;
                ite++;
            }
        }else{
            while(p != null){
                if(ite < tamL){
                    a.insert(p.nodoArbol);
                }else{
                    b.insert(p.nodoArbol);
                }
                p = p.link;
                ite++;
            }
        }
        return new ListaEnlazada[]{a,b};
    }
    
    public void recorridoPostOrden(NodoArbol raiz){
        if(raiz.hijos.ptr != null){
            for(int i = 0; i < raiz.hijos.getTamaño(); i++){
                recorridoPostOrden((NodoArbol) raiz.hijos.getNodoArbol(i));
            }  
        }
        cadenaPostOrder = cadenaPostOrder.concat((String) raiz.dato +  " ");
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
    
}

