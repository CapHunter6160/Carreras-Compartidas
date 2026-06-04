<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="ch.dto.VehiculoDTO" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Vehículos Disponibles</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f2f2f2; margin: 0; padding: 20px; }
        .container { max-width: 1200px; margin: 0 auto; }
        h1 { color: #173B73; text-align: center; margin-bottom: 30px; }
        .grid { display: grid; grid-template-columns: repeat(auto-fill, minmax(280px, 1fr)); gap: 20px; }
        .card { background: white; padding: 20px; border-radius: 12px; box-shadow: 0 4px 10px rgba(0,0,0,0.1); border-top: 5px solid #f57c00; transition: transform 0.2s; }
        .card:hover { transform: translateY(-5px); }
        .marca { color: #173B73; margin-top: 0; font-size: 22px; }
        .info { color: #555; margin: 8px 0; font-size: 15px; }
        .precio { font-size: 18px; color: #e65100; font-weight: bold; margin-top: 15px; }
        .no-data { text-align: center; color: #777; font-size: 18px; grid-column: 1 / -1; margin-top: 40px; }
        .btn-volver { display: inline-block; margin-bottom: 20px; padding: 10px 15px; background: #173B73; color: white; text-decoration: none; border-radius: 5px; font-weight: bold; }
        .btn-volver:hover { background: #0f2950; }
    </style>
</head>
<body>
    <div class="container">
        <a href="javascript:history.back()" class="btn-volver">← Volver</a>
        
        <h1>Vehículos Disponibles para Carreras Compartidas</h1>
        
        <div class="grid">
            <% 
                // Recuperamos la lista que envía el Servlet a través del objeto request
                List<VehiculoDTO> lista = (List<VehiculoDTO>) request.getAttribute("listaVehiculos");
                
                // Validamos si la lista existe y contiene elementos
                if (lista != null && !lista.isEmpty()) {
                    for (VehiculoDTO v : lista) {
            %>
                        <div class="card">
                            <h2 class="marca"><%= v.getMarca() %></h2>
                            <p class="info"><strong>Placa:</strong> <%= v.getPlaca() %></p>
                            <p class="info"><strong>Modelo:</strong> <%= v.getModelo() %></p>
                            <p class="precio">Precio por día: $<%= v.getPrecio_dia() %></p>
                        </div>
            <% 
                    } 
                } else { 
            %>
                    <p class="no-data">No hay vehículos registrados o disponibles en este momento.</p>
            <% 
                } 
            %>
        </div>
    </div>
</body>
</html>