package lineales;

import excepciones.*;
import java.util.LinkedList;
import java.util.Queue;
import miarbol.nodoBinario;
import modelos.TADAB;

/**
 *
 * @author Abel de Andrés e Iván Barbado
 * @param <E>
 */

public class ArbolBinario<E extends Comparable> implements TADAB<E> {
    
    nodoBinario<E> raiz = new nodoBinario<E>();
    
    
    public ArbolBinario(){
        raiz=null;
    }
    
    public ArbolBinario(E raiz)
    {
        nodoBinario<E> nuevo = new nodoBinario<E>();
        nuevo.setElemento(raiz);
        this.raiz=nuevo;
    }
    
    public ArbolBinario(E elemRaiz, ArbolBinario<E> hijoIzq,ArbolBinario<E> hijoDer) throws NullPointerException
    {
        if(hijoIzq==null ||hijoDer==null) throw new NullPointerException();
       nodoBinario<E> nuevo = new nodoBinario<E>();
       nuevo.setElemento(elemRaiz);
       nuevo.setHijoIzq(((ArbolBinario<E>)hijoIzq).raiz);
       nuevo.setHijoDer(((ArbolBinario<E>)hijoDer).raiz);
       raiz=nuevo;
       
    }
    
    private ArbolBinario(nodoBinario<E> raiz){
        this.raiz=raiz;
    }
    
    @Override
    public void suprimir(){
        this.raiz=null;
    }
     
    @Override
    public boolean esVacio(){
        if (this.raiz==null) return true;
        else return false;
    }
    
    
    @Override
    public E raiz() throws ArbolVacioExcepcion {
        if (this.raiz==null){
            ArbolVacioExcepcion e= new ArbolVacioExcepcion();
            throw e;
        }
        else return raiz.getElemento();
    }
    
    public void setRaiz(E raiz){
        if (!this.esVacio())
        {
            this.raiz.setElemento(raiz);
        }else{
            nodoBinario<E> nuevo= new nodoBinario();
            nuevo.setElemento(raiz);
            this.raiz=nuevo; 
        }

    }
    
    @Override
    public ArbolBinario<E> hijoIzq() throws ArbolVacioExcepcion{
        if (raiz.getHijoIzq()==null){
            ArbolVacioExcepcion e = new ArbolVacioExcepcion();
            throw e;
        }
        else return new ArbolBinario(raiz.getHijoIzq());
    }
    
    public ArbolBinario<E> hijoDer() throws ArbolVacioExcepcion{
        if (raiz.getHijoDer()==null){
            ArbolVacioExcepcion e = new ArbolVacioExcepcion();
            throw e;
        }
        else return new ArbolBinario(raiz.getHijoDer());
    }
    @Override
    public boolean buscar(E elemento){
        return buscarRec(elemento,this.raiz);
    }
    
    private boolean buscarRec(E elemento, nodoBinario<E> nodo){
        boolean flag=false;
        if(nodo.getElemento().compareTo(elemento)==0) return true;
        else{
            flag=buscarRec(elemento, nodo.getHijoIzq());
            flag=buscarRec(elemento, nodo.getHijoDer());
        }
        return flag;
    }
   
    @Override
    public void setHijoIzq(ArbolBinario<E> hijoIzq) throws ArbolVacioExcepcion, NullPointerException{
        if(hijoIzq==null) throw new NullPointerException();
        if(esVacio()){
            ArbolVacioExcepcion ex = new ArbolVacioExcepcion();
            throw ex;
        }
        raiz.setHijoIzq(((ArbolBinario<E>)hijoIzq).raiz);
    }
    
    @Override
    public void setHijoDer(ArbolBinario<E> hijoDer) throws ArbolVacioExcepcion, NullPointerException{
        if(hijoDer==null) throw new NullPointerException();
        if(esVacio()){
            ArbolVacioExcepcion ex = new ArbolVacioExcepcion();
            throw ex;
        }
        raiz.setHijoDer(((ArbolBinario<E>)hijoDer).raiz);
    }
    
    public String toStringPreOrden() {
        return toStringPreOrdenRec(this.raiz);
    }
    
    private String toStringPreOrdenRec(nodoBinario<E> nodo){
        if (nodo==null) return "";
        else {
           return nodo.getElemento()+"\n"+toStringPreOrdenRec(nodo.getHijoIzq())+toStringPreOrdenRec(nodo.getHijoDer());
        }
    }


    public String toStringPostOrden() {
             return toStringPostOrdenRec(this.raiz);
    }
    
    private String toStringPostOrdenRec(nodoBinario<E> nodo)
    {
        if (nodo==null) return "";
        else {
           return toStringPostOrdenRec(nodo.getHijoIzq())+toStringPostOrdenRec(nodo.getHijoDer())+nodo.getElemento()+"\n";
        }
    }


     public String toStringInOrden() {
        return toStringInOrdenRec(this.raiz);
    }
    
 
    private String toStringInOrdenRec(nodoBinario<E> nodo) {
        if (nodo==null) return "";
        else {
           return toStringInOrdenRec(nodo.getHijoIzq())+nodo.getElemento()+"\n"+toStringInOrdenRec(nodo.getHijoDer());
        }
    }
    
     public String toStringInOrdenConv() {
        return toStringInOrdenConvRec(this.raiz);
    }
    
 
    private String toStringInOrdenConvRec(nodoBinario<E> nodo) {
        if (nodo==null) return "";
        else {
           return toStringInOrdenConvRec(nodo.getHijoDer())+nodo.getElemento()+"\n"+toStringInOrdenConvRec(nodo.getHijoIzq());
        }
    }   
    

    public String toStringPorNiveles() {
        Queue<nodoBinario<E>> cola = new LinkedList<nodoBinario<E>>();
        String cadena="";
        if(!esVacio()) cola.add(this.raiz);
        while(!cola.isEmpty()){
            nodoBinario<E> nodo = cola.poll();//cola.desencolar()
            cadena+= nodo.getElemento()+", ";
            if(nodo.getHijoIzq()!=null)cola.add(nodo.getHijoIzq());
            if(nodo.getHijoDer()!=null)cola.add(nodo.getHijoDer());
        }        
        return cadena;
    }
   
}
