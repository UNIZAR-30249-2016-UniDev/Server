package test.bd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import puertosyadaptadores.EspacioRepositoryPostgre;
import dominio.Espacio;
import dominio.EspacioRepository;
import dominio.Temperatura;

/**
 * Tests contra el repositorio de espacios
 */
public class BDTest {

	static EspacioRepository prueba;

	/**
	 * Instancia el repositorio
	 */
	@BeforeClass
	public static void setUp() {
		prueba = new EspacioRepositoryPostgre();
	}

	/**
	 * Comprueba que el metodo de buscar despachos devuelva despachos
	 */
	@Test
	public void testFindDespachos() {
		List<Espacio> espacios = prueba.findDespachos(0);
		assertEquals(espacios.get(0).esDespacho(), true);
	}

	/**
	 * Comprueba que el metodo de buscar laboratorio devuelva laboratorios
	 */
	@Test
	public void testLaboratorios() {
		List<Espacio> espacios = prueba.findLaboratorios(0);
		assertEquals(espacios.get(0).esLaboratorio(), true);
	}

	/**
	 * Comprueba que el metodo de buscar aulas devuelva aulas
	 */
	@Test
	public void testFindAulas() {
		List<Espacio> espacios = prueba.findAulas(0);
		assertEquals(espacios.get(0).esAula(), true);
	}

	/**
	 * Comprueba que el metodo de buscar servicios devuelva servicios
	 */
	@Test
	public void testFindWcs() {
		List<Espacio> espacios = prueba.findWcs(0);
		assertEquals(espacios.get(0).esWC(), true);
	}

	/**
	 * Busca un laboratorio por su identificador y comprueba que devuelve
	 * efectivamente un laboratorio
	 */
	@Test
	public void testFindById() {
		Espacio espacio = prueba.findById("00.180");
		assertEquals(espacio.esLaboratorio(), true);
	}

	/**
	 * Busca un espacio inexistente y comprueba que devuelve nulo
	 */
	@Test
	public void testErrorFindById() {
		Espacio espacio = prueba.findById("hola");
		assertEquals(espacio, null);
	}

	/**
	 * Busca todos los espacios y comprueba que entre ellos se encuentra uno de
	 * identificador 00.180
	 */
	@Test
	public void testFindAll() {
		List<Espacio> espacios = prueba.findAll();
		Espacio encontrado = null;
		for (Espacio espacio : espacios) {
			if (espacio.getID().equals("00.180")) {
				encontrado = espacio;
			}
		}
		assertEquals(encontrado.esLaboratorio(), true);
	}

	/**
	 * Comprueba que la actualizacion de espacios funciona actualizando la
	 * temperatura objetivo de un espacio
	 */
	@Test
	public void testUpdate() {
		double newTemp = 25.0;
		List<Espacio> espacios = prueba.findAll();
		
		Espacio espacio = espacios.get(0);
		
		String buscar = espacio.getID();
		espacio.temperaturaObjetivo(new Temperatura(newTemp));
		espacios = new ArrayList<Espacio>();
		espacios.add(espacio);
		
		boolean res = prueba.update(espacios);
		if(!res){
			assertTrue("Update ha fallado", false);
		}
		
		Espacio temp = prueba.findById(buscar);
		assertEquals("Temperatura objetivo no actualizada correctamente", temp.temperaturaObjetivo().getTemperature(), newTemp);
	}

	/**
	 * Comprueba que la actualizacion de espacios funciona actualizacion la
	 * temperatura objetivo de un espacio
	 */
	@Test
	public void testUpdateById() {
		double newTemp = 25.0;
		
		Espacio espacio = prueba.findById("00.180");
		espacio.temperaturaObjetivo(new Temperatura(newTemp));
		
		boolean res = prueba.updateById(espacio);
		if(!res){
			assertTrue("Update ha fallado", false);
		}
		
		espacio = prueba.findById("00.180");
		Temperatura temp = espacio.temperaturaObjetivo();
		assertEquals(temp.getTemperature(), newTemp);
	}
}
