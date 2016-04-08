package dominio.simulator;

import java.util.Random;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import aplicacion.simulacion.SimuladorEspacio;
import dominio.entity.Espacio;

public class SimuladorLuces {
	
	private static final int PROBABILIDAD_AULA_LAB = 75;
	private static final int PROBABILIDAD_DESP_WC = 85;
	
	public static void simular(Espacio espacio) {
		DateTime time = DateTime.now(DateTimeZone.forID("Europe/Madrid"));
		int hour = time.getHourOfDay();
		Random random = new Random();

		if (espacio.lucesApagadas()) {
			if (espacio.esAula() || espacio.esLaboratorio()) {
				if ((hour >= SimuladorEspacio.HORA_ENTRADA && hour < SimuladorEspacio.HORA_COMIDA)
						|| (hour >= SimuladorEspacio.HORA_FIN_COMIDA && hour < SimuladorEspacio.HORA_SALIDA)) {
					if (random.nextInt(100) < PROBABILIDAD_AULA_LAB) {
						espacio.encenderLuces();
					}
				}
			} else if (espacio.esDespacho() || espacio.esWC()) {
				if ((hour >= SimuladorEspacio.HORA_ENTRADA)
						&& (hour < SimuladorEspacio.HORA_SALIDA)) {
					if (random.nextInt(100) < PROBABILIDAD_DESP_WC) {
						espacio.encenderLuces();
					}
				}
			}
		} else {
			if (espacio.esAula() || espacio.esLaboratorio()) {
				if (hour >= SimuladorEspacio.HORA_SALIDA
						|| hour < SimuladorEspacio.HORA_ENTRADA
						|| (hour >= SimuladorEspacio.HORA_COMIDA && hour < SimuladorEspacio.HORA_FIN_COMIDA)) {
					espacio.apagarLuces();
				}
			} else if (espacio.esDespacho() || espacio.esWC()) {
				if (hour >= SimuladorEspacio.HORA_SALIDA
						|| hour < SimuladorEspacio.HORA_ENTRADA) {
					espacio.apagarLuces();
				}
			}
		}
	}

}
