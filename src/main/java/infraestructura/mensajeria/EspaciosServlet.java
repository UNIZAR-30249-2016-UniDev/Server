package infraestructura.mensajeria;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/espacios", name="EspaciosServlet")
public class EspaciosServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String REQ_ESPACIO = "tipo";
	private static final String REQ_EDIFICIO = "edificio";
	private static final String REQ_PLANTA = "planta";
	
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
	
	private void handleRequest(HttpServletRequest req, HttpServletResponse resp){
		String response = null;
		String tipo_espacio = null;
		String edificio = null;
		String strFloor = null;
		int planta = -1;
		
		tipo_espacio = req.getParameter(REQ_ESPACIO);
		edificio = req.getParameter(REQ_EDIFICIO);
		strFloor = req.getParameter(REQ_PLANTA);
		
		/* Chequear parametros */
		if (tipo_espacio == null){
			response = "{ }";	// TODO
		}
		
		if(edificio == null){
			response = "{ }";	// TODO
		}
		
		if(strFloor == null){
			response = "{ }";	// TODO
		} else{
			if(strFloor.matches("^\\d+$")){
				// es un entero
				planta = Integer.parseInt(strFloor);
			}
			else{
				response = "{ }";	// TODO
			}
		}
		
		if(response == null){
			response = "{ espacio : " + tipo_espacio + ", edificio : " + edificio + ", planta : " + planta + " }";
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
