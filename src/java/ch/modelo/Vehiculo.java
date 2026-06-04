package ch.modelo;

public class Vehiculo {
    // Tus atributos actuales
    private String placa;
    private String marca;
    private String modelo;
    private double precio; // El que usas como v.getPrecio()
    
    // 1. AGREGA ESTA NUEVA VARIABLE
    private int id_conductor; 

    // Constructor vacío
    public Vehiculo() {
    }

    // Constructor completo (puedes actualizarlo o dejar el que ya tenías)
    public Vehiculo(String placa, String marca, String modelo, double precio, int id_conductor) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.id_conductor = id_conductor;
    }

    // --- Tus Getters y Setters actuales ---
    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    // 2. AGREGA ESTOS DOS MÉTODOS AL FINAL DE LA CLASE
    public int getId_conductor() {
        return id_conductor;
    }

    public void setId_conductor(int id_conductor) {
        this.id_conductor = id_conductor;
    }
}