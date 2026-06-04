package ch;

import ch.conexion.ConexionBD; 
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "RegistroUsuarioServlet", urlPatterns = {"/RegistroUsuarioServlet"})
public class RegistroUsuarioServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String rol = request.getParameter("rol"); 
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena"); 

        Connection conRegistros = null;
        Connection conVehiculos = null;
        PreparedStatement psUsu = null;
        PreparedStatement psVeh = null;
        ResultSet rs = null;

        try {
            // CORREGIDO: Ahora usa el Singleton getInstancia() igual que tus DAOs
            conRegistros = ConexionBD.getInstancia().getConexionRegistros(); 
            
            String sqlUsuario = "INSERT INTO usuarios (nombre, correo, contrasena, rol, licencia) VALUES (?, ?, ?, ?, ?) RETURNING id_usuario";
            psUsu = conRegistros.prepareStatement(sqlUsuario);
            psUsu.setString(1, nombre);
            psUsu.setString(2, correo);
            psUsu.setString(3, contrasena);
            psUsu.setString(4, rol);
            
            if ("conductor".equals(rol)) {
                psUsu.setString(5, request.getParameter("licencia"));
            } else {
                psUsu.setNull(5, java.sql.Types.VARCHAR);
            }
            
            rs = psUsu.executeQuery();
            
            if (rs.next()) {
                int idUsuarioGenerado = rs.getInt("id_usuario");
                
                if ("conductor".equals(rol)) {
                    // CORREGIDO: Ahora usa el Singleton getInstancia() igual que tus DAOs
                    conVehiculos = ConexionBD.getInstancia().getConexionVehiculos();
                    
                    String sqlVehiculo = "INSERT INTO vehiculos (placa, marca, modelo, precio_dia, id_conductor) VALUES (?, ?, ?, ?, ?)";
                    psVeh = conVehiculos.prepareStatement(sqlVehiculo);
                    
                    psVeh.setString(1, request.getParameter("placa"));
                    psVeh.setString(2, request.getParameter("marca"));
                    psVeh.setString(3, request.getParameter("modelo"));
                    psVeh.setDouble(4, Double.parseDouble(request.getParameter("precio_dia"))); 
                    psVeh.setInt(5, idUsuarioGenerado); 
                    
                    psVeh.executeUpdate();
                }
            }
            
            response.sendRedirect("Login.jsp?registro=exitoso");

        } catch (Exception e) {
            e.printStackTrace();
            try (PrintWriter out = response.getWriter()) {
                out.println("<html><body>");
                out.println("<h3 style='color:red;'>Error en el flujo transaccional de Registro:</h3>");
                out.println("<p>Detalle técnico: <code>" + e.getMessage() + "</code></p>");
                out.println("<a href='CrearCuenta.jsp'>Volver a intentar</a>");
                out.println("</body></html>");
            }
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (psUsu != null) psUsu.close(); } catch (Exception e) {}
            try { if (psVeh != null) psVeh.close(); } catch (Exception e) {}
            try { if (conRegistros != null) conRegistros.close(); } catch (Exception e) {}
            try { if (conVehiculos != null) conVehiculos.close(); } catch (Exception e) {}
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}