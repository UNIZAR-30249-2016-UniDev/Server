package test.bd;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.*;

import dominio.Espacio;
import dominio.EspacioRepository;
import infraestructura.EspacioRepositoryPostgre;

public class BDTest {
	
	EspacioRepository prueba;
	
	@Before
	public void setUp() {
		prueba = new EspacioRepositoryPostgre();
	}

	@Test
	@Ignore
	public void testFindDespachos() {
		List<Espacio> espacios = prueba.findDespachos(0);
		assertEquals(espacios.get(1).esDespacho(),true);
	}
	
	@Test
	@Ignore
	public void testLaboratorios() {
		List<Espacio> espacios = prueba.findLaboratorios(0);
		assertEquals(espacios.get(1).esLaboratorio(),true);
	}
	
	@Test
	@Ignore
	public void testFindAulas() {
		List<Espacio> espacios = prueba.findAulas(0);
		assertEquals(espacios.get(1).esAula(),true);
	}
	
	@Test
	@Ignore
	public void testFindWcs() {
		List<Espacio> espacios = prueba.findWcs(0);
		assertEquals(espacios.get(1).esWC(),true);
	}
	
	@Ignore
	@Test
	public void testFindById() {
		Espacio espacio = prueba.findById("00.180");
		assertEquals(espacio.localizacion().getPoint().getX(),"675854.0864458996");
	}
	

	@Ignore
	@Test
	public void testFindAll() {
		List<Espacio> espacios = prueba.findAll();
		assertEquals(espacios.get(1).esWC(),true);
	}
	
	@Ignore
	@Test
	public void testUpdate() {
		
	}
}
