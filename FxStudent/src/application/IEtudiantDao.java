package application;

import java.util.List;

import model.Etudiant;


public interface IEtudiantDao {
	
	public List<Etudiant> getAll();
	
	public void add(Etudiant e);
	
	public Etudiant update(Etudiant e);

}
