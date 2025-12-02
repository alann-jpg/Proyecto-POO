

public class Cliente {

    private String Nombre;
    private int Edad;

    public Cliente(String nombre, int edad){
        this.Nombre = nombre;
        this.Edad = edad;
    }

    public void solicitarReserva(){
    }

    public String getNombre(){
        return Nombre;
    }

    public void setNombre(String nombre){
        this.Nombre = nombre;
    }

    public int getEdad(){
        return Edad;
    }

    public void setEdad(int edad){
        this.Edad = edad;
    }
}
