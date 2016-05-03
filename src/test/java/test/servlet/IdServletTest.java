package test.servlet;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.junit.*;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import puertosyadaptadores.IdServlet;

public class IdServletTest {
	
	private IdServlet servlet;
	private MockHttpServletRequest req;
	private MockHttpServletResponse resp;

	/**
	 * Setup para los tests del servlet
	 */

	@Before
	public void setUp() {
		servlet = new IdServlet();
		req = new MockHttpServletRequest();
		resp = new MockHttpServletResponse();
	}

	@Test
	public void testOK() throws ServletException, IOException {
		String id = "00.180";

		req.addParameter("id", id);
		
		servlet.doGet(req, resp);

		String respuesta = resp.getContentAsString();

		assertTrue(resp.getStatus() == HttpServletResponse.SC_OK && respuesta != null && !respuesta.isEmpty());
	}
	
	@Test
	public void testIdFalsa() throws ServletException, IOException {
		String id = "hola";

		req.addParameter("id", id);
		
		servlet.doGet(req, resp);

		assertTrue(resp.getStatus() == HttpServletResponse.SC_BAD_REQUEST);
	}
	
	@Test
	public void testIdNull() throws ServletException, IOException {
		String id = null;

		req.addParameter("id", id);
		
		servlet.doGet(req, resp);

		assertTrue(resp.getStatus() == HttpServletResponse.SC_BAD_REQUEST);
	}

}
