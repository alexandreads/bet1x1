package com.bet1x1.entidades.competicoes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.bet1x1.entidades.DAOJPA;
import com.bet1x1.entidades.excecoes.PersistenciaException;
import com.bet1x1.entidades.modalidades.Modalidade;




public class CompeticaoDAO extends DAOJPA{


	



	public void save(Competicao instancia) throws PersistenciaException {
		
		EntityManager em = null;
		
		try {
			
			em = getEm();
			em.getTransaction().begin();
			em.persist(instancia);
			
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		} finally {
			em.close();
		}
	
		
			
			
		
	}

	
	public List<Competicao> recuperarTodas (){
		
		

		EntityManager em = null;
		List<Competicao> results = null;
		
		try {
			em = getEm();
			
			TypedQuery<Competicao> query = em.createQuery("SELECT c FROM Competicao c", Competicao.class);
			results = query.getResultList();
			  
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		
		} finally {
			em.close();
		}
		
		return results;
		
		
	}
	
	public List<Competicao> recuperarTodasOrdemNome (){
		EntityManager em = null;
		List<Competicao> results = null;
		
		try {
			em = getEm();
			TypedQuery<Competicao> query = em.createQuery("SELECT c FROM Competicao c ORDER BY c.nome", Competicao.class);
			results = query.getResultList();
			  
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		
		} finally {
			em.close();
		}
		
		return results;
		
		
	}
	
	public List<Competicao> recuperarTodasDeUmaModalidadeOrdemNome (Modalidade modalidade) throws PersistenciaException{
		
		EntityManager em = null;
		List<Competicao> results = null;
		
		try {
			
			em = getEm();
			// se modalidade nula retorna tudo
			if(modalidade == null) {
				
				
				TypedQuery<Competicao> query = em.createQuery("SELECT c FROM Competicao c ORDER BY c.nome", Competicao.class);
				results = query.getResultList();
					  
				return results;
				
			} else {
				
				
				TypedQuery<Competicao> query = em.createQuery("SELECT c FROM Competicao c JOIN c.modalidade m "
						+ "WHERE m.id = :idmodalidade ORDER BY c.nome", 
						Competicao.class);
				query.setParameter("idmodalidade", modalidade.getId());
				results = query.getResultList();
					  
				return results;
			}
		
		} catch (PersistenceException pe) {
		pe.printStackTrace();
		
		} finally {
			em.close();
		}


		return results;
		
	}
	
	public Competicao  getById(Long id) {
		
		

		EntityManager em = getEm();
		Competicao resultado = null;
		
		if(id == null) {
			
			return null;
		}
		
		try {
			resultado = em.find(Competicao.class, id);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			
		} finally {
			em.close();
		}

		return resultado;
		
			  
		
	}
	
	
	


	
}
