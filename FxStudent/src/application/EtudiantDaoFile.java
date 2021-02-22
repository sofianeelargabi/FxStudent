package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import model.Etudiant;

public class EtudiantDaoFile implements IEtudiantDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static List<Etudiant> listeEtudiant = new ArrayList<Etudiant>();
	static HashMap<List <String> ,List <Double>  > elevenote = new HashMap<>();
	static List<String> matieres = new ArrayList<String>();
	HashMap<Matieres,List <Double>> test = new HashMap<Matieres, List <Double>>();

	
	public List<Etudiant> getAll() {

		return listeEtudiant;
	}

	public void add() {

		OutputStream w;
		ObjectOutputStream dos;
		

		File file = new File("bd\\EtudiantTest.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			try {

				w = new FileOutputStream(file);
				dos = new ObjectOutputStream(w);

				dos.writeObject(getAll());

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} else {
			try {

				w = new FileOutputStream(file);
				dos = new ObjectOutputStream(w);
				dos.writeObject(getAll());

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

	public Etudiant update(Etudiant e) {

		OutputStream w;
		ObjectOutputStream dos;
		List<Etudiant> l = getAll();

		File file = new File("EtudiantFx.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			try {

				w = new FileOutputStream(file);
				dos = new ObjectOutputStream(w);
				for (Etudiant et : l) {
					if (e.getIdProperty() == et.getIdProperty()) {
						et.getNomProperty().equals(e.getNomProperty());
						et.getPrenomProperty().equals(e.getPrenomProperty());
						et.getDateProperty().equals(e.getDateProperty());
						// et.getFile().equals(e.getFile());
					}
					dos.writeObject(et);

				}

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} else {
			try {

				w = new FileOutputStream(file);
				dos = new ObjectOutputStream(w);
				for (Etudiant et : l) {
					dos.writeObject(et);
				}

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public static List<Etudiant> restitutionEtudiant() {
		File file = new File("bd\\EtudiantTest.txt");
		List<Etudiant> listeEtudiants = new ArrayList<Etudiant>();
		try {

			if (file.length() != 0) {
				FileInputStream os = new FileInputStream("bd\\EtudiantTest.txt");
				ObjectInputStream ois = new ObjectInputStream(os);
				listeEtudiant = (List<Etudiant>) ois.readObject();
				ois.close();
			} else {
				return listeEtudiants;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return listeEtudiants;
		} catch (IOException e) {
			e.printStackTrace();
			return listeEtudiants;
		}
		
		return listeEtudiant;

	}

	@Override
	public void add(Etudiant e) {
		// TODO Auto-generated method stub

	}

}
