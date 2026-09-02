import java.util.ArrayList;
import java.util.Comparator;

public class Selection {
    private Selection() { }

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

    private static boolean less(Comparator<Pokemon> comparator, Pokemon v, Pokemon w) {
        return comparator.compare(v, w) < 0;
    }

    private static void exch(ArrayList<Pokemon> a, int i, int j) {
        Pokemon swap = a.get(i);
        a.set(i, a.get(j));
        a.set(j, swap);
    }
}
