package puertosyadaptadores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Conexion a la Base de Datos
 */
public class ConexionBD {

	private static Connection conexion = null;

	/**
	 * Inicia conexion con una base de datos PostgreSQL.
	 * 
	 * @return true si se ha iniciado la conexion correctamente, o false si no
	 *         se ha iniciado la conexion correctamente
	 */
	public static boolean iniciarConexion() {
		try {
			Class.forName("org.postgresql.Driver");
			conexion = DriverManager
					.getConnection("jdbc:postgresql://ec2-79-125-126-192.eu-west-1.compute.amazonaws.com:5432/d7984qau8ubrrl?sslmode=require&user=kvwkxeyidjqmms&password=Ar-RYxpcE6C4kErrimKCb0lXFD");

			if (conexion != null) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Conexion fallida");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver JDBC de PostgreSQL no encontrado");
		}
		return false;
	}

	/**
	 * Devuelve la conexion a la base de datos PostgreSQL inicializada
	 * anteriormente
	 */
	public static Connection getConexion() {
		return conexion;
	}

	/**
	 * Cierra la conexion de la base de datos PostgreSQL inicializada
	 * anteriormente
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
