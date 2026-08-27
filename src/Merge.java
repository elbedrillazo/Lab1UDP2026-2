import java.util.ArrayList;
import java.util.Comparator;

/**
 * La clase Merge proporciona métodos estáticos para ordenar un ArrayList
 * de objetos Pokemon usando una versión recursiva top-down de Merge Sort.
 * Adaptado de la implementación original de Princeton (algs4).
 */
public class Merge {

    // Constructor privado para que la clase no deba ser instanciada.
    private Merge() { }

    // Fusiona a[lo .. mid] con a[mid+1 ..hi] usando aux[lo .. hi] de forma estable
    private static void merge(ArrayList<Pokemon> a, ArrayList<Pokemon> aux, int lo, int mid, int hi, Comparator<Pokemon> comparator) {
        // Copiar a aux
        for (int k = lo; k <= hi; k++) {
            aux.set(k, a.get(k));
        }

        // Fusionar de regreso a a
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)                                      a.set(k, aux.get(j++));
            else if (j > hi)                                       a.set(k, aux.get(i++));
            else if (less(comparator, aux.get(j), aux.get(i)))     a.set(k, aux.get(j++));
            else                                                   a.set(k, aux.get(i++));
        }
    }

    // Ordenamiento recursivo mergesort usando la lista auxiliar
    private static void sort(ArrayList<Pokemon> a, ArrayList<Pokemon> aux, int lo, int hi, Comparator<Pokemon> comparator) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid, comparator);
        sort(a, aux, mid + 1, hi, comparator);
        merge(a, aux, lo, mid, hi, comparator);
    }

    /**
     * Reorganiza la lista en orden ascendente, usando el comparador especificado.
     * @param a La lista de Pokémon a ordenar.
     * @param comparator El comparador que define el criterio de ordenamiento.
     */
    public static void sort(ArrayList<Pokemon> a, Comparator<Pokemon> comparator) {
        // Creamos una lista auxiliar inicializada con el mismo tamaño y elementos
        ArrayList<Pokemon> aux = new ArrayList<>(a);
        sort(a, aux, 0, a.size() - 1, comparator);
    }

    /**
     * Funciones auxiliares.
     */

    // ¿Es v < w?
    private static boolean less(Comparator<Pokemon> comparator, Pokemon v, Pokemon w) {
        return comparator.compare(v, w) < 0;
    }
}
