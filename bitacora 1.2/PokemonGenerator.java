

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PokemonGenerator {

    public static List<Pokemon> generateDatabase(int n, long seed) {
        //  herramientas básicas
        Random random = new Random(seed);
        List<Pokemon> database = new ArrayList<>();
        
        // cargar los datos desde el CSV
        LectorCSVPokemon lector = new LectorCSVPokemon("pokemon.csv");
        List<String> nombresBase = lector.getNombres();
        List<String> tiposBase = lector.getTipos();

        //  crea los N Pokémon de manera directa
        for (int i = 0; i < n; i++) {
            int id = i + 1;
            
            // elegir un nombre base y sumarle un número si queremos nombres únicos
            String nombreBase = nombresBase.get(i % nombresBase.size());
            String name = nombreBase + "_" + i;

            // elegir un tipo al azar
            int indiceTipo = random.nextInt(tiposBase.size());
            String type1 = tiposBase.get(indiceTipo);

            // generar estadísticas aleatorias
            // random.nextInt(max - min + 1) + min
            int hp = random.nextInt(255) + 1;        // entre 1 y 255
            int attack = random.nextInt(187) + 5;     // entre 5 y 191
            int defense = random.nextInt(227) + 5;    // entre 5 y 231
            int speed = random.nextInt(177) + 5;      // entre 5 y 181

            // crear el objeto y guardarlo en la lista
            Pokemon nuevoPokemon = new Pokemon(id, name, type1, hp, attack, defense, speed);
            database.add(nuevoPokemon);
        }

        return database;
    }
}
