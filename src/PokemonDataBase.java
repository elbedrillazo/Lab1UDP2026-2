import java.util.ArrayList;
import java.util.Comparator;

/**
 * Gestiona una lista de objetos Pokemon y expone los métodos
 * de ordenamiento y búsqueda.
 */
public class PokemonDatabase {

    private ArrayList<Pokemon> pokemons;

    /**
     * Constructor de la base de datos.
     * @param pokemons Lista inicial de Pokémon.
     */
    public PokemonDatabase(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }

    /**
     * Ordena la lista de Pokémon mediante el algoritmo especificado.
     *
     * @param algoritmo Valores aceptados: "selectionSort" o "mergeSort".
     * @param atributo  Campo para ordenar ("hp", "attack", "defense", "speed", "name", "totalStats").
     */
    public void ordenarPorAlgoritmo(String algoritmo, String atributo) {
        Comparator<Pokemon> comparador;

        // Regla: Si el atributo no se reconoce, es null o vacío, se usa totalStats
        if (atributo == null || atributo.trim().isEmpty()) {
            atributo = "totalStats";
        }

        switch (atributo.toLowerCase()) {
            case "name":
                comparador = Pokemon.BY_NAME;
                break;
            case "hp":
                comparador = Pokemon.BY_HP;
                break;
            case "attack":
                comparador = Pokemon.BY_ATTACK;
                break;
            case "defense":
                comparador = Pokemon.BY_DEFENSE;
                break;
            case "speed":
                comparador = Pokemon.BY_SPEED;
                break;
            default:
                comparador = Pokemon.BY_TOTAL_STATS;
                break;
        }

        // Llamada a los algoritmos adaptados (los cuales modificaremos en el siguiente paso)
        if ("selectionSort".equalsIgnoreCase(algoritmo)) {
            Selection.sort(this.pokemons, comparador);
        } else if ("mergeSort".equalsIgnoreCase(algoritmo)) {
            Merge.sort(this.pokemons, comparador);
        } else {
            throw new IllegalArgumentException("Algoritmo no soportado: " + algoritmo);
        }
    }

    /**
     * Recorre la lista desde el inicio hasta el final buscando coincidencias exactas.
     * No requiere que la lista esté ordenada.
     *
     * @param clave    Valor a buscar (ej. "Pikachu" o "45").
     * @param atributo Atributo sobre el cual buscar.
     * @return ArrayList con todos los Pokémon que coinciden.
     */
    public ArrayList<Pokemon> sequentialSearch(String clave, String atributo) {
        ArrayList<Pokemon> resultados = new ArrayList<>();

        for (Pokemon p : pokemons) {
            // Comparamos usando una función auxiliar para facilitar la lectura
            if (compararClaveConPokemon(clave, p, atributo) == 0) {
                resultados.add(p);
            }
        }

        return resultados;
    }

    /**
     * Búsqueda binaria. Asume que la lista ya está ordenada por el atributo indicado.
     * Complejidad: O(log n + k).
     *
     * @param clave    Valor a buscar.
     * @param atributo Atributo sobre el cual buscar.
     * @return ArrayList con todas las coincidencias contiguas.
     */
    public ArrayList<Pokemon> binarySearch(String clave, String atributo) {
        ArrayList<Pokemon> resultados = new ArrayList<>();
        if (pokemons.isEmpty()) return resultados;

        int left = 0;
        int right = pokemons.size() - 1;
        int matchIndex = -1;

        // Fase 1: Encontrar UNA coincidencia en O(log n)
        while (left <= right) {
            int mid = left + (right - left) / 2;
            Pokemon midPokemon = pokemons.get(mid);
            int cmp = compararClaveConPokemon(clave, midPokemon, atributo);

            if (cmp == 0) {
                matchIndex = mid;
                break;
            } else if (cmp < 0) {
                // La clave es menor que el valor en mid, buscar en la izquierda
                right = mid - 1;
            } else {
                // La clave es mayor que el valor en mid, buscar en la derecha
                left = mid + 1;
            }
        }

        // Fase 2: Si encontramos una coincidencia, recolectar las contiguas (factor + k)
        if (matchIndex != -1) {
            // Ir hacia la izquierda hasta que cambie el valor
            int start = matchIndex;
            while (start > 0 && compararClaveConPokemon(clave, pokemons.get(start - 1), atributo) == 0) {
                start--;
            }

            // Ir hacia la derecha hasta que cambie el valor
            int end = matchIndex;
            while (end < pokemons.size() - 1 && compararClaveConPokemon(clave, pokemons.get(end + 1), atributo) == 0) {
                end++;
            }

            // Añadir todas las coincidencias juntas al resultado
            for (int i = start; i <= end; i++) {
                resultados.add(pokemons.get(i));
            }
        }

        return resultados;
    }

    /**
     * Método auxiliar privado para comparar un String de búsqueda ("clave") 
     * con el atributo correspondiente de un objeto Pokemon.
     */
    private int compararClaveConPokemon(String clave, Pokemon p, String atributo) {
        if (atributo == null || atributo.trim().isEmpty()) {
            atributo = "totalStats";
        }

        // Si el atributo es de tipo entero, parseamos la clave para hacer una comparación matemática correcta
        try {
            switch (atributo.toLowerCase()) {
                case "name":
                    return clave.compareTo(p.getName());
                case "hp":
                    return Integer.compare(Integer.parseInt(clave), p.getHp());
                case "attack":
                    return Integer.compare(Integer.parseInt(clave), p.getAttack());
                case "defense":
                    return Integer.compare(Integer.parseInt(clave), p.getDefense());
                case "speed":
                    return Integer.compare(Integer.parseInt(clave), p.getSpeed());
                default: // totalStats
                    return Integer.compare(Integer.parseInt(clave), p.getTotalStats());
            }
        } catch (NumberFormatException e) {
            // Si intentan buscar texto en un atributo numérico, devolvemos -1 (no coincide)
            return -1;
        }
    }
}
