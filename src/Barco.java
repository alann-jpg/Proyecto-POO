public class Barco {

    private String nombre;
    private String tipo;
    private int capacidadPasajeros;
    private double capacidadCarga;  // en kg
    private String estado;

    public Barco(String nombre, String tipo,
                 int capacidadPasajeros, double capacidadCarga,
                 String estado) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidadPasajeros = capacidadPasajeros;
        this.capacidadCarga = capacidadCarga;
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public void setCapacidadPasajeros(int capacidadPasajeros) {
        this.capacidadPasajeros = capacidadPasajeros;
    }

    public double getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(double capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
