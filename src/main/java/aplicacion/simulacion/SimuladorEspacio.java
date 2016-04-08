package aplicacion.simulacion;

import java.util.List;

import dominio.entity.Espacio;
import dominio.repository.EspacioRepository;
import dominio.simulator.SimuladorLuces;
import dominio.simulator.SimuladorTemperatura;

public class SimuladorEspacio extends Thread {
	
	public static int HORA_ENTRADA = 8;
	public static int HORA_SALIDA = 21;
	
	public static int HORA_COMIDA = 14;
	public static int HORA_FIN_COMIDA = 15;
	
	private EspacioRepository repo;

	public SimuladorEspacio(EspacioRepository repo) {
		this.repo = repo;
	}

	@Override
	public void run() {
		List<Espacio> espacios = repo.findAll();
		for(Espacio espacio : espacios){
			SimuladorLuces.simular(espacio);
			SimuladorTemperatura.simular(espacio);
		}
	}
}
