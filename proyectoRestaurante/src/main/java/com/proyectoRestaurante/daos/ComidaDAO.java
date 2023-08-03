package com.proyectoRestaurante.daos;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.proyectoRestaurante.conexiones.*;
import com.proyectoRestaurante.entidades.*;

public class ComidaDAO {

	
	//Aqui se hara el primer proceso de la bbdd: 
	
	
	//INSERTAR REGISTRO.
	
	public void insertar(Comida c) {
		try {
			//PASO 1: obtener la conexión:
			Connection conexion = Conexion.conectar();
			//PASO 2: armar el query:
			String sql = "INSERT INTO COMIDA (NOMBRE, DESCRIPCION, PRECIO) VALUES" 
			+ "('" + c.getNombre() + "','" + c.getDescripcion()+ "'," +  c.getPrecio()+ ")";
			//PASO 3: crear la variable de tipo Statement que me permite realizar la conexion, envia la conexion:
			//Java genera como un permiso para pedirle a sql para conectar.
			Statement stmt = conexion.createStatement();
			//PASO 4: Ahora le digo que lo ejecute:
			stmt.execute(sql); //sql es el unico string que tengo para conectar.
			System.out.println("El registro fue ingresado correctamente!");
			//OPCIONAL:
			conexion.close(); //por seguridad, pero no es obligatorio. Para evitar ataques de sql injection, porque sino la bbdd queda abierta.
		}catch(Exception e) {
			System.out.println("No se pudo insertar el registro.");
			e.printStackTrace();
		}
	}
	
	
	//BORRAR REGISTRO:
	
	public static void borrar(int id) {
		try {
			Connection conexion = Conexion.conectar();
			String sql = "DELETE FROM COMIDA WHERE ID = "+id;
			Statement stmt = conexion.createStatement();
			stmt.execute(sql);
			System.out.println("Registro eliminado.");
			stmt.close();
			conexion.close();
		}catch(Exception e) {
			System.out.println("No se pudo borrar el registro.");
			e.printStackTrace();//esto después hay que borrarlo porque queda mal que aparezca en la consola.
			}
		}
	
	
	//MODIFICAR REGISTRO:
	
	public static void modificar(Comida comida) {
		try {
			Connection conexion = Conexion.conectar();
			String sql = "UPDATE COMIDA SET NOMBRE = '" + comida.getNombre() +"' , DESCRIPCION = '" 
					+ comida.getDescripcion()+ "', PRECIO = '" + comida.getPrecio()+
					"' WHERE ID = " + comida.getId();
			Statement stmt = conexion.createStatement();
			stmt.execute(sql);
			System.out.println("Registro modificado.");
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
			String query = "SELECT * FROM COMIDA";
			ResultSet datos = stmt.executeQuery(query); //execute query me retornara todos los datos.
			//System.out.println("Los datos fueron mostrados correctamente.");
			while(datos.next()) {
				System.out.println("ID: "+ datos.getInt("id"));
				System.out.println("Nombre: "+ datos.getString("nombre"));
				System.out.println("Descripcion: "+ datos.getString("descripcion"));
				System.out.println("Precio: "+ datos.getDouble("precio"));
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
