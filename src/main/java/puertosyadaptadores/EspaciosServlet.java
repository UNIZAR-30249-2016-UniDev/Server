package puertosyadaptadores;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aplicacion.BuscaEspacio;
import dominio.Espacio;
import dominio.EspacioRepository;

@WebServlet(value="/api/espacios", name="EspaciosServlet")
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

		tipo_espacio = req.getParameter(REQ_ESPACIO);
		strFloor = req.getParameter(REQ_PLANTA);

		resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);


		List<Espacio> lista = null;

		/* Chequear parametros */
		if (strFloor != null && strFloor.matches("^\\d+$") && tipo_espacio != null) {
			lista = BuscaEspacio.buscarEspacio(Integer.parseInt(strFloor), tipo_espacio);
		} else {
			lista = repository.findAll();
		}
		
		try{
			assert lista!=null;
		}
		catch(AssertionError ae){
			System.err.println(ae.getMessage());
		}
		if (lista != null) {
			resp.setStatus(HttpServletResponse.SC_OK);
			response = "{ \"espacios\" : [ ";
			for(Espacio espacio: lista) {
				response += espacio.toJSON() + ",";
			}
			response = response.substring(0, response.length()-1) + "] }";
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
			e.printStackTrace();
		}
	}
}
