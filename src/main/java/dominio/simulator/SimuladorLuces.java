package dominio.simulator;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import dominio.entity.Espacio;
import dominio.policy.SimuladorApagadoLuces;
import dominio.policy.SimuladorEncendidoLuces;

public class SimuladorLuces {
	
	public static void simular(Espacio espacio) {
		DateTime time = DateTime.now(DateTimeZone.forID("Europe/Madrid"));
		int hour = time.getHourOfDay();
		
		if(SimuladorEncendidoLuces.test(espacio, hour)){
			espacio.encenderLuces();
		}
		else {
			if(SimuladorApagadoLuces.test(espacio, hour)){
				espacio.apagarLuces();
			}
		}
	}

}
