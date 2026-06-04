package ch.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {
    
    private static ConexionBD instancia;

    private ConexionBD() { }

    public static synchronized ConexionBD getInstancia() {
        if (instancia == null) {
            instancia = new ConexionBD();
        }
        return instancia;
    }

    public Connection getConexionRegistros() {
        try {
            Class.forName("org.postgresql.Driver");
            // Pon aquí tu contraseña real que ya te funcionó
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/DB_Registros", "postgres", "12345");
        } catch (Exception e) {
            throw new RuntimeException("ERROR DE BD REGISTROS: " + e.getMessage());
        }
    }

    // VERIFICA QUE ESTE MÉTODO ESTÉ EXACTAMENTE ASÍ ESCRITO:
    public Connection getConexionVehiculos() {
        try {
            Class.forName("org.postgresql.Driver");
            // Pon aquí tu contraseña real que ya te funcionó
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/DB_Vehiculos", "postgres", "12345");
        } catch (Exception e) {
            throw new RuntimeException("ERROR DE BD VEHÍCULOS: " + e.getMessage());
        }
    }
}