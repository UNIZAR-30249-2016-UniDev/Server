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

public class EspacioRepositoryPostgre extends EspacioRepository {

	private Connection conn = null;

	public EspacioRepositoryPostgre() {
		ConexionBD.iniciarConexion();
		this.conn = ConexionBD.getConexion();
	}

	@Override
	public List<Espacio> findDespachos(int floor, String building) {
		List<Espacio> espacios = new LinkedList<Espacio>();
		try {
			String codigo_edificio = edificioToCodigoUTC(building) + floor;
			String sql = "SELECT * FROM TB_ESPACIOS WHERE ID_ESPACIO " + "LIKE '"
					+ codigo_edificio + "%' AND ID_CENTRO LIKE 'DESPACHO%'";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
			  espacios.add(new Espacio(rs.getString("ejemplo1"), rs.getInt("ejemplo2")));
			}
		} catch (SQLException e) { }
		return espacios;
	}

	@Override
	public List<Espacio> findLaboratorios(int floor, String building) {
		List<Espacio> espacios = new LinkedList<Espacio>();

		String codigo_edificio = edificioToCodigoUTC(building) + floor;
		String sql = "SELECT * FROM TB_ESPACIOS WHERE ID_ESPACIO " + "LIKE '"
				+ codigo_edificio + "%' AND ID_CENTRO LIKE 'LABORATORIO%'";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
		  espacios.add(new Espacio(rs.getString("ejemplo1"), rs.getInt("ejemplo2")));
		}
		return espacios;
	}

	@Override
	public List<Espacio> findWcs(int floor, String building) {
		List<Espacio> espacios = new LinkedList<Espacio>();

		String codigo_edificio = edificioToCodigoUTC(building) + floor;
		String sql = "SELECT * FROM TB_ESPACIOS WHERE ID_ESPACIO " + "LIKE '"
				+ codigo_edificio + "%' AND ID_CENTRO LIKE 'BA�O%'";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
		  espacios.add(new Espacio(rs.getString("ejemplo1"), rs.getInt("ejemplo2")));
		}
		return espacios;
	}

	@Override
	public List<Espacio> findAulas(int floor, String building) {
		List<Espacio> espacios = new LinkedList<Espacio>();

		String codigo_edificio = edificioToCodigoUTC(building) + floor;
		String sql = "SELECT * FROM TB_ESPACIOS WHERE ID_ESPACIO " + "LIKE '"
				+ codigo_edificio + "%' AND ID_CENTRO LIKE 'AULA%'";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
		  espacios.add(new Espacio(rs.getString("ejemplo1"), rs.getInt("ejemplo2")));
		}
		return espacios;
	}

	@Override
	public Espacio finById(String id) {
		Espacio espacio = null;
		String sql = "SELECT * FROM TB_ESPACIOS WHERE ID_ESPACIO = '" + id + "'";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			espacio = new Espacio(rs.getString("ejemplo1"), rs.getInt("ejemplo2"));
		}
		return espacio;
	}

	@Override
	public List<Espacio> findAll() {
		List<Espacio> espacios = new LinkedList<Espacio>();
		String sql = "SELECT * FROM TB_ESPACIOS";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			espacios.add(new Espacio(rs.getString("ejemplo1"), rs.getInt("ejemplo2")));
		}
		return espacios;
	}

	private String edificioToCodigoUTC(String building) {
		String codigoEdificio = "";
		if (building.equals(EDIFICIO.TQ.toString())) {
			codigoEdificio = "CRE.1065.0";
		} else if (building.equals(EDIFICIO.ADA.toString())) {
			codigoEdificio = "CRE.1200.0";
		} else if (building.equals(EDIFICIO.BETAN.toString())) {
			codigoEdificio = "CRE.1201.0";
		}
		return codigoEdificio;
	}
}
