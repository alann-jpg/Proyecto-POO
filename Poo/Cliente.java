public class Cliente {

    private String nombre;
    private int edad;

    public static java.util.List<Cliente> clientesGlobales = new java.util.ArrayList<>();

    public Cliente(String nombre, int edad){
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre(){
        return nombre;
    }

    public int getEdad(){
        return edad;
    }

    public String toString(){
        return nombre;
    }
}
