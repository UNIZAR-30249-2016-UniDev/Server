package test.servlet;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.junit.*;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import puertosyadaptadores.EspaciosServlet;

/**
 * Tests contra el servlet de espacios
 */
public class EspaciosServletTest {

	private static EspaciosServlet servlet;
	private MockHttpServletRequest req;
	private MockHttpServletResponse resp;

	/**
	 * Setup para los tests del servlet
	 */
	@BeforeClass
	public static void setUp() {
		servlet = new EspaciosServlet();
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
	 * Comprueba que se puede buscar espacios con los parametros tipo y planta
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public void testOK() throws ServletException, IOException {
		String tipo = "DESPACHO";
		String planta = "0";

		req.addParameter("tipo", tipo);
		req.addParameter("planta", planta);

		servlet.doGet(req, resp);

		String respuesta = resp.getContentAsString();

		assertTrue(resp.getStatus() == HttpServletResponse.SC_OK
				&& respuesta != null && !respuesta.isEmpty());
	}
	
	/**
	 * Comprueba que se produce un error al buscar con parametros incorrectos
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public void testFalso() throws ServletException, IOException {
		String tipo = "hola";
		String planta = "100";

		req.addParameter("tipo", tipo);
		req.addParameter("planta", planta);

		servlet.doGet(req, resp);

		assertTrue(resp.getStatus() == HttpServletResponse.SC_BAD_REQUEST);
	}
	
	/**
	 * Comprueba que se pproduce un error al buscar sin parametros
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public void testNull() throws ServletException, IOException {
		servlet.doGet(req, resp);

		assertTrue(resp.getStatus() == HttpServletResponse.SC_BAD_REQUEST);
	}

}
