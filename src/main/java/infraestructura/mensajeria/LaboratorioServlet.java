package infraestructura.mensajeria;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/laboratorios", name="LaboratorioServlet")
public class LaboratorioServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		pw.println("<HTML><HEAD><TITLE>PRUEBA</TITLE></HEAD>");
		pw.println("<BODY>");
		pw.println("<H2>PRUEBA GET LABORATORIOS</H2>");
		pw.println("</BODY></HTML>");
		pw.close();
		//response(resp,Espacio2Json.espacio2Json(new Espacio(null, null, null, null)));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req,resp); //En principio todas seran GET
	}
}
