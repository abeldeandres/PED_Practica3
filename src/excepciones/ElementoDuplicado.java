package excepciones;

/**
 *
 * @author Abel de Andrés e Iván Barbado
 */

public class ElementoDuplicado extends Exception {
    public ElementoDuplicado(){
        super("\nEl elemento esta duplicado en el arbol\n");
    }
}
