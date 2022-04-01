package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entity.Ansatt;
import entity.Prosjekt;
import entity.Prosjektdeltakelse;

public class ProsjektdeltakelseDAO {

	private EntityManagerFactory emf;
	
	public ProsjektdeltakelseDAO() {
		emf = Persistence.createEntityManagerFactory("obl");
	}
	
	public boolean registrereProsjektdeltakelse(Ansatt ansattid, Prosjekt prosjektid, int antallheletimer, String rolle) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		boolean fullfort = false; 
		try {
			tx.begin();
			Prosjektdeltakelse prodel = new Prosjektdeltakelse(ansattid, prosjektid);
			em.persist(prodel);
			tx.commit();
			fullfort = true;
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		
		return fullfort;
	}
	
	public void listProsjektdeltakelse() {
		String queryString = "SELECT p FROM Prosjekt p";
		EntityManager em = emf.createEntityManager();
		
		List<Prosjektdeltakelse> pdeltakelse;
		
		try {
			TypedQuery<Prosjektdeltakelse> query = em.createQuery(queryString, Prosjektdeltakelse.class);
			pdeltakelse = query.getResultList();
			for(Prosjektdeltakelse prosjektdel : pdeltakelse) {
				System.out.println(prosjektdel);
			}
		} finally {
			em.close();
		}
	}
}
