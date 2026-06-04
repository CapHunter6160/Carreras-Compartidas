package ch;

import ch.conexion.ConexionBD;
import ch.modelo.Solicitud; // Asegúrate de tener este modelo
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/PanelConductor")
public class PanelConductorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Solicitud> listaSolicitudes = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        // Consulta que busca solo lo pendiente
        // Cambia tu consulta actual por esta:
       String sql = "SELECT s.id, u.nombre, s.punto_encuentro, s.destino, s.estado " +
                    "FROM solicitudes s " +
                    "JOIN usuarios u ON s.id_pasajero = u.id_usuario " + // <-- AQUÍ ESTÁ EL CAMBIO
                    "WHERE s.estado = 'PENDIENTE'";

        try {
            con = ConexionBD.getInstancia().getConexionRegistros();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Solicitud s = new Solicitud();
                s.setId(rs.getInt("id"));
                s.setNombrePasajero(rs.getString("nombre"));
                s.setPuntoEncuentro(rs.getString("punto_encuentro"));
                s.setDestino(rs.getString("destino"));
                s.setEstado(rs.getString("estado"));
                
                listaSolicitudes.add(s);
            }

            // Enviamos la lista al JSP
            request.setAttribute("listaSolicitudes", listaSolicitudes);
            request.getRequestDispatcher("PanelConductor.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error cargando panel: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
    }
}