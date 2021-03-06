package puertosyadaptadores;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Espacio;
import dominio.EspacioRepository;

/**
 * Servlet de identificacion de espacios
 */
@WebServlet(value = "/api/identificacion", name = "IdServlet")
public class IdServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static EspacioRepository repository = new EspacioRepositoryPostgre();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		handleRequest(req, resp);
	}

	/**
	 * Maneja la respuesta al cliente
	 * 
	 * @param req
	 *            request
	 * @param resp
	 *            response
	 */
	private void handleRequest(HttpServletRequest req, HttpServletResponse resp) {
		String response = null;
		String id = null;

		id = req.getParameter("id");

		resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);

		/* Chequear parametros */
		try {
			assert id != null;
		} catch (AssertionError ae) {
			System.err.println(ae.getMessage());
		}
		if (id != null) {
			Espacio espacio = repository.findById(id);
			if (espacio != null) {
				resp.setStatus(HttpServletResponse.SC_OK);
				response = espacio.toJSON();
			}
		}
		setResponse(response, resp);
	}

	/**
	 * Agrega una respuesta a la response
	 * 
	 * @param response
	 *            respuesta
	 * @param resp
	 *            response
	 */
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
