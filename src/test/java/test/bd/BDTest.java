package test.bd;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.*;

import dominio.Espacio;
import dominio.EspacioRepository;
import dominio.Temperatura;
import puertosyadaptadores.EspacioRepositoryPostgre;

public class BDTest {
	
	EspacioRepository prueba;
	
	@Before
	public void setUp() {
		prueba = new EspacioRepositoryPostgre();
	}

	@Test
	public void testFindDespachos() {
		List<Espacio> espacios = prueba.findDespachos(0);
		assertEquals(espacios.get(1).esDespacho(),true);
	}
	

	
	@Test
	public void testLaboratorios() {
		List<Espacio> espacios = prueba.findLaboratorios(0);
		assertEquals(espacios.get(1).esLaboratorio(),true);
	}
	
	@Test
	public void testFindAulas() {
		List<Espacio> espacios = prueba.findAulas(0);
		assertEquals(espacios.get(1).esAula(),true);
	}
	
	@Test
	public void testFindWcs() {
		List<Espacio> espacios = prueba.findWcs(0);
		assertEquals(espacios.get(1).esWC(),true);
	}
	
	@Test
	public void testFindById() {
		Espacio espacio = prueba.findById("00.180");
		assertEquals(espacio.esLaboratorio(),true);
	}
	
	@Test
	public void testErrorFindById() {
		Espacio espacio = prueba.findById("hola");
		assertEquals(espacio,null);
	}
	
	@Test
	public void testFindAll() {
		List<Espacio> espacios = prueba.findAll();
		Espacio encontrado = null;
		for (Espacio espacio : espacios) {
			if (espacio.getID().equals("00.180")) {
				encontrado = espacio;
			}
		}
		assertEquals(encontrado.esLaboratorio(),true);
	}
	
	@Test
	public void testUpdate() {
		List<Espacio> espacios = prueba.findAll();
		String buscar = espacios.get(1).getID();
		espacios.get(1).temperaturaObjetivo(new Temperatura(25.0));
		prueba.update(espacios);
		espacios = prueba.findAll();
		Espacio temp = null;
		for (Espacio espacio : espacios) {
			if (espacio.getID().equals(buscar)) {
				temp = espacio;
			}
		}
		assertEquals(temp.temperaturaObjetivo().getTemperature(),25.0);
	}
	
	@Test
	public void testUpdateById() {
		Espacio espacio = prueba.findById("00.180");
		espacio.temperaturaObjetivo(new Temperatura(25));
		prueba.updateById(espacio);
		espacio = prueba.findById("00.180");
		Temperatura temp = espacio.temperaturaObjetivo();
		assertEquals(temp.getTemperature(),25.0);
	}
}
