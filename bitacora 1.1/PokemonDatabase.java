import java.util.ArrayList;

/**
 * Gestiona una lista de objetos Pokemon y expone los métodos de ordenamiento y búsqueda[cite: 1].
 */
public class PokemonDatabase {

    private ArrayList<Pokemon> pokemons;

    /**
     * Constructor de la base de datos.
     * @param pokemons Lista inicial de Pokémon.
     */
    public PokemonDatabase(ArrayList<Pokemon> pokemons) {
    }

    /**
     * Ordena la lista de Pokémon mediante el algoritmo y atributo especificados[cite: 1].
     *
     * @param algoritmo Valores aceptados: "selectionSort" o "mergeSort"[cite: 1].
     * @param atributo  Campo para ordenar (ej. "hp", "attack", "totalStats")[cite: 1].
     */
    public void ordenarPorAlgoritmo(String algoritmo, String atributo) {
    }

    /**
     * Recorre la lista desde el inicio hasta el final buscando coincidencias exactas[cite: 1].
     *
     * @param clave    Valor a buscar.
     * @param atributo Atributo sobre el cual buscar.
     * @return ArrayList con todos los Pokémon que coinciden[cite: 1].
     */
    public ArrayList<Pokemon> sequentialSearch(String clave, String atributo) {
        return null;
    }

    /**
     * Búsqueda binaria sobre una lista previamente ordenada[cite: 1].
     * Su complejidad temporal esperada es O(log(n) + k)[cite: 1].
     *
     * @param clave    Valor a buscar.
     * @param atributo Atributo sobre el cual buscar.
     * @return ArrayList con todas las coincidencias contiguas[cite: 1].
     */
    public ArrayList<Pokemon> binarySearch(String clave, String atributo) {
        return null;
    }
}
