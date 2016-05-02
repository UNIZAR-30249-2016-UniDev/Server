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
import dominio.Temperatura;
import infraestructura.EspacioRepositoryPostgre;

@WebServlet(value = "/api/actualizacion", name = "ActualizacionServlet")
public class ActualizacionServlet extends HttpServlet {

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
		String strLuz = null;
		String strPuertas = null;
		String strPresencia = null;
		String strTemp = null;
		String strTempObj = null;
		
		boolean luz = false;
		boolean puertas = false;
		boolean presencia = false;
		double temp = -100.0;
		double tempObj = -100.0;
		
		boolean error = true;

		id = req.getParameter("id");
		strLuz = req.getParameter("luz");
		strPuertas = req.getParameter("puertas");
		strPresencia = req.getParameter("presencia");
		strTemp = req.getParameter("temperatura");
		strTempObj = req.getParameter("temperatura_objetivo");

		resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);

		/* Chequear parametros */
		if (id != null) {
			error = false;
			if (strLuz != null) {
				luz = Boolean.valueOf(strLuz);
			}
			if (strPuertas != null) {
				puertas = Boolean.valueOf(strPuertas);
			}
			if (strPresencia != null) {
				presencia = Boolean.valueOf(strPresencia);
			}
			if (strTemp != null) {
				temp = Double.parseDouble(strTemp);
			}
			if (strTempObj != null) {
				tempObj = Double.parseDouble(strTempObj);
			}
		}
				
		if(!error) {
			Espacio espacio = repository.findById(id);
			espacio = actualizarEspacio(espacio, strLuz, luz, strPuertas,
					puertas, strPresencia, presencia, temp, tempObj);
			boolean actualizado = repository.updateById(espacio);
			
			if (actualizado) {
				response = Espacio2Json.espacio2Json(espacio);
				resp.setStatus(HttpServletResponse.SC_OK);
			}
		}
		setResponse(response, resp);
	}

	private Espacio actualizarEspacio(Espacio espacio, String strLuz, boolean luz,
			String strPuertas, boolean puertas, String strPresencia, boolean presencia,
			double temperatura, double calefaccion) {
		if(espacio == null) {
			return null;
		}
		else {
			if (strLuz != null) {
				if (luz) {
					espacio.encenderLuces();
				} else {
					espacio.apagarLuces();
				}
			}
			if (strPuertas != null) {
				if (puertas) {
					espacio.abrirPuertas();
				} else {
					espacio.cerrarPuertas();
				}
			}
			if (strPresencia != null) {
				if (presencia) {
					espacio.encenderPresencia();
				} else {
					espacio.apagarPresencia();
				}
			}
			if (temperatura != -100.0) {
				espacio.cambiarTemperatura(new Temperatura(temperatura));
			}
			if (calefaccion != -100.0) {
				espacio.temperaturaObjetivo(new Temperatura(calefaccion));
			}
		}		
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
