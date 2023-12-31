package com.proyectoRestaurante.demo;

import com.proyectoRestaurante.entidades.*;
import com.proyectoRestaurante.daos.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class AppPrincipal {

	public static void main(String[] args) throws SQLException, IOException {
		// TODO Auto-generated method stub


		Scanner teclado = new Scanner(System.in);
		int opcion = 9;
		
		
		do{ 		
			System.out.println("----------------------");
			System.out.println("Ingrese su opción:" + 
			"\n" + "1.- Ingresar clientes." 
			+"\n" + "2.- Ingresar comidas." 
			+ "\n" + "3.- Hacer una venta."
			+ "\n" + "4.- Consultar comidas consumidas por un cliente."
			+ "\n" + "5.- Consultar total acumulado de un cliente."
			+ "\n" + "6.- Consultar ganancia recaudada total del restaurante."
			+ "\n" + "7.- Mostrar mejor vendedor."
			+ "\n" + "9.- Generar INFORMES."
			+ "\n" + "8.- Salir del sistema.");
			int valor = teclado.nextInt();
			opcion = valor;
			switch(valor) {
			case 1: 	
				do {
				System.out.println("Ingrese el DNI del cliente:");
				int dni = teclado.nextInt();				
				if(ClienteDAO.buscar(dni)) {
					System.out.println("");
					System.out.println("ERROR. Ya existe el cliente en la base de datos.");
				}else {
					System.out.println("Ingrese el nombre del cliente:");
					String nombreCliente = teclado.next();
					teclado.nextLine();
					System.out.println("Ingrese el email del cliente:");
					String email = teclado.next();
					Cliente c1 = new Cliente(dni, nombreCliente, email);
					ClienteDAO cDAO = new ClienteDAO();
					cDAO.insertar(c1);
				}
				System.out.println("");
				System.out.println("¿Quiere ingresar otro cliente? SI: 1 | NO: 0");
				opcion = teclado.nextInt();
				}while(opcion != 0);
				break;
			case 2:
				do {
				System.out.println("Ingrese nombre de comida:");
				String nombreComida = teclado.nextLine();
				teclado.nextLine();
				System.out.println("Ingrese descripción comida:");
				String descripcion = teclado.nextLine();
				System.out.println("Ingrese precio comida:");
				double precio = teclado.nextDouble();
				Comida comida1 = new Comida(nombreComida, descripcion, precio);
				ComidaDAO comidaDAO = new ComidaDAO();
				comidaDAO.insertar(comida1);
				System.out.println("");
				System.out.println("¿Quiere ingresar otra comida? SI: 1 | NO: 0");
				opcion = teclado.nextInt();
				}while(opcion != 0);
				break;					
			case 3:
				do {
				System.out.println("~~~~~~ LISTADO DE CLIENTES ~~~~~~");
				System.out.println("");
				ClienteDAO.listar();
				System.out.println("");
				System.out.println("Ingrese id cliente:");
				int idCliente = teclado.nextInt();
				System.out.println("~~~~~~ LISTADO DE COMIDAS ~~~~~~");
				System.out.println("");
				ComidaDAO.listar();
				System.out.println("");
				System.out.println("Ingrese id comida:");
				int idComida = teclado.nextInt();
				System.out.println("Ingrese nombre vendedor:");
				String vendedor = teclado.next();
				Venta venta1 = new Venta(idCliente, idComida, vendedor);
				VentaDAO ventaDAO = new VentaDAO();
				ventaDAO.insertar(venta1);
				System.out.println("");
				System.out.println("");
				System.out.println("~~~~~~ RESUMEN VENTAS: ~~~~~~");
				System.out.println("");
				VentaDAO.listar();
				teclado.nextLine();
				System.out.println("");
				System.out.println("¿Quiere hacer otra venta? SI: 1 | NO: 0");
				opcion = teclado.nextInt();
				}while(opcion != 0);
				break;
			case 4:
				do {
				System.out.println("Ingrese id cliente:");
				int idCliente = teclado.nextInt();
				VentaDAO.mostrarConsumosCliente(idCliente); 
				System.out.println("");
				System.out.println("¿Quiere consultar consumos de otro cliente? SI: 1 | NO: 0");
				opcion = teclado.nextInt();
				}while(opcion != 0);
				break;
			case 5:
				do {
				System.out.println("Ingrese id cliente:");
				int idCliente = teclado.nextInt();
				VentaDAO.mostrarCostoAcumuladoCliente(idCliente); 
				System.out.println("");
				System.out.println("¿Quiere consultar costo acumulado de otro cliente? SI: 1 | NO: 0");
				opcion = teclado.nextInt();
				}while(opcion != 0);
				break;
			case 6:
				do {
				VentaDAO.mostrarGananciaTotal(); 
				System.out.println("");
				System.out.println("¿Quiere volver al menú principal? SI: 0 | NO: 1");
				opcion = teclado.nextInt();
				}while(opcion != 0);
				break;
			case 7:
				do {
				VentaDAO.mostrarMejorVendedor(); 
				System.out.println("");
				System.out.println("¿Quiere volver al menú principal? SI: 0 | NO: 1");
				opcion = teclado.nextInt();
				}while(opcion != 0);
				break;
			case 9:
				do {
				//VentaDAO.retornarDatos();
				VentaDAO.generarInforme(); 
				System.out.println("");
				System.out.println("¿Quiere volver al menú principal? SI: 0 | NO: 1");
				opcion = teclado.nextInt();
				}while(opcion != 0);
				break;
			case 8:				
				break;
			default:
				System.out.println("Ingresaste un número equivocado. Volvé a intentar.");
				//break;
				}
		}while(opcion != 8);
		System.out.println("Saliste del sistema.");


		
		
		
		
	}	
}