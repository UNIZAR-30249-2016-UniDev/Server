package dominio;

import java.util.List;

public abstract class EspacioRepository {
	
	public abstract List<Espacio> findDespachos(int floor);
	
	public abstract List<Espacio> findLaboratorios(int floor);
	
	public abstract List<Espacio> findWcs(int floor);
	
	public abstract List<Espacio> findAulas(int floor);
	
	public abstract List<Espacio> findAll();
	
	public abstract Espacio finById(String id);
	
	public abstract boolean update(List<Espacio> espacios);
	
}