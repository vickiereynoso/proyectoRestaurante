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
	
		
	
	public static void generarInforme() throws SQLException { 
		
		ArrayList resultados =  new ArrayList<>() ;
		String fila = null;
		
		Connection conexion = Conexion.conectar();
		Statement stmt = conexion.createStatement();
		
		try {
			Scanner teclado = new Scanner(System.in);
			int opcion = 9;
			do{ 		
				System.out.println("----------------------");
				System.out.println("Generar informe de:" + 
				"\n" + "1.- Clientes." 
				+"\n" + "2.- Comidas." 
				+ "\n" + "3.- Ventas."
				+ "\n" + "4.- Comidas consumidas por un cliente."
				+ "\n" + "5.- Mejor vendedor."
				+ "\n" + "6.- Volver al menú anterior.");
				int valor = teclado.nextInt();
				opcion = valor;
				switch(valor) {
				case 1: 	
					generarReporte("reporteClientes");
					break;
				case 2:
					generarReporte("reporteComidas");
					break;					
				case 3:
					generarReporte("reporteVentas");
					break;
				case 4:
					generarReporte("reporteComidasPorCliente");
					break;
				case 5:
					generarReporte("reporteMejorVendedor");
					break;
				case 6:				
					break;
				default:
					System.out.println("Ingresaste un número equivocado. Volvé a intentar.");
					//break;	
				}
			}while(opcion != 6);
		}catch(Exception e) {
			System.out.println("No se pudo mostrar los datos.");
			e.printStackTrace();//esto después hay que borrarlo porque queda mal que aparezca en la consola.
		}finally {
			stmt.close();
			conexion.close();
		}
	}
	
	
	
	private static void generarReporte(String locacionArchivo) throws SQLException, IOException { 

		Connection conexion = Conexion.conectar();
		Statement stmt = conexion.createStatement();
		try {
			
			File file = new File(locacionArchivo);
			FileWriter fw = new FileWriter(file);
			char[] chars = retornarDatos(locacionArchivo).toString().toCharArray();
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
	
	
	
	private static ArrayList retornarDatos(String locacionArchivo) throws SQLException { 
		
		ArrayList resultados =  new ArrayList<>() ;	
		String fila = null;
		Connection conexion = Conexion.conectar();
		Statement stmt = conexion.createStatement();
		try {
			if(locacionArchivo.equals("reporteClientes")) {
				String query = "Select * from Cliente";
				ResultSet datos = stmt.executeQuery(query); 	
				while(datos.next()) {
					fila = "\n"
							+ "ID: "+ (Integer.toString(datos.getInt("id")))
							+ "\n"+ "DNI: "+ (Integer.toString(datos.getInt("dni")))
							+ "\n" + "NOMBRE: " + datos.getString("nombre")
							+ "\n" + "EMAIL: " + datos.getString("email")
							+ "\n";		
					resultados.add(fila);
				}		
			}if(locacionArchivo.equals("reporteComidas")) {
				String query = "Select * from Comida";
				ResultSet datos = stmt.executeQuery(query); 	
				while(datos.next()) {
					fila = "\n"
							+ "ID: "+ (Integer.toString(datos.getInt("id")))
							+ "\n"+ "NOMBRE: "+ datos.getString("nombre")
							+ "\n" + "DESCRIPCION: " + datos.getString("descripcion")
							+ "\n" + "PRECIO: " + datos.getString("precio")
							+ "\n";		
					resultados.add(fila);
				}	
			}if(locacionArchivo.equals("reporteVentas")) {
				String query = "Select * from Venta";
				ResultSet datos = stmt.executeQuery(query); 	
				while(datos.next()) {
					fila = "\n"
							+ "IDVENTA: "+ (Integer.toString(datos.getInt("idventa")))
							+ "\n"+ "IDCLIENTE: "+ (Integer.toString(datos.getInt("idcliente")))
							+ "\n" + "IDCOMIDA: " + (Integer.toString(datos.getInt("idcomida")))
							+ "\n" + "VENDEDOR: " + datos.getString("vendedor")
							+ "\n";		
					resultados.add(fila);
				}	
			}if(locacionArchivo.equals("reporteComidasPorCliente")) {
					String query = " SELECT v.idcliente, cli.nombre, c.descripcion, c.precio from Venta v left join Comida c on v.idcomida = c.id left join Cliente cli on v.idcliente = cli.id order by idcliente asc";
					ResultSet datos = stmt.executeQuery(query); 	
					while(datos.next()) {
						fila = "\n"
								+ "IDCLIENTE: "+ (Integer.toString(datos.getInt("idcliente")))
								+ "\n" + "NOMBRE: " + datos.getString("nombre")
								+ "\n" + "DESCRIPCION: " + datos.getString("descripcion")
								+ "\n" + "PRECIO: " + datos.getString("precio")
								+ "\n";		
						resultados.add(fila);
					}	
			}if(locacionArchivo.equals("reporteMejorVendedor")) {
				String query = "SELECT vendedor, count(*) from VENTA GROUP BY vendedor HAVING COUNT(*) > 1";;
				ResultSet datos = stmt.executeQuery(query); 	
				while(datos.next()) {
					fila = "\n"
							+ "\n" + "VENDEDOR: " + datos.getString("vendedor")
							+ "\n" + "N° VENTAS: " + (Integer.toString(datos.getInt("count(*)")))
							+ "\n";		
					resultados.add(fila);
				}	
			}
		}catch(Exception e) {
			System.out.println("No se pudo mostrar los datos.");
			e.printStackTrace();//esto después hay que borrarlo porque queda mal que aparezca en la consola.
		}finally {
			stmt.close();
			conexion.close();
		}
		return resultados;
	}

	
	
	
	
	
	
	
//	//Retornar datos para el reporte de ventas:
//	
//	private static ArrayList retornarDatos() throws SQLException { 
//
////		-- Quiero ver la tabla ventas + precio de cada venta:
////			-- ventas (idventa y vendedor) y comida (nombre, descripcion y precio)
////			Select v.idventa, v.vendedor, c.nombre,c.descripcion,c.precio from Venta v
////			left join Comida c on v.idcomida = c.id;
//		
//		ArrayList resultados =  new ArrayList<>() ;
//		String fila = null;
//		
//		Connection conexion = Conexion.conectar();
//		Statement stmt = conexion.createStatement();
//		
//		try {
//			String query = "Select v.idventa, v.vendedor, c.nombre, c.descripcion, c.precio from Venta v left join Comida c on v.idcomida = c.id";
//			ResultSet datos = stmt.executeQuery(query); 
//			
//			while(datos.next()) {
//				fila = "\n"
//						+ "IDVENTA: "+ (Integer.toString(datos.getInt("idventa"))
//						+ "\n"+ "VENDEDOR: "+ datos.getString("vendedor"))
//						+ "\n" + "PLATO: " + datos.getString("nombre")
//						+ "\n" + "DESCRIPCIÓN: " + datos.getString("descripcion")
//						+ "\n" + "PRECIO: " + datos.getString("precio")
//						+ "\n";		
//				resultados.add(fila);
//			}
//		}catch(Exception e) {
//			System.out.println("No se pudo mostrar los datos.");
//			e.printStackTrace();//esto después hay que borrarlo porque queda mal que aparezca en la consola.
//		}finally {
//			stmt.close();
//			conexion.close();
//		}
//		return resultados;
//	}
	
	
}