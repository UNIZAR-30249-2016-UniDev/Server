package dominio;

import java.util.UUID;

public class BaseEntity implements BaseEntityInterface {

	private String id = null;

	public BaseEntity() {
		id = UUID.randomUUID().toString();
	}

	public BaseEntity(String id) {
		this.id = id;
	}

	public String getID() {
		return id;
	}

	public boolean equals(BaseEntity baseEntity) {
		if (this.id == baseEntity.getID()) {
			return true;
		} else {
			return false;
		}
	}
}