package dominio;

import java.util.List;

import dominio.Constantes.EDIFICIO;

public abstract class EspacioRepository {
	
	public abstract List<Espacio> findDespachos(int floor, EDIFICIO building);
	
	public abstract List<Espacio> findLaboratorios(int floor, EDIFICIO building);
	
	public abstract List<Espacio> findWcs(int floor, EDIFICIO building);
	
	public abstract List<Espacio> findAulas(int floor, EDIFICIO building);
	
	public abstract List<Espacio> findAll();
	
	public abstract Espacio finById(String id);
	
	public abstract boolean update(List<Espacio> espacios);
	
}