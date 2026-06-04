package ch;

import ch.modelo.Vehiculo;
import ch.dao.VehiculoDAO;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RegistroVehiculo")
public class RegistroVehiculo extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8"); // Evita problemas con eñes o tildes

        String placa = request.getParameter("placa");
        String marca = request.getParameter("marca");
        String modelo = request.getParameter("modelo");
        double precio = Double.parseDouble(request.getParameter("precio"));
        
        // CORREGIDO: Capturamos el ID del conductor que viene desde el formulario
        // Asegúrate de que en tu 'FormVehiculo.jsp' el campo tenga name="id_conductor"
        int idConductor = Integer.parseInt(request.getParameter("id_conductor"));

        // CORREGIDO: Ahora pasamos los 5 parámetros exactos que espera el modelo
        Vehiculo nuevoVehiculo = new Vehiculo(placa, marca, modelo, precio, idConductor);
        VehiculoDAO dao = new VehiculoDAO();

        try (PrintWriter out = response.getWriter()) {

            int resultado = dao.registrar(nuevoVehiculo);

            if (resultado > 0) {
                out.println("<h1>¡Registro Exitoso!</h1>");
                out.println("<p>Vehículo " + marca + " guardado.</p>");
                out.println("<a href='FormVehiculo.jsp'>Volver</a>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}