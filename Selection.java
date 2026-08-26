import java.util.ArrayList;
import java.util.Comparator;

/**
 * La clase Selection proporciona métodos estáticos para ordenar un ArrayList
 * de objetos Pokemon usando el algoritmo Selection Sort.
 * Adaptado de la implementación original de Princeton (algs4).
 */
public class Selection {

    // Constructor privado para que la clase no deba ser instanciada.
    private Selection() { }

    /**
     * Reorganiza la lista en orden ascendente usando el comparador proporcionado.
     * @param a La lista de Pokémon a ordenar.
     * @param comparator El comparador que define el criterio de ordenamiento.
     */
    public static void sort(ArrayList<Pokemon> a, Comparator<Pokemon> comparator) {
        int n = a.size();
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (less(comparator, a.get(j), a.get(min))) {
                    min = j;
                }
            }
            exch(a, i, min);
        }
    }

    /**
     * Funciones auxiliares de ordenamiento.
     */

    // ¿Es v < w?
    private static boolean less(Comparator<Pokemon> comparator, Pokemon v, Pokemon w) {
        return comparator.compare(v, w) < 0;
    }

    // Intercambia los elementos a.get(i) y a.get(j)
    private static void exch(ArrayList<Pokemon> a, int i, int j) {
        Pokemon swap = a.get(i);
        a.set(i, a.get(j));
        a.set(j, swap);
    }
}
