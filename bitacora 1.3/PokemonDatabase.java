import java.util.ArrayList;
import java.util.Comparator;

public class PokemonDatabase {
    private ArrayList<Pokemon> pokemons;

    public PokemonDatabase(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public void ordenarPorAlgoritmo(String algoritmo, String atributo) {
        Comparator<Pokemon> comparador;
        if (atributo == null || atributo.trim().isEmpty()) {
            atributo = "totalStats";
        }

        String atributoSeleccionado = atributo.toLowerCase();
        if (atributoSeleccionado.equals("name")){
            comparador = Pokemon.Su_Nombre;
        } else if (atributoSeleccionado.equals("hp")){
            comparador = Pokemon.Su_Hp;
        } else if (atributoSeleccionado.equals("attack")){
            comparador = Pokemon.Su_Ataque;
        } else if (atributoSeleccionado.equals("defense")){
            comparador = Pokemon.Su_Defensa;
        } else if (atributoSeleccionado.equals("speed")){
            comparador = Pokemon.Su_Velocidad;
        } else {
            comparador = Pokemon.Su_Total_Stats;
        }

        if ("selectionSort".equalsIgnoreCase(algoritmo)) {
            Selection.sort(this.pokemons, comparador);
        } else if ("mergeSort".equalsIgnoreCase(algoritmo)) {
            Merge.sort(this.pokemons, comparador);
        } else {
            throw new IllegalArgumentException("Algoritmo no soportado: " + algoritmo);
        }
    }

    public ArrayList<Pokemon> sequentialSearch(String clave, String atributo) {
        ArrayList<Pokemon> resultados = new ArrayList<>();
        for (Pokemon p : pokemons) {
            if (compararClaveConPokemon(clave, p, atributo) == 0) {
                resultados.add(p);
            }
        }
        return resultados;
    }

    public ArrayList<Pokemon> binarySearch(String clave, String atributo) {
        ArrayList<Pokemon> resultados = new ArrayList<>();
        if (pokemons.isEmpty()) return resultados;

        int left = 0;
        int right = pokemons.size() - 1;
        int matchIndex = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            Pokemon midPokemon = pokemons.get(mid);
            int cmp = compararClaveConPokemon(clave, midPokemon, atributo);

            if (cmp == 0) {
                matchIndex = mid;
                break;
            } else if (cmp < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        if (matchIndex != -1) {
            int start = matchIndex;
            while (start > 0 && compararClaveConPokemon(clave, pokemons.get(start - 1), atributo) == 0) {
                start--;
            }
            int end = matchIndex;
            while (end < pokemons.size() - 1 && compararClaveConPokemon(clave, pokemons.get(end + 1), atributo) == 0) {
                end++;
            }
            for (int i = start; i <= end; i++) {
                resultados.add(pokemons.get(i));
            }
        }
        return resultados;
    }

    private int compararClaveConPokemon(String clave, Pokemon p, String atributo) {
        if (atributo == null || atributo.trim().isEmpty()) atributo = "totalStats";

        String atributoSeleccionado = atributo.toLowerCase();
        try {
            if (atributoSeleccionado.equals("name")){
                return clave.compareTo(p.getNombre());
            } else if (atributoSeleccionado.equals("hp")){
                return Integer.compare(Integer.parseInt(clave), p.getHp());
            } else if (atributoSeleccionado.equals("attack")){
                return Integer.compare(Integer.parseInt(clave), p.getAtaque());
            } else if (atributoSeleccionado.equals("defense")){
                return Integer.compare(Integer.parseInt(clave), p.getDefensa());
            } else if (atributoSeleccionado.equals("speed")){
                return Integer.compare(Integer.parseInt(clave), p.getVelocidad());
            } else {
                return Integer.compare(Integer.parseInt(clave), p.getTotalStats());
            }
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}