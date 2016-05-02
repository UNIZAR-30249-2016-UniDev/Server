package aplicacion;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Espacio;
import dominio.EspacioRepository;
import infraestructura.EspacioRepositoryPostgre;

@WebServlet(value="/api/identificacion", name="IdServlet")
public class IdServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static EspacioRepository repository = new EspacioRepositoryPostgre();
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		handleRequest(req, resp);
	}

	private void handleRequest(HttpServletRequest req, HttpServletResponse resp) {
		String response = null;
		String id = null;
		
		id = req.getParameter("id");
		
		resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		
		/* Chequear parametros */
		if(id != null){
			Espacio espacio = repository.findById(id);
			
			if(espacio != null){
				resp.setStatus(HttpServletResponse.SC_OK);
				response = Espacio2Json.espacio2Json(espacio);
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
