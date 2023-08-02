package com.proyectoRestaurante.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.proyectoRestaurante.conexiones.*;
import com.proyectoRestaurante.entidades.*;

public class ClienteDAO {

	
	//INSERTAR REGISTRO.
	
	public void insertar(Cliente c) {
		try {
			
			Connection conexion = Conexion.conectar();			
			String sql = "INSERT INTO CLIENTE(DNI, NOMBRE, EMAIL) VALUES (?,?,?)";
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setInt(1, c.getDni());
			stmt.setString(2, c.getNombre());
			stmt.setString(3, c.getEmail());
			stmt.execute();
			System.out.println("El registro fue ingresado correctamente");
			stmt.close();
			conexion.close();
			
//			//PASO 1: obtener la conexión:
//			Connection conexion = Conexion.conectar();
//			//PASO 2: armar el query:
//			String sql = "INSERT INTO CLIENTE(DNI, NOMBRE, EMAIL) VALUES (?,?,?)"; //OTRA FORMA DE HACERLO PERO SIN TANTO LÍO DE COMAS SIMPLES Y DOBLES. 
//			//+ "(" + c.getDni() + ",'" + c.getNombre()+ ",'" + ",'" + c.getEmail() + "'" +")";
//			//PASO 3: crear la variable de tipo Statement que me permite realizar la conexion, envia la conexion:
//			//Java genera como un permiso para pedirle a sql para conectar.
//			//Statement stmt = conexion.createStatement(); // ESTA YA NO FUNCIONA AL ARMAR EL STRING CON (?,?,?)
//			PreparedStatement stmt = conexion.prepareStatement(sql); //AGREGAMOS ESO NUEVO.
//			stmt.setInt(1, c.getDni());
//			stmt.setString(2, c.getNombre());
//			stmt.setString(3, c.getEmail());
//			//PASO 4: Ahora le digo que lo ejecute:
//			stmt.execute(sql); //sql es el unico string que tengo para conectar.
//			System.out.println("El registro fue ingresado correctamente!");
//			//OPCIONAL:
//			stmt.close();
//			conexion.close(); //por seguridad, pero no es obligatorio. Para evitar ataques de sql injection, porque sino la bbdd queda abierta.
		}catch(Exception e) {
			System.out.println("No se pudo insertar el registro.");
			e.printStackTrace();
		}
	}
	
	
	//BORRAR REGISTRO:
	
	public static void borrar(int id) {
		try {
			Connection conexion = Conexion.conectar();
			String sql = "DELETE FROM CLIENTE WHERE ID = "+id;
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
	
	public static void modificar(Cliente cliente) {
		try {
			Connection conexion = Conexion.conectar();
			String sql = "UPDATE CLIENTE SET DNI = '" + cliente.getDni() +"' , NOMBRE = '" 
					+ cliente.getNombre()+ "', EMAIL = '" + cliente.getEmail() + "'WHERE ID = " + cliente.getId();
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
			String query = "SELECT * FROM CLIENTE";
			ResultSet datos = stmt.executeQuery(query); //execute query me retornara todos los datos.
			System.out.println("Los datos fueron mostrados correctamente.");
			while(datos.next()) {
				System.out.println("ID: "+ datos.getInt("id"));
				System.out.println("DNI: "+ datos.getInt("dni"));
				System.out.println("Nombre: "+ datos.getString("nombre"));
				System.out.println("Email: "+ datos.getString("email"));
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

