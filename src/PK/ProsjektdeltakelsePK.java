package PK;

public class ProsjektdeltakelsePK {
	private int ansattid;
	private int prosjektid;
	
	public ProsjektdeltakelsePK() {
		
	}
	
	public ProsjektdeltakelsePK(int ansattid, int prosjektid) {
		this.ansattid = ansattid;
		this.prosjektid = prosjektid;
	}
}
