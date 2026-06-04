package ch.modelo;

public class Registro {

    private int usuarioId;
    private String vehiculoPlaca;
    private String origen;
    private String destino;
    private String estado;

    public Registro(){}

    public Registro(int usuarioId,
            String vehiculoPlaca,
            String origen,
            String destino,
            String estado) {

        this.usuarioId = usuarioId;
        this.vehiculoPlaca = vehiculoPlaca;
        this.origen = origen;
        this.destino = destino;
        this.estado = estado;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public String getVehiculoPlaca() {
        return vehiculoPlaca;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public String getEstado() {
        return estado;
    }
}