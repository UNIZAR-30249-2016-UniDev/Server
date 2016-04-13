package infraestructura.persistencia.repositorios;

import infraestructura.services.ConexionBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import dominio.entity.Espacio;
import dominio.repository.EspacioRepository;
import dominio.value_object.Constantes.EDIFICIO;
import dominio.value_object.Constantes.STATE;
import dominio.value_object.Constantes.TYPE;
import dominio.value_object.Location;
import dominio.value_object.Point;
import dominio.value_object.SensorActuadorBinario;
import dominio.value_object.SensorActuadorTemperatura;
import dominio.value_object.Temperatura;

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
			String sql = "SELECT * FROM proyecto.planta_" + floor + "_despacho WHERE ID_EDIFICIO " + " = " + edif
					+ " AND ID_PLANTA = " + floor;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				STATE iluminacion = getState(rs.getString("ILUMINACION"));
				espacios.add(new Espacio(rs.getString("ID_UTC"), new Location(new Point(rs.getDouble("LOCATIONX"), rs.getDouble("LOCATIONY")), floor, edif),
						TYPE.DESPACHO, new SensorActuadorBinario(iluminacion), new SensorActuadorTemperatura(new Temperatura(rs.getDouble("TEMPERATURA")),
								new Temperatura(rs.getDouble("TEMPERATURAOBJETIVO")))));
			}
		} catch (SQLException e) { }
		return espacios;
	}

	@Override
	public List<Espacio> findLaboratorios(int floor, EDIFICIO building) {
		List<Espacio> espacios = new LinkedList<Espacio>();
		try {
			int edif = getBuilding(building);
			String sql = "SELECT * FROM proyecto.planta_" + floor + "_lab WHERE ID_EDIFICIO " + " = " + edif
					+ " AND ID_PLANTA = " + floor;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				STATE iluminacion = getState(rs.getString("ILUMINACION"));
				espacios.add(new Espacio(rs.getString("ID_UTC"), new Location(new Point(rs.getDouble("LOCATIONX"), rs.getDouble("LOCATIONY")), floor, edif),
						TYPE.LAB, new SensorActuadorBinario(iluminacion), new SensorActuadorTemperatura(new Temperatura(rs.getDouble("TEMPERATURA")),
								new Temperatura(rs.getDouble("TEMPERATURAOBJETIVO")))));
			}
		} catch (SQLException e) { }
		return espacios;
	}

	@Override
	public List<Espacio> findWcs(int floor, EDIFICIO building) {
		List<Espacio> espacios = new LinkedList<Espacio>();
		try {
			int edif = getBuilding(building);
			String sql = "SELECT * FROM proyecto.planta_" + floor + "_wc WHERE ID_EDIFICIO " + " = " + edif
					+ " AND ID_PLANTA = " + floor;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				STATE iluminacion = getState(rs.getString("ILUMINACION"));
				espacios.add(new Espacio(rs.getString("ID_UTC"), new Location(new Point(rs.getDouble("LOCATIONX"), rs.getDouble("LOCATIONY")), floor, edif),
						TYPE.WC, new SensorActuadorBinario(iluminacion), new SensorActuadorTemperatura(new Temperatura(rs.getDouble("TEMPERATURA")),
								new Temperatura(rs.getDouble("TEMPERATURAOBJETIVO")))));
			}
		} catch (SQLException e) { }
		return espacios;
	}

	@Override
	public List<Espacio> findAulas(int floor, EDIFICIO building) {
		List<Espacio> espacios = new LinkedList<Espacio>();
		try {
			int edif = getBuilding(building);
			String sql = "SELECT * FROM proyecto.planta_" + floor + "_aula WHERE ID_EDIFICIO " + " = " + edif
					+ " AND ID_PLANTA = " + floor;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				STATE iluminacion = getState(rs.getString("ILUMINACION"));
				espacios.add(new Espacio(rs.getString("ID_UTC"), new Location(new Point(rs.getDouble("LOCATIONX"), rs.getDouble("LOCATIONY")), floor, edif),
						TYPE.AULA, new SensorActuadorBinario(iluminacion), new SensorActuadorTemperatura(new Temperatura(rs.getDouble("TEMPERATURA")),
								new Temperatura(rs.getDouble("TEMPERATURAOBJETIVO")))));
			}
		} catch (SQLException e) { }
		return espacios;
	}

	@Override
	public Espacio finById(String id) {
		Espacio espacio = null;
		try {
			int i = 0;
			boolean encontrado = false;
			Statement stmt = conn.createStatement();
			while (i < 5 && !encontrado) {
				String sql = "SELECT * FROM proyecto.planta_" + i + "_base WHERE ID_UTC = '" + id + "'";
				ResultSet rs = stmt.executeQuery(sql);
				if (rs.next()) {
					TYPE tipo = getType(rs.getString("ID_CENTRO"));
					STATE iluminacion = getState(rs.getString("ILUMINACION"));
					espacio = new Espacio(rs.getString("ID_UTC"), new Location(new Point(rs.getDouble("LOCATIONX"), rs.getDouble("LOCATIONY")),
							rs.getInt("ID_PLANTA"), rs.getInt("ID_EDIFICIO")), tipo, new SensorActuadorBinario(iluminacion),
							new SensorActuadorTemperatura(new Temperatura(rs.getDouble("TEMPERATURA")), new Temperatura(rs.getDouble("TEMPERATURAOBJETIVO"))));
					encontrado = true;
				}
				i++;
			}
		} catch (SQLException e) { }
		return espacio;
	}

	@Override
	public List<Espacio> findAll() {
		List<Espacio> espacios = new LinkedList<Espacio>();
		try {
			Statement stmt = conn.createStatement();
			for (int i = 0; i < 5; i++) {
				String sql = "SELECT * FROM proyecto.planta_" + i + "_base";
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					TYPE tipo = getType(rs.getString("ID_CENTRO"));
					STATE iluminacion = getState(rs.getString("ILUMINACION"));
					espacios.add(new Espacio(rs.getString("ID_UTC"), new Location(new Point(rs.getDouble("LOCATIONX"), rs.getDouble("LOCATIONY")), 
							rs.getInt("ID_PLANTA"), rs.getInt("ID_EDIFICIO")), tipo, new SensorActuadorBinario(iluminacion),
							new SensorActuadorTemperatura(new Temperatura(rs.getDouble("TEMPERATURA")), new Temperatura(rs.getDouble("TEMPERATURAOBJETIVO")))));
				}
			}
		} catch (SQLException e) { }
		return espacios;
	}

	@Override
	public boolean update(List<Espacio> espacios) {			
		for (Espacio espacio : espacios) {
			String sql = "UPDATE proyecto.planta_" + espacio.localizacion().getFloor() + "_base "
					+ "SET ILUMINACION ='"+espacio.lucesEncendidas()+"', TEMPERATURA ='"+espacio.temperatura()+"', TEMPERATURAOBJETIVO='"+espacio.temperaturaObjetivo()+"' "
					+ "WHERE ID_UTC = '"+espacio.getID() + "'";	
			try {
				Statement stmt = conn.createStatement();
				stmt.executeQuery(sql);
			} catch (SQLException e) {
				e.printStackTrace();
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
