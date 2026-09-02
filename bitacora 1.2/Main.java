/*
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StopwatchCPU;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("empieza experimento");
        int[] valorn = {10, 11, 12, 13, 14, 15};
        for (int x : valorn) {
            int n= (int) Math.pow(2, t);
            System.out.println("instancia n= " + n);
            */

import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StopwatchCPU;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando prototipo de pruebas Hito 1.2...");

        // Valores t reducidos para probar correctitud rápidamente (n = 16, 32, 64)
        int[] valoresT = {4, 5, 6};

        for (int t : valoresT) {
            int n = (int) Math.pow(2, t);
            System.out.println("Ejecutando instancia de prueba n = " + n);

            Out outExp1 = new Out("prueba_exp1_n" + n + ".csv");
            outExp1.println("instancia,selectionSort,mergeSort");

            Out outExp2 = new Out("prueba_exp2_n" + n + ".csv");
            outExp2.println("instancia,nombre,t_ordenamiento,t_lineal,t_binaria");

            for (int i = 0; i < 100; i++) {
                long seed = n + i;
                ArrayList<Pokemon> dbOriginal = PokemonGenerator.generateDatabase(n, seed);

                ArrayList<Pokemon> copiaSelection = new ArrayList<>(dbOriginal);
                ArrayList<Pokemon> copiaMerge = new ArrayList<>(dbOriginal);
                
                PokemonDatabase pdSelection = new PokemonDatabase(copiaSelection);
                PokemonDatabase pdMerge = new PokemonDatabase(copiaMerge);

                StopwatchCPU timerSel = new StopwatchCPU();
                pdSelection.ordenarPorAlgoritmo("selectionSort", "totalStats");
                double timeSelection = timerSel.elapsedTime();

                StopwatchCPU timerMerge = new StopwatchCPU();
                pdMerge.ordenarPorAlgoritmo("mergeSort", "totalStats");
                double timeMerge = timerMerge.elapsedTime();

                outExp1.printf("%d,%.6f,%.6f\n", i, timeSelection, timeMerge);

                ArrayList<Pokemon> copiaBusqueda = new ArrayList<>(dbOriginal);
                PokemonDatabase pdBusqueda = new PokemonDatabase(copiaBusqueda);

                StopwatchCPU timerOrdenamiento = new StopwatchCPU();
                pdBusqueda.ordenarPorAlgoritmo("mergeSort", "name");
                double t_ordenamiento = timerOrdenamiento.elapsedTime();

                ArrayList<String> vocabularioUnico = extraerVocabulario(dbOriginal);
                int[] indicesBusqueda = {0, n / 200, n / 100, (3 * n) / 200, n / 50};

                for (int idx : indicesBusqueda) {
                    int safeIdx = Math.max(0, Math.min(idx, vocabularioUnico.size() - 1));
                    String nombreObjetivo = vocabularioUnico.get(safeIdx);

                    PokemonDatabase pdDesordenada = new PokemonDatabase(dbOriginal);
                    StopwatchCPU timerSecuencial = new StopwatchCPU();
                    pdDesordenada.sequentialSearch(nombreObjetivo, "name");
                    double t_lineal = timerSecuencial.elapsedTime();

                    StopwatchCPU timerBinario = new StopwatchCPU();
                    for (int b = 0; b < 100; b++) {
                        pdBusqueda.binarySearch(nombreObjetivo, "name");
                    }
                    double t_binaria = timerBinario.elapsedTime() / 100.0; 

                    outExp2.printf("%d,%s,%.6f,%.6f,%.6f\n", i, nombreObjetivo, t_ordenamiento, t_lineal, t_binaria);
                }
            }
            outExp1.close();
            outExp2.close();
        }
        System.out.println("Prototipo finalizado exitosamente.");
    }

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
