import java.util.ArrayList;
import java.util.Comparator;

public class Merge {
    private Merge() { }

    private static void merge(ArrayList<Pokemon> a, ArrayList<Pokemon> aux, int lo, int mid, int hi, Comparator<Pokemon> comparator) {
        for (int k = lo; k <= hi; k++) {
            aux.set(k, a.get(k));
        }

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)                                      a.set(k, aux.get(j++));
            else if (j > hi)                                       a.set(k, aux.get(i++));
            else if (less(comparator, aux.get(j), aux.get(i)))     a.set(k, aux.get(j++));
            else                                                   a.set(k, aux.get(i++));
        }
    }

    private static void sort(ArrayList<Pokemon> a, ArrayList<Pokemon> aux, int lo, int hi, Comparator<Pokemon> comparator) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid, comparator);
        sort(a, aux, mid + 1, hi, comparator);
        merge(a, aux, lo, mid, hi, comparator);
    }

    public static void sort(ArrayList<Pokemon> a, Comparator<Pokemon> comparator) {
        ArrayList<Pokemon> aux = new ArrayList<>(a);
        sort(a, aux, 0, a.size() - 1, comparator);
    }

    private static boolean less(Comparator<Pokemon> comparator, Pokemon v, Pokemon w) {
        return comparator.compare(v, w) < 0;
    }
}
