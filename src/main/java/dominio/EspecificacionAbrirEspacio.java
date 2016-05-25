package dominio;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 * Especificación de abrir un espacio. Si se cumple, se puede abrir el espacio.
 */
public class EspecificacionAbrirEspacio {
	private int HORA_ENTRADA = 8;
	private int HORA_SALIDA = 21;
	private Espacio sp;
	
	public EspecificacionAbrirEspacio(Espacio sp) {
		this.sp = sp;
	}
	/**
	 * Decide si se pueden abrir el espacio
	 * 
	 * @param sp
	 *            espacio
	 * @return true si se puede abrir el espacio, false en caso contrario
	 */
	public boolean esAbrible() {
		DateTime time = DateTime.now(DateTimeZone.forID("Europe/Madrid"));
		int hour = time.getHourOfDay();
		
		//fuera del horario lectivo, los espacios deben permanecer cerrados
		if (hour < HORA_ENTRADA || hour > HORA_SALIDA) {
			return false;
		}
		
		//Si no hay presencia y hay más de 40º se considera incendio, el espacio debe permanecer cerrado
		if (sp.presenciaApagada() && sp.temperatura().getTemperature()>40) {
			return false;
		}
		return true;
	}

}
