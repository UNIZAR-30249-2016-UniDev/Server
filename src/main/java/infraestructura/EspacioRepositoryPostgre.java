package infraestructura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import dominio.Espacio;
import dominio.EspacioRepository;
import dominio.Location;
import dominio.Point;
import dominio.SensorActuadorBinario;
import dominio.SensorActuadorTemperatura;
import dominio.Temperatura;
import dominio.Constantes.EDIFICIO;
import dominio.Constantes.STATE;
import dominio.Constantes.TYPE;

public class EspacioRepositoryPostgre extends EspacioRepository {

	private Connection conn = null;

	public EspacioRepositoryPostgre() {
		ConexionBD.iniciarConexion();
		this.conn = ConexionBD.getConexion();
	}

	@Override
	public List<Espacio> findDespachos(int floor, EDIFICIO building) {
		List<Espacio> espacios = new LinkedList<Espacio>();
		try {
			int edif = getBuilding(building);
			String sql = "SELECT * FROM proyecto.espacios WHERE ID_EDIFICIO " + " = " + edif
					+ " AND ID_PLANTA = " + floor + " AND ID_CENTRO LIKE 'DESPACHO%'";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				STATE iluminacion = getState(rs.getString("ILUMINACION"));
				espacios.add(new Espacio(rs.getString("ID_UTC"), new Location(new Point(rs.getDouble("LOCATIONX"), rs.getDouble("LOCATIONY")), floor, edif),
						TYPE.DESPACHO, new SensorActuadorBinario(iluminacion), new SensorActuadorTemperatura(new Temperatura(rs.getDouble("TEMPERATURA")),
								new Temperatura(rs.getDouble("TEMPERATURAOBJETIVO")))));
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error en buscar Despachos");
		}
		return espacios;
	}

	@Override
	public List<Espacio> findLaboratorios(int floor, EDIFICIO building) {
		List<Espacio> espacios = new LinkedList<Espacio>();
		try {
			int edif = getBuilding(building);
			String sql = "SELECT * FROM proyecto.espacios WHERE ID_EDIFICIO " + " = " + edif
					+ " AND ID_PLANTA = " + floor + " AND ID_CENTRO LIKE 'L.%' OR ID_CENTRO LIKE 'LABORATORIO%'";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				STATE iluminacion = getState(rs.getString("ILUMINACION"));
				espacios.add(new Espacio(rs.getString("ID_UTC"), new Location(new Point(rs.getDouble("LOCATIONX"), rs.getDouble("LOCATIONY")), floor, edif),
						TYPE.LAB, new SensorActuadorBinario(iluminacion), new SensorActuadorTemperatura(new Temperatura(rs.getDouble("TEMPERATURA")),
								new Temperatura(rs.getDouble("TEMPERATURAOBJETIVO")))));
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error en buscar Laboratorios");
		}
		return espacios;
	}

	@Override
	public List<Espacio> findWcs(int floor, EDIFICIO building) {
		List<Espacio> espacios = new LinkedList<Espacio>();
		try {
			int edif = getBuilding(building);
			String sql = "SELECT * FROM proyecto.espacios WHERE ID_EDIFICIO " + " = " + edif
					+ " AND ID_PLANTA = " + floor + " AND ID_CENTRO LIKE 'WC%'";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				STATE iluminacion = getState(rs.getString("ILUMINACION"));
				espacios.add(new Espacio(rs.getString("ID_UTC"), new Location(new Point(rs.getDouble("LOCATIONX"), rs.getDouble("LOCATIONY")), floor, edif),
						TYPE.WC, new SensorActuadorBinario(iluminacion), new SensorActuadorTemperatura(new Temperatura(rs.getDouble("TEMPERATURA")),
								new Temperatura(rs.getDouble("TEMPERATURAOBJETIVO")))));
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error en buscar WCs");
		}
		return espacios;
	}

	@Override
	public List<Espacio> findAulas(int floor, EDIFICIO building) {
		List<Espacio> espacios = new LinkedList<Espacio>();
		try {
			int edif = getBuilding(building);
			String sql = "SELECT * FROM proyecto.espacios WHERE ID_EDIFICIO " + " = " + edif
					+ " AND ID_PLANTA = " + floor + " AND ID_CENTRO LIKE 'AULA%'";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				STATE iluminacion = getState(rs.getString("ILUMINACION"));
				espacios.add(new Espacio(rs.getString("ID_UTC"), new Location(new Point(rs.getDouble("LOCATIONX"), rs.getDouble("LOCATIONY")), floor, edif),
						TYPE.AULA, new SensorActuadorBinario(iluminacion), new SensorActuadorTemperatura(new Temperatura(rs.getDouble("TEMPERATURA")),
								new Temperatura(rs.getDouble("TEMPERATURAOBJETIVO")))));
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error en buscar Aulas");
		}
		return espacios;
	}

	@Override
	public Espacio finById(String id) {
		Espacio espacio = null;
		try {
			String sql = "SELECT * FROM proyecto.espacios WHERE ID_UTC = '" + id + "'";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				TYPE tipo = getType(rs.getString("ID_CENTRO"));
				STATE iluminacion = getState(rs.getString("ILUMINACION"));
				espacio = new Espacio(rs.getString("ID_UTC"), new Location(new Point(rs.getDouble("LOCATIONX"), rs.getDouble("LOCATIONY")), 
						rs.getInt("ID_PLANTA"), rs.getInt("ID_EDIFICIO")), tipo, new SensorActuadorBinario(iluminacion),
						new SensorActuadorTemperatura(new Temperatura(rs.getDouble("TEMPERATURA")), new Temperatura(rs.getDouble("TEMPERATURAOBJETIVO"))));
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
			String sql = "SELECT * FROM proyecto.espacios";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery(sql);
			while (rs.next()) {
				TYPE tipo = getType(rs.getString("ID_CENTRO"));
				STATE iluminacion = getState(rs.getString("ILUMINACION"));
				espacios.add(new Espacio(rs.getString("ID_UTC"), new Location(new Point(rs.getDouble("LOCATIONX"), rs.getDouble("LOCATIONY")), 
						rs.getInt("ID_PLANTA"), rs.getInt("ID_EDIFICIO")), tipo, new SensorActuadorBinario(iluminacion),
						new SensorActuadorTemperatura(new Temperatura(rs.getDouble("TEMPERATURA")), new Temperatura(rs.getDouble("TEMPERATURAOBJETIVO")))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error en buscar todos");
		}
		return espacios;
	}
	
	@Override
	public boolean update(List<Espacio> espacios) {			
		for (Espacio espacio : espacios) {
			String sql = "UPDATE proyecto.espacio SET ILUMINACION ='"+espacio.lucesEncendidas()+"', TEMPERATURA ='"
					+espacio.temperatura()+"', TEMPERATURAOBJETIVO='"+espacio.temperaturaObjetivo()+"' "
					+ "WHERE ID_UTC = '"+espacio.getID() + "'";	
			try {
				Statement stmt = conn.createStatement();
				stmt.executeQuery(sql);
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error en actualizar espacios");
				return false;
			}
		}
		return true;
	}

	private int getBuilding(EDIFICIO building) {
		if (building.toString().equals("ADA")) {
			return 1;
		}
		else if (building.toString().equals("TQ")) {
			return 2;
		}
		else if (building.toString().equals("BETAN")) {
			return 3;
		}
		else {
			return 0;
		}
	}

	private STATE getState(String estado) {
		if (estado.equals("Y")) {
			return STATE.ON;
		}
		else {
			return STATE.OFF;
		}
	}

	private TYPE getType(String tipo) {
		if (tipo.contains("AULA") || tipo.contains("SEMINARIO")) {
			return TYPE.AULA;
		}
		else if (tipo.contains("DESPACHO")) {
			return TYPE.DESPACHO;
		}
		else if (tipo.contains("WC")) {
			return TYPE.WC;
		}
		else if (tipo.contains("LABORATORIO") || tipo.contains("L.")){
			return TYPE.LAB;
		}
		else {
			return TYPE.UNKNOWN;
		}
	}
}
