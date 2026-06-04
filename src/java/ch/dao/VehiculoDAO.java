package ch.dao;

import ch.conexion.ConexionBD;
import ch.modelo.Vehiculo;
import ch.dto.VehiculoDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDAO {

    public int registrar(Vehiculo v) throws Exception {
        // Se añade id_conductor al INSERT para cumplir con el nuevo esquema
        String sql = "INSERT INTO vehiculos (placa, marca, modelo, precio_dia, id_conductor) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexionBD.getInstancia().getConexionVehiculos();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, v.getPlaca());
            stmt.setString(2, v.getMarca());
            stmt.setString(3, v.getModelo());
            stmt.setDouble(4, v.getPrecio()); // Si tu modelo usa getPrecio_dia(), cámbialo aquí
            stmt.setInt(5, v.getId_conductor()); // Enlace lógico del conductor asignado
            
            return stmt.executeUpdate();
        }
    }
    
    public List<VehiculoDTO> listarVehiculosDTO() throws Exception {
        List<VehiculoDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM vehiculos";

        try (Connection conn = ConexionBD.getInstancia().getConexionVehiculos();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                // CORREGIDO: Ahora pasa los 5 parámetros exactos que el DTO necesita
                VehiculoDTO dto = new VehiculoDTO(
                    rs.getString("placa"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getDouble("precio_dia"),
                    rs.getInt("id_conductor") // Parámetro faltante que causaba el error
                );
                lista.add(dto);
            }
        }
        return lista;
    }
}