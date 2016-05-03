package aplicacion;

import java.util.List;

import dominio.Espacio;
import dominio.EspacioRepository;
import dominio.Constantes.TYPE;
import puertosyadaptadores.EspacioRepositoryPostgre;

public class BuscaEspacio {
	
	private static EspacioRepository repository = new EspacioRepositoryPostgre();
	
	public static List<Espacio> buscarEspacio(int planta, String tipo_espacio) {
		List<Espacio> lista;
		if (tipo_espacio.equals(TYPE.DESPACHO.toString())) {
			lista = repository.findDespachos(planta);
		}
		else if (tipo_espacio.equals(TYPE.LAB.toString())) {
			lista = repository.findLaboratorios(planta);	
		}
		else if (tipo_espacio.equals(TYPE.WC.toString())) {
			lista = repository.findWcs(planta);	
		}
		else if (tipo_espacio.equals(TYPE.AULA.toString())) {
			lista = repository.findAulas(planta);
		}
		else {
			lista = null;
		}
		return lista;
	}
}
