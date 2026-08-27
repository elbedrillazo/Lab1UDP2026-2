import java.util.Comparator;

/* representa un Pokemon dentro de la base de datos con sus estadísticas de combate */
public class Pokemon {
 
    private int id;
    private String name;
    private String type1;
    private int hp;
    private int attack;
    private int defense;
    private int speed;
    private int totalStats;

    /* constructor de la clase Pokemon y recibe la ID, nombre, tipo principal, y las estadisticas del pokemon (vida, ataque, defensa, velocidad) */
 
    public Pokemon(int id, String name, String type1, int hp, int attack, int defense, int speed) {
     
    }
    public int getId() { 
     
     return 0;
    }
    public void setId(int id) { 
     
    }
    public String getName() { 
     
     return null;
    }
    public void setName(String name) { 
     
    }
    public String getType1() { 
     
     return null;
    }
    public void setType1(String type1) { 
     
    }
    public int getHp() { 
     
     return 0;
    }
    public void setHp(int hp) { 
     
    }
    public int getAttack() {
     
     return 0;
    }
    public void setAttack(int attack) { 
     
    }
    public int getDefense() { 
     
     return 0;
    }
    public void setDefense(int defense) { 
     
    }
    public int getSpeed() { 
     
     return 0;
    }
    public void setSpeed(int speed) { 
     
    }
    public int getTotalStats() { 
     
     return 0;
    }
    public void setTotalStats(int totalStats) {
     
    }

    public static final Comparator<Pokemon> BY_NAME = null;
    public static final Comparator<Pokemon> BY_HP = null;
    public static final Comparator<Pokemon> BY_ATTACK = null;
    public static final Comparator<Pokemon> BY_DEFENSE = null;
    public static final Comparator<Pokemon> BY_SPEED = null;
    public static final Comparator<Pokemon> BY_TOTAL_STATS = null;
}
