package dominio;

import java.util.Random;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import aplicacion.SimuladorEspacio;

public class SimuladorLuces {
	
	private static final int PROBABILIDAD_AULA_LAB = 75;
	private static final int PROBABILIDAD_DESP_WC = 85;
	
	public static void simular(Espacio espacio) {
		DateTime time = DateTime.now(DateTimeZone.forID("Europe/Madrid"));
		int hour = time.getHourOfDay();
		
		if(debeEncenderse(espacio, hour)){
			espacio.encenderLuces();
		}
		else {
			if(debeApagarse(espacio, hour)){
				espacio.apagarLuces();
			}
		}
	}
	
	private static boolean debeEncenderse(Espacio espacio, int hora){
		if(espacio.lucesEncendidas()){
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