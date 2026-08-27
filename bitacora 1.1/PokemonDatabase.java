import java.util.ArrayList;

/*
  Gestiona una lista de objetos Pokemon y expone los métodos de ordenamiento y búsqueda[cite: 1]
 */
public class PokemonDatabase {

    private ArrayList<Pokemon> pokemons;

    /*
     es el constructor de la base de datos
     lista inicial de Pokémon.
     */
    public PokemonDatabase(ArrayList<Pokemon> pokemons) {
    }

    /*
      se ordena la lista de Pokémon mediante el algoritmo y atributo especificados
     algoritmo Valores aceptados: "selectionSort" o "mergeSort"[cite: 1]
     atributo  Campo para ordenar (ej. "hp", "attack", "totalStats")[cite: 1]
     */
    public void ordenarPorAlgoritmo(String algoritmo, String atributo) {
    }

    /*
     * Recorre la lista desde el inicio hasta el final buscando coincidencias exactas
     * valor a buscar
     * atributo sobre el cual buscar
     * arrayList con todos los Pokémon que coinciden
     */
    public ArrayList<Pokemon> sequentialSearch(String clave, String atributo) {
        return null;
    }

    /*
     Búsqueda binaria sobre una lista previamente ordenada
     clave    Valor a buscar
     atributo sobre el cual buscar
     arrayList con todas las coincidencias contiguas
     */
    public ArrayList<Pokemon> binarySearch(String clave, String atributo) {
        return null;
    }
}
