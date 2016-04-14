package aplicacion;

import java.util.List;

import dominio.Espacio;
import dominio.EspacioRepository;
import dominio.SimuladorLuces;
import dominio.SimuladorTemperatura;

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
		
		if(espacios != null) {
		
			for(Espacio espacio : espacios) {
				SimuladorLuces.simular(espacio);
				SimuladorTemperatura.simular(espacio);
			}
			
			repo.update(espacios);
		}
	}
}
