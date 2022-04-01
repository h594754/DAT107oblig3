package dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entity.Ansatt;

public class AnsattDAO {
	
	private EntityManagerFactory emf;
	
	public AnsattDAO() {
		emf = Persistence.createEntityManagerFactory("obl");
	}
	
	public Ansatt finnAnsattMedId(int id) {
		EntityManager em = emf.createEntityManager();
		
		Ansatt ansatt = null; 
		
		try {
			ansatt = em.find(Ansatt.class, id);
		} finally {
			em.close();
		}
	return ansatt; 	
	}
	
	public Ansatt finnAnsattMedBrukernavn(String brukernavn) {
		EntityManager em = emf.createEntityManager();
		
		
		try {
			TypedQuery<Ansatt> query = em.createQuery("SELECT a FROM Ansatt a WHERE a.brukernavn LIKE :brukernavn", Ansatt.class);
			query.setParameter("brukernavn", brukernavn);
			
			return query.getSingleResult();
		} finally {
			em.close();
		}
	}
	
	public void listAlleAnsatte() {
		String queryString= "SELECT a FROM Ansatt a";
		EntityManager em = emf.createEntityManager();
		List<Ansatt> ansatte;
		
		try {
			TypedQuery<Ansatt> query = em.createQuery(queryString, Ansatt.class);
			ansatte = query.getResultList();
			for(Ansatt ansatt : ansatte) {
				System.out.println(ansatt);
			}
		}  finally {
			em.close();
		}
	}
	
	public void oppdaterAnsatt(Ansatt ansatt) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			em.persist(ansatt);
			tx.commit();
		} catch(Throwable e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
	}
}
	
	public boolean leggTilAnsatt(String brukernavn, String fornavn, String etternavn, LocalDate ansettelsesdato, String stilling, int manedslonn, int avdeling) {
		EntityManager em  = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		
		boolean fullfort = false; 
		try {
			tx.begin();
			Ansatt ansatt = new Ansatt(brukernavn, fornavn, etternavn, ansettelsesdato, stilling, manedslonn, avdeling);
			em.persist(ansatt);
			tx.commit();
			fullfort = true;
			System.out.println(brukernavn + " har blitt lagt til");
			
		} catch(Throwable e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		return fullfort;
	}
	
	
	
}
