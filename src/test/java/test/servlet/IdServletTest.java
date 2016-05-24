package test.servlet;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.junit.*;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import puertosyadaptadores.IdServlet;

/**
 * Tests contra el servlet de identificacion
 */
public class IdServletTest {

	private static IdServlet servlet;
	private MockHttpServletRequest req;
	private MockHttpServletResponse resp;

	/**
	 * Setup para los tests del servlet
	 */
	@BeforeClass
	public static void setUp() {
		servlet = new IdServlet();
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
	 * Comprueba que se puede obtener informacion de un espacio dado su
	 * identificador
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public void testOK() throws ServletException, IOException {
		String id = "00.180";

		req.addParameter("id", id);

		servlet.doGet(req, resp);

		String respuesta = resp.getContentAsString();

		assertTrue(resp.getStatus() == HttpServletResponse.SC_OK
				&& respuesta != null && !respuesta.isEmpty());
	}

	/**
	 * Comprueba que se devuelve un codigo de respuesta incorrecto al buscar un
	 * identificador no existente
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public void testIdFalsa() throws ServletException, IOException {
		String id = "hola";

		req.addParameter("id", id);

		servlet.doGet(req, resp);

		assertTrue(resp.getStatus() == HttpServletResponse.SC_BAD_REQUEST);
	}

	/**
	 * Comprueba que se devuelve un codigo de respuesta incorrecto al buscar un
	 * identificador nulo
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public void testIdNull() throws ServletException, IOException {
		servlet.doGet(req, resp);

		assertTrue(resp.getStatus() == HttpServletResponse.SC_BAD_REQUEST);
	}

}
