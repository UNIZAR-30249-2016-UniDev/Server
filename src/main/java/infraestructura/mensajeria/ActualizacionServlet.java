package infraestructura.mensajeria;

import infraestructura.persistencia.repositorios.EspacioRepositoryPostgre;
import infraestructura.services.Espacio2Json;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.entity.Espacio;
import dominio.repository.EspacioRepository;
import dominio.value_object.Temperatura;

@WebServlet(value = "/actualizacion", name = "ActualizacionServlet")
public class ActualizacionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
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
		String id = null;
		String strLuz = null;
		String strTemp = null;
		String strTempObj = null;
		
		boolean luz = false;
		int temp = -1;
		int tempObj = -1;
		
		boolean error = true;

		id = req.getParameter("id");
		strLuz = req.getParameter("luz");
		strTemp = req.getParameter("temperatura");
		strTempObj = req.getParameter("calefaccion");

		resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);

		/* Chequear parametros */
		if (id != null && strLuz != null && strTemp != null
				&& strTempObj != null && strLuz.matches("^\\d$")
				&& strTemp.matches("^\\d+$") && strTempObj.matches("^\\d+$")) {
			temp = Integer.parseInt(strTemp);
			tempObj = Integer.parseInt(strTempObj);
			
			int intLuz = Integer.parseInt(strLuz);
			if(intLuz == 0){
				luz = false;
				error = false;
			}
			else if(intLuz == 1){
				luz = true;
				error = false;
			}
			else{
				// error
				error = true;
			}
		}
		
		if(!error){
			Espacio espacio = repository.finById(id);
			espacio = actualizarEspacio(espacio, luz, temp, tempObj);
			List<Espacio> espacios = new LinkedList<Espacio>();
			espacios.add(espacio);
			
			repository.update(espacios);
			
			espacio = repository.finById(id);

			if (espacio != null) {
				resp.setStatus(HttpServletResponse.SC_OK);
				response = "{" + Espacio2Json.espacio2Json(espacio) + "}";
			}
		}
		setResponse(response, resp);
	}

	private Espacio actualizarEspacio(Espacio espacio, boolean luz, int temperatura,
			int calefaccion) {
		if(luz){
			espacio.encenderLuces();
		}
		else{
			espacio.apagarLuces();
		}
		
		espacio.cambiarTemperatura(new Temperatura(temperatura));
		espacio.temperaturaObjetivo(new Temperatura(calefaccion));
		
		return espacio;
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
