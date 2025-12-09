public class Barco {

    private String nombre;
    private String tipo;
    private int capacidadPasajeros;
    private double capacidadCarga;
    private String estado;

    public static java.util.List<Barco> barcosGlobales = new java.util.ArrayList<>();

    public Barco(String nombre, String tipo, int capacidadPasajeros, double capacidadCarga, String estado){
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidadPasajeros = capacidadPasajeros;
        this.capacidadCarga = capacidadCarga;
        this.estado = estado;
    }

    public String getNombre(){
        return nombre;
    }

    public String getTipo(){
        return tipo;
    }

    public int getCapacidadPasajeros(){
        return capacidadPasajeros;
    }

    public double getCapacidadCarga(){
        return capacidadCarga;
    }

    public String getEstado(){
        return estado;
    }

    public void setNombre(String n){ nombre=n; }
    public void setTipo(String t){ tipo=t; }
    public void setCapacidadPasajeros(int c){ capacidadPasajeros=c; }
    public void setCapacidadCarga(double c){ capacidadCarga=c; }
    public void setEstado(String e){ estado=e; }
}
