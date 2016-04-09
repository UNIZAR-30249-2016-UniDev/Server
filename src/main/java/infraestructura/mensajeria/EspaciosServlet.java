package infraestructura.mensajeria;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.entity.Espacio;
import infraestructura.persistencia.repositorios.EspacioRepositoryPostgre;
import infraestructura.services.Espacio2Json;

@WebServlet(value="/espacios", name="EspaciosServlet")
public class EspaciosServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String REQ_ESPACIO = "tipo";
	private static final String REQ_EDIFICIO = "edificio";
	private static final String REQ_PLANTA = "planta";
	private static EspacioRepositoryPostgre repository = new EspacioRepositoryPostgre();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		handleRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
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
			// es un entero
			planta = Integer.parseInt(strFloor);
			List<Espacio> lista = null;
			switch (tipo_espacio) {
				case "despachos":
					lista = repository.findDespachos(planta, edificio);
			        break;
		        case "laboratorios":
		        	lista = repository.findLaboratorios(planta, edificio);	
		        	break;
		        case "wcs":
		        	lista = repository.findWcs(planta, edificio);	
		        	break;
		        case "aulas":
		        	lista = repository.findAulas(planta, edificio);	
		        	break;
		        case "todo":
		        	lista = repository.findAll(planta, edificio);	
		        	break;
			}
			if (lista != null) {
				resp.setStatus(HttpServletResponse.SC_OK);
				response = "[";
				for(Espacio espacio: lista) {
					response += Espacio2Json.espacio2Json(espacio) + ",";
				}
				response = response.substring(0, response.length()-1) + "]";
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
}
