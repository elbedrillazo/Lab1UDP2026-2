import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StopwatchCPU;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Iniciando batería de experimentos...");

        // Tamaños de instancia: n = 2^t, con t entre 10 y 15
        int[] valoresT = {10, 11, 12, 13, 14, 15};

        for (int t : valoresT) {
            int n = (int) Math.pow(2, t);
            System.out.println("\nEjecutando para tamaño n = " + n + " (2^" + t + ")");

            // Archivos de salida para este tamaño de instancia
            Out outExp1 = new Out("exp1_ordenamiento_n" + n + ".csv");
            outExp1.println("instancia,selectionSort,mergeSort");

            Out outExp2 = new Out("exp2_busqueda_n" + n + ".csv");
            outExp2.println("instancia,nombre,t_ordenamiento,t_lineal,t_binaria");

            for (int i = 0; i < 100; i++) {
                long seed = n + i;

                // 1. Generar la base de datos desordenada
                ArrayList<Pokemon> dbOriginal = PokemonGenerator.generateDatabase(n, seed);

                // =================================================================
                // EXPERIMENTO 1: Selection Sort vs Merge Sort
                // =================================================================

                // Copias independientes para que ambos partan del mismo estado desordenado
                ArrayList<Pokemon> copiaSelection = new ArrayList<>(dbOriginal);
                ArrayList<Pokemon> copiaMerge = new ArrayList<>(dbOriginal);

                PokemonDatabase pdSelection = new PokemonDatabase(copiaSelection);
                PokemonDatabase pdMerge = new PokemonDatabase(copiaMerge);

                // Medir Selection Sort (por totalStats)
                StopwatchCPU timerSel = new StopwatchCPU();
                pdSelection.ordenarPorAlgoritmo("selectionSort", "totalStats");
                double timeSelection = timerSel.elapsedTime();

                // Medir Merge Sort (por totalStats)
                StopwatchCPU timerMerge = new StopwatchCPU();
                pdMerge.ordenarPorAlgoritmo("mergeSort", "totalStats");
                double timeMerge = timerMerge.elapsedTime();

                // Guardar microdatos del Experimento 1
                outExp1.printf("%d,%.6f,%.6f\n", i, timeSelection, timeMerge);

                // =================================================================
                // EXPERIMENTO 2: Búsqueda Secuencial vs Búsqueda Binaria
                // =================================================================

                ArrayList<Pokemon> copiaBusqueda = new ArrayList<>(dbOriginal);
                PokemonDatabase pdBusqueda = new PokemonDatabase(copiaBusqueda);

                // Medir tiempo de ordenamiento previo (Merge Sort por "name")
                StopwatchCPU timerOrdenamiento = new StopwatchCPU();
                pdBusqueda.ordenarPorAlgoritmo("mergeSort", "name");
                double t_ordenamiento = timerOrdenamiento.elapsedTime();

                // Extraer el vocabulario único generado para obtener los 5 nombres en posiciones fijas
                ArrayList<String> vocabularioUnico = extraerVocabulario(dbOriginal);

                // Índices fijos requeridos por el enunciado: 0, n/200, n/100, 3n/200, n/50
                int[] indicesBusqueda = {0, n / 200, n / 100, (3 * n) / 200, n / 50};

                for (int idx : indicesBusqueda) {
                    // Ajuste de seguridad por si el índice supera el vocabulario real generado
                    int safeIdx = Math.min(idx, vocabularioUnico.size() - 1);
                    String nombreObjetivo = vocabularioUnico.get(safeIdx);

                    // A) Búsqueda Secuencial (Lineal) sobre la lista DESORDENADA
                    PokemonDatabase pdDesordenada = new PokemonDatabase(dbOriginal);
                    StopwatchCPU timerSecuencial = new StopwatchCPU();
                    pdDesordenada.sequentialSearch(nombreObjetivo, "name");
                    double t_lineal = timerSecuencial.elapsedTime();

                    // B) Búsqueda Binaria sobre la lista ORDENADA (100 veces)
                    StopwatchCPU timerBinario = new StopwatchCPU();
                    for (int b = 0; b < 100; b++) {
                        pdBusqueda.binarySearch(nombreObjetivo, "name");
                    }
                    double tiempoTotalBinario = timerBinario.elapsedTime();
                    double t_binaria = tiempoTotalBinario / 100.0; // Promedio por búsqueda

                    // Guardar microdatos del Experimento 2
                    outExp2.printf("%d,%s,%.6f,%.6f,%.6f\n", i, nombreObjetivo, t_ordenamiento, t_lineal, t_binaria);
                }
            }
            // Cerrar flujos de escritura para guardar los archivos
            outExp1.close();
            outExp2.close();
            System.out.println("Archivos CSV para n = " + n + " generados exitosamente.");
        }

        System.out.println("\n¡Todos los experimentos han finalizado!");
    }

    /**
     * Método auxiliar para extraer los nombres únicos presentes en la base de datos generada.
     * Esto permite obtener los nombres en las posiciones fijas que requiere el Experimento 2.
     */
    private static ArrayList<String> extraerVocabulario(ArrayList<Pokemon> db) {
        ArrayList<String> unicos = new ArrayList<>();
        for (Pokemon p : db) {
            if (!unicos.contains(p.getName())) {
                unicos.add(p.getName());
            }
        }
        return unicos;
    }
}
