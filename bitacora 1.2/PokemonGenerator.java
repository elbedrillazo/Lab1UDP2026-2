import edu.princeton.cs.algs4.StdRandom;
import java.util.ArrayList;

public class PokemonGenerator {
    // Optimización: Carga única del CSV para evitar lentitud
    private static final LectorCSVPokemon LECTOR = new LectorCSVPokemon("pokemon.csv");
    private static final ArrayList<String> NOMBRES_BASE = LECTOR.getNombres();
    private static final ArrayList<String> TIPOS_BASE = LECTOR.getTipos();

    public static ArrayList<Pokemon> generateDatabase(int n, long seed) {
        StdRandom.setSeed(seed);
        ArrayList<Pokemon> database = new ArrayList<>(n);

        int t = (int) (Math.log(n) / Math.log(2));
        if (t == 0) t = 1;
        int m = (int) Math.ceil((double) n / t);

        ArrayList<String> vocabulario = new ArrayList<>(m);
        int cantidadNombresBase = NOMBRES_BASE.size();
        
        for (int i = 0; i < m; i++) {
            int indiceBase = i % cantidadNombresBase;
            int sufijo = i / cantidadNombresBase;
            String nombreUnico = NOMBRES_BASE.get(indiceBase);
            if (sufijo > 0) nombreUnico += "_" + (sufijo - 1);
            vocabulario.add(nombreUnico);
        }

        String[] nombresAsignados = new String[n];
        for (int i = 0; i < n; i++) nombresAsignados[i] = vocabulario.get(i % m);
        StdRandom.shuffle(nombresAsignados);

        for (int i = 0; i < n; i++) {
            int id = i + 1;
            String name = nombresAsignados[i];
            String type1 = TIPOS_BASE.get(StdRandom.uniformInt(TIPOS_BASE.size()));
            
            // uniformInt en lugar de uniform para evitar errores de obsolescencia
            int hp = StdRandom.uniformInt(1, 256);
            int attack = StdRandom.uniformInt(5, 191);
            int defense = StdRandom.uniformInt(5, 231);
            int speed = StdRandom.uniformInt(5, 181);
            
            database.add(new Pokemon(id, name, type1, hp, attack, defense, speed));
        }
        return database;
    }
}
