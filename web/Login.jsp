<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Iniciar Sesión</title>
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
            padding: 40px 30px;
            box-sizing: border-box;
            border-radius: 30px;
            box-shadow: 0 15px 35px rgba(0,0,0,0.5);
            display: flex;
            flex-direction: column;
        }
        .back-btn {
            color: #FFFFFF;
            text-decoration: none;
            font-size: 24px;
            margin-bottom: 30px;
            display: inline-block;
            width: 30px;
        }
        h2 {
            color: #FFFFFF;
            font-size: 28px;
            margin: 0 0 8px 0;
            font-weight: 700;
        }
        p.subtitle {
            color: #8E8E93;
            margin: 0 0 40px 0;
            font-size: 15px;
        }
        .form-group {
            margin-bottom: 24px;
        }
        label {
            display: block;
            color: #AEAEE2;
            font-size: 13px;
            font-weight: 600;
            margin-bottom: 8px;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }
        input {
            width: 100%;
            padding: 16px;
            background: #24242C;
            border: 1px solid #3A3A42;
            border-radius: 14px;
            color: #FFFFFF;
            font-size: 15px;
            box-sizing: border-box;
            transition: all 0.2s ease;
        }
        input:focus {
            outline: none;
            border-color: #F2C94C;
            background: #2A2A35;
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
            margin-top: 16px;
            transition: background 0.2s;
        }
        .btn-submit:hover {
            background-color: #E0BA43;
        }
        .footer-links {
            text-align: center;
            margin-top: auto;
            padding-top: 20px;
        }
        .footer-links a {
            color: #F2C94C;
            text-decoration: none;
            font-size: 14px;
            font-weight: 500;
        }
        .footer-links a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

    <div class="phone-wrapper">
        
        <h2>Inicia sesión</h2>
        <p class="subtitle">Qué bueno verte de regreso por aquí</p>

        <form action="LoginServlet" method="POST">
            <div class="form-group">
                <label>Correo Electrónico</label>
                <input type="email" name="correo" required placeholder="tu@correo.com">
            </div>

            <div class="form-group">
                <label>Contraseña</label>
                <input type="password" name="contrasena" required placeholder="••••••••">
            </div>

            <button type="submit" class="btn-submit">Entrar</button>
        </form>

        <div class="footer-links">
            <a href="CrearCuenta.jsp">¿No tienes cuenta? Regístrate</a>
        </div>
    </div>

</body>
</html>