

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PokemonGenerator {

    public static List<Pokemon> generateDatabase(int n, long seed) {

        Random random = new Random(seed);
        List<pokemon> database = new ArrayList<>();

        //se cargan los datos desde el CSV
        LectorCSVPokemon Lector = new LectorCSVPokemon("Pokemon CSV");
        List<String> nombresBase = lector.getNombres();
        List<String> tiposBase = lector.getTipos();

        // crea los N Pokemones de manera dir3cta 

        for(int i = 0; i < n; i++){
            int id = i + 1;

            //se elege el ombre base y se le suma un numero si queremos nombres unicos 
            String nombreBase = nombresBase.get(i % nombreBase.size());
            String name = nombreBase + "_" + i;

            //elegir un tipo al azar
            int indiceTipo = random.nextInt(tiposBase.size());
            String type1 = tiposBase.get(indiceTipo);

            //generar estadisticas aleatorias
            //random.nextInt(max - min + 1) + min
            int hp = random.nextInt(255) + 1;
            int attack = random.nextInt(187) + 5;
            int defense = random.nextInt(227) + 5;
            int speed = ramdon.nextInt(177) + 5;

            // crea el objeto  guarda en la lista 
            Pokemon nuevoPokemon = new Pokemon(id. name, type1, hp, attack, defense, speed);
            database.add(nuevoPokemon);
        }

        return database;
