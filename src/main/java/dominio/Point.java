package dominio;

/**
 * Coordenadas X e Y de un espacio
 */
public class Point {

	private final double X;
	private final double Y;

	/**
	 * Metodo creador
	 * 
	 * @param x
	 *            coordenada X
	 * @param y
	 *            coordenada Y
	 */
	public Point(double x, double y) {
		this.X = x;
		this.Y = y;
	}

	/**
	 * @return coordenada X
	 */
	public double getX() {
		return X;
	}

	/**
	 * @return coordenada Y
	 */
	public double getY() {
		return Y;
	}

}
