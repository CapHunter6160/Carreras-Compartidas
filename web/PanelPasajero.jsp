<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="ch.dto.VehiculoDTO" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Panel Pasajero</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            margin: 0;
            padding: 0;
            background-color: #0F0F12;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }
        .phone-wrapper {
            width: 100%;
            max-width: 412px;
            height: 100vh;
            max-height: 840px;
            background: #16161A;
            border-radius: 30px;
            box-shadow: 0 15px 35px rgba(0,0,0,0.5);
            display: flex;
            flex-direction: column;
            overflow: hidden;
            position: relative;
        }
        .header-panel {
            padding: 24px 20px;
            background: #1E1E24;
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-bottom: 1px solid #2A2A35;
        }
        h1 {
            color: #FFFFFF;
            font-size: 18px;
            margin: 0;
            font-weight: 600;
        }
        .btn-logout {
            background-color: rgba(211, 47, 47, 0.15);
            color: #FF5252;
            text-decoration: none;
            padding: 6px 12px;
            border-radius: 8px;
            font-weight: 600;
            font-size: 12px;
        }
        .content-scroll {
            padding: 20px;
            overflow-y: auto;
            flex: 1;
            display: flex;
            flex-direction: column;
            gap: 16px;
        }
        .content-scroll::-webkit-scrollbar { width: 0px; }

        /* Tarjeta de Ruta tipo Mockup */
        .route-card {
            background: #1E1E24;
            padding: 16px;
            border-radius: 20px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.2);
        }
        .route-title {
            color: #F2C94C;
            font-size: 14px;
            font-weight: 600;
            margin-bottom: 12px;
            text-transform: uppercase;
        }
        .route-input-group {
            background: #24242C;
            border-radius: 10px;
            padding: 10px;
            margin-bottom: 8px;
            border: 1px solid #3A3A42;
        }
        .route-input-group label {
            display: block;
            font-size: 11px;
            color: #8E8E93;
            margin-bottom: 2px;
        }
        .route-input-group input {
            width: 100%;
            background: transparent;
            border: none;
            color: #FFFFFF;
            font-size: 14px;
            outline: none;
            padding: 0;
        }

        /* Alertas de Estado */
        .alerta-exito, .alerta-status {
            background-color: #1E3A24;
            color: #4AF27A;
            padding: 14px;
            border-radius: 14px;
            font-size: 13px;
            font-weight: 600;
            text-align: center;
        }
        .alerta-status {
            background-color: #2D2715;
            color: #F2C94C;
        }
        .btn-status-link {
            color: #FFFFFF;
            background: rgba(255,255,255,0.1);
            padding: 4px 8px;
            border-radius: 6px;
            text-decoration: none;
            font-size: 11px;
            margin-left: 6px;
        }

        /* Lista Vertical de Vehículos - Mockup 4 */
        .list-title {
            color: #FFFFFF;
            font-size: 16px;
            font-weight: 600;
            margin: 10px 0 2px 0;
        }
        .card-vehiculo {
            background: #24242C;
            border: 1px solid #3A3A42;
            border-radius: 18px;
            padding: 16px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            transition: transform 0.2s;
        }
        .info-vehiculo {
            flex: 1;
        }
        .info-vehiculo h3 {
            margin: 0 0 4px 0;
            color: #FFFFFF;
            font-size: 16px;
        }
        .info-vehiculo p {
            margin: 2px 0;
            color: #8E8E93;
            font-size: 13px;
        }
        .precio {
            color: #F2C94C !important;
            font-weight: 600;
            font-size: 15px !important;
            margin-top: 6px !important;
        }
        .car-avatar {
            width: 70px;
            height: 50px;
            background: #3A3A42;
            border-radius: 10px;
            display: flex;
            justify-content: center;
            align-items: center;
            margin-left: 12px;
        }
        .car-avatar svg {
            width: 40px;
            height: 40px;
            fill: #F2C94C;
        }
        .btn-solicitar {
            background-color: #F2C94C;
            color: #16161A;
            border: none;
            padding: 12px;
            width: 100%;
            border-radius: 12px;
            font-weight: 600;
            font-size: 13px;
            cursor: pointer;
            margin-top: 12px;
        }
        .sin-vehiculos {
            text-align: center;
            padding: 30px;
            color: #8E8E93;
            font-style: italic;
            font-size: 14px;
        }
    </style>
</head> 
<body>

<div class="phone-wrapper">
    <div class="header-panel">
        <h1>Explorar Viajes</h1>
        <a href="LogoutServlet" class="btn-logout">Salir</a>
    </div>

    <div class="content-scroll">
        
        <%-- LÓGICA DE ALERTAS Y REFRESH --%>
        <% 
            String mensaje = request.getParameter("mensaje");
            String estado = (String) request.getAttribute("estadoSolicitud"); 
            
            if ("SolicitudEnviada".equals(mensaje)) { %>
                <div class="alerta-exito">✓ Solicitud enviada correctamente</div>
        <%  } 
            
            if ("ACEPTADO".equals(estado)) { %>
                <div class="alerta-exito">
                    ✓ ¡Viaje Aceptado! El conductor va en camino.
                    <a href="ListarVehiculosServlet" class="btn-status-link">Actualizar</a>
                </div>
        <%  } else if ("PENDIENTE".equals(estado)) { %>
                <div class="alerta-status">⏳ Esperando respuesta del conductor...</div>
                <meta http-equiv="refresh" content="5">
        <%  } %>

        <%-- FORMULARIO GLOBAL DE RUTA --%>
        <div class="route-card">
            <div class="route-title">¿A dónde vamos hoy?</div>
            <div class="route-input-group">
                <label>Punto de Encuentro (Origen)</label>
                <input type="text" id="global_origen" placeholder="Ej: Calle 72 # 45-23" oninput="sincronizarRutas()">
            </div>
            <div class="route-input-group">
                <label>Destino Final</label>
                <input type="text" id="global_destino" placeholder="Ej: Universidad Autónoma" oninput="sincronizarRutas()">
            </div>
        </div>

        <div class="list-title">Vehículos Cercanos</div>

        <%-- LISTADO DE VEHÍCULOS COMPACTOS --%>
        <%
            List<VehiculoDTO> listavehiculos = (List<VehiculoDTO>) request.getAttribute("listaVehiculos");
            if (listavehiculos != null && !listavehiculos.isEmpty()) {
                int index = 0;
                for (VehiculoDTO v : listavehiculos) {
                    index++;
        %>
                    <div style="background: #24242C; border-radius: 18px; padding: 12px; border: 1px solid #3A3A42;">
                        <div class="card-vehiculo" style="border:none; padding:0;">
                            <div class="info-vehiculo">
                                <h3><%= v.getMarca() %> <%= v.getModelo() %></h3>
                                <p>Placa: <span style="text-transform: uppercase; color:#FFF;"><%= v.getPlaca() %></span></p>
                                <p class="precio">$<%= v.getPrecio() %> COP</p>
                            </div>
                            <div class="car-avatar">
                                <svg viewBox="0 0 24 24"><path d="M18.92 11.01C18.72 10.42 18.16 10 17.5 10H6.5c-.66 0-1.21.42-1.42 1.01L3 17v8c0 .55.45 1 1 1h1c.55 0 1-.45 1-1v-1h12v1c0 .55.45 1 1 1h1c.55 0 1-.45 1-1v-8l-2.08-5.99zM6.5 22c-.83 0-1.5-.67-1.5-1.5S5.67 19 6.5 19s1.5.67 1.5 1.5S7.33 22 6.5 22zm11 0c-.83 0-1.5-.67-1.5-1.5s.67-1.5 1.5-1.5 1.5.67 1.5 1.5-.67 1.5-1.5 1.5z"/></svg>
                            </div>
                        </div>
                        
                        <form action="RegistrarViajeServlet" method="POST" id="form_<%= index %>">
                            <input type="hidden" name="placa_vehiculo" value="<%= v.getPlaca() %>">
                            <input type="hidden" name="punto_encuentro" id="origen_<%= index %>" required>
                            <input type="hidden" name="destino" id="destino_<%= index %>" required>
                            
                            <button type="submit" class="btn-solicitar" onclick="validarRuta(event, <%= index %>)">Viajar ahora</button>
                        </form>
                    </div>
        <%
                }
            } else {
        %>
                <div class="sin-vehiculos">No hay vehículos disponibles en tu zona.</div>
        <% } %>
    </div>
</div>

<script>
    // Sincroniza los inputs de la vista móvil con los formularios internos
    function sincronizarRutas() {
        var origen = document.getElementById("global_origen").value;
        var destino = document.getElementById("global_destino").value;
        
        <% 
            if (listavehiculos != null) { 
                for(int i=1; i<=listavehiculos.size(); i++) { 
        %>
                    document.getElementById("origen_<%= i %>").value = origen;
                    document.getElementById("destino_<%= i %>").value = destino;
        <% 
                } 
            } 
        %>
    }

    function validarRuta(e, index) {
        var orig = document.getElementById("origen_" + index).value;
        var dest = document.getElementById("destino_" + index).value;
        if(!orig || !dest) {
            e.preventDefault();
            alert("Por favor completa los campos de dirección en la tarjeta de arriba antes de solicitar el auto.");
        }
    }
</script>

</body>
</html>