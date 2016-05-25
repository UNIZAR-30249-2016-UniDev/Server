package dominio;

/**
 * Servicio de apagado de luces. Implementa la politica de apagado de luces
 */
public class ServicioApagadoLuces {

	/**
	 * Decide si se puede apagar la luz o no
	 * 
	 * @param sp
	 *            espacio
	 * @return true si se puede apagar la luz, false en caso contrario
	 */
	public static boolean sePuedeApagar(Espacio sp) {
		if (sp.presenciaApagada() || sp.lucesApagadas()) {
			return false;
		}
		return true;
	}

}
