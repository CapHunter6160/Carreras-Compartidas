<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Registro Conductor</title>

    <style>

        body{
            background:#f2f2f2;
            font-family:Arial;
            display:flex;
            justify-content:center;
            align-items:center;
            height:100vh;
        }

        .card{
            width:400px;
            background:white;
            padding:30px;
            border-radius:20px;
            box-shadow:0 5px 20px rgba(0,0,0,0.2);
        }

        h2{
            text-align:center;
            color:#f57c00;
            margin-bottom:20px;
        }

        input{
            width:100%;
            padding:12px;
            margin-bottom:15px;
            border-radius:10px;
            border:1px solid #ccc;
        }

        button{
            width:100%;
            padding:14px;
            border:none;
            border-radius:10px;
            background:#f57c00;
            color:white;
            font-size:16px;
            font-weight:bold;
            cursor:pointer;
        }

    </style>

</head>

<body>

    <div class="card">

        <h2>Registro Conductor</h2>

        <form action="FormVehiculo.jsp">

            <input type="text" placeholder="Nombre Completo" required>

            <input type="email" placeholder="Correo" required>

            <input type="password" placeholder="Contraseña" required>

            <button type="submit">
                Continuar
            </button>

        </form>

    </div>

</body>
</html>