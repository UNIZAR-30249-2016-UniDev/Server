package dominio;

/**
 * Temperatura de un espacio
 */
public class Temperatura {

	private final double temperature;

	/**
	 * Metodo creador
	 * 
	 * @param temperature
	 *            temperatura
	 */
	public Temperatura(double temperature) {
		this.temperature = temperature;
	}

	/**
	 * @return temperatura
	 */
	public double getTemperature() {
		return temperature;
	}

	/**
	 * Comprueba si esta temperatura es mayor que otra
	 * 
	 * @param temp
	 *            otra temperatura
	 * @return true si esta temperatura es mayor que @param temp
	 */
	public boolean mayorTemperaturaQue(Temperatura temp) {
		return temperature > temp.getTemperature();
	}

	/**
	 * Comprueba si esta temperatura es menor que otra
	 * 
	 * @param temp
	 *            otra temperatura
	 * @return true si esta temperatura es menor que @param temp
	 */
	public boolean menorTemperaturaQue(Temperatura temp) {
		return temperature < temp.getTemperature();
	}

	/**
	 * Comprueba si esta temperatura es igual que otra
	 * 
	 * @param temp
	 *            otra temperatura
	 * @return true si esta temperatura es igual que @param temp
	 */
	public boolean igualTemperaturaQue(Temperatura temp) {
		return temperature == temp.getTemperature();
	}

}
