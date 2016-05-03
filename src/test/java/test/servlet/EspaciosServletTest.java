package test.servlet;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.junit.*;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import puertosyadaptadores.EspaciosServlet;

public class EspaciosServletTest {
	
	private EspaciosServlet servlet;
	private MockHttpServletRequest req;
	private MockHttpServletResponse resp;

	/**
	 * Setup para los tests del servlet
	 */

	@Before
	public void setUp() {
		servlet = new EspaciosServlet();
		req = new MockHttpServletRequest();
		resp = new MockHttpServletResponse();
	}

	@Test
	public void testOK() throws ServletException, IOException {
		String tipo = "DESPACHO";
		String planta = "0";

		req.addParameter("tipo", tipo);
		req.addParameter("planta", planta);

		servlet.doGet(req, resp);

		String respuesta = resp.getContentAsString();

		assertTrue(resp.getStatus() == HttpServletResponse.SC_OK && respuesta != null && !respuesta.isEmpty());
	}

}
