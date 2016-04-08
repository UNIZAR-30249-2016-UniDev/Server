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

			// Variables de entorno para crear la url de acceso
			String name = System.getenv("OPENSHIFT_APP_NAME");
			String host = System.getenv("OPENSHIFT_POSTGRESQL_DB_HOST");
			String port = System.getenv("OPENSHIFT_POSTGRESQL_DB_PORT");

			// Url de acceso a la BD
			String url = "jdbc:postgresql://" + host + ":" + port + "/" + name;

			// Usuario y password de la BD
			String user = System.getenv("OPENSHIFT_POSTGRESQL_DB_USERNAME");
			String password = System.getenv("OPENSHIFT_POSTGRESQL_DB_PASSWORD");

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
