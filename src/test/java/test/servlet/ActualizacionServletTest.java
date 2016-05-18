package test.servlet;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.junit.*;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import puertosyadaptadores.ActualizacionServlet;

/**
 * Tests contra el servlet de actualizacion
 */
public class ActualizacionServletTest {

	private ActualizacionServlet servlet;
	private MockHttpServletRequest req;
	private MockHttpServletResponse resp;

	/**
	 * Setup para los tests del servlet
	 */
	@BeforeClass
	public void setUp() {
		servlet = new ActualizacionServlet();
	}
	
	/**
	 * Setup adicional para los tests del servlet
	 */
	@Before
	public void setUpRequestResponse(){
		req = new MockHttpServletRequest();
		resp = new MockHttpServletResponse();
	}

	/**
	 * Comprueba que la actualizacion de un espacio funciona correctamente
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public void testOK() throws ServletException, IOException {
		String id = "00.180";
		String temp = "26.0";

		req.addParameter("id", id);
		req.addParameter("temperatura_objetivo", temp);

		servlet.doGet(req, resp);

		String respuesta = resp.getContentAsString();

		assertTrue(resp.getStatus() == HttpServletResponse.SC_OK
				&& respuesta != null && !respuesta.isEmpty());
	}

}
