package com.proyectoRestaurante.conexiones;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

	//Creamos variable de tipo Connection.
	static Connection conexion = null;
	//Debo importar: import java.sql.Connection;
	
	//Creamos un método de tipo Connection.
	public static Connection conectar() {
		//Las conexiones tienen que tener manejo de errores:
		try {
			//Dos pasos:
			
			//YA NO ES NECESARIO ESTE PASO 1. ESTA DEPRECADO.:
			//1. Pasar el Driver a través de una función llamada forName:
			//Class.forName("com.mysql.cj.jdbc.Driver"); NO ES NECESARIO ESTO YA.
			//Esta es una clase que me permite entablar la conexión.
			//O sea, le paso la ruta del conector.
		
			//PASAR DIRECTAMENTE A ESTO:
			//2. Ahora debo establaer la conexion con la bbdd a traves del enlace:
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/mirest","root",""); 
			//nombre de la bbdd, nombre de usuario o sea root, y a lo ultimo iria la clave si la sabemos.
			//le digo a dónde se va a conectar.
			
			System.out.println("Conexión establecida!");
			
		}catch(Exception e) {
			System.out.println("No me pude conectar.");
			e.printStackTrace(); //es sólo para probarlo ahora, en general no va.
		}
		return conexion;
	}
	
	
	

}

