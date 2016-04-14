package dominio;

public class Temperatura {
	
	private final double temperature;
	
	public Temperatura(double temperature) {
		this.temperature = temperature;
	}
	
	public double getTemperature() {
		return temperature;
	}
	
	public boolean mayorTemperaturaQue(Temperatura temp) {
		return temperature > temp.getTemperature();
	}
	
	public boolean menorTemperaturaQue(Temperatura temp) {
		return temperature < temp.getTemperature();
	}
	
	public boolean igualTemperaturaQue(Temperatura temp) {
		return temperature == temp.getTemperature();
	}

}
