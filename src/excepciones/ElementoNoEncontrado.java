package excepciones;

/**
 *
 * @author Abel de Andrés e Iván Barbado
 */

public class ElementoNoEncontrado extends Exception {
    public ElementoNoEncontrado(){
        super("\nEl elemento no se ha encontrado\n");
    }
}
