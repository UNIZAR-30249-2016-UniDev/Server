package aplicacion;

import dominio.Espacio;
import dominio.EspacioRepository;
import dominio.Temperatura;
import puertosyadaptadores.EspacioRepositoryPostgre;

public class ActualizaEspacio {
	
	private static EspacioRepository repository = new EspacioRepositoryPostgre();
	
	public static Object[] actualizarEspacio(String id, String strLuz, boolean luz,
			String strPuertas, boolean puertas, String strPresencia, boolean presencia,
			double temperatura, double calefaccion) {
		Object[] tabla = new Object[2];
		Espacio espacio = repository.findById(id);
		espacio = actualizaEspacio(espacio, strLuz, luz, strPuertas,
				puertas, strPresencia, presencia, temperatura, calefaccion);
		boolean actualizado = repository.updateById(espacio);
		tabla[0] = espacio;
		tabla[1] = actualizado;
		return tabla;
	}
	
	private static Espacio actualizaEspacio(Espacio espacio, String strLuz, boolean luz,
			String strPuertas, boolean puertas, String strPresencia, boolean presencia,
			double temperatura, double calefaccion) {
		assert espacio!=null;
		if(espacio == null) {
			return null;
		}
		else {
			if (strLuz != null) {
				if (luz) {
					espacio.encenderLuces();
				} else {
					espacio.apagarLuces();
				}
			}
			if (strPuertas != null) {
				if (puertas) {
					espacio.abrirPuertas();
				} else {
					espacio.cerrarPuertas();
				}
			}
			if (strPresencia != null) {
				if (presencia) {
					espacio.encenderPresencia();
				} else {
					espacio.apagarPresencia();
				}
			}
			if (temperatura != -100.0) {
				espacio.cambiarTemperatura(new Temperatura(temperatura));
			}
			if (calefaccion != -100.0) {
				espacio.temperaturaObjetivo(new Temperatura(calefaccion));
			}
		}		
		return espacio;
	}
}
