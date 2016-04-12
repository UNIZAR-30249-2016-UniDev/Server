package dominio.policy;

import java.util.Random;

import aplicacion.simulacion.SimuladorEspacio;
import dominio.entity.Espacio;

public class SimuladorEncendidoLuces {

	private static final int PROBABILIDAD_AULA_LAB = 75;
	private static final int PROBABILIDAD_DESP_WC = 85;

	public static boolean test(Espacio espacio, int hora) {
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
				if ((hora >= SimuladorEspacio.HORA_ENTRADA) && (hora < SimuladorEspacio.HORA_SALIDA)) {
					if (random.nextInt(100) < PROBABILIDAD_DESP_WC) {
						return true;
					}
				}
			}
		}
		return false;
	}

}
