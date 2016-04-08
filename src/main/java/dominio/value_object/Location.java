package dominio.value_object;

public class Location {
	
	private final Point point;
	private final int floor;
	private final int building;
	
	public Location(Point point, int floor, int building) {
		this.point = point;
		this.floor = floor;
		this.building = building;
	}
	
	public Point getPoint() {
		return point;		
	}
	
	public int getFloor() {
		return floor;
	}
	
	public int getBuilding() {
		return building;
	}

}
