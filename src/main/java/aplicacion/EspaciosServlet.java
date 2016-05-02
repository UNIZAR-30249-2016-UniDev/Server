package aplicacion;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Espacio;
import dominio.EspacioRepository;
import dominio.Constantes.TYPE;
import infraestructura.EspacioRepositoryPostgre;

@WebServlet(value="/espacios", name="EspaciosServlet")
public class EspaciosServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String REQ_ESPACIO = "tipo";
	private static final String REQ_PLANTA = "planta";
	private static EspacioRepository repository = new EspacioRepositoryPostgre();
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		handleRequest(req, resp);
	}
	
	private void handleRequest(HttpServletRequest req, HttpServletResponse resp) {
		String response = null;
		String tipo_espacio = null;
		String strFloor = null;
		int planta = -1;
		
		tipo_espacio = req.getParameter(REQ_ESPACIO);
		strFloor = req.getParameter(REQ_PLANTA);
		
		resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		

		List<Espacio> lista = null;
		
		/* Chequear parametros */
		if (strFloor != null && strFloor.matches("^\\d+$") && tipo_espacio != null) {
			planta = Integer.parseInt(strFloor);
			if (tipo_espacio.equals(TYPE.DESPACHO.toString())) {
					lista = repository.findDespachos(planta);
			}
			else if (tipo_espacio.equals(TYPE.LAB.toString())) {
					lista = repository.findLaboratorios(planta);	
			}
			else if (tipo_espacio.equals(TYPE.WC.toString())) {
		        	lista = repository.findWcs(planta);	
			}
			else if (tipo_espacio.equals(TYPE.AULA.toString())) {
		        	lista = repository.findAulas(planta);
			}
		} else {
				lista = repository.findAll();
		}
		if (lista != null) {
			resp.setStatus(HttpServletResponse.SC_OK);
			response = " ";
			for(Espacio espacio: lista) {
				response += Espacio2Json.espacio2Json(espacio) + ",";
			}
			response = response.substring(0, response.length()-1);
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
