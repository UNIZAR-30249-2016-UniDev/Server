package dominio;

import java.util.Random;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import aplicacion.SimuladorEspacio;

/**
 * Simula las luces en un espacio
 */
public class SimuladorLuces {

	private static final int PROBABILIDAD_AULA_LAB = 75;
	private static final int PROBABILIDAD_DESP_WC = 85;

	/**
	 * Simula el encendido y apagado de luces en un espacio
	 * 
	 * @param espacio
	 *            espacio que simular
	 */
	public static void simular(Espacio espacio) {
		DateTime time = DateTime.now(DateTimeZone.forID("Europe/Madrid"));
		int hour = time.getHourOfDay();

		if (debeEncenderse(espacio, hour)) {
			espacio.encenderLuces();
		} else {
			if (debeApagarse(espacio, hour)) {
				espacio.apagarLuces();
			}
		}
	}

	/**
	 * Decide si las luces deben encenderse o no
	 * 
	 * @param espacio
	 *            espacio
	 * @param hora
	 *            hora del dia
	 * @return true si se debe encender las luces, false en caso contrario
	 */
	private static boolean debeEncenderse(Espacio espacio, int hora) {
		if (espacio.lucesEncendidas()) {
			if (espacio.esAula() || espacio.esLaboratorio()) {
				if (hora >= SimuladorEspacio.HORA_SALIDA
						|| hora < SimuladorEspacio.HORA_ENTRADA
						|| (hora >= SimuladorEspacio.HORA_COMIDA && hora < SimuladorEspacio.HORA_FIN_COMIDA)) {
					return true;
				}
			} else if (espacio.esDespacho() || espacio.esWC()) {
				if (hora >= SimuladorEspacio.HORA_SALIDA
						|| hora < SimuladorEspacio.HORA_ENTRADA) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Decide si deben apagarse las luces o no
	 * 
	 * @param espacio
	 *            espacio
	 * @param hora
	 *            hora del dia
	 * @return true si se deben apagar las luces, false en caso contrario
	 */
	private static boolean debeApagarse(Espacio espacio, int hora) {
		Random random = new Random();
		if (espacio.lucesApagadas()) {
			if (espacio.esAula() || espacio.esLaboratorio()) {
				if ((hora >= SimuladorEspacio.HORA_ENTRADA && hora < SimuladorEspacio.HORA_COMIDA)
						|| (hora >= SimuladorEspacio.HORA_FIN_COMIDA && hora < SimuladorEspacio.HORA_SALIDA)) {
					if (random.nextInt(100) < PROBABILIDAD_AULA_LAB) {
						return true;
					}
				}
			} else if (espacio.esDespacho() || espacio.esWC()) {
				if ((hora >= SimuladorEspacio.HORA_ENTRADA)
						&& (hora < SimuladorEspacio.HORA_SALIDA)) {
					if (random.nextInt(100) < PROBABILIDAD_DESP_WC) {
						return true;
					}
				}
			}
		}
		return false;
	}

}