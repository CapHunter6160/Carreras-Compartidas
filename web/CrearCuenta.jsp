<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Crear Cuenta</title>
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
            padding: 30px;
            box-sizing: border-box;
            border-radius: 30px;
            box-shadow: 0 15px 35px rgba(0,0,0,0.5);
            display: flex;
            flex-direction: column;
            overflow-y: auto;
        }
        /* Ocultar barra de scroll para estética limpia */
        .phone-wrapper::-webkit-scrollbar {
            width: 4px;
        }
        .phone-wrapper::-webkit-scrollbar-thumb {
            background: #3A3A42;
            border-radius: 4px;
        }
        .back-btn {
            color: #FFFFFF;
            text-decoration: none;
            font-size: 24px;
            margin-bottom: 20px;
            display: inline-block;
        }
        h2 {
            color: #FFFFFF;
            font-size: 26px;
            margin: 0 0 24px 0;
            font-weight: 700;
        }
        .form-group {
            margin-bottom: 18px;
        }
        label {
            display: block;
            color: #AEAEE2;
            font-size: 12px;
            font-weight: 600;
            margin-bottom: 6px;
            text-transform: uppercase;
        }
        input, select {
            width: 100%;
            padding: 14px;
            background: #24242C;
            border: 1px solid #3A3A42;
            border-radius: 12px;
            color: #FFFFFF;
            font-size: 14px;
            box-sizing: border-box;
        }
        input:focus, select:focus {
            outline: none;
            border-color: #F2C94C;
        }
        .seccion-conductor {
            background-color: #1E1E24;
            border-left: 4px solid #F2C94C;
            padding: 15px;
            margin-top: 15px;
            border-radius: 12px;
        }
        .seccion-title {
            color: #F2C94C;
            font-weight: bold;
            margin-bottom: 12px;
            font-size: 13px;
            text-transform: uppercase;
        }
        .btn-submit {
            width: 100%;
            padding: 16px;
            background-color: #F2C94C;
            color: #16161A;
            border: none;
            border-radius: 16px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            margin-top: 10px;
        }
        .btn-login {
            display: block;
            text-align: center;
            margin-top: 20px;
            color: #8E8E93;
            text-decoration: none;
            font-size: 14px;
        }
        .btn-login span {
            color: #F2C94C;
        }
    </style>
    <script>
        function alternarCamposRol() {
            var selectRol = document.getElementById("rol");
            var seccionConductor = document.getElementById("campos-conductor");
            var inputsConductor = seccionConductor.getElementsByTagName("input");

            if (selectRol.value === "conductor") {
                seccionConductor.style.display = "block";
                for (var i = 0; i < inputsConductor.length; i++) {
                    inputsConductor[i].required = true;
                }
            } else {
                seccionConductor.style.display = "none";
                for (var i = 0; i < inputsConductor.length; i++) {
                    inputsConductor[i].required = false;
                    inputsConductor[i].value = "";
                }
            }
        }
        window.onload = function() {
            alternarCamposRol();
        };
    </script>
</head>
<body>

    <div class="phone-wrapper">
        <a href="Index.html" class="back-btn">←</a>
        <h2>Crea tu cuenta</h2>

        <form action="RegistroUsuarioServlet" method="POST">
            <div class="form-group">
                <label for="nombre">Nombre Completo</label>
                <input type="text" id="nombre" name="nombre" required placeholder="Ej: Juan Pérez">
            </div>

            <div class="form-group">
                <label for="correo">Correo Electrónico</label>
                <input type="email" id="correo" name="correo" required placeholder="ejemplo@correo.com">
            </div>

            <div class="form-group">
                <label for="contrasena">Contraseña</label>
                <input type="password" id="contrasena" name="contrasena" required placeholder="Crea una clave segura">
            </div>

            <div class="form-group">
                <label for="rol">¿Cómo deseas ingresar?</label>
                <select id="rol" name="rol" onchange="alternarCamposRol()">
                    <option value="pasajero" selected>Pasajero (Quiero viajar)</option>
                    <option value="conductor">Conductor (Ofrecer vehículo)</option>
                </select>
            </div>

            <div id="campos-conductor" class="seccion-conductor" style="display: none;">
                <div class="seccion-title">Datos del Vehículo</div>
                
                <div class="form-group">
                    <label for="licencia">Número de Licencia</label>
                    <input type="text" id="licencia" name="licencia" placeholder="Ej: LIC-98765">
                </div>

                <div class="form-group">
                    <label for="placa">Placa del Vehículo</label>
                    <input type="text" id="placa" name="placa" placeholder="Ej: ABC123">
                </div>

                <div class="form-group">
                    <label for="marca">Marca</label>
                    <input type="text" id="marca" name="marca" placeholder="Ej: Chevrolet">
                </div>

                <div class="form-group">
                    <label for="modelo">Modelo (Año)</label>
                    <input type="text" id="modelo" name="modelo" placeholder="Ej: 2024">
                </div>

                <div class="form-group">
                    <label for="precio_dia">Precio de Renta por Día ($)</label>
                    <input type="number" id="precio_dia" name="precio_dia" step="0.01" placeholder="Ej: 50000">
                </div>
            </div>

            <button type="submit" class="btn-submit">Registrarse</button>
            
            <a href="Login.jsp" class="btn-login">¿Ya tienes cuenta? <span>Inicia sesión</span></a>
        </form>
    </div>

</body>
</html>