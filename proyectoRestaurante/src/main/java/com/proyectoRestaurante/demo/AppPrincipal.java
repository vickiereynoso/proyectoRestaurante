package com.proyectoRestaurante.demo;

import com.proyectoRestaurante.entidades.*;
import com.proyectoRestaurante.daos.*;
import java.sql.SQLException;
import java.util.*;

public class AppPrincipal {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

//		Comida comida1 = new Comida();
		//Usando setters:
//		comida1.setNombre("Pizza");
//		comida1.setDescripcion("Pizza doble muzza");
//		comida1.setPrecio(4200);
		
		
		
		//INSERTAR REGISTRO:
		
//		//Ahora tenemos que decirle que lo envíe a ComidaDAO.
//		//Creo un objeto de tipo ComidaDAO.
//		ComidaDAO cDAO = new ComidaDAO();
//		//Al nuevo objeto le envío ahora comida1 a través de insertar().
//		cDAO.insertar(comida1);
	
		
//		Comida comida2 = new Comida("Empanada", "Empanada de queso", 500);
//		ComidaDAO cDAO = new ComidaDAO();
//		cDAO.insertar(comida2);
		
//		Comida comida3 = new Comida("Milanesa", "Mila con puré", 3000);
//		ComidaDAO cDAO = new ComidaDAO();
//		cDAO.insertar(comida3);
		
//		Comida comida4 = new Comida("Pancho", "Con papas", 1000);
//		ComidaDAO cDAO = new ComidaDAO();
//		cDAO.insertar(comida4);
		
		
		//BORRAR REGISTRO:
		//ComidaDAO.borrar(3);
		
		//MODIFICAR REGISTRO:
//		Comida comida5 = new Comida(5, "Hamburguesa", "Con papas", 3000);
//		ComidaDAO cDAO = new ComidaDAO();
//		cDAO.modificar(comida5);
		
		//MOSTRAR REGISTRO:
		//ComidaDAO cDAO =  new ComidaDAO();
		//cDAO.listar();
		
		
		
		// --------------------- CLIENTE -------------------
		
		//INSERTAR CLIENTE:
		
		//Cliente c1 = new Cliente(12345, "Juan Gómez", "juan@gmail.com");
		//ClienteDAO cDAO = new ClienteDAO();
		//cDAO.insertar(c1);
		
//		Cliente c2 = new Cliente(336517, "María Vega", "maria@gmail.com");
//		ClienteDAO cDAO = new ClienteDAO();
//		cDAO.insertar(c2);
		
		//BORRAR REGISTRO:
		//ClienteDAO.borrar(7);
		
		//MODIFICAR REGISTRO:
//		Cliente cliente1 = new Cliente(3,336517, "Xavier", "x666@gmail.com");
//		ClienteDAO clienteDAO = new ClienteDAO();
//		clienteDAO.modificar(cliente1);
		
		
		//MOSTRAR REGISTRO:
//		ClienteDAO cDAO =  new ClienteDAO();
//		cDAO.listar();
		
		
		// --------------------- Lo mismo pero con Scanner -------------------
		
		Scanner teclado = new Scanner(System.in);
		int opcion = 9;
		
		
		do{ 		
			System.out.println("----------------------");
			System.out.println("Ingrese su opción:" + 
			"\n" + "1.- Ingresar clientes." 
			+"\n" + "2.- Ingresar comidas." 
			+ "\n" + "3.- Hacer una venta."
			+ "\n" + "4.- Salir del sistema.");
			int valor = teclado.nextInt();
			opcion = valor;
			switch(valor) {
			case 1: 	
				do {
				System.out.println("Ingrese el DNI del cliente:");
				int dni = teclado.nextInt();
				System.out.println("Ingrese el nombre del cliente:");
				String nombreCliente = teclado.next();
				teclado.nextLine();
				System.out.println("Ingrese el email del cliente:");
				String email = teclado.next();
				Cliente c1 = new Cliente(dni, nombreCliente, email);
				ClienteDAO cDAO = new ClienteDAO();
				cDAO.insertar(c1);
				System.out.println("");
				System.out.println("¿Quiere ingresar otro cliente? SI: 1 | NO: 0");
				opcion = teclado.nextInt();
				}while(opcion != 0);
				break;
			case 2:
				//opcion = 9;
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
			case 4:				
				break;
			case 3:
				do {
				ClienteDAO.listar();
				System.out.println("Ingrese id cliente:");
				int idCliente = teclado.nextInt();
				ComidaDAO.listar();
				System.out.println("Ingrese id comida:");
				int idComida = teclado.nextInt();
				System.out.println("Ingrese nombre vendedor:");
				String nombreVendedor = teclado.next();
				Venta venta1 = new Venta(idCliente, idComida, nombreVendedor);
				VentaDAO ventaDAO = new VentaDAO();
				ventaDAO.insertar(venta1);
				System.out.println("");
				System.out.println("¿Quiere hacer otra venta? SI: 1 | NO: 0");
				opcion = teclado.nextInt();
				}while(opcion != 0);
				break;
			default:
				System.out.println("Ingresaste un número equivocado. Volvé a intentar.");
				//break;
				}
		}while(opcion != 4);
		System.out.println("Saliste del sistema.");

//		case 3:
//			while(true) {
//			System.out.println("Ingrese id cliente:");
//			int idCliente = teclado.nextInt();
//			System.out.println("Ingrese id comida:");
//			int idComida = teclado.nextInt();
//			System.out.println("Ingrese nombre vendedor:");
//			String nombreVendedor = teclado.next();
//			Venta venta1 = new Venta(idCliente, idComida, nombreVendedor);
//			VentaDAO ventaDAO = new VentaDAO();
//			ventaDAO.insertar(venta1);
//			break;
//			}

		
		
		
		
	}

	
}