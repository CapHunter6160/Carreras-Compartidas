package ch;

import ch.conexion.ConexionBD;
import ch.modelo.Usuario;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/RegistrarViajeServlet")
public class RegistrarViajeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. Verificación de sesión
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }
        
        Usuario pasajero = (Usuario) session.getAttribute("usuarioLogueado");
        
        // 2. Recepción y validación de parámetros
        // Usamos .trim() para evitar que envíen solo espacios en blanco
        String placaVehiculo = request.getParameter("placa_vehiculo");
        String puntoEncuentro = request.getParameter("punto_encuentro");
        String destino = request.getParameter("destino");
        
        if (placaVehiculo == null || placaVehiculo.trim().isEmpty() || 
            puntoEncuentro == null || puntoEncuentro.trim().isEmpty() || 
            destino == null || destino.trim().isEmpty()) {
            
            response.getWriter().println("Error: Debes completar todos los campos del formulario.");
            return;
        }
        
        Connection con = null;
        PreparedStatement ps = null;
        
        // 3. Inserción en DB_Registros
        // Asegúrate de que la tabla 'solicitudes' esté creada en DB_Registros
        String sql = "INSERT INTO solicitudes (id_pasajero, placa_vehiculo, punto_encuentro, destino, estado) " +
                     "VALUES (?, ?, ?, ?, 'PENDIENTE')";
                     
        try {
            // Obtenemos la conexión a la base de datos de registros
            con = ConexionBD.getInstancia().getConexionRegistros();
            
            if (con == null) {
                throw new Exception("No se pudo establecer conexión con DB_Registros");
            }
            
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, pasajero.getId());      // ID del pasajero
            ps.setString(2, placaVehiculo);      // Placa (String)
            ps.setString(3, puntoEncuentro);     // Origen
            ps.setString(4, destino);            // Destino
            
            int filas = ps.executeUpdate();
            
            if (filas > 0) {
                // Éxito: Redirigimos al pasajero
                response.sendRedirect("ListarVehiculosServlet?mensaje=SolicitudEnviada");
            } else {
                response.getWriter().println("Error: No se pudo registrar la solicitud.");
            }
            
        } catch (Exception e) {
            // Imprimir error en la consola de NetBeans para diagnóstico rápido
            e.printStackTrace();
            response.getWriter().println("Error en base de datos: " + e.getMessage());
        } finally {
            // Cierre seguro de recursos para evitar fugas de memoria
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
    }
}