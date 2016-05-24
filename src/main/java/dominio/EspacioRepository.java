package dominio;

import java.util.List;

/**
 * Repositorio del espacio
 */
public abstract class EspacioRepository {

	/**
	 * Devuelve los despachos que se encuentran en una planta
	 * 
	 * @param floor
	 *            planta
	 * @return lista de despachos que se encuentran en @param floor
	 */
	public abstract List<Espacio> findDespachos(int floor);

	/**
	 * Devuelve los laboratorios que se encuentran en una planta
	 * 
	 * @param floor
	 *            planta
	 * @return lista de laboratorios que se encuentran en @param floor
	 */
	public abstract List<Espacio> findLaboratorios(int floor);

	/**
	 * Devuelve los servicios que se encuentran en una planta
	 * 
	 * @param floor
	 *            planta
	 * @return lista de servicios que se encuentran en @param floor
	 */
	public abstract List<Espacio> findWcs(int floor);

	/**
	 * Devuelve las aulas que se encuentran en una planta
	 * 
	 * @param floor
	 *            planta
	 * @return lista de aulas que se encuentran en @param floor
	 */
	public abstract List<Espacio> findAulas(int floor);

	/**
	 * Devuelve todos los espacios
	 * 
	 * @return lista de todos los espacios de todas las plantas
	 */
	public abstract List<Espacio> findAll();

	/**
	 * Devuelve el espacio que se identifica por su identificador
	 * 
	 * @param id
	 *            identificador
	 * @return espacio cuyo identificador es @param id
	 */
	public abstract Espacio findById(String id);

	/**
	 * Actualiza una lista de espacios
	 * 
	 * @param espacios
	 *            espacios a actualizar
	 * @return true si se ha actualizado, false en caso contrario
	 */
	public abstract boolean update(List<Espacio> espacios);

	/**
	 * Actualiza un espacio
	 * 
	 * @param espacio
	 *            espacio de actualizar
	 * @return true si se ha actualizado, false en caso contrario
	 */
	public abstract boolean updateById(Espacio espacio);

}