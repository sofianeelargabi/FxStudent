package service;

import java.util.List;

import model.Etudiant;



public interface IEtudiantService {
	
	public List<Etudiant> listEtudiant();
	
	public void ajouterEtudiant(Etudiant e);
	
	public Etudiant modifierEtudiant(Etudiant e);
	
	

}
