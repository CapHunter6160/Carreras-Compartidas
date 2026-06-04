<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registro de Vehículo</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .form-container {
            background: white;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 450px;
            border-top: 6px solid #f57c00; /* Detalle naranja */
        }
        h2 {
            color: #173B73; /* Azul institucional */
            text-align: center;
            margin-bottom: 25px;
            font-size: 24px;
        }
        .form-group {
            margin-bottom: 18px;
        }
        label {
            display: block;
            margin-bottom: 7px;
            color: #333;
            font-weight: bold;
            font-size: 14px;
        }
        input[type="text"],
        input[type="number"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 6px;
            box-sizing: border-box;
            font-size: 15px;
            transition: border-color 0.3s;
        }
        input[type="text"]:focus,
        input[type="number"]:focus {
            border-color: #173B73;
            outline: none;
        }
        .btn-submit {
            width: 100%;
            padding: 12px;
            background-color: #173B73;
            color: white;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s;
            margin-top: 10px;
        }
        .btn-submit:hover {
            background-color: #0f2950;
        }
        .btn-regresar {
            display: block;
            text-align: center;
            margin-top: 15px;
            color: #555;
            text-decoration: none;
            font-size: 14px;
        }
        .btn-regresar:hover {
            color: #f57c00;
            text-decoration: underline;
        }
        .nota-sesion {
            background-color: #e3f2fd;
            border-left: 4px solid #173B73;
            padding: 10px;
            font-size: 12px;
            color: #0d47a1;
            margin-bottom: 15px;
            border-radius: 4px;
        }
    </style>
</head>
<body>

    <div class="form-container">
        <h2>Registrar Nuevo Vehículo</h2>
        
        <form action="RegistroVehiculo" method="POST">
            
            <div class="form-group">
                <label for="id_conductor">ID del Conductor Asignado:</label>
                <input type="number" id="id_conductor" name="id_conductor" placeholder="Ej: 12" required>
            </div>

            <div class="form-group">
                <label for="placa">Placa del Vehículo:</label>
                <input type="text" id="placa" name="placa" placeholder="Ej: ABC123" required>
            </div>

            <div class="form-group">
                <label for="marca">Marca:</label>
                <input type="text" id="marca" name="marca" placeholder="Ej: Chevrolet" required>
            </div>

            <div class="form-group">
                <label for="modelo">Modelo (Año):</label>
                <input type="text" id="modelo" name="modelo" placeholder="Ej: 2024" required>
            </div>

            <div class="form-group">
                <label for="precio">Precio por Día ($):</label>
                <input type="number" id="precio" name="precio" step="0.01" placeholder="Ej: 45000" required>
            </div>

            <button type="submit" class="btn-submit">Guardar Vehículo</button>
            
            <a href="javascript:history.back()" class="btn-regresar">← Volver al panel</a>
        </form>
    </div>

</body>
</html>