
import java.util.Comparator;
public class Pokemon{
    private int id;
    private String nombre;
    private String tipo;
    private int hp;
    private int ataque;
    private int defensa;
    private int velocidad;
    private int totalStats;

    public Pokemon(int id, String nombre, String tipo, int hp, int ataque, int defensa,int velocidad, int totalStats){
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
public String getNombre(){
    return nombre;}
public String getTipo(){
    return tipo;}

public void setHp(int hp){
    this.hp= hp; ActualizarTotalStats();}
public int getHp(){
    return hp;}
public void setAtaque(int ataque){
    this.ataque=ataque; ActualizarTotalStats();}
    public int getAtaque(){
        return ataque;}
public void setDefensa(int defensa){
    this.defensa=defensa; ActualizarTotalStats();}
public int getDefensa(){
    return defensa;}
public void setVelocidad(int velocidad){
    this.velocidad=velocidad; ActualizarTotalStats();}
public int getVelocidad(){
    return velocidad;}
private void ActualizarTotalStats(){
    this.totalStats=this.hp+this.defensa+this.velocidad+this.ataque;}
public int getTotalStats(){
    return totalStats;}
public static final Comparator <Pokemon> Su_Nombre = Comparator.comparing (Pokemon::getNombre);
public static final Comparator <Pokemon> Su_Hp = Comparator.comparing (Pokemon::getHp);
public static final Comparator <Pokemon> Su_Ataque = Comparator.comparing (Pokemon::getAtaque);
public static final Comparator <Pokemon> Su_Defensa = Comparator.comparing (Pokemon::getDefensa);
public static final Comparator <Pokemon> Su_Velocidad = Comparator.comparing (Pokemon::getVelocidad);
public static final Comparator <Pokemon> Su_Total_Stats = Comparator.comparing (Pokemon::getTotalStats);
}
