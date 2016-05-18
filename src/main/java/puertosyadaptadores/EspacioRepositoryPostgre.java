package puertosyadaptadores;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import dominio.Constantes.STATE;
import dominio.Constantes.TYPE;
import dominio.Espacio;
import dominio.EspacioRepository;
import dominio.Location;
import dominio.Point;
import dominio.SensorActuadorBinario;
import dominio.SensorActuadorTemperatura;
import dominio.Temperatura;

public class EspacioRepositoryPostgre extends EspacioRepository {

	private Connection conn = null;

	public EspacioRepositoryPostgre() {
		ConexionBD.iniciarConexion();
		this.conn = ConexionBD.getConexion();
	}

	@Override
	public List<Espacio> findDespachos(int floor) {
		List<Espacio> espacios = new LinkedList<Espacio>();
		try {
			String sql = "SELECT ID_UTC, ST_X(ST_TRANSFORM(the_geom, 23030)) AS LOCATIONX, ST_Y(ST_TRANSFORM(the_geom, 23030)) AS LOCATIONY, ID_EDIFICIO, ILUMINACION,"
					+ " PUERTAS, PRESENCIA, TEMPERATURA, TEMPERATURAOBJETIVO FROM proyecto.espacios WHERE ID_PLANTA = " + floor + " AND TIPO_DE_US = 17";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				STATE iluminacion = getState(rs.getString("ILUMINACION"));
				STATE puertas = getState(rs.getString("PUERTAS"));
				STATE presencia = getState(rs.getString("PRESENCIA"));
				espacios.add(new Espacio(rs.getString("ID_UTC"), new Location(new Point(rs.getDouble("LOCATIONX"), rs.getDouble("LOCATIONY")),
						floor, rs.getInt("ID_EDIFICIO")), TYPE.DESPACHO, new SensorActuadorBinario(iluminacion),
						new SensorActuadorBinario(puertas), new SensorActuadorBinario(presencia), new SensorActuadorTemperatura(
								new Temperatura(rs.getDouble("TEMPERATURA")), new Temperatura(rs.getDouble("TEMPERATURAOBJETIVO")))));
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error en buscar Despachos");
		}
		return espacios;
	}

	@Override
	public List<Espacio> findLaboratorios(int floor) {
		List<Espacio> espacios = new LinkedList<Espacio>();
		try {
			String sql = "SELECT ID_UTC, ST_X(ST_TRANSFORM(the_geom, 23030)) AS LOCATIONX, ST_Y(ST_TRANSFORM(the_geom, 23030)) AS LOCATIONY, ID_EDIFICIO, ILUMINACION,"
					+ " PUERTAS, PRESENCIA, TEMPERATURA, TEMPERATURAOBJETIVO FROM proyecto.espacios WHERE ID_PLANTA = " + floor + " AND (TIPO_DE_US = 41 OR TIPO_DE_US = 44 OR TIPO_DE_US = 55)";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				STATE iluminacion = getState(rs.getString("ILUMINACION"));
				STATE puertas = getState(rs.getString("PUERTAS"));
				STATE presencia = getState(rs.getString("PRESENCIA"));
				espacios.add(new Espacio(rs.getString("ID_UTC"), new Location(new Point(rs.getDouble("LOCATIONX"), rs.getDouble("LOCATIONY")),
						floor, rs.getInt("ID_EDIFICIO")), TYPE.LAB, new SensorActuadorBinario(iluminacion),
						new SensorActuadorBinario(puertas), new SensorActuadorBinario(presencia), new SensorActuadorTemperatura(
								new Temperatura(rs.getDouble("TEMPERATURA")), new Temperatura(rs.getDouble("TEMPERATURAOBJETIVO")))));
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error en buscar Laboratorios");
		}
		return espacios;
	}

	@Override
	public List<Espacio> findWcs(int floor) {
		List<Espacio> espacios = new LinkedList<Espacio>();
		try {
			String sql = "SELECT ID_UTC, ST_X(ST_TRANSFORM(the_geom, 23030)) AS LOCATIONX, ST_Y(ST_TRANSFORM(the_geom, 23030)) AS LOCATIONY, ID_EDIFICIO, ILUMINACION,"
					+ " PUERTAS, PRESENCIA, TEMPERATURA, TEMPERATURAOBJETIVO FROM proyecto.espacios WHERE ID_PLANTA = " + floor + " AND (TIPO_DE_US = 7 OR TIPO_DE_US = 9)";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				STATE iluminacion = getState(rs.getString("ILUMINACION"));
				STATE puertas = getState(rs.getString("PUERTAS"));
				STATE presencia = getState(rs.getString("PRESENCIA"));
				espacios.add(new Espacio(rs.getString("ID_UTC"), new Location(new Point(rs.getDouble("LOCATIONX"), rs.getDouble("LOCATIONY")),
						floor, rs.getInt("ID_EDIFICIO")), TYPE.WC, new SensorActuadorBinario(iluminacion),
						new SensorActuadorBinario(puertas), new SensorActuadorBinario(presencia), new SensorActuadorTemperatura(
								new Temperatura(rs.getDouble("TEMPERATURA")), new Temperatura(rs.getDouble("TEMPERATURAOBJETIVO")))));
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error en buscar WCs");
		}
		return espacios;
	}

	@Override
	public List<Espacio> findAulas(int floor) {
		List<Espacio> espacios = new LinkedList<Espacio>();
		try {
			String sql = "SELECT ID_UTC, ST_X(ST_TRANSFORM(the_geom, 23030)) AS LOCATIONX, ST_Y(ST_TRANSFORM(the_geom, 23030)) AS LOCATIONY, ID_EDIFICIO, ILUMINACION,"
					+ " PUERTAS, PRESENCIA, TEMPERATURA, TEMPERATURAOBJETIVO FROM proyecto.espacios WHERE ID_PLANTA = " + floor + " AND TIPO_DE_US = 6";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				STATE iluminacion = getState(rs.getString("ILUMINACION"));
				STATE puertas = getState(rs.getString("PUERTAS"));
				STATE presencia = getState(rs.getString("PRESENCIA"));
				espacios.add(new Espacio(rs.getString("ID_UTC"), new Location(new Point(rs.getDouble("LOCATIONX"), rs.getDouble("LOCATIONY")),
						floor, rs.getInt("ID_EDIFICIO")), TYPE.AULA, new SensorActuadorBinario(iluminacion),
						new SensorActuadorBinario(puertas), new SensorActuadorBinario(presencia), new SensorActuadorTemperatura(
								new Temperatura(rs.getDouble("TEMPERATURA")), new Temperatura(rs.getDouble("TEMPERATURAOBJETIVO")))));
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error en buscar Aulas");
		}
		return espacios;
	}

	@Override
	public Espacio findById(String id) {
		Espacio espacio = null;
		try {
			String sql = "SELECT ID_UTC, TIPO_DE_US, ID_PLANTA, ST_X(ST_TRANSFORM(the_geom, 23030)) AS LOCATIONX, ST_Y(ST_TRANSFORM(the_geom, 23030)) AS LOCATIONY, ID_EDIFICIO,"
					+ " ILUMINACION, PUERTAS, PRESENCIA, TEMPERATURA, TEMPERATURAOBJETIVO FROM proyecto.espacios WHERE ID_UTC = '" + id + "'";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				TYPE tipo = getType(rs.getInt("TIPO_DE_US"));
				STATE iluminacion = getState(rs.getString("ILUMINACION"));
				STATE puertas = getState(rs.getString("PUERTAS"));
				STATE presencia = getState(rs.getString("PRESENCIA"));
				espacio = new Espacio(rs.getString("ID_UTC"), new Location(new Point(rs.getDouble("LOCATIONX"), rs.getDouble("LOCATIONY")), 
						rs.getInt("ID_PLANTA"), rs.getInt("ID_EDIFICIO")), tipo, new SensorActuadorBinario(iluminacion),
						new SensorActuadorBinario(puertas), new SensorActuadorBinario(presencia), new SensorActuadorTemperatura(
								new Temperatura(rs.getDouble("TEMPERATURA")), new Temperatura(rs.getDouble("TEMPERATURAOBJETIVO"))));
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error en buscar por ID");
		}
		return espacio;
	}

	@Override
	public List<Espacio> findAll() {
		List<Espacio> espacios = new LinkedList<Espacio>();
		try {
			String sql = "SELECT ID_UTC, TIPO_DE_US, ID_PLANTA, ST_X(ST_TRANSFORM(the_geom, 23030)) AS LOCATIONX, ST_Y(ST_TRANSFORM(the_geom, 23030)) AS LOCATIONY, ID_EDIFICIO,"
					+ " ILUMINACION, PUERTAS, PRESENCIA, TEMPERATURA, TEMPERATURAOBJETIVO FROM proyecto.espacios";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				TYPE tipo = getType(rs.getInt("TIPO_DE_US"));
				STATE iluminacion = getState(rs.getString("ILUMINACION"));
				STATE puertas = getState(rs.getString("PUERTAS"));
				STATE presencia = getState(rs.getString("PRESENCIA"));
				espacios.add(new Espacio(rs.getString("ID_UTC"), new Location(new Point(rs.getDouble("LOCATIONX"), rs.getDouble("LOCATIONY")), 
						rs.getInt("ID_PLANTA"), rs.getInt("ID_EDIFICIO")), tipo, new SensorActuadorBinario(iluminacion),
						new SensorActuadorBinario(puertas), new SensorActuadorBinario(presencia), new SensorActuadorTemperatura(
								new Temperatura(rs.getDouble("TEMPERATURA")), new Temperatura(rs.getDouble("TEMPERATURAOBJETIVO")))));
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error en buscar todos");
		}
		return espacios;
	}
	
	@Override
	public boolean update(List<Espacio> espacios) {
		boolean returned = false;
		try {
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();
			for (Espacio espacio : espacios) {
				String sql = "UPDATE proyecto.espacios SET ILUMINACION ='"+getString(espacio.lucesEncendidas())+"',"
						+ " PUERTAS ='"+getString(espacio.puertasAbiertas())+"',"
						+ " PRESENCIA ='"+getString(espacio.presenciaEncendida())+"',"
						+ " TEMPERATURA ='"+espacio.temperatura().getTemperature()+"',"
						+ " TEMPERATURAOBJETIVO ='"+espacio.temperaturaObjetivo().getTemperature()+"'"
						+ " WHERE ID_UTC ='"+espacio.getID()+"'";
				stmt.addBatch(sql);
			}
			stmt.executeBatch();
			returned = true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error en actualizar espacios");
			returned = false;
		}
		finally{
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return returned;
	}
	
	@Override
	public boolean updateById(Espacio espacio) {
		int nou = 0;
		String sql = "UPDATE proyecto.espacios SET ILUMINACION ='"+getString(espacio.lucesEncendidas())+"',"
				+ " PUERTAS ='"+getString(espacio.puertasAbiertas())+"',"
				+ " PRESENCIA ='"+getString(espacio.presenciaEncendida())+"',"
				+ " TEMPERATURA ='"+espacio.temperatura().getTemperature()+"',"
				+ " TEMPERATURAOBJETIVO ='"+espacio.temperaturaObjetivo().getTemperature()+"'"
				+ " WHERE ID_UTC ='"+espacio.getID()+"'";
		try {
			Statement stmt = conn.createStatement();
			nou = stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error en actualizar espacio por id=" + espacio.getID());
			return false;
		}
		return nou > 0;
	}

	private STATE getState(String estado) {
		if (estado.equals("Y")) {
			return STATE.ON;
		}
		else {
			return STATE.OFF;
		}
	}
	
	private String getString(boolean estado) {
		if (estado) {
			return "Y";
		}
		else {
			return "N";
		}
	}

	private TYPE getType(int tipo) {
		if (tipo == 6) {
			return TYPE.AULA;
		}
		else if (tipo == 17) {
			return TYPE.DESPACHO;
		}
		else if (tipo == 7 || tipo == 9) {
			return TYPE.WC;
		}
		else if (tipo == 41 || tipo == 44 || tipo == 55){
			return TYPE.LAB;
		}
		else {
			return TYPE.UNKNOWN;
		}
	}
}