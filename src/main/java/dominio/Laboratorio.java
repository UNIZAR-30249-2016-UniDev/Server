package dominio;

import dominio.Constantes.TYPE;

/**
 * Laboratorio con capacidad y ocupacion
 */
public class Laboratorio extends Espacio {

	private int capacidad;
	private int ocupacion;

	/**
	 * Constructor del Laboratio
	 * 
	 * @param location
	 *            localizacion del espacio
	 * @param luz
	 *            sensor-actuador de la luz
	 * @param puertas
	 *            sensor-actuador de puertas
	 * @param presencia
	 *            sensor-actuador de presencia
	 * @param temperatura
	 *            sensor-actuador de la temperatura
	 * @param capacidad
	 *            capacidad del laboratorio
	 * @param ocupacion
	 *            ocupacion inicial del laboratorio
	 */
	public Laboratorio(Location location, SensorActuadorBinario luz,
			SensorActuadorBinario puertas, SensorActuadorBinario presencia,
			SensorActuadorTemperatura temperatura, int capacidad, int ocupacion) {
		super(location, TYPE.LAB, luz, puertas, presencia, temperatura);
		this.capacidad = capacidad;
		this.ocupacion = ocupacion;
	}

	/**
	 * Constructor del Laboratio
	 * 
	 * @param id
	 *            identificador de espacio personalizado
	 * @param location
	 *            localizacion del espacio
	 * @param luz
	 *            sensor-actuador de la luz
	 * @param puertas
	 *            sensor-actuador de puertas
	 * @param presencia
	 *            sensor-actuador de presencia
	 * @param temperatura
	 *            sensor-actuador de la temperatura
	 * @param capacidad
	 *            capacidad del laboratorio
	 * @param ocupacion
	 *            ocupacion inicial del laboratorio
	 */
	public Laboratorio(String id, Location location, SensorActuadorBinario luz,
			SensorActuadorBinario puertas, SensorActuadorBinario presencia,
			SensorActuadorTemperatura temperatura, int capacidad, int ocupacion) {
		super(id, location, TYPE.LAB, luz, puertas, presencia, temperatura);
		this.capacidad = capacidad;
		this.ocupacion = ocupacion;
	}

	/**
	 * @return capacidad del laboratorio
	 */
	public int capacidad() {
		return this.capacidad;
	}

	/**
	 * @return ocupacion actual del laboratorio
	 */
	public int ocupacion() {
		return this.ocupacion;
	}

	/**
	 * Cambia la ocupacion del laboratorio
	 * 
	 * @param ocupacion
	 *            nueva ocupacion del laboratorio
	 */
	public void cambiarOcupacion(int ocupacion) {
		this.ocupacion = ocupacion;
	}
}
