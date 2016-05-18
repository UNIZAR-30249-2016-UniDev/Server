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

	private static ActualizacionServlet servlet;
	private MockHttpServletRequest req;
	private MockHttpServletResponse resp;

	/**
	 * Setup para los tests del servlet
	 */
	@BeforeClass
	public static void setUp() {
		servlet = new ActualizacionServlet();
	}

	/**
	 * Setup adicional para los tests del servlet
	 */
	@Before
	public void setUpRequestResponse() {
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

	/**
	 * Comprueba que la actualizacion de un espacio con parametros incorrectos
	 * devuelve error
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public void testFalso() throws ServletException, IOException {
		String id = "hola";
		String temp = "26.0";

		req.addParameter("id", id);
		req.addParameter("temperatura_objetivo", temp);

		servlet.doGet(req, resp);

		assertTrue(resp.getStatus() == HttpServletResponse.SC_BAD_REQUEST);
	}

	/**
	 * Comprueba que la actualizacion de un espacio con parametros null devuelve
	 * todos los espacios
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public void testNull() throws ServletException, IOException {
		servlet.doGet(req, resp);

		String respuesta = resp.getContentAsString();

		assertTrue(resp.getStatus() == HttpServletResponse.SC_OK
				&& respuesta != null && !respuesta.isEmpty());
	}

}
