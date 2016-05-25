package puertosyadaptadores;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aplicacion.GestionaEspacio;

/**
 * Servlet para abrir todos los espacios de un edificio y planta
 */
@WebServlet(value = "/api/abrir", name = "AbrirServlet")
public class AbrirTodosServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

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
		String response = "Se han pasado mal los parámetros";
		String planta = null;
		String edificio = null;

		edificio = req.getParameter("edificio");
		planta = req.getParameter("planta");

		resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);

		/* Chequear parametros */
		if (edificio != null && planta != null) {
			response = edificio+" "+planta;
			/*boolean ok = GestionaEspacio.abrirTodosEspacios(Integer.parseInt(edificio), Integer.parseInt(planta));
			if (ok) {
				resp.setStatus(HttpServletResponse.SC_OK);
				response = "Operación realizada correctamente";
			}*/
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
		resp.setContentType("text/plain");
		try {
			PrintWriter out = resp.getWriter();
			out.print(response);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
