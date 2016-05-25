package aplicacion;

import java.util.List;

import dominio.Espacio;
import dominio.EspacioRepository;
import puertosyadaptadores.EspacioRepositoryPostgre;

/**
 * Abre/cierra todos los espacios de un edificio y planta
 * 
 * @param planta
 *            numero de la planta a buscar
 * @param edificio
 *            edificio a buscar
 * @return true si ha tenido exito, false en el caso contrario
 */

public class GestionaEspacio {
	
	private static EspacioRepository repository = new EspacioRepositoryPostgre();

	/**
	 * Abre todos los espacios de un edificio y planta
	 * 
	 * @param planta
	 *            numero de la planta a buscar
	 * @param edificio
	 *            edificio a buscar
	 * @return true si ha tenido exito, false en el caso contrario
	 */
	public static boolean abrirTodosEspacios(int edificio, int planta) {
		List<Espacio> espacios = repository.findAll();
		for (Espacio espacio : espacios) {
			if (espacio.localizacion().getBuilding() == edificio &&
					espacio.localizacion().getFloor() == planta) {
				espacio.abrirPuertas();
			}
		}
		return repository.update(espacios);
	}
	
	/**
	 * Cierra todos los espacios de un edificio y planta
	 * 
	 * @param planta
	 *            numero de la planta a buscar
	 * @param edificio
	 *            edificio a buscar
	 * @return true si ha tenido exito, false en el caso contrario
	 */
	public static boolean cerrarTodosEspacios(int edificio, int planta) {
		List<Espacio> espacios = repository.findAll();
		for (Espacio espacio : espacios) {
			if (espacio.localizacion().getBuilding() == edificio &&
					espacio.localizacion().getFloor() == planta) {
				espacio.cerrarPuertas();
			}
		}
		return repository.update(espacios);
	}
	
	/**
	 * Apaga todas las luces de los espacios de un edificio y planta
	 * 
	 * @param planta
	 *            numero de la planta a buscar
	 * @param edificio
	 *            edificio a buscar
	 * @return true si ha tenido exito, false en el caso contrario
	 */
	public static boolean apagarTodosEspacios(int edificio, int planta) {
		List<Espacio> espacios = repository.findAll();
		for (Espacio espacio : espacios) {
			if (espacio.localizacion().getBuilding() == edificio &&
					espacio.localizacion().getFloor() == planta) {
				espacio.apagarLuces();
			}
		}
		return repository.update(espacios);
	}
	
	/**
	 * Enciende todas las luces de los espacios de un edificio y planta
	 * 
	 * @param planta
	 *            numero de la planta a buscar
	 * @param edificio
	 *            edificio a buscar
	 * @return true si ha tenido exito, false en el caso contrario
	 */
	public static boolean encenderTodosEspacios(int edificio, int planta) {
		List<Espacio> espacios = repository.findAll();
		for (Espacio espacio : espacios) {
			if (espacio.localizacion().getBuilding() == edificio &&
					espacio.localizacion().getFloor() == planta) {
				espacio.encenderLuces();
			}
		}
		return repository.update(espacios);
	}
}
