package miarbol;

import excepciones.ArbolVacioExcepcion;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import lineales.ArbolBinario;
import lineales.ArbolBinarioBusqueda;

/**
 *
 * @author Abel de Andrés e Iván Barbado
 */

public class PED_G09_P3 {

    public static Scanner ent = null;

    public static int leerInt() {//Lectura de número por teclado, con la implementación de una excepción en caso de introducir un caracter que no se corresponda con un número.

        String strNumero = "";
        int numero = 0;
        boolean lecturaBien = false;
        do {
            ent = new Scanner(System.in);
            System.out.print("> ");
            strNumero = ent.nextLine();

            try {
                numero = Integer.parseInt(strNumero);
                lecturaBien = true;
            } catch (Exception e) {
            }

        } while (!lecturaBien);//Se repetirá la petición de strNumero mientras lecturaBien tenga el valor booleano false, es decir, no se corresponda con un entero.

        return numero;
    }

    //METODOS SOLICITADOS EN LA PRACTICA
    public static int sumarElementosImpares(ArbolBinarioBusqueda arbol) {
        return sumarElementosImparesRec(arbol);
    }

    private static int sumarElementosImparesRec(ArbolBinarioBusqueda arbol) {
        int suma = 0;
        if (!arbol.esVacio()) {
            try {
                if ((Integer) arbol.raiz() % 2 != 0) {
                    suma += (Integer) arbol.raiz();
                }
                suma += sumarElementosImparesRec(arbol.hijoIzq());
                suma += sumarElementosImparesRec(arbol.hijoDer());
            } catch (ArbolVacioExcepcion ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            return 0;
        }
        return suma;
    }

    public static ArbolBinario copiarABB(ArbolBinarioBusqueda arbol, ArbolBinario arbolBinario) {
        return copiarABBRec(arbol, arbolBinario);
    }

    private static ArbolBinario copiarABBRec(ArbolBinarioBusqueda arbol, ArbolBinario arbolBinario) {
        if (!arbol.esVacio()) {
            try {
                arbolBinario.setRaiz(arbol.raiz());                

                if (arbol.hijoIzq() != null) {
                    ArbolBinario<Integer> ar1 = new ArbolBinario<>();
                    arbolBinario.setHijoIzq(copiarABBRec(arbol.hijoIzq(), ar1));
                }
                if (arbol.hijoDer() != null) {
                    ArbolBinario<Integer> ar2 = new ArbolBinario<>();
                    arbolBinario.setHijoDer(copiarABBRec(arbol.hijoDer(), ar2));
                }
            } catch (ArbolVacioExcepcion ex) {
                System.out.println(ex.getMessage());
            }
        }
        return arbolBinario;
    }

    public static void modificarNiveles(ArbolBinario arbol, int nivel1, int nivel2, int valor) {
        if (!arbol.esVacio()) {
            modificarNivelesRec(arbol, 0, nivel1, nivel2, valor);
        }
    }

    private static ArbolBinario modificarNivelesRec(ArbolBinario arbolActual, int nivelActual, int nivel1, int nivel2, int valor) {
        try {
            if (nivel1 <= nivelActual && nivelActual <= nivel2) {            
                Integer dato = (Integer) arbolActual.raiz() * valor;
                arbolActual.setRaiz(dato);
                System.out.println(arbolActual.raiz());
            }
            if (arbolActual.hijoIzq() != null) {
                arbolActual.setHijoIzq(modificarNivelesRec(arbolActual.hijoIzq(), nivelActual + 1, nivel1, nivel2, valor));             
            }
            if (arbolActual.hijoDer() != null) {
                arbolActual.setHijoDer(modificarNivelesRec(arbolActual.hijoDer(), nivelActual + 1, nivel1, nivel2, valor));
            }
        } catch (ArbolVacioExcepcion ex) {
            //System.out.println(ex.getMessage());
        }
        return arbolActual;
    }
    
    
    
    public static String toStringPorNiveles(ArbolBinarioBusqueda arbol) {
        Queue<ArbolBinarioBusqueda> cola = new LinkedList<ArbolBinarioBusqueda>();
        String cadena="";
        if(!arbol.esVacio()) cola.add(arbol);
        while(!cola.isEmpty()){
            ArbolBinarioBusqueda arbolAux = cola.poll();//cola.desencolar()
            try {
                cadena+= arbolAux.raiz()+", ";
                if(!arbolAux.hijoIzq().esVacio())cola.add(arbolAux.hijoIzq());
                if(!arbolAux.hijoDer().esVacio())cola.add(arbolAux.hijoDer());
            } catch (ArbolVacioExcepcion ex) {
                
            }
        }        
        return cadena;
    }
       

    public static void menu() {
        System.out.println("\t\tMENÚ ÁRBOLES");
        System.out.println("\t\t------------");
        System.out.println("1.-Crear ABB de números enteros");
        System.out.println("2.-Dibujar árbol");
        System.out.println("3.-Listado de claves en un recorrido en PreOrden");
        System.out.println("4.-Listado de claves en un recorrido en InOrden");
        System.out.println("5.-Listado de claves en un recorrido en PostOrden");
        System.out.println("6.-Sumar elementos impares");
        System.out.println("7.-Copiar árbol ABB en árbol AB");
        System.out.println("8.-Actualizar nodos entre dos niveles (sólo en AB)");
        System.out.println("9.-Listar los nodos de un ABB en orden descendente");
        System.out.println("0.-Salir");
        System.out.println("Elija Opción:");
    }

    public static void menu2() {
        System.out.println("\t\tSeleccione modo de inserción: \n");
        System.out.println("1.-Inserción mediante vector ordenado (evita árboles degenerados)");
        System.out.println("2.-Inserción directa de elementos en el árbol");
        System.out.println("0.-Salir");
        System.out.println("Elija Opción:");
    }

    public static void main(String[] args) throws IOException, ArbolVacioExcepcion {

        int opcion;
        int opcion2;
        //ArrayCola<Integer> ArrayCola = null;
        ArbolBinarioBusqueda<Integer> arbol = null;
        ArbolBinario<Integer> arBin = null;
        boolean salir = false;
        boolean salir2= false;
        boolean ABBCreado = false;
        boolean ABCreado = false;
        boolean ArbolConContenido = false;

        do {
            menu();
            opcion = leerInt();          

            switch (opcion) {

                case 1:
                    //Claves: 10-7-12-5-8-3-6
                    arbol = new ArbolBinarioBusqueda<Integer>();
                    System.out.println("Árbol Binario de Búsqueda creado correctamente.");
                    System.out.println("Pulsa <Intro> para continuar...");
                    System.in.read();
                    ABBCreado = true;
                    Vector<Integer> v = new Vector<Integer>();

                    if (!ABBCreado) {
                        System.out.println("\nLo sentimos, ha de crear el árbol correctamente.\n");
                    } else {

                        do {
                            menu2();
                            opcion2 = leerInt();

                            switch (opcion2) {

                                case 1:
                                    boolean terminar = false;

                                    do {
                                        System.out.println("Introduzca números enteros para insertar en el árbol(Para terminar la insercción pulse -1): ");
                                        int numero = leerInt();

                                        if (numero == -1) {
                                            terminar = true;
                                        } else {

                                            v.add(numero);

                                        }

                                    } while (!terminar);
                                    Integer vector[] = new Integer[v.size()];
                                    for (int i = 0; i <= v.size() - 1; i++) {
                                        vector[i] = v.elementAt(i);
                                        //System.out.println(vector[i]);
                                    }

                                    arbol.crearABB(vector);
                                    salir2=true;
                                    ArbolConContenido = true;

                                    break;

                                case 2:
                                    terminar = false;
                                    do {
                                        System.out.println("Introduzca números enteros para insertar en el árbol(Para terminar la insercción pulse -1): ");
                                        int numero = leerInt();

                                        if (numero == -1) {
                                            terminar = true;
                                        } else {

                                            arbol.insertar(new Integer(numero));
                                        }

                                    } while (!terminar);
                                    
                                    salir2=true;

                                    break;

                                case 0:

                                    salir2 = true;
                                    System.out.println("ADIOS");

                                    break;
                            }
                        } while (!salir2);
                    }
                break;

                case 2:
                    
                    if (!ABBCreado) {
                        System.out.println("\nLo sentimos, el árbol no está creado, ha de crearlo previamente (Opción 1 del menú).\n");
                    } else {

                        if (!arbol.esVacio()) {
                            System.out.println("\nEl árbol contiene la siguiente información:\n");
                            //System.out.println(arbol.toStringPorNiveles());
                            System.out.println(toStringPorNiveles(arbol));
                            
                        } else {
                            System.out.println("\nÁrbol vacío.\n");
                            System.out.println("\nLo sentimos, el árbol está vacío, por favor, rellenelo de elementos previamente (Opción 1 del menú).\n");
                        }
                    }

                    break;

                case 3:

                    if (!ABBCreado) {
                        System.out.println("\nLo sentimos, el árbol no está creado, ha de crearlo previamente (Opción 1 del menú).\n");
                    } else {

                        if (!arbol.esVacio()) {
                            System.out.println("\nEl árbol contiene la siguiente información:\n");
                            System.out.println(arbol.toStringPreOrden());
                        } else {
                            System.out.println("\nÁrbol vacío.\n");
                            System.out.println("\nLo sentimos, el árbol está vacío, por favor, rellenelo de elementos previamente (Opción 1 del menú).\n");
                        }
                    }

                    break;

                case 4:

                    if (!ABBCreado) {
                        System.out.println("\nLo sentimos, el árbol no está creado, ha de crearlo previamente (Opción 1 del menú).\n");
                    } else {

                        if (!arbol.esVacio()) {
                            System.out.println("\nEl árbol contiene la siguiente información:\n");
                            System.out.println(arbol.toStringInOrden());
                        } else {
                            System.out.println("\nÁrbol vacía.\n");
                            System.out.println("\nLo sentimos, el árbol está vacío, por favor, rellenelo de elementos previamente (Opción 1 del menú).\n");
                        }
                    }

                    break;

                case 5:

                    if (!ABBCreado) {
                        System.out.println("\nLo sentimos, el árbol no está creado, ha de crearlo previamente (Opción 1 del menú).\n");
                    } else {

                        if (!arbol.esVacio()) {
                            System.out.println("\nEl árbol contiene la siguiente información:\n");
                            System.out.println(arbol.toStringPostOrden());
                        } else {
                            System.out.println("\nÁrbol vacía.\n");
                            System.out.println("\nLo sentimos, el árbol está vacío, por favor, rellenelo de elementos previamente (Opción 1 del menú).\n");
                        }
                    }

                    break;

                case 6:

                    if (!ABBCreado) {
                        System.out.println("\nLo sentimos, el árbol no está creado, ha de crearlo previamente (Opción 1 del menú).\n");
                    } else {

                        if (!arbol.esVacio()) {
                            System.out.println("\nLa suma de los elementos impares del árbol es la siguiente:\n");
                            System.out.println(sumarElementosImpares(arbol));
                        } else {
                            System.out.println("\nÁrbol vacío.\n");
                            System.out.println("\nLo sentimos, el árbol está vacío, por favor, rellenelo de elementos previamente (Opción 1 del menú).\n");
                        }
                    }

                    break;

                case 7:
                    if (!ABBCreado) {
                        System.out.println("\nLo sentimos, el árbol no está creado, ha de crearlo previamente (Opción 1 del menú).\n");
                    } else {

                        if (!arbol.esVacio()) {
                            System.out.println("\nEl árbol binario de busqueda contiene la siguiente información:\n");
                            System.out.println(arbol.toStringPreOrden());                            
                            arBin = new ArbolBinario<Integer>();
                            ABCreado = true;
                            if(ABCreado){
                                copiarABB(arbol, arBin);
                                System.out.println("\nEl árbol binario (copia) contiene la siguiente información:\n");
                                System.out.println(arBin.toStringPreOrden());
                            }
                            else{
                                System.out.println("\nLo sentimos, el árbol binario no está creado, ha de crearlo previamente (Vuelva a elegir la opción 7 del menú).\n");
                            }
                            
                        } else {
                            System.out.println("\nÁrbol vacío.\n");
                            System.out.println("\nLo sentimos, el árbol está vacío, por favor, rellenelo de elementos previamente (Opción 1 del menú).\n");
                        }
                    }

                    break;

                case 8:

                    if (!ABCreado) {
                        System.out.println("\nLo sentimos, el árbol binario no está creado, ha de crearlo previamente (Opción 7 del menú).\n");
                    } else {

                        if (!arbol.esVacio()) {
                            System.out.println("\nEl árbol binario contiene la siguiente información:\n");
                            System.out.println(arBin.toStringPreOrden());
                            System.out.println("\nSeleccione el nivel inferior:\n");
                            int nivel1 = leerInt();
                            System.out.println("\nSeleccione el nivel superior:\n");
                            int nivel2 = leerInt();
                            System.out.println("\nSeleccione el valor de modificación (Todo las claves entre los dos niveles elegidos anteriormente serán multiplicadas por este valor): \n");
                            int valor = leerInt();
                            if (nivel1 > nivel2) {
                                int aux;
                                aux = nivel1;
                                nivel1 = nivel2;
                                nivel2 = aux;
                            }
                            modificarNiveles(arBin, nivel1, nivel2, valor);
                            System.out.println(arBin.toStringPreOrden());

                        } else {
                            System.out.println("\nÁrbol vacío.\n");
                            System.out.println("\nLo sentimos, el árbol está vacío, por favor, rellenelo de elementos previamente (Opción 7 del menú).\n");
                        }
                    }

                    break;

                case 9:

                    if (!ABBCreado) {
                        System.out.println("\nLo sentimos, el árbol no está creado, ha de crearlo previamente (Opción 1 del menú).\n");
                    } else {

                        if (!arbol.esVacio()) {
                            System.out.println("\nEl árbol contiene la siguiente información mostrada descendentemente:\n");
                            System.out.println(arbol.toStringInOrdenConv());
                        } else {
                            System.out.println("\nÁrbol vacía.\n");
                            System.out.println("\nLo sentimos, el árbol está vacío, por favor, rellenelo de elementos previamente (Opción 1 del menú).\n");
                        }
                    }

                    break;
                    
                    case 0:

                    System.out.println("\n\t\t\t\tGracias por utilizar nuestro TAD Árbol\n");
                    System.out.println("\n\t\t\t\t\tIván Barbado & Abel de Andrés\n");
                    salir = true;
                    break;

                default:
                    System.out.println("\nPor favor, introduzca una opcion correcta, del 0 al 9, ambos inclusive y según corresponda.");

                    break;
            }

        } while (!salir);

    }

}
