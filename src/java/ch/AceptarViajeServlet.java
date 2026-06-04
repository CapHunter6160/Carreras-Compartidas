package ch;

import ch.conexion.ConexionBD;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/AceptarViajeServlet")
public class AceptarViajeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idSolicitudStr = request.getParameter("id_solicitud");
        
        if (idSolicitudStr != null && !idSolicitudStr.isEmpty()) {
            int idSolicitud = Integer.parseInt(idSolicitudStr);
            Connection con = null;
            PreparedStatement ps = null;
            
            // Actualizamos a ACEPTADO
            String sql = "UPDATE solicitudes SET estado = 'ACEPTADO' WHERE id = ?";
            
            try {
                con = ConexionBD.getInstancia().getConexionRegistros();
                ps = con.prepareStatement(sql);
                ps.setInt(1, idSolicitud);
                ps.executeUpdate();
                
                // Redirigimos a la pantalla de conductor con la confirmación
                response.sendRedirect("ViajeEnCurso.jsp");
                
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try { if (ps != null) ps.close(); } catch (Exception e) {}
                try { if (con != null) con.close(); } catch (Exception e) {}
            }
        }
    }
}