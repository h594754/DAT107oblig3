package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entity.Ansatt;
import entity.Avdeling;

public class AvdelingDAO {
	
	private EntityManagerFactory emf; 
	
	public AvdelingDAO () {
		emf = Persistence.createEntityManagerFactory("obl");
	}
	
	public Avdeling finnAvdelingMedID(int avdelingId) {
		
		EntityManager em = emf.createEntityManager();
		Avdeling a1;
		
		try {
			a1 = em.find(Avdeling.class, avdelingId);
		} finally {
			em.close();
		}
		return a1;
	}
	
	public List<Ansatt> alleAnsatteAvdeling(String navn) {
		String queryString = "SELECT a FROM Ansatt a WHERE a.avdeling = :navn";
		
		EntityManager em = emf.createEntityManager();
		List<Ansatt> ansatte = null;
		
		try {
			TypedQuery<Ansatt> query = em.createQuery(queryString, Ansatt.class);
			query.setParameter("navn", navn);
			ansatte = query.getResultList();
		} catch(NoResultException e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return ansatte; 
	}
	
	public void oppdaterAvdelingAnsatt(Ansatt ansatt) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			em.merge(ansatt);
			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			if(tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
	}
	
	public void leggTilAvdeling(Avdeling avdeling) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			em.persist(avdeling);
			tx.commit();
		} catch(Throwable e) {
			e.printStackTrace();
			if(tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
	}
}
