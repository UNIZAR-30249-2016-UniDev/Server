package aplicacion;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Espacio;
import dominio.Location;
import dominio.Point;
import dominio.SensorActuadorBinario;
import dominio.SensorActuadorTemperatura;
import dominio.Temperatura;
import dominio.Constantes.STATE;
import dominio.Constantes.TYPE;

@WebServlet(value="/prueba", name="PruebaServlet")
public class PruebaServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		handleRequest(req, resp);
	}

	private void handleRequest(HttpServletRequest req, HttpServletResponse resp) {
		String response = null;

		resp.setStatus(HttpServletResponse.SC_OK);
		response = "{" + Espacio2Json.espacio2Json(new Espacio("ID_UTC", new Location(new Point(2.0, 2.0), 
				1, 1), TYPE.LAB, new SensorActuadorBinario(STATE.ON),
				new SensorActuadorTemperatura(new Temperatura(20.0), new Temperatura(22.0)))) + "}";

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
