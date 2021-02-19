package model;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

import javafx.scene.control.Button;

public class Etudiant implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idProperty;
	private String nomProperty;
	private String prenomProperty;
	private String dateProperty;
	private String file;
	private String image;
    public static AtomicLong NEXT_ID = new AtomicLong(1);
	static Button modifyProperty;
	static Button deleteProperty;
	public static boolean  incrementId = false;
	public Etudiant() {

	}

	public Etudiant(String nomProperty, String prenomProperty, String dateProperty, String file) {
		this.idProperty = NEXT_ID.getAndIncrement();
		this.nomProperty = nomProperty;
		this.prenomProperty = prenomProperty;
		this.dateProperty = dateProperty;
		this.file = file;
		
	}

	@Override
	public String toString() {
		return "Etudiant [idProperty=" + idProperty + ", nomProperty=" + nomProperty + ", prenomProperty="
				+ prenomProperty + ", dateProperty=" + dateProperty + ", file=" + file + ", image=" + image + "]";
	}

	public Long getIdProperty() {
		return idProperty;
	}

	public void setIdProperty(Long idProperty) {
		this.idProperty = idProperty;
	}

	public String getNomProperty() {
		return nomProperty;
	}

	public void setNomProperty(String nomProperty) {
		this.nomProperty = nomProperty;
	}

	public String getPrenomProperty() {
		return prenomProperty;
	}

	public void setPrenomProperty(String prenomProperty) {
		this.prenomProperty = prenomProperty;
	}

	public String getDateProperty() {
		return dateProperty;
	}

	public void setDateProperty(String dateProperty) {
		this.dateProperty = dateProperty;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public static AtomicLong getNEXT_ID() {
		return NEXT_ID;
	}

	public static void setNEXT_ID(AtomicLong nEXT_ID) {
		NEXT_ID = nEXT_ID;
	}



}
