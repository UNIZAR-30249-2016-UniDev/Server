package test.servlet;

import static org.junit.Assert.assertTrue;
import infraestructura.mensajeria.EspaciosServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class EspaciosServletTest {
	
	private EspaciosServlet servlet;
	private MockHttpServletRequest req;
	private MockHttpServletResponse resp;

	/**
	 * Setup para los tests del servlet
	 */
	@Ignore
	@Before
	public void setUp() {
		servlet = new EspaciosServlet();
		req = new MockHttpServletRequest();
		resp = new MockHttpServletResponse();
	}

	@Ignore
	@Test
	public void test_ok() throws ServletException, IOException {
		String edificio = "ADA";
		String tipo = "DESPACHO";
		String planta = "0";
		
		req.addParameter("edificio", edificio);
		req.addParameter("tipo", tipo);
		req.addParameter("planta", planta);

		servlet.doGet(req, resp);

		String respuesta = resp.getContentAsString();

		assertTrue(resp.getStatus() == HttpServletResponse.SC_OK && respuesta != null && !respuesta.isEmpty());
	}

}
