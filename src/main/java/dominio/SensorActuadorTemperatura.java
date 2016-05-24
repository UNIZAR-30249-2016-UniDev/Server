package dominio;

/**
 * Sensor-actuador de la temperatura (escala de reales)
 */
public class SensorActuadorTemperatura {

	private Temperatura temperatura;
	private Temperatura temperaturaObjetivo;

	/**
	 * Constructor del sensor-actuador de temperatura
	 * 
	 * @param temperatura
	 *            temperatura inicial
	 * @param temperaturaObjetivo
	 *            temperatura objetivo inicial
	 */
	public SensorActuadorTemperatura(Temperatura temperatura,
			Temperatura temperaturaObjetivo) {
		this.temperatura = temperatura;
		this.temperaturaObjetivo = temperaturaObjetivo;
	}

	/**
	 * @return temperatura captada por el sensor en un momento determinado
	 */
	public Temperatura temperatura() {
		return this.temperatura;
	}

	/**
	 * @return temperatura objetivo del climatizador
	 */
	public Temperatura temperaturaObjetivo() {
		return this.temperaturaObjetivo;
	}

	/**
	 * Cambia la temperatura objetivo del climatizador
	 * 
	 * @param temperaturaObjetivo
	 *            nueva temperatura objetivo
	 */
	public void cambiarTemperaturaObjetivo(Temperatura temperaturaObjetivo) {
		this.temperaturaObjetivo = temperaturaObjetivo;
	}

	/**
	 * Cambia la temperatura del sensor
	 * 
	 * @param temperatura
	 *            nueva temperatura
	 */
	public void cambiarTemperatura(Temperatura temperatura) {
		this.temperatura = temperatura;
	}

}
