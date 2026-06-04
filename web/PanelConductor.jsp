<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="ch.modelo.Solicitud" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Panel de Control - Conductor</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f9;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 1000px;
            margin: 0 auto;
            background: white;
            padding: 25px;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
            border-top: 6px solid #f57c00; /* Detalle naranja */
        }
        .header-panel {
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-bottom: 2px solid #eee;
            padding-bottom: 15px;
            margin-bottom: 25px;
        }
        h1 {
            color: #173B73; /* Azul institucional */
            margin: 0;
            font-size: 26px;
        }
        .btn-logout {
            background-color: #d32f2f;
            color: white;
            text-decoration: none;
            padding: 8px 16px;
            border-radius: 6px;
            font-weight: bold;
            font-size: 14px;
        }
        .btn-logout:hover {
            background-color: #b71c1c;
        }
        .tabla-solicitudes {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }
        .tabla-solicitudes th {
            background-color: #173B73;
            color: white;
            text-align: left;
            padding: 12px;
            font-size: 15px;
        }
        .tabla-solicitudes td {
            padding: 12px;
            border-bottom: 1px solid #ddd;
            color: #333;
            font-size: 14px;
        }
        .tabla-solicitudes tr:hover {
            background-color: #f1f5f9;
        }
        .badge-pendiente {
            background-color: #fff3e0;
            color: #e65100;
            padding: 4px 8px;
            border-radius: 4px;
            font-weight: bold;
            font-size: 12px;
            border: 1px solid #ffe0b2;
        }
        .btn-aceptar {
            background-color: #f57c00; /* Naranja corporativo */
            color: white;
            border: none;
            padding: 7px 14px;
            border-radius: 4px;
            cursor: pointer;
            font-weight: bold;
            transition: background 0.2s;
        }
        .btn-aceptar:hover {
            background-color: #e65100;
        }
        .sin-solicitudes {
            text-align: center;
            padding: 40px;
            color: #777;
            font-style: italic;
        }
    </style>
</head>
<body>

    <div class="container">
        <div class="header-panel">
            <h1>Panel del Conductor - Solicitudes Disponibles</h1>
            <a href="LogoutServlet" class="btn-logout">Cerrar Sesión</a>
        </div>

        <p>A continuación se muestran los pasajeros que solicitan un viaje en este momento. Puedes aceptar el servicio para iniciar la ruta:</p>

        <table class="tabla-solicitudes">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Pasajero</th>
                    <th>Punto de Encuentro</th>
                    <th>Destino</th>
                    <th>Estado</th>
                    <th>Acción</th>
                </tr>
            </thead>
            <tbody>
                <%
                    // Recuperamos la lista que el Servlet inyectó en el request
                    List<Solicitud> solicitudes = (List<Solicitud>) request.getAttribute("listaSolicitudes");
                    
                    if (solicitudes != null && !solicitudes.isEmpty()) {
                        for (Solicitud s : solicitudes) {
                %>
                            <tr>
                                <td><strong>#<%= s.getId() %></strong></td>
                                <td><%= s.getNombrePasajero() %></td>
                                <td>📍 <%= s.getPuntoEncuentro() %></td>
                                <td>🏁 <%= s.getDestino() %></td>
                                <td><span class="badge-pendiente"><%= s.getEstado() %></span></td>
                                <td>
                                    <form action="AceptarViajeServlet" method="POST" style="margin:0;">
                                        <input type="hidden" name="id_solicitud" value="<%= s.getId() %>">
                                        <button type="submit" class="btn-aceptar">Aceptar Carrera</button>
                                    </form>
                                </td>
                            </tr>
                <% 
                        }
                    } else {
                %>
                        <tr>
                            <td colspan="6" class="sin-solicitudes">
                                No hay usuarios solicitando servicios en este momento. Las solicitudes aparecerán automáticamente aquí.
                            </td>
                        </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
    </div>

</body>

<div id="modalViaje" style="display:none; position:fixed; top:50%; left:50%; transform:translate(-50%,-50%); background:white; padding:20px; border:2px solid #f57c00; border-radius:10px;">
    <h2>¡Viaje en Curso!</h2>
    <p>La ruta ha comenzado. Por favor, conduce con precaución.</p>
</div>

</html>