package com.proyectoRestaurante.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	//MODIFICAR REGISTRO:
	
	public static void modificar(Venta venta) {
		try {
			Connection conexion = Conexion.conectar();			
			String sql = "INSERT INTO VENTA(IDCLIENTE, IDCOMIDA, VENDEDOR) VALUES (?,?,?)";
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setInt(1, venta.getIdCliente());
			stmt.setInt(2, venta.getIdComida());
			stmt.setString(3, venta.getVendedor());
			stmt.execute();
			System.out.println("El registro fue ingresado correctamente");
			stmt.close();
			conexion.close();
		}catch(Exception e) {
			System.out.println("No se pudo modificar el registro.");
			e.printStackTrace();//esto después hay que borrarlo porque queda mal que aparezca en la consola.
		}
	}
	
	
	//MOSTRAR/LISTAR REGISTRO:
	
	public static void listar() throws SQLException { 
		
		//Otra manera de escribir el código:
		
		Connection conexion = Conexion.conectar();
		Statement stmt = conexion.createStatement();
		
		try {
			String query = "SELECT * FROM VENTA";
			ResultSet datos = stmt.executeQuery(query); //execute query me retornará todos los datos.
			//System.out.println("Los datos fueron mostrados correctamente.");
			while(datos.next()) {
				System.out.println("ID VENTA: "+ datos.getInt("idVenta"));
				System.out.println("ID CLIENTE: "+ datos.getInt("idCliente"));
				System.out.println("ID COMIDA: "+ datos.getInt("idComida"));
				System.out.println("VENDEDOR: "+ datos.getString("vendedor"));
				System.out.println("-----------------------------------");
			}
		}catch(Exception e) {
			System.out.println("No se pudo mostrar los datos.");
			e.printStackTrace();//esto después hay que borrarlo porque queda mal que aparezca en la consola.
		}finally {
			stmt.close();
			conexion.close();
		}
	}
	
}