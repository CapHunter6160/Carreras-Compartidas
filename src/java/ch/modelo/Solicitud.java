package ch.modelo;

public class Solicitud {
    private int id;
    private String nombrePasajero;
    private String puntoEncuentro;
    private String destino;
    private String estado; // Ejemplo: "PENDIENTE", "ACEPTADO"

    public Solicitud() {}

    public Solicitud(int id, String nombrePasajero, String puntoEncuentro, String destino, String estado) {
        this.id = id;
        this.nombrePasajero = nombrePasajero;
        this.puntoEncuentro = puntoEncuentro;
        this.destino = destino;
        this.estado = estado;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombrePasajero() { return nombrePasajero; }
    public void setNombrePasajero(String nombrePasajero) { this.nombrePasajero = nombrePasajero; }

    public String getPuntoEncuentro() { return puntoEncuentro; }
    public void setPuntoEncuentro(String puntoEncuentro) { this.puntoEncuentro = puntoEncuentro; }

    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
