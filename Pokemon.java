import java.util.Comparator;

/**
 * Representa una criatura dentro de la base de datos.
 * Contiene identificadores básicos y estadísticas de combate.
 */
public class Pokemon {

    private int id;
    private String name;
    private String type1;
    private int hp;
    private int attack;
    private int defense;
    private int speed;
    private int totalStats;

    /**
     * Constructor de la clase Pokemon.
     * Calcula automáticamente el atributo totalStats sumando las estadísticas de combate.
     *
     * @param id      Identificador único del Pokémon (entero secuencial).
     * @param name    Nombre o código identificador.
     * @param type1   Tipo primario (ej. "Electric", "Fire").
     * @param hp      Puntos de vida (entre 1 y 255).
     * @param attack  Puntos de ataque (entre 1 y 255).
     * @param defense Puntos de defensa (entre 1 y 255).
     * @param speed   Velocidad en combate (entre 1 y 255).
     */
    public Pokemon(int id, String name, String type1, int hp, int attack, int defense, int speed) {
        this.id = id;
        this.name = name;
        this.type1 = type1;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        // Según las instrucciones, totalStats es la suma exacta de estas 4 estadísticas
        this.totalStats = hp + attack + defense + speed;
    }

    // ==========================================
    // GETTERS Y SETTERS
    // ==========================================

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType1() { return type1; }
    public void setType1(String type1) { this.type1 = type1; }

    public int getHp() { return hp; }
    public void setHp(int hp) {
        this.hp = hp;
        actualizarTotalStats();
    }

    public int getAttack() { return attack; }
    public void setAttack(int attack) {
        this.attack = attack;
        actualizarTotalStats();
    }

    public int getDefense() { return defense; }
    public void setDefense(int defense) {
        this.defense = defense;
        actualizarTotalStats();
    }

    public int getSpeed() { return speed; }
    public void setSpeed(int speed) {
        this.speed = speed;
        actualizarTotalStats();
    }

    public int getTotalStats() { return totalStats; }
    public void setTotalStats(int totalStats) { this.totalStats = totalStats; }

    /**
     * Método auxiliar para mantener la consistencia de totalStats si se modifica
     * un atributo individualmente con un setter.
     */
    private void actualizarTotalStats() {
        this.totalStats = this.hp + this.attack + this.defense + this.speed;
    }

    // ==========================================
    // COMPARADORES (Para Selection Sort y Merge Sort)
    // ==========================================

    /** Comparador para ordenar por Nombre (alfabético) */
    public static final Comparator<Pokemon> BY_NAME = Comparator.comparing(Pokemon::getName);

    /** Comparador para ordenar por Puntos de Vida (HP) */
    public static final Comparator<Pokemon> BY_HP = Comparator.comparingInt(Pokemon::getHp);

    /** Comparador para ordenar por Ataque */
    public static final Comparator<Pokemon> BY_ATTACK = Comparator.comparingInt(Pokemon::getAttack);

    /** Comparador para ordenar por Defensa */
    public static final Comparator<Pokemon> BY_DEFENSE = Comparator.comparingInt(Pokemon::getDefense);

    /** Comparador para ordenar por Velocidad */
    public static final Comparator<Pokemon> BY_SPEED = Comparator.comparingInt(Pokemon::getSpeed);

    /** Comparador para ordenar por Estadísticas Totales */
    public static final Comparator<Pokemon> BY_TOTAL_STATS = Comparator.comparingInt(Pokemon::getTotalStats);

    /**
     * Retorna una representación en String del Pokémon, útil para las pruebas en consola.
     */
    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type1='" + type1 + '\'' +
                ", hp=" + hp +
                ", attack=" + attack +
                ", defense=" + defense +
                ", speed=" + speed +
                ", totalStats=" + totalStats +
                '}';
    }
}
