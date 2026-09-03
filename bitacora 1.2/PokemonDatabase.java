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

        switch (atributo.toLowerCase()) {
            case "name": comparador = Pokemon.BY_NAME; break;
            case "hp": comparador = Pokemon.BY_HP; break;
            case "attack": comparador = Pokemon.BY_ATTACK; break;
            case "defense": comparador = Pokemon.BY_DEFENSE; break;
            case "speed": comparador = Pokemon.BY_SPEED; break;
            default: comparador = Pokemon.BY_TOTAL_STATS; break;
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
        try {
            switch (atributo.toLowerCase()) {
                case "name": return clave.compareTo(p.getName());
                case "hp": return Integer.compare(Integer.parseInt(clave), p.getHp());
                case "attack": return Integer.compare(Integer.parseInt(clave), p.getAttack());
                case "defense": return Integer.compare(Integer.parseInt(clave), p.getDefense());
                case "speed": return Integer.compare(Integer.parseInt(clave), p.getSpeed());
                default: return Integer.compare(Integer.parseInt(clave), p.getTotalStats());
            }
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
/*
import java.util.ArrayList;

public class PokemonDatabase {
    private ArrayList<Pokemon> pokemons;

    public PokemonDatabase(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

   private int compararPokemons(Pokemon p1, Pokemon p2, String atributo){
       if(atributo.equals("name")){
           return p1.getName().compareTo(p2.getName());
        }else if (atributo.equals("hp")){
           return p1.getHp() - p2.getHp();
       }else if (atributo.equals("attack")){
           return p1.getAttack() - p2.getAttack();
   }else if (atributo.equals("defense")){
           return p1.getDefense() - p2-getSpeed();
       }else{
           return p1.getTotalStats() - p2.getTotalStats();
       }
   }
private void selection(string atributo){
int n = pokemons.size();
for (int i=0; i<n - 1; i++){
int min_idx = i;
for (int j=i+1; j<n; j++){
if(compararPokemons(pokemons.get(j), pokemons.get(min_idx), atributo) < 0){
min_idx = j;
      }
      }
      Pokemon temp= pokemons.get(min_idx);
      pokemons.set(min_edx, pokemons.get(i));
      pokemons.set(i,temp);
      }
      }
      public void ordenarPorAlgoritmo(string algoritmo, string atributo){
      if(atributo==null || atributo.equals("")){
      selection(atributo);
     
*/

