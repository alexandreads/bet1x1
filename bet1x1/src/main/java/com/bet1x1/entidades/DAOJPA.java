package com.bet1x1.entidades;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class DAOJPA {
	//em suas apps sempre crie esta factory somente uma vez, pois eh custoso cria-la	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdbet1x1");

	//private EntityManager em = null;

	public DAOJPA () {
		
			//em = emf.createEntityManager();
		
	}

	public static EntityManager createAndInitEntityManager() {
			return emf.createEntityManager();
	}

	public static void closeEntityManagerFactory() { 
		if (emf != null && emf.isOpen()) {
			emf.close();
			emf = null;
		}
	}

//	public void closeEntityManager() {
//		if (em != null && em.isOpen()) {
//			em.close();
//			em = null;
//		}
//	}

	public EntityManager getEm() {
		return emf.createEntityManager();
	}

//	public void setEm(EntityManager em) {
//		this.em = em;
//	}

	
	public boolean notEmpty(String obj) {
		
		if(obj !=  null) {
			if (!obj.trim().isEmpty()){
				return true;
			}
		}
		return false;
	}
	
	public boolean notNull(Object obj) {
		
		if(obj !=  null) {
			return true;
		}
		return false;
	}



	
	


}
