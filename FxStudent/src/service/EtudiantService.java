package service;

import java.util.List;

import application.EtudiantDaoFile;
import application.IEtudiantDao;
import model.Etudiant;

public class EtudiantService implements IEtudiantService {
	
	private IEtudiantDao dao = new EtudiantDaoFile();

	public List<Etudiant> listEtudiant() {
		return dao.getAll();
	}

	public void ajouterEtudiant(Etudiant e) {
		dao.add(e);
	}

	public Etudiant modifierEtudiant(Etudiant e) {
		return dao.update(e);
	}

}
