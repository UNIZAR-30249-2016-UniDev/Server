package dominio;

import dominio.Constantes.TYPE;

public class Wc extends Espacio {

	public enum GENDER{
		CHICAS, CHICOS
	};
	
	private GENDER genero;
	
	/**
	 * Constructor del servicio (WC)
	 * @param location localizacion del espacio
	 * @param luz sensor-actuador del espacio
	 * @param puertas sensor-actuador de puertas
	 * @param presencia sensor-actuador de presencia 
	 * @param temperatura sensor-ctuador del espacio
	 * @param genero servicio de hombres o mujeres
	 */
	public Wc(Location location, SensorActuadorBinario luz,
			SensorActuadorBinario puertas, SensorActuadorBinario presencia,
			SensorActuadorTemperatura temperatura, GENDER genero) {
		super(location, TYPE.WC, luz, puertas, presencia, temperatura);
		this.genero = genero;
	}
	
	/**
	 * Constructor del servicio (WC)
	 * @param id identificador del espacio personaliza
	 * @param location localizacion del espacio
	 * @param luz sensor-actuador del espacio
	 * @param puertas sensor-actuador de puertas
	 * @param presencia sensor-actuador de presencia 
	 * @param temperatura sensor-ctuador del espacio
	 * @param genero servicio de hombres o mujeres
	 */
	public Wc(String id, Location location, SensorActuadorBinario luz,
			SensorActuadorBinario puertas, SensorActuadorBinario presencia,
			SensorActuadorTemperatura temperatura, GENDER genero) {
		super(id, location, TYPE.WC, luz, puertas, presencia, temperatura);
		this.genero = genero;
	}
	
	/**
	 * @return true si el servicio es de mujeres
	 */
	public boolean esDeChicas(){
		return this.genero == GENDER.CHICAS;
	}
	
	/**
	 * @return true si el servicio es de hombres
	 */
	public boolean esDeChicos(){
		return this.genero == GENDER.CHICOS;
	}
}
