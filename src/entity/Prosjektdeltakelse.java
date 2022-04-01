package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import PK.ProsjektdeltakelsePK;

@Entity
@Table(name ="Prosjektdeltakelse", schema = "oblig3")
@IdClass(ProsjektdeltakelsePK.class)


public class Prosjektdeltakelse {
	@Id
	@ManyToOne
	@JoinColumn(name = "ansattid")
	private Ansatt ansattid;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "prosjektid")
	private Prosjekt prosjektid;
	
	private int antallheletimer;
	private String rolle;
	
	
	public Prosjektdeltakelse() {
		
	}
	
	public Prosjektdeltakelse(Ansatt ansattid, Prosjekt prosjektid) {
		
	}

	public Ansatt getAnsattid() {
		return ansattid;
	}

	public void setAnsattid(Ansatt ansattid) {
		this.ansattid = ansattid;
	}

	public Prosjekt getProsjektid() {
		return prosjektid;
	}

	public void setProsjektid(Prosjekt prosjektid) {
		this.prosjektid = prosjektid;
	}

	public int getAntallheletimer() {
		return antallheletimer;
	}

	public void setAntallheletimer(int antallheletimer) {
		this.antallheletimer = antallheletimer;
	}

	public String getRolle() {
		return rolle;
	}

	public void setRolle(String rolle) {
		this.rolle = rolle;
	}
	
}
