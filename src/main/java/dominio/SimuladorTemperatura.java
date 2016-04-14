package dominio;

import java.util.Random;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import aplicacion.SimuladorEspacio;

public class SimuladorTemperatura {
	
	private static final double TEMPERATURA_ABIERTO = 22.0;
	private static final double TEMPERATURA_CERRADO = 18.0;
	
	private static final int PROBABILIDAD_AULA_LAB = 45;
	private static final int PROBABILIDAD_DESP_WC = 60;

	public static void simular(Espacio espacio) {
		DateTime time = DateTime.now(DateTimeZone.forID("Europe/Madrid"));
		int hour = time.getHourOfDay();
		Random random = new Random();
		
		Temperatura temp = espacio.temperatura();
		Temperatura tempObj = espacio.temperaturaObjetivo();
		if ((hour >= SimuladorEspacio.HORA_ENTRADA)
				&& (hour < SimuladorEspacio.HORA_SALIDA)) {
			if (!tempObj.igualTemperaturaQue(new Temperatura(TEMPERATURA_ABIERTO))) {
				espacio.temperaturaObjetivo(new Temperatura(TEMPERATURA_ABIERTO));
			}
		} else {
			if (!tempObj.igualTemperaturaQue(new Temperatura(TEMPERATURA_CERRADO))) {
				espacio.temperaturaObjetivo(new Temperatura(TEMPERATURA_CERRADO));
			}
		}
		if (temp.mayorTemperaturaQue(tempObj)) {
			if (random.nextInt(100) < PROBABILIDAD_AULA_LAB) {
				espacio.cambiarTemperatura(new Temperatura(temp.getTemperature() - 0.5));
			}
		} else if (temp.menorTemperaturaQue(tempObj)) {
			if (random.nextInt(100) < PROBABILIDAD_DESP_WC) {
				espacio.cambiarTemperatura(new Temperatura(temp.getTemperature() + 0.5));
			}
		}
	}

}
