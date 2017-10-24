package lineales;

import excepciones.*;
import java.util.LinkedList;
import java.util.Queue;
import miarbol.nodoBinario;
import modelos.TADABB;

/**
 *
 * @author Abel de Andrés e Iván Barbado
 * @param <E>
 */

public class ArbolBinarioBusqueda<E extends Comparable> implements TADABB<E>  {
    //Todos son iguales salvo insertarConDuplicado,insertarSinDulicados,eliminar,
    nodoBinario<E> raiz = new nodoBinario<E>(); 
    
    public ArbolBinarioBusqueda() {
        raiz=null;
    }
    
    public ArbolBinarioBusqueda(nodoBinario<E> r) {
        raiz=r;
    }
    
    @Override
    public void suprimir(){
        this.raiz=null;
    }

    @Override
    public E raiz() throws ArbolVacioExcepcion {
        if(this.raiz==null){
            ArbolVacioExcepcion e= new ArbolVacioExcepcion();
            throw e;
        }
        else return raiz.getElemento();
    }

    @Override
    public ArbolBinarioBusqueda<E> hijoIzq() throws ArbolVacioExcepcion {
        if(this.raiz==null){
            ArbolVacioExcepcion e= new ArbolVacioExcepcion();
            throw e;
        }
        else return new ArbolBinarioBusqueda(raiz.getHijoIzq());
    }

    @Override
    public ArbolBinarioBusqueda<E> hijoDer() throws ArbolVacioExcepcion {
        if(this.raiz==null){
            ArbolVacioExcepcion e= new ArbolVacioExcepcion();
            throw e;
        }
        else return new ArbolBinarioBusqueda(raiz.getHijoDer());
    }
    

    public void setHijoIzq(ArbolBinarioBusqueda<E> hijoIzq) throws ArbolVacioExcepcion, NullPointerException{
        if(hijoIzq==null) throw new NullPointerException();
        if(esVacio()){
            ArbolVacioExcepcion ex = new ArbolVacioExcepcion();
            throw ex;
        }
        raiz.setHijoIzq(((ArbolBinarioBusqueda<E>)hijoIzq).raiz);
    }
    

    public void setHijoDer(ArbolBinarioBusqueda<E> hijoDer) throws ArbolVacioExcepcion, NullPointerException{
        if(hijoDer==null) throw new NullPointerException();
        if(esVacio()){
            ArbolVacioExcepcion ex = new ArbolVacioExcepcion();
            throw ex;
        }
        raiz.setHijoDer(((ArbolBinarioBusqueda<E>)hijoDer).raiz);
    }
    
    @Override
    public boolean esVacio() {
        if(this.raiz==null) return true;
        else return false;
    }

    private boolean esHoja(nodoBinario<E> nodo)
    {
        if(nodo!=null){
           if(nodo.getHijoDer()==null && nodo.getHijoIzq()==null) return true;
            else return false; 
        }
        else return false;
            
    }
    
    @Override
    public boolean buscar(E elem){
        return buscarRec(this.raiz,elem);
    }
    
    private boolean buscarRec(nodoBinario nodo, E elem)
    {
       boolean bandera=false;
        if (nodo!=null){
           if(nodo.getElemento().compareTo(elem)==0) return true;
           else if(nodo.getElemento().compareTo(elem)<0){
               bandera=buscarRec(nodo.getHijoDer(),elem);
           }
           else if(nodo.getElemento().compareTo(elem)>0){
               bandera=buscarRec(nodo.getHijoIzq(),elem);
           }
               
       } 
        return bandera;
    }

    public E recuperarMin() throws ArbolVacioExcepcion {
        return recuperarMinRec(this.raiz);
    }
    
    private E recuperarMinRec(nodoBinario<E> nodo) throws ArbolVacioExcepcion{
        if(nodo==null){
            ArbolVacioExcepcion e=new ArbolVacioExcepcion();
            throw e;
        }
        else
        {
            if(esHoja(nodo)) return nodo.getElemento();
            else if (nodo.getHijoIzq()!=null){               
                return recuperarMinRec(nodo.getHijoIzq());
            }
            else return nodo.getElemento();
        }
    }
    
    //Hemos elegido que el metodo insertar utilice el metodo insertarConDuplicados por defecto.
    @Override
    public void insertar (E elemento){
        this.insertarConDuplicado(elemento);
    }


    public void insertarSinDulicados(E elem) throws ElementoDuplicado {
        this.raiz=insertarSinDuplicadosRec(this.raiz,elem);
    }
    
    protected nodoBinario<E> insertarSinDuplicadosRec(nodoBinario<E> nodo, E elem) throws ElementoDuplicado{
        if (nodo==null)
        {
            nodoBinario<E> nodoNuevo= new nodoBinario<E>();
            nodoNuevo.setElemento(elem);
            return nodoNuevo;
        }
        else{
           if(nodo.getElemento().compareTo(elem)==0){
            ElementoDuplicado e = new ElementoDuplicado();
            throw e;
            } 
           else if(nodo.getElemento().compareTo(elem)>0) nodo.setHijoIzq(insertarSinDuplicadosRec(nodo.getHijoIzq(),elem));
           else nodo.setHijoDer(insertarSinDuplicadosRec(nodo.getHijoDer(),elem));
           return nodo;
        }       
    }


    public void insertarConDuplicado(E elem) {
        this.raiz=insertarConDuplicadoRec(this.raiz,elem);
    }
    
     private nodoBinario<E> insertarConDuplicadoRec(nodoBinario<E> nodo, E elem) {
        if (nodo==null)
        {
            nodoBinario<E> nodoNuevo= new nodoBinario<E>();
            nodoNuevo.setElemento(elem);
            return nodoNuevo;
        }
        else{
           if(nodo.getElemento().compareTo(elem)>=0) 
           {
               nodo.setHijoIzq(insertarConDuplicadoRec(nodo.getHijoIzq(),elem));
           }
           else nodo.setHijoDer(insertarConDuplicadoRec(nodo.getHijoDer(),elem));
           
        }
        return nodo;
    }
    
    @Override
    public void eliminar(E elem) throws ElementoNoEncontrado {
        if(this.raiz==null){
            throw new ElementoNoEncontrado();
        }
        else{
            if(esHoja(raiz)){
                if(this.raiz.getElemento().compareTo(elem)==0) this.raiz=null;
            }
            else eliminarRec(this.raiz,elem);
        }
    }
    private void eliminarRec(nodoBinario<E> padre,E elem){
        if(padre.getHijoIzq().getElemento().compareTo(elem)==0){try {
            //Está en el izquierdo
            padre.getHijoIzq().setElemento(recuperarMinRec(padre.getHijoIzq().getHijoDer()));
            } catch (ArbolVacioExcepcion ex) {
               
            }
            eliminarMinRec(padre.getHijoIzq().getHijoDer());
        }
        else if(padre.getHijoDer().getElemento().compareTo(elem)==0){
            try {
                padre.getHijoDer().setElemento(recuperarMinRec(padre.getHijoDer().getHijoDer()));
            } catch (ArbolVacioExcepcion ex) {
                
            }
            eliminarMinRec(padre.getHijoDer().getHijoDer());
        }
        else if(padre.getElemento().compareTo(elem)<0){//En alguna parte del Hijo derecho
            eliminarRec(padre.getHijoDer(), elem);
        }
        else{//Algun hijo del izquierdo
            eliminarRec(padre.getHijoIzq(), elem);
        }
    }

    public void eliminarMin() {
        if(!esVacio()){
            if(esHoja(raiz)) this.raiz=null;
            else eliminarMinRec(this.raiz);
        }
    }
    private void eliminarMinRec(nodoBinario<E> padre){
        if(padre.getHijoIzq().getHijoIzq()==null){
            if(padre.getHijoIzq().getHijoDer()!=null){
                padre.setHijoIzq(padre.getHijoIzq().getHijoIzq());
            }
            else padre.setHijoIzq(null);
        }
        else eliminarMinRec(padre.getHijoIzq());
    }



    public int altura() {
        return alturaRec(this.raiz);
    }
    
    private int alturaRec(nodoBinario<E> nodo)
    {
        if(nodo==null) return 0;
        else{
            return 1+maximo(alturaRec(nodo.getHijoIzq()),alturaRec(nodo.getHijoDer()));
        }
    }
    
    private int maximo(int a, int b)
    {
        if(a>b) return a;
        else return b;
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
   
  public void crearABB(E vector[])
   {
       vector=ordenarVector(vector);
       crearABBRec(vector,0,vector.length-1);
   }
   
   public void crearABBRec(E[] vector,int inicio,int fin){
           if(inicio<=fin)
           {
               int mediana=(fin+1+inicio)/2;
               insertarConDuplicado(vector[mediana]);
               crearABBRec(vector,inicio,mediana-1);
               crearABBRec(vector,mediana+1,fin);
           }
           
           
       }            
   
   private E[] ordenarVector(E[] vector)
   {
    E aux;
    for (int i = 0; i <= vector.length - 1; i++) {
        for (int x = i + 1; x <= vector.length-1; x++) {
            //vector[x] < vector[i]
            if (vector[x].compareTo(vector[i])<0) {
                aux = vector[i];
                vector[i] = vector[x];
                vector[x] = aux;
            }
        }
    }
    return vector;
   }
   
}
