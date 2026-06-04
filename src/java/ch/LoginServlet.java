package ch; // Asegúrate de mantener tu paquete correcto arriba

import ch.dao.UsuarioDAO; // Verifica si te faltaba este import o si ya estaba implícito
import ch.modelo.Usuario;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");

        UsuarioDAO dao = new UsuarioDAO();

        try {
            Usuario u = dao.login(correo, contrasena);

            if (u != null) {
                // 1. Creamos o recuperamos la sesión HTTP del usuario
                HttpSession session = request.getSession();
                session.setAttribute("usuarioLogueado", u); // Guardamos el objeto completo

                // 2. Evaluamos el rol para redirigir
                if (u.getRol().equals("conductor")) {
                    // CAMBIO AQUÍ: Redirigimos al SERVLET, NO al .jsp viejo
                    response.sendRedirect("PanelConductor");
                } else {
                    // En lugar de ir a una lista suelta, mandamos al pasajero a su flujo controlador
                    response.sendRedirect("ListarVehiculosServlet"); 
                }
            } else {
                response.getWriter().println("Usuario o contraseña incorrectos");
            }

        } catch (Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}