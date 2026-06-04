package ch.dao;

import ch.conexion.ConexionBD;
import ch.modelo.Solicitud;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SolicitudDAO {

    public List<Solicitud> listarSolicitudesPendientes() {
        List<Solicitud> lista = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        // Consulta que une la solicitud con el nombre del usuario/pasajero
        String sql = "SELECT s.id, u.nombre AS pasajero, s.punto_encuentro, s.destino, s.estado " +
                     "FROM solicitudes s " +
                     "JOIN usuarios u ON s.id_pasajero = u.id " +
                     "WHERE s.estado = 'PENDIENTE' ORDER BY s.id DESC";

        try {
            // Usamos la conexión Singleton que ya corregimos
            con = ConexionBD.getInstancia().getConexionRegistros();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Solicitud sol = new Solicitud();
                sol.setId(rs.getInt("id"));
                sol.setNombrePasajero(rs.getString("pasajero"));
                sol.setPuntoEncuentro(rs.getString("punto_encuentro"));
                sol.setDestino(rs.getString("destino"));
                sol.setEstado(rs.getString("estado"));
                lista.add(sol);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
        return lista;
    }
}