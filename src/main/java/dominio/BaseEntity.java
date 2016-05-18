package dominio;

import java.util.UUID;

/**
 * Entidad base
 */
public class BaseEntity implements BaseEntityInterface {

	private String id = null;

	/**
	 * Metodo creador. Agrega un identificador aleatorio.
	 */
	public BaseEntity() {
		id = UUID.randomUUID().toString();
	}

	/**
	 * Metodo creador segun un identificador predeterminado
	 * 
	 * @param id
	 *            identificador
	 */
	public BaseEntity(String id) {
		this.id = id;
	}

	public String getID() {
		return id;
	}

	/**
	 * Metodo comparador.
	 * 
	 * @param baseEntity
	 *            entidad con la que comparar
	 * @return true si son iguales (ids iguales), false en caso contrario
	 */
	public boolean equals(BaseEntity baseEntity) {
		if (this.id == baseEntity.getID()) {
			return true;
		} else {
			return false;
		}
	}
}