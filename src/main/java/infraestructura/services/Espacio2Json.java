package infraestructura.services;

import dominio.entity.Espacio;
import dominio.entity.Laboratorio;
import dominio.value_object.Point;
import dominio.value_object.Temperatura;

public class Espacio2Json {

	/**
	 * Convierte un espacio en JSON, tomando solo la informacion relevante:
	 * identificador, estado de las luces, temperatura, temperatura del
	 * climatizador, capacidad y ocupacion ademas si es un laboratorio
	 * 
	 * @param sp espacio que convertir a JSON 
	 * @return una cadena que representa el espacio como JSON
	 */
	public static String espacio2Json(Espacio sp) {
		/* atributos comunes que enviar */
		String id = sp.getID();

		boolean lucesOn = sp.lucesEncendidas();

		Point coordenadas = sp.localizacion().getPoint();
		
		Temperatura temp = sp.temperatura();
		Temperatura tempClimatizador = sp.temperaturaObjetivo();

		String json = "{ ";
		json += "\"id\":\"" + id + "\", ";
		json += "\"localizacion_x\":\"" + coordenadas.getX() + "\", ";
		json += "\"localizacion_y\":\"" + coordenadas.getY() + "\", ";
		json += "\"luz\":\"" + lucesOn + "\", ";
		json += "\"temperatura\":\"" + temp.getTemperature() + "\", ";
		json += "\"temperatura_objetivo\":\"" + tempClimatizador.getTemperature() + "\"";

		/* atributos laboratorios */
		/*if (sp.esLaboratorio()) {
			Laboratorio lab = (Laboratorio) sp;

			int capacidad = lab.capacidad();
			int ocupacion = lab.ocupacion();

			json += ", \"capacidad\":\"" + capacidad + "\" ";
			json += ", \"ocupacion\":\"" + ocupacion + "\" ";
		}*/

		json += "}";
		return json;
	}
}
