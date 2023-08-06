package com.proyectoRestaurante.daos;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.proyectoRestaurante.conexiones.*;
import com.proyectoRestaurante.entidades.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedWriter;

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
	
	
	
	
	//MOSTRAR valor $ total de ventas:
	
	
//	Select sum(PRECIO) from Venta v
//	left join Comida c on v.idcomida = c.id
//	left join Cliente cli on v.idcliente = cli.id;
	
	
	public static void mostrarGananciaTotal() throws SQLException { 
		
		Connection conexion = Conexion.conectar();
		Statement stmt = conexion.createStatement();
		
		try {
			String query = " SELECT sum(PRECIO) from Venta v left join Comida c on v.idcomida = c.id left join Cliente cli on v.idcliente = cli.id";
			ResultSet datos = stmt.executeQuery(query); 
			while(datos.next()) {
					System.out.println("");
					System.out.println("TOTAL RECAUDADO $: " + datos.getInt("sum(PRECIO)"));
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
	
	
	//MOSTRAR valor $ total consumido por un cliente en específico:
	
		//	Select sum(PRECIO),idcliente from Venta v
		//	left join Comida c on v.idcomida = c.id
		//	left join Cliente cli on v.idcliente = cli.id
		//	where cli.id = 38;
	
	public static void mostrarCostoAcumuladoCliente(int idCliente) throws SQLException { 
		
		Connection conexion = Conexion.conectar();
		Statement stmt = conexion.createStatement();
		
		try {
			String query = " SELECT sum(PRECIO),idcliente from Venta v left join Comida c on v.idcomida = c.id left join Cliente cli on v.idcliente = cli.id where cli.id = " + idCliente;
			ResultSet datos = stmt.executeQuery(query); 
			while(datos.next()) {
				if(datos.getInt("idcliente") == idCliente ) {
					System.out.println("");
					System.out.println("ID CLIENTE: "+ datos.getInt("idCliente"));
					System.out.println("TOTAL $: " + datos.getInt("sum(PRECIO)"));
					System.out.println("-----------------------------------");
				}
			}
		}catch(Exception e) {
			System.out.println("No se pudo mostrar los datos.");
			e.printStackTrace();//esto después hay que borrarlo porque queda mal que aparezca en la consola.
		}finally {
			stmt.close();
			conexion.close();
		}
		
	}
	
	
	//MOSTRAR los platos consumidos por un cliente en específico:
	
	public static void mostrarConsumosCliente(int idCliente) throws SQLException { 

		//	Select v.idcliente,cli.nombre,c.descripcion,c.precio from Venta v
		//	left join Comida c on v.idcomida = c.id
		//	left join Cliente cli on v.idcliente = cli.id
		//	where cli.id = 38;
		
		Connection conexion = Conexion.conectar();
		Statement stmt = conexion.createStatement();
		
		try {
			String query = " SELECT v.idcliente, cli.nombre, c.descripcion, c.precio from Venta v left join Comida c on v.idcomida = c.id left join Cliente cli on v.idcliente = cli.id where cli.id = " + idCliente;
			ResultSet datos = stmt.executeQuery(query); 
			while(datos.next()) {
				if(datos.getInt("idcliente") == idCliente ) {
					System.out.println("");
					System.out.println("ID CLIENTE: "+ datos.getInt("idCliente"));
					System.out.println("NOMBRE: "+ datos.getString("nombre"));
					System.out.println("DESCRIPCION: "+ datos.getString("descripcion"));
					System.out.println("PRECIO: "+ datos.getString("precio"));
					System.out.println("-----------------------------------");
				}
			}
		}catch(Exception e) {
			System.out.println("No se pudo mostrar los datos.");
			e.printStackTrace();//esto después hay que borrarlo porque queda mal que aparezca en la consola.
		}finally {
			stmt.close();
			conexion.close();
		}
		
	}
	
	
	//MOSTRAR vendedor con más ventas:
	
	public static void mostrarMejorVendedor() throws SQLException { 

		//	SELECT vendedor, count(*) FROM Venta 
		//    GROUP BY vendedor
		//    HAVING COUNT(*)>1;
		
		Connection conexion = Conexion.conectar();
		Statement stmt = conexion.createStatement();
		
		try {
			String query = "SELECT vendedor, count(*) from VENTA GROUP BY vendedor HAVING COUNT(*) > 1";
			ResultSet datos = stmt.executeQuery(query); 
			while(datos.next()) {
					System.out.println("");
					System.out.println("VENDEDOR: "+ datos.getString("vendedor"));
					System.out.println("N° VENTAS: "+ datos.getInt("count(*)"));
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
	
	
	//Retornar datos para el reporte:
	
	public static ArrayList retornarDatos() throws SQLException { 

//		-- Quiero ver la tabla ventas + precio de cada venta:
//			-- ventas (idventa y vendedor) y comida (nombre, descripcion y precio)
//			Select v.idventa, v.vendedor, c.nombre,c.descripcion,c.precio from Venta v
//			left join Comida c on v.idcomida = c.id;
		
		ArrayList resultados =  new ArrayList<>() ;
		String fila = null;
		
		Connection conexion = Conexion.conectar();
		Statement stmt = conexion.createStatement();
		
		try {
			String query = "Select v.idventa, v.vendedor, c.nombre, c.descripcion, c.precio from Venta v left join Comida c on v.idcomida = c.id";
			ResultSet datos = stmt.executeQuery(query); 
			
			while(datos.next()) {
				fila = "IDVENTA: "+ (Integer.toString(datos.getInt("idventa"))
						+ "\n"+ "VENDEDOR: "+ datos.getString("vendedor"))
						+ "\n" + "PLATO: " + datos.getString("nombre")
						+ "\n" + "DESCRIPCIÓN: " + datos.getString("descripcion")
						+ "\n" + "PRECIO: " + datos.getString("precio")
						+ "\n";		
				resultados.add(fila);
			}
		}catch(Exception e) {
			System.out.println("No se pudo mostrar los datos.");
			e.printStackTrace();//esto después hay que borrarlo porque queda mal que aparezca en la consola.
		}finally {
			stmt.close();
			conexion.close();
		}
		System.out.println(resultados);
		return resultados;
	}
	
	
	public static void generarReporteVentas() throws SQLException, IOException { 

		Connection conexion = Conexion.conectar();
		Statement stmt = conexion.createStatement();
		try {
			
			File file = new File("reporteVentas.txt");
			FileWriter fw = new FileWriter(file);
			char[] chars = retornarDatos().toString().toCharArray();
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(chars, 0, chars.length);
			bw.newLine(); //es una especie de "\n".
			bw.close();
		}catch(Exception e) {
			System.out.println("No se pudo mostrar los datos.");
			e.printStackTrace();//esto después hay que borrarlo porque queda mal que aparezca en la consola.
		}finally {
			stmt.close();
			conexion.close();
		}
	}
	
	
	
	
}