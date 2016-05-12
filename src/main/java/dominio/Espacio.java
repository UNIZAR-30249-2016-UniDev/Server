package dominio;

import dominio.Constantes.TYPE;

public class Espacio extends BaseEntity {

	private Location location;
	private TYPE type;

	private SensorActuadorBinario luz;
	private SensorActuadorBinario puertas;
	private SensorActuadorBinario presencia;
	private SensorActuadorTemperatura temperatura;

	/**
	 * Constructor de espacios
	 * @param location localizacion del espacio
	 * @param type tipo de espacio
	 * @param luz sensor-actuador de luz
	 * @param puertas sensor-actuador de puertas
	 * @param presencia sensor-actuador de presencia
	 * @param temperatura sensor-actuador de temperatura
	 */
	public Espacio(Location location, TYPE type, SensorActuadorBinario luz,
			SensorActuadorBinario puertas, SensorActuadorBinario presencia,
			SensorActuadorTemperatura temperatura) {
		this.location = location;
		this.type = type;
		this.luz = luz;
		this.puertas = puertas;
		this.presencia = presencia;
		this.temperatura = temperatura;
	}
	
	/**
	 * Constructor de espacios
	 * @param id identificador del espacio personalizado
	 * @param location localizacion del espacio
	 * @param type tipo de espacio
	 * @param luz sensor-actuador de luz 
	 * @param puertas sensor-actuador de puertas
	 * @param presencia sensor-actuador de presencia
	 * @param temperatura sensor-actuador de temperatura
	 */
	public Espacio(String id, Location location, TYPE type, SensorActuadorBinario luz,
			SensorActuadorBinario puertas, SensorActuadorBinario presencia,
			SensorActuadorTemperatura temperatura) {
		super(id);
		this.location = location;
		this.type = type;
		this.luz = luz;
		this.puertas = puertas;
		this.presencia = presencia;
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
		if (!luz.estaEncendido()) {
			luz.actuar();
		}
		return luz.estaEncendido();
	}
	
	/**
	 * @return true si ha apagado las luces del espacio, false en caso contrario
	 */
	public boolean apagarLuces(){
		if (!luz.estaApagado()) {
			luz.actuar();
		}
		return luz.estaApagado();
	}
	
	public boolean puertasAbiertas(){
		return puertas.estaEncendido();
	}
	
	public boolean puertasCerradas(){
		return puertas.estaApagado();
	}
	
	/**
	 * @return true si han abierto las puertas del espacio, false en caso contrario
	 */
	public boolean abrirPuertas(){
		if (!puertas.estaEncendido()) {
			puertas.actuar();
		}
		return puertas.estaEncendido();
	}
	
	/**
	 * @return true si han cerrado las puertas del espacio, false en caso contrario
	 */
	public boolean cerrarPuertas(){
		if (!puertas.estaApagado()) {
			puertas.actuar();
		}
		return puertas.estaApagado();
	}
	
	public boolean presenciaEncendida(){
		return presencia.estaEncendido();
	}
	
	public boolean presenciaApagada(){
		return presencia.estaApagado();
	}
	
	/**
	 * @return true si ha encendido la presencia del espacio, false en caso contrario
	 */
	public boolean encenderPresencia(){
		if (!presencia.estaEncendido()) {
			presencia.actuar();
		}
		return presencia.estaEncendido();
	}
	
	/**
	 * @return true si ha apagado la presencia del espacio, false en caso contrario
	 */
	public boolean apagarPresencia(){
		if (!presencia.estaApagado()) {
			presencia.actuar();
		}
		return presencia.estaApagado();
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

	public String toJSON() {
		/* atributos comunes que enviar */
		String id = this.getID();

		boolean lucesOn = this.lucesEncendidas();
		boolean puertasOpen = this.puertasAbiertas();
		boolean presenciaOn = this.presenciaEncendida();

		Point coordenadas = this.localizacion().getPoint();
		
		Temperatura temp = this.temperatura();
		Temperatura tempClimatizador = this.temperaturaObjetivo();

		String json = "{ ";
		json += "\"id\":\"" + id + "\", ";
		json += "\"geometry\": { \"type\": \"Point\", \"coordinates\": [" + coordenadas.getX() + ", " + coordenadas.getY() + "] }, ";
		json += "\"luz\":" + lucesOn + ", ";
		json += "\"puertas\":" + puertasOpen + ", ";
		json += "\"presencia\":" + presenciaOn + ", ";
		json += "\"temperatura\":" + temp.getTemperature() + ", ";
		json += "\"temperatura_objetivo\":" + tempClimatizador.getTemperature();

		json += "}";
		return json;
	}

}