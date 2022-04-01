package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entity.Prosjekt;

public class ProsjektDAO {

	private EntityManagerFactory emf;
	
	public ProsjektDAO() {
		emf = Persistence.createEntityManagerFactory("obl");
	}
	
	public boolean leggTilProsjekt(String prosjektnavn, String prosjektbeskrivelse) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		boolean fullfort = false;
		try {
			tx.begin();
			Prosjekt prosjekt = new Prosjekt(prosjektnavn, prosjektbeskrivelse);
			em.persist(prosjekt);
			tx.commit();
			fullfort = true;
			System.out.println("Et nytt prosjekt har blitt lagt til!");
		} catch(Throwable e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		return fullfort;
	}
	
	public void listAlleProsjekt() {
		String queryString = "SELECT t FROM Prosjekt t";
		EntityManager em = emf.createEntityManager();
		List<Prosjekt> prosjekter;
		
		try {
			TypedQuery<Prosjekt> query = em.createQuery(queryString, Prosjekt.class);
			prosjekter = query.getResultList();
			for(Prosjekt prosjekt : prosjekter) {
				System.out.println(prosjekt);
			}
		} finally {
			em.close();
		}
	}

	
}
