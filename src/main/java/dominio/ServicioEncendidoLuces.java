package dominio;

/**
 * Servicio de encendido de luces. Decide si se pueden encender las luces o no.
 */
public class ServicioEncendidoLuces {

	/**
	 * Decide si se pueden encender las luces en un espacio o no
	 * 
	 * @param sp
	 *            espacio
	 * @return true si se puede encender las luces, false en caso contrario
	 */
	public static boolean sePuedeEncender(Espacio sp) {
		if (sp.presenciaApagada()) {
			return false;
		}
		if (sp.lucesEncendidas()) {
			return false;
		}

		return true;
	}

}
