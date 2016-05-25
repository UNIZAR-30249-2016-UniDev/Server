package aplicacion;

import dominio.Espacio;
import dominio.EspacioRepository;
import dominio.Temperatura;
import puertosyadaptadores.EspacioRepositoryPostgre;

/**
 * Actualizador de espacios
 */
public class ActualizaEspacio {

	private static EspacioRepository repository = new EspacioRepositoryPostgre();

	/**
	 * Actualiza un espacio
	 * 
	 * @param id
	 *            identificador del espacio
	 * @param strLuz
	 *            identificador de luz
	 * @param luz
	 *            true si encendida, false si apagada
	 * @param strPuertas
	 *            identificador de puertas
	 * @param puertas
	 *            true si abierta, false si cerrada
	 * @param strPresencia
	 *            identificador de presencia
	 * @param presencia
	 *            true si hay presencia, false si no
	 * @param temperatura
	 *            temperatura actual del espacio
	 * @param calefaccion
	 *            temperatura objetivo del espacio
	 * @return array de objetos, donde el primero es el espacio actualizado (o
	 *         null) y el segundo es un booleano que indica si se ha actualizado
	 *         el espacio
	 */
	public static Object[] actualizarEspacio(String id, String strLuz,
			boolean luz, String strPuertas, boolean puertas,
			String strPresencia, boolean presencia, double temperatura,
			double calefaccion) {
		Object[] tabla = new Object[2];
		Espacio espacio = repository.findById(id);
		
		try {
			assert espacio != null;
		} catch (AssertionError ae) {
			System.err.println(ae.getMessage());
		}

		if (espacio != null) {
			espacio = actualizaEspacio(espacio, strLuz, luz, strPuertas,
					puertas, strPresencia, presencia, temperatura, calefaccion);
			boolean actualizado = repository.updateById(espacio);
			tabla[0] = espacio;
			tabla[1] = actualizado;
		} else {
			tabla[0] = null;
			tabla[1] = false;
		}
		return tabla;
	}

	/**
	 * Dado un espacio, lo actualiza segun los parametros y devuelve el espacio
	 * actualizado
	 * 
	 * @param espacio
	 *            espacio a actualizar
	 * @param strLuz
	 *            identificador de luz
	 * @param luz
	 *            true si encendida, false si apagada
	 * @param strPuertas
	 *            identificador de puertas
	 * @param puertas
	 *            true si abierta, false si cerrada
	 * @param strPresencia
	 *            identificador de presencia
	 * @param presencia
	 *            true si hay presencia, false si no
	 * @param temperatura
	 *            temperatura actual del espacio
	 * @param calefaccion
	 *            temperatura objetivo del espacio
	 * @return espacio actualizado
	 */
	private static Espacio actualizaEspacio(Espacio espacio, String strLuz,
			boolean luz, String strPuertas, boolean puertas,
			String strPresencia, boolean presencia, double temperatura,
			double calefaccion) {
		assert espacio != null;
		if (espacio == null) {
			return null;
		} else {
			if (strLuz != null) {
				if (luz) {
					espacio.encenderLuces();
				} else {
					espacio.apagarLuces();
				}
			}
			if (strPuertas != null) {
				if (puertas) {
					espacio.abrirPuertas();
				} else {
					espacio.cerrarPuertas();
				}
			}
			if (strPresencia != null) {
				if (presencia) {
					espacio.encenderPresencia();
				} else {
					espacio.apagarPresencia();
				}
			}
			if (temperatura != -100.0) {
				espacio.cambiarTemperatura(new Temperatura(temperatura));
			}
			if (calefaccion != -100.0) {
				espacio.temperaturaObjetivo(new Temperatura(calefaccion));
			}
		}
		return espacio;
	}
}
