package com.proyectoRestaurante.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.proyectoRestaurante.conexiones.*;
import com.proyectoRestaurante.entidades.*;

public class VentaDAO {

	public void insertar(Venta v) {
		try {
			
			Connection conexion = Conexion.conectar();			
			String sql = "INSERT INTO VENTA(IDCLIENTE, IDCOMIDA, VENDEDOR) VALUES (?,?,?)";
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setInt(1, v.getIdCliente());
			stmt.setInt(2, v.getIdComida());
			stmt.setString(3, v.getVendedor());
			stmt.execute();
			System.out.println("El registro fue ingresado correctamente");
			stmt.close();
			conexion.close();
		}catch(Exception e) {
			System.out.println("No se pudo insertar el registro.");
			e.printStackTrace();
		}
	}
	
	
}