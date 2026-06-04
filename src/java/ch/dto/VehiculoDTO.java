package ch.dto;

public class VehiculoDTO {
    // Atributos privados que coinciden con las columnas de tu DB_Vehiculos
    private String placa;
    private String marca;
    private String modelo;
    private double precio_dia;
    private int id_conductor; // Enlace lógico con el usuario conductor

    // Constructor vacío (Requisito estándar de JavaBeans)
    public VehiculoDTO() {
    }

    // Constructor completo para instanciar rápidamente desde tu DAO
    public VehiculoDTO(String placa, String marca, String modelo, double precio_dia, int id_conductor) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.precio_dia = precio_dia;
        this.id_conductor = id_conductor;
    }

    // Métodos Getters y Setters
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecio_dia() {
        return precio_dia;
    }

    public void setPrecio_dia(double precio_dia) {
        this.precio_dia = precio_dia;
    }

    public int getId_conductor() {
        return id_conductor;
    }

    public void setId_conductor(int id_conductor) {
        this.id_conductor = id_conductor;
    }
    
    // Abre VehiculoDTO.java y agrega esto antes de la última llave }

    public double getPrecio() {
        return this.precio_dia; // Si tu variable se llama precioDia, cámbialo por this.precioDia
    }
    
}