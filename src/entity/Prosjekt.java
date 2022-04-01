package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "prosjekt", schema="oblig3")

public class Prosjekt {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prosjektid;
	
	
	private String prosjektnavn;
	private String prosjektbeskrivelse;
	
	public Prosjekt() {
		
	}
	
	public Prosjekt(String prosjektnavn, String prosjektbeskrivelse) {
		this.prosjektnavn = prosjektnavn; 
		this.prosjektbeskrivelse = prosjektbeskrivelse;
	}

	public int getProsjektid() {
		return prosjektid;
	}

	public void setProsjektid(int prosjektid) {
		this.prosjektid = prosjektid;
	}

	public String getProsjektnavn() {
		return prosjektnavn;
	}

	public void setProsjektnavn(String prosjektnavn) {
		this.prosjektnavn = prosjektnavn;
	}

	public String getProsjektbeskrivelse() {
		return prosjektbeskrivelse;
	}

	public void setProsjektbeskrivelse(String prosjektbeskrivelse) {
		this.prosjektbeskrivelse = prosjektbeskrivelse;
	}
}
