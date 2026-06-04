package ch;

import ch.dao.EvaluacionNoSQLDAO;
import ch.dto.EvaluacionDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/GuardarEvaluacionServlet")
public class GuardarEvaluacionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int estrellas = Integer.parseInt(request.getParameter("estrellas"));
        String comentario = request.getParameter("comentario");
        String version = request.getParameter("versionApp"); // Captura de trazabilidad

        EvaluacionDTO evaluacion = new EvaluacionDTO(estrellas, comentario, version);
        EvaluacionNoSQLDAO mongoDAO = new EvaluacionNoSQLDAO();
        
        boolean exito = mongoDAO.guardarEnMongoDB(evaluacion);
        
        response.setContentType("text/html;charset=UTF-8");
        if(exito) {
            response.getWriter().println("<h1>¡Gracias por calificar el servicio!</h1>");
            response.getWriter().println("<p>Tu retroalimentación fue enviada de forma segura al Microservicio NoSQL.</p>");
            response.getWriter().println("<a href='ListarVehiculosServlet'>Volver al Inicio</a>");
        }
    }
}