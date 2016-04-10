package dominio.repository;

import java.util.List;

import dominio.entity.Espacio;

public abstract class EspacioRepository {
	
	public enum EDIFICIO{
		TQ, ADA, BETAN
	};
	
	public abstract List<Espacio> findDespachos(int floor, String building);
	
	public abstract List<Espacio> findLaboratorios(int floor, String building);
	
	public abstract List<Espacio> findWcs(int floor, String building);
	
	public abstract List<Espacio> findAulas(int floor, String building);
	
	public abstract List<Espacio> findAll();
	
	public abstract Espacio finById(String id);
	
}