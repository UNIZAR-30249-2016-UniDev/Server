package dominio.policy;

import aplicacion.simulacion.SimuladorEspacio;
import dominio.entity.Espacio;

public class SimuladorApagadoLuces {
	
	public static boolean test(Espacio espacio, int hora){
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

}
