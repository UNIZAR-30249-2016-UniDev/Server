package dominio;

/**
 * Localizacion de un espacio
 */
public class Location {

	private final Point point;
	private final int floor;
	private final int building;

	/**
	 * Metodo creador
	 * 
	 * @param point
	 *            coordenadas X e Y
	 * @param floor
	 *            planta
	 * @param building
	 *            edificio
	 */
	public Location(Point point, int floor, int building) {
		this.point = point;
		this.floor = floor;
		this.building = building;
	}

	/**
	 * @return coordenadas X e Y
	 */
	public Point getPoint() {
		return point;
	}

	/**
	 * @return planta
	 */
	public int getFloor() {
		return floor;
	}

	/**
	 * @return edificio
	 */
	public int getBuilding() {
		return building;
	}

}
