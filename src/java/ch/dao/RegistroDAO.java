package ch.dao;

import ch.conexion.ConexionBD;
import ch.modelo.Registro;
import java.sql.*;

public class RegistroDAO {

    public int registrarViaje(Registro r) throws Exception {
        String sql = "INSERT INTO registros (usuario_id, vehiculo_placa, origen, destino, estado) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.getInstancia().getConexionRegistros();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, r.getUsuarioId());
            stmt.setString(2, r.getVehiculoPlaca());
            stmt.setString(3, r.getOrigen());
            stmt.setString(4, r.getDestino());
            stmt.setString(5, r.getEstado());

            return stmt.executeUpdate();
        }
    }
}