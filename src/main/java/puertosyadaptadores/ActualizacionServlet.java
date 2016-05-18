package puertosyadaptadores;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aplicacion.ActualizaEspacio;
import dominio.Espacio;

@WebServlet(value = "/api/actualizacion", name = "ActualizacionServlet")
public class ActualizacionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private boolean luz = false;
	private boolean puertas = false;
	private boolean presencia = false;
	private double temp = -100.0;
	private double tempObj = -100.0;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		handleRequest(req, resp);
	}

	private void handleRequest(HttpServletRequest req, HttpServletResponse resp) {
		String response = null;

		String id = req.getParameter("id");
		String strLuz = req.getParameter("luz");
		String strPuertas = req.getParameter("puertas");
		String strPresencia = req.getParameter("presencia");
		String strTemp = req.getParameter("temperatura");
		String strTempObj = req.getParameter("temperatura_objetivo");

		resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				
		if(peticionCorrecta(id,strLuz,strPuertas,strPresencia,strTemp,strTempObj)) {
			Object[] actualizado = ActualizaEspacio.actualizarEspacio(id,strLuz, luz, strPuertas,
					puertas, strPresencia, presencia, temp, tempObj);
			
			if ((Boolean) actualizado[1]) {
				response = ((Espacio) actualizado[0]).toJSON();
				resp.setStatus(HttpServletResponse.SC_OK);
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
			e.printStackTrace();
		}
	}
	
	private boolean peticionCorrecta(String id,String strLuz,
			String strPuertas,String strPresencia,String strTemp,String strTempObj) {
		try{
			assert id!=null;
		}
		catch(AssertionError ae){
			System.err.println(ae.getMessage());
		}
		
		if (id != null) {
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
			return true;
		}
		return false;
	}
}
