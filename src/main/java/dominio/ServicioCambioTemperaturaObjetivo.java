package dominio;

/**
 * Servicio de cambio de temperatura. Decide si la temperatura objetivo se puede
 * cambiar
 */
public class ServicioCambioTemperaturaObjetivo {

	/**
	 * Decide si la temperatura objetivo puede cambiarse
	 * 
	 * @param sp
	 *            espacio
	 * @param temp
	 *            temperatura objetivo nueva
	 * @return true si se puede cambiar la temperatura objetivo, false en caso
	 *         contrario
	 */
	public static boolean sePuedeCambiar(Espacio sp, Temperatura temp) {
		if (sp.temperaturaObjetivo().equals(temp)) {
			return false;
		}
		return true;
	}

}
