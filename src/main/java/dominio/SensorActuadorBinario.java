package dominio;

import dominio.Constantes.STATE;

/**
 * Sensor-actuador con dos estados: encendido y apagado
 */
public class SensorActuadorBinario {

	private STATE estado;

	/**
	 * Constructor del sensor-actuador cuyo estado solo puede ser ON/OFF
	 * 
	 * @param estado
	 *            estado inicial del sensor-actuador
	 */
	public SensorActuadorBinario(STATE estado) {
		this.estado = estado;
	}

	/**
	 * @return true si el sensor esta encendido (en estado ON) y false en caso
	 *         contrario
	 */
	public boolean estaEncendido() {
		return this.estado == STATE.ON;
	}

	/**
	 * @return true si el sensor esta apagado (en estado OFF) y false en caso
	 *         contrario
	 */
	public boolean estaApagado() {
		return this.estado == STATE.OFF;
	}

	/**
	 * Cambia de estado al conjunto sensor-actuador. Si estaba ON, pasara a OFF.
	 * Si estaba OFF, pasara a ON.
	 */
	public void actuar() {
		switch (this.estado) {
		case ON:
			this.estado = STATE.OFF;
			break;
		case OFF:
			this.estado = STATE.ON;
			break;
		}
	}

}
