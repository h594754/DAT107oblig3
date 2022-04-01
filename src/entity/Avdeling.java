package entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "avdeling", schema ="oblig3")


public class Avdeling {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int avdelingId;
	private String avdelingsnavn;
	private int avdelingssjef;
	
	private List<Ansatt> avdListe;
	
	public List<Ansatt> getAvdListe() {
		return avdListe;
	}

	public void setAvdListe(List<Ansatt> avdListe) {
		this.avdListe = avdListe;
	}

	public Avdeling() {
		
	}
	
	public Avdeling(String avdelingsnavn, int avdelingssjef) {
		this.avdelingsnavn = avdelingsnavn;
		this.avdelingssjef = avdelingssjef;
	}
	
	public int getAvdelingssjef() {
		return avdelingssjef;
	}
	
	public void setAvdelingssjef(int avdelingssjef) {
		this.avdelingssjef = avdelingssjef;
	}
	
	public String getAvdelingsnavn() {
		return avdelingsnavn; 
	}
	
	public void setAvdelingsnavn(String avdelingsnavn) {
		this.avdelingsnavn = avdelingsnavn;
	}
	
	public int getAvdelingId() {
		return avdelingId;
	}
	
	public void setAvdelingId(int avdelingId) {
		this.avdelingId = avdelingId;
	}

	@Override
	public String toString() {
		return "Avdeling [avdelingId=" + avdelingId + ", avdelingsnavn=" + avdelingsnavn + ", avdelingssjef="
				+ avdelingssjef + "]";
	}
	
	public void skrivUt() {
		System.out.println("Informasjon om avdeling " + avdelingId +  ":" + "\nAvdelingsnavn: " + avdelingsnavn + "\nAvdelingssjef: " + avdelingssjef); 
	}
	
	
	
}
