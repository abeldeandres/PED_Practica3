package excepciones;

/**
 *
 * @author Abel de Andrés e Iván Barbado
 */

public class ArbolVacioExcepcion extends Exception {
    public ArbolVacioExcepcion(){
        super("\nEl arbol está vacio\n");
    }
}
