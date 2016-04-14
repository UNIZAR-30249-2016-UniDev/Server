package dominio;

import dominio.Constantes.TYPE;

public class Espacio extends BaseEntity {

	private Location location;
	private TYPE type;

	private SensorActuadorBinario luz;
	private SensorActuadorTemperatura temperatura;

	/**
	 * Constructor de espacios
	 * @param location localizacion del espacio
	 * @param type tipo de espacio
	 * @param luz sensor-actuador de luz
	 * @param temperatura sensor-actuador de temperatura
	 */
	public Espacio(Location location, TYPE type, SensorActuadorBinario luz,
			SensorActuadorTemperatura temperatura) {
		this.location = location;
		this.type = type;
		this.luz = luz;
		this.temperatura = temperatura;
	}
	
	/**
	 * Constructor de espacios
	 * @param id identificador del espacio personalizado
	 * @param location localizacion del espacio
	 * @param type tipo de espacio
	 * @param luz sensor-actuador de luz
	 * @param temperatura sensor-actuador de temperatura
	 */
	public Espacio(String id, Location location, TYPE type, SensorActuadorBinario luz,
			SensorActuadorTemperatura temperatura) {
		super(id);
		this.location = location;
		this.type = type;
		this.luz = luz;
		this.temperatura = temperatura;
	}

	/**
	 * @return localizacion del espacio
	 */
	public Location localizacion() {
		return location;
	}

	/**
	 * @return true si es WC, false en caso contrario
	 */
	public boolean esWC() {
		return this.type == TYPE.WC;
	}

	/**
	 * @return true si es laboratorio, false en caso contrario
	 */
	public boolean esLaboratorio() {
		return this.type == TYPE.LAB;
	}

	/**
	 * @return true si es aula, false en caso contrario
	 */
	public boolean esAula() {
		return this.type == TYPE.AULA;
	}

	/**
	 * @return true si es despacho, false en caso contrario
	 */
	public boolean esDespacho() {
		return this.type == TYPE.DESPACHO;
	}

	public boolean lucesEncendidas(){
		return luz.estaEncendido();
	}
	
	public boolean lucesApagadas(){
		return luz.estaApagado();
	}
	
	/**
	 * @return true si ha encendido las luces del espacio, false en caso contrario
	 */
	public boolean encenderLuces(){
		return false;	// TODO
	}
	
	/**
	 * @return true si ha apagado las luces del espacio, false en caso contrario
	 */
	public boolean apagarLuces(){
		return false;	// TODO
	}
	
	/**
	 * @return temperatura del espacio
	 */
	public Temperatura temperatura(){
		return temperatura.temperatura();
	}
	
	/**
	 * Cambia la temperatura del espacio
	 * @param temp temperatura ambiente actual
	 */
	public void cambiarTemperatura(Temperatura temp){
		temperatura.cambiarTemperatura(temp);
	}
	
	/**
	 * @return temperatura objetivo del climatizador
	 */
	public Temperatura temperaturaObjetivo(){
		return temperatura.temperaturaObjetivo();
	}
	
	/**
	 * Cambia la temperatura objetivo del espacio
	 * @param temperaturaObjetivo nueva temperatura objetivo del climatizador
	 */
	public void temperaturaObjetivo(Temperatura temperaturaObjetivo){
		temperatura.cambiarTemperaturaObjetivo(temperaturaObjetivo);
	}

}