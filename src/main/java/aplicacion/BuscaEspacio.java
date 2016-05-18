package aplicacion;

import java.util.List;

import dominio.Espacio;
import dominio.EspacioRepository;
import dominio.Constantes.TYPE;
import puertosyadaptadores.EspacioRepositoryPostgre;

/**
 * Buscador de espacios
 */
public class BuscaEspacio {

	private static EspacioRepository repository = new EspacioRepositoryPostgre();

	/**
	 * Busca los espacios de una planta y de un tipo concreto
	 * 
	 * @param planta
	 *            numero de la planta a buscar
	 * @param tipo_espacio
	 *            tipo de espacio a buscar
	 * @return lista de espacios que concuerdan con las caracteristicas
	 */
	public static List<Espacio> buscarEspacio(int planta, String tipo_espacio) {
		List<Espacio> lista;
		if (tipo_espacio.equals(TYPE.DESPACHO.toString())) {
			lista = repository.findDespachos(planta);
		} else if (tipo_espacio.equals(TYPE.LAB.toString())) {
			lista = repository.findLaboratorios(planta);
		} else if (tipo_espacio.equals(TYPE.WC.toString())) {
			lista = repository.findWcs(planta);
		} else if (tipo_espacio.equals(TYPE.AULA.toString())) {
			lista = repository.findAulas(planta);
		} else {
			lista = null;
		}
		return lista;
	}
}
