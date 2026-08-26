import edu.princeton.cs.algs4.StdRandom;
import java.util.ArrayList;

/**
 * Clase responsable de generar las bases de datos sintéticas de Pokémon
 * para los experimentos de algoritmos de ordenamiento y búsqueda.
 */
public class PokemonGenerator {

    /**
     * Genera una base de datos de tamaño n utilizando una semilla específica
     * para asegurar la reproducibilidad.
     *
     * @param n    Tamaño de la instancia (debe ser una potencia de 2, ej. n=2^t).
     * @param seed Semilla para la generación de números aleatorios.
     * @return ArrayList con n objetos Pokemon.
     */
    public static ArrayList<Pokemon> generateDatabase(int n, long seed) {
        // Fijar la semilla para reproducibilidad
        StdRandom.setSeed(seed);

        ArrayList<Pokemon> database = new ArrayList<>();

        // 1. Instanciar el lector para extraer vocabulario base
        // Asegúrate de que el archivo pokemon.csv esté en el directorio correcto de tu proyecto
        LectorCSVPokemon lector = new LectorCSVPokemon("pokemon.csv");
        ArrayList<String> nombresBase = lector.getNombres();
        ArrayList<String> tiposBase = lector.getTipos();

        // 2. Calcular t sabiendo que n = 2^t
        int t = (int) (Math.log(n) / Math.log(2));
        if (t == 0) t = 1; // Protección básica matemática por si n = 1

        // 3. Calcular m = ceil(n/t)
        int m = (int) Math.ceil((double) n / t);

        // 4. Construir vocabulario de exactamente m nombres únicos
        ArrayList<String> vocabulario = new ArrayList<>();
        int cantidadNombresBase = nombresBase.size();

        for (int i = 0; i < m; i++) {
            int indiceBase = i % cantidadNombresBase;
            int sufijo = i / cantidadNombresBase;

            String nombreUnico = nombresBase.get(indiceBase);
            if (sufijo > 0) {
                // Si necesitamos más nombres de los que hay, agregamos sufijo (ej. Pikachu_0)
                nombreUnico += "_" + (sufijo - 1);
            }
            vocabulario.add(nombreUnico);
        }

        // 5. Distribuir los n registros de manera balanceada entre los m nombres
        String[] nombresAsignados = new String[n];
        for (int i = 0; i < n; i++) {
            nombresAsignados[i] = vocabulario.get(i % m);
        }

        // 6. Mezclar (shuffle) la secuencia para que las repeticiones no queden agrupadas
        StdRandom.shuffle(nombresAsignados);

        // 7. Generar los n objetos Pokemon con atributos aleatorios realistas
        for (int i = 0; i < n; i++) {
            int id = i + 1; // Secuencial de 1 a n
            String name = nombresAsignados[i];

            // Tipo aleatorio de la lista extraída del CSV
            String type1 = tiposBase.get(StdRandom.uniform(tiposBase.size()));

            // Estadísticas dentro de los márgenes estipulados (uniform(a, b) genera en rango [a, b-1])
            int hp = StdRandom.uniform(1, 256);      // [1, 255]
            int attack = StdRandom.uniform(5, 191);  // [5, 190]
            int defense = StdRandom.uniform(5, 231); // [5, 230]
            int speed = StdRandom.uniform(5, 181);   // [5, 180]

            Pokemon p = new Pokemon(id, name, type1, hp, attack, defense, speed);
            database.add(p);
        }

        return database;
    }
}
