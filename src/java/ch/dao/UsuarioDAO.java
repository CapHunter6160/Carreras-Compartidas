package ch.dao;

import ch.conexion.ConexionBD;
import ch.modelo.Usuario;
import java.sql.*;

public class UsuarioDAO {

    public int registrar(Usuario u) throws Exception {
        String sql = "INSERT INTO usuarios(nombre, correo, contrasena, rol) VALUES(?,?,?,?)";

        try (Connection conn = ConexionBD.getInstancia().getConexionRegistros();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, u.getNombre());
            stmt.setString(2, u.getCorreo());
            stmt.setString(3, u.getContrasena());
            stmt.setString(4, u.getRol());

            return stmt.executeUpdate();
        }
    }

    public Usuario login(String correo, String contrasena) throws Exception {
        String sql = "SELECT * FROM usuarios WHERE correo=? AND contrasena=?";

        try (Connection conn = ConexionBD.getInstancia().getConexionRegistros();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, correo);
            stmt.setString(2, contrasena);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario();
                    u.setId(rs.getInt("id_usuario"));
                    u.setNombre(rs.getString("nombre"));
                    u.setCorreo(rs.getString("correo"));
                    u.setRol(rs.getString("rol"));
                    return u;
                }
            }
        }
        return null;
    }
}