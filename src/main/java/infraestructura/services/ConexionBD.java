package infraestructura.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

	private static Connection conexion = null;
	
	/**
	 * Inicia conexión con una base de datos PostgreSQL.
	 * @return <true> Si se ha iniciado la conexión correctamente
	 *         <false> Si no se ha iniciado la conexión correctamente
	 */
	public static boolean iniciarConexion() {
		try {
			Class.forName("org.postgresql.Driver");
			
			// Url de acceso a la BD
			String url = "jdbc:postgresql://ec2-79-125-126-192.eu-west-1.compute.amazonaws.com:5432/d7984qau8ubrrl";

			// Usuario y password de la BD
			String user = "kvwkxeyidjqmms";
			String password = "Ar-RYxpcE6C4kErrimKCb0lXFD";

			conexion = DriverManager.getConnection(url, user, password);

			if (conexion != null) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Conexión fallida");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver JDBC de PostgreSQL no encontrado");
		}
		return false;
	}
	
	/**
	 * Devuelve la conexión a la base de datos PostgreSQL inicializada anteriormente
	 */
	public static Connection getConexion() {
		return conexion;
	}
	
	/**
	 * Cierra la conexión de la base de datos PostgreSQL inicializada anteriormente
	 */
	public static void cerrarConexion() {
		if (conexion != null) {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
