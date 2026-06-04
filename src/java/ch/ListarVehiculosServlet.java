package ch;

import ch.dao.VehiculoDAO;
import ch.dto.VehiculoDTO;
import ch.modelo.Usuario;
import ch.conexion.ConexionBD;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/ListarVehiculosServlet")
public class ListarVehiculosServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // 1. Recuperar el usuario logueado desde la sesión HTTP
            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("usuarioLogueado") != null) {
                Usuario user = (Usuario) session.getAttribute("usuarioLogueado");
                
                // 2. Consultar el estado de la última solicitud del pasajero
                String sql = "SELECT estado FROM solicitudes WHERE id_pasajero = ? ORDER BY id DESC LIMIT 1";
                
                try (Connection con = ConexionBD.getInstancia().getConexionRegistros();
                     PreparedStatement ps = con.prepareStatement(sql)) {
                    
                    ps.setInt(1, user.getId());
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            String estadoActual = rs.getString("estado");
                            request.setAttribute("estadoSolicitud", estadoActual);
                        } else {
                            request.setAttribute("estadoSolicitud", "NINGUNA");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Aviso: No se pudo verificar el estado del viaje: " + e.getMessage());
                }
            }

            // 3. Traer el catálogo de vehículos usando el patrón DTO
            VehiculoDAO dao = new VehiculoDAO();
            List<VehiculoDTO> listaVehiculos = dao.listarVehiculosDTO();
            
            // Colocamos la lista en el alcance del request para la Vista
            request.setAttribute("listaVehiculos", listaVehiculos);
            
            // Despachamos el flujo hacia el JSP de manera limpia
            request.getRequestDispatcher("PanelPasajero.jsp").forward(request, response);
            
        } catch (Exception e) {
            response.getWriter().println("Error cargando el catálogo: " + e.getMessage());
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}