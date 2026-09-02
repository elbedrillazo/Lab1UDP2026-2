/*
import java.util.Comparator;
public class Pokemon{
    private int id;
    private string nombre;
    private string tipo;
    private int hp;
    private int ataque;
    private int defensa;
    private int velocidad;
    private int totalStats;

    public Pokemon(int id, string nombre, string tipo, int hp, int ataque, int defensa,int velocidad, int totalStats){
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.hp= hp;
        this.ataque= ataque;
        this.defensa= defensa;
        this.velocidad= velocidad;
        this.totalStats= hp + ataque + defensa + velocidad;
    }
public int getId(){
    return id;}
public string getNombre(){
    return nombre;}
public string getTipo(){
    return tipo;}

public void setHp(int hp){
    this.hp= hp; ActualizarTotalStats();}
publuc int getHp(){
    return hp;}
*/

import java.util.Comparator;

public class Pokemon {
    private int id;
    private String name;
    private String type1;
    private int hp;
    private int attack;
    private int defense;
    private int speed;
    private int totalStats;

    public Pokemon(int id, String name, String type1, int hp, int attack, int defense, int speed) {
        this.id = id;
        this.name = name;
        this.type1 = type1;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.totalStats = hp + attack + defense + speed; 
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getType1() { return type1; }
    
    public int getHp() { return hp; }
    public void setHp(int hp) { this.hp = hp; actualizarTotalStats(); }

    public int getAttack() { return attack; }
    public void setAttack(int attack) { this.attack = attack; actualizarTotalStats(); }

    public int getDefense() { return defense; }
    public void setDefense(int defense) { this.defense = defense; actualizarTotalStats(); }

    public int getSpeed() { return speed; }
    public void setSpeed(int speed) { this.speed = speed; actualizarTotalStats(); }

    public int getTotalStats() { return totalStats; }

    private void actualizarTotalStats() {
        this.totalStats = this.hp + this.attack + this.defense + this.speed;
    }

    public static final Comparator<Pokemon> BY_NAME = Comparator.comparing(Pokemon::getName);
    public static final Comparator<Pokemon> BY_HP = Comparator.comparingInt(Pokemon::getHp);
    public static final Comparator<Pokemon> BY_ATTACK = Comparator.comparingInt(Pokemon::getAttack);
    public static final Comparator<Pokemon> BY_DEFENSE = Comparator.comparingInt(Pokemon::getDefense);
    public static final Comparator<Pokemon> BY_SPEED = Comparator.comparingInt(Pokemon::getSpeed);
    public static final Comparator<Pokemon> BY_TOTAL_STATS = Comparator.comparingInt(Pokemon::getTotalStats);
}
