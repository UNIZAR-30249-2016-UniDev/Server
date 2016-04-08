package infraestructura.persistencia.repositorios;

import infraestructura.services.ConexionBD;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

import dominio.entity.Espacio;
import dominio.repository.EspacioRepository;

public class EspacioRepositoryImpl extends EspacioRepository {

	private Connection con = null;

	public EspacioRepositoryImpl() {
		ConexionBD.iniciarConexion();
		this.con = ConexionBD.getConexion();
	}

	@Override
	public List<Espacio> findDespachos(int floor, EDIFICIO building) {
		List<Espacio> espacios = new LinkedList<Espacio>();

		String codigo_edificio = edificioToCodigoUTC(building) + floor;
		String sql = "SELECT * FROM TB_ESPACIOS WHERE ID_ESPACIO " + "LIKE '"
				+ codigo_edificio + "%' AND ID_CENTRO LIKE 'DESPACHO%'";

		return espacios;
	}

	@Override
	public List<Espacio> findLaboratorios(int floor, EDIFICIO building) {
		List<Espacio> espacios = new LinkedList<Espacio>();

		String codigo_edificio = edificioToCodigoUTC(building) + floor;
		String sql = "SELECT * FROM TB_ESPACIOS WHERE ID_ESPACIO " + "LIKE '"
				+ codigo_edificio + "%' AND ID_CENTRO LIKE 'LABORATORIO%'";

		return espacios;
	}

	@Override
	public List<Espacio> findWcs(int floor, EDIFICIO building) {
		List<Espacio> espacios = new LinkedList<Espacio>();

		String codigo_edificio = edificioToCodigoUTC(building) + floor;
		String sql = "SELECT * FROM TB_ESPACIOS WHERE ID_ESPACIO " + "LIKE '"
				+ codigo_edificio + "%' AND ID_CENTRO LIKE 'BA�O%'";

		return espacios;
	}

	@Override
	public List<Espacio> findAulas(int floor, EDIFICIO building) {
		List<Espacio> espacios = new LinkedList<Espacio>();

		String codigo_edificio = edificioToCodigoUTC(building) + floor;
		String sql = "SELECT * FROM TB_ESPACIOS WHERE ID_ESPACIO " + "LIKE '"
				+ codigo_edificio + "%' AND ID_CENTRO LIKE 'AULA%'";

		return espacios;
	}

	@Override
	public Espacio finById(String id) {
		String sql = "SELECT * FROM TB_ESPACIOS WHERE ID_ESPACIO = '" + id
				+ "'";
		return null;
	}

	@Override
	public List<Espacio> findAll() {
		List<Espacio> espacios = new LinkedList<Espacio>();

		String sql = "SELECT * FROM TB_ESPACIOS";

		return espacios;
	}

	private String edificioToCodigoUTC(EDIFICIO building) {
		String codigoEdificio = "";
		if (building.equals("TQ")) {
			codigoEdificio = "CRE.1065.0";
		} else if (building.equals("ADA")) {
			codigoEdificio = "CRE.1200.0";
		} else if (building.equals("BETAN")) {
			codigoEdificio = "CRE.1201.0";
		}
		return codigoEdificio;
	}
}
