import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PokemonGenerator {

    public static List<Pokemon> generateDatabase(int n, long seed) {

        Random random = new Random(seed);
        List<Pokemon> database = new ArrayList<>();

        LectorCSVPokemon lector = new LectorCSVPokemon("pokemon.csv");
        List<String> nombresBase = lector.getNombres();
        List<String> tiposBase = lector.getTipos();

        for (int i = 0; i < n; i++) {
            int id = i + 1;

            String nombreBase = nombresBase.get(i % nombresBase.size());
            String name = nombreBase + "_" + i;

            int indiceTipo = random.nextInt(tiposBase.size());
            String type1 = tiposBase.get(indiceTipo);

            int hp = random.nextInt(255) + 1;
            int attack = random.nextInt(187) + 5;
            int defense = random.nextInt(227) + 5;
            int speed = random.nextInt(177) + 5;

            // Se añade un 0 al final para cumplir con el parámetro totalStats de tu constructor
            Pokemon nuevoPokemon = new Pokemon(id, name, type1, hp, attack, defense, speed, 0);
            database.add(nuevoPokemon);
        }

        return database;
    }
}