package ch.dto;

public class EvaluacionDTO {
    private int estrellas; // 1 a 5 campos
    private String comentario;
    private String versionApp; // RNF70.4 (Trazabilidad)

    public EvaluacionDTO(int estrellas, String comentario, String versionApp) {
        this.estrellas = estrellas;
        this.comentario = comentario;
        this.versionApp = versionApp;
    }

    public int getEstrellas() { return estrellas; }
    public String getComentario() { return comentario; }
    public String getVersionApp() { return versionApp; }
}