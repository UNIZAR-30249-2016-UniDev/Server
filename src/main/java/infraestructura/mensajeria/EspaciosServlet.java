package infraestructura.mensajeria;

import infraestructura.persistencia.repositorios.EspacioRepositoryPostgre;
import infraestructura.services.Espacio2Json;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.entity.Espacio;
import dominio.repository.EspacioRepository;
import dominio.value_object.Constantes.EDIFICIO;
import dominio.value_object.Constantes.TYPE;

@WebServlet(value="/espacios", name="EspaciosServlet")
public class EspaciosServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String REQ_ESPACIO = "tipo";
	private static final String REQ_EDIFICIO = "edificio";
	private static final String REQ_PLANTA = "planta";
	private static EspacioRepository repository = new EspacioRepositoryPostgre();
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		handleRequest(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		handleRequest(req, resp);
	}
	
	private void handleRequest(HttpServletRequest req, HttpServletResponse resp) {
		String response = null;
		String tipo_espacio = null;
		String edificio = null;
		String strFloor = null;
		int planta = -1;
		
		tipo_espacio = req.getParameter(REQ_ESPACIO);
		edificio = req.getParameter(REQ_EDIFICIO);
		strFloor = req.getParameter(REQ_PLANTA);
		
		resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		
		/* Chequear parametros */
		if ((tipo_espacio != null) && (edificio != null) && (strFloor != null)
				&& strFloor.matches("^\\d+$")) {
			EDIFICIO building = getEdificio(edificio);
			planta = Integer.parseInt(strFloor);
			List<Espacio> lista = null;
			if (tipo_espacio.equals(TYPE.DESPACHO.toString())) {
					lista = repository.findDespachos(planta, building);
			}
			else if (tipo_espacio.equals(TYPE.LAB.toString())) {
					lista = repository.findLaboratorios(planta, building);	
			}
			else if (tipo_espacio.equals(TYPE.WC.toString())) {
		        	lista = repository.findWcs(planta, building);	
			}
			else if (tipo_espacio.equals(TYPE.AULA.toString())) {
		        	lista = repository.findAulas(planta, building);	
			}
			if (lista != null) {
				resp.setStatus(HttpServletResponse.SC_OK);
				response = "{ espacios : [ ";
				for(Espacio espacio: lista) {
					response += Espacio2Json.espacio2Json(espacio) + ",";
				}
				response = response.substring(0, response.length()-1) + "] }";
			}
		}	
		setResponse(response, resp);
	}

	private void setResponse(String response, HttpServletResponse resp) {
		resp.setContentType("application/json");
		try {
			PrintWriter out = resp.getWriter();
			out.print(response);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private EDIFICIO getEdificio(String edificio) {
		if (edificio.equals("ADA")) {
			return EDIFICIO.ADA;
		}
		else if (edificio.equals("TQ")) {
			return EDIFICIO.TQ;
		}
		else if (edificio.equals("BETAN")) {
			return EDIFICIO.BETAN;
		}
		else {
			return EDIFICIO.UNKNOWN;
		}
	}
}
