package modelos;


import excepciones.*;
import lineales.ArbolBinarioBusqueda;

/**
 *
 * @author Abel de Andrés e Iván Barbado
 */

public interface TADABB<E extends Comparable> {
    
    
    //TAD ARBOL BINARIO BÚSQUEDA
    public E raiz() throws ArbolVacioExcepcion;
    public ArbolBinarioBusqueda<E> hijoIzq() throws ArbolVacioExcepcion;
    public ArbolBinarioBusqueda<E> hijoDer() throws ArbolVacioExcepcion;
    public boolean esVacio();
    public void insertar (E elemento);
    public void eliminar(E elemento)throws ElementoNoEncontrado;
    public boolean buscar(E elemento);
    public void suprimir();
    
}
