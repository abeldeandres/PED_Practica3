package modelos;

import excepciones.ArbolVacioExcepcion;
import lineales.ArbolBinario;

/**
 *
 * @author Abel de Andrés e Iván Barbado
 */

public interface TADAB<E extends Comparable> {
    public boolean esVacio();
    public E raiz() throws ArbolVacioExcepcion;
    public void setRaiz(E raiz);
    public ArbolBinario<E> hijoIzq() throws ArbolVacioExcepcion;
    public ArbolBinario<E> hijoDer() throws ArbolVacioExcepcion;
    public void setHijoIzq(ArbolBinario<E> hijoIzq) throws ArbolVacioExcepcion, NullPointerException;
    public void setHijoDer(ArbolBinario<E> hijoIzq) throws ArbolVacioExcepcion, NullPointerException;
    public boolean buscar(E elemento);
    public void suprimir();  
    
}
