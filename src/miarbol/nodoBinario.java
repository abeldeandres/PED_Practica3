package miarbol;

/**
 *
 * @author Abel de Andrés e Iván Barbado
 */

public class nodoBinario<E extends Comparable>  {
    E elemento;
    nodoBinario<E> hijoIzq;
    nodoBinario<E> hijoDer;
    int tamanio;
        

    public E getElemento() {
        return elemento;
    }

    public void setElemento(E elemento) {
        this.elemento = elemento;
    }

    public nodoBinario<E> getHijoIzq() {
        return hijoIzq;
    }

    public void setHijoIzq(nodoBinario<E> hijoIzq) {
        this.hijoIzq = hijoIzq;
    }

    public nodoBinario<E> getHijoDer() {
        return hijoDer;
    }

    public void setHijoDer(nodoBinario<E> hijoDer) {
        this.hijoDer = hijoDer;
    }       
    
}
