package com.bet1x1.entidades.disputantes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.bet1x1.entidades.DAOJPA;
import com.bet1x1.entidades.disputantes.Disputante;
import com.bet1x1.entidades.eventos.Evento;
import com.bet1x1.entidades.excecoes.PersistenciaException;
import com.bet1x1.entidades.modalidades.Modalidade;
import com.bet1x1.entidades.scores.Score;







public class DisputanteDAO extends DAOJPA{


	



	public void save(Disputante instancia) throws PersistenciaException {
		
		
		EntityManager em = null;
	
		try {
			em = getEm();
			em.getTransaction().begin();
			
				
			em.persist(instancia);
				
			em.getTransaction().commit();
			
			
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		
	}
	
	public void update(Disputante instancia) throws PersistenciaException {
		
		EntityManager em = getEm();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Disputante resultado = instancia;
		try {
			resultado = em.merge(instancia);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
		
		
	}

	
	public void delete(Disputante obj) throws  PersistenciaException{
		EntityManager em = getEm();
		try {
			em.getTransaction().begin();
			obj = em.find(Disputante.class, obj.getId());
			em.remove(obj);
			em.getTransaction().commit();
			
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		}
		finally {
			em.close();
		}
	
	}

	
	public List<Disputante> recuperarTodas (){
		
		EntityManager em = getEm();
		List<Disputante> resultado = null;
		try {
			TypedQuery<Disputante> query = em.createQuery("SELECT c FROM Disputante c", Disputante.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		
		ArrayList<Disputante> resultadoFinal  = new ArrayList<Disputante>();
		for(Disputante d: resultado) {
			resultadoFinal.add(d);
		}
		return resultadoFinal;
		
		
	}
	
	
	
	public List<Disputante> recuperarFullFiltro (Disputante disputante) throws PersistenciaException{
		
		EntityManager em = null;
		List<Disputante> results = null;
		try {
		
			em = getEm();
		
			TypedQuery<Disputante> query = em.createQuery("SELECT d FROM Disputante d JOIN d.modalidade m "
					+ "WHERE d.nome LIKE :disputanteNome AND m.id = :idmodalidade ORDER BY d.id", Disputante.class);
			query.setParameter("disputanteNome", disputante.getNome());
			query.setParameter("idmodalidade", disputante.getModalidade().getId());
			results = query.getResultList();
			
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			System.out.println("exceção no metodo getbyid do disputante dao");
			
		} finally {
			em.close();
		}

		return results;
		
		
	}
	
	public List<Disputante> retornarFiltroNomeLike (String nome) throws PersistenciaException{
		
		EntityManager em = null;
		List<Disputante> results = null;
		try {
		
			em = getEm();
		
			TypedQuery<Disputante> query = em.createQuery("SELECT d FROM Disputante d JOIN d.modalidade m "
					+ "WHERE d.nome LIKE :disputanteNome ORDER BY d.id", Disputante.class);
			query.setParameter("disputanteNome", nome);
			results = query.getResultList();
			
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			System.out.println("exceção no metodo getbyid do disputante dao");
			
		} finally {
			em.close();
		}

		return results;
		
		
	}
	
	public List<Disputante> retornarFiltroModalidade (Disputante disputante) throws PersistenciaException{
		
		EntityManager em = null;
		List<Disputante> results = null;
		try {
		
			em = getEm();
		
			TypedQuery<Disputante> query = em.createQuery("SELECT d FROM Disputante d JOIN d.modalidade m "
					+ "WHERE d.nome LIKE :disputanteNome AND m.id = :idmodalidade ORDER BY d.id", Disputante.class);
			query.setParameter("disputanteNome", disputante.getNome());
			query.setParameter("idmodalidade", disputante.getModalidade().getId());
			results = query.getResultList();
			
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			System.out.println("exceção no metodo getbyid do disputante dao");
			
		} finally {
			em.close();
		}

		return results;
		
		
	}
	
	
	
	public List<Disputante> recuperarTodasOrdemNome (){
		
		EntityManager em = null;
		List<Disputante> results = null;
		try {
		
			em = getEm();
		
			TypedQuery<Disputante> query = em.createQuery("SELECT c FROM Disputante c ORDER BY c.nome", Disputante.class);
			results = query.getResultList();
			
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			System.out.println("exceção no metodo getbyid do disputante dao");
			
		} finally {
			em.close();
		}

		return results;
		
		
	}
	
	
	public List<Disputante> retornarTodosDeUmaModalidade(Modalidade modalidade){
		
		

		EntityManager em = null;
		List<Disputante> results = null;
		
		if(modalidade == null) {
			return null;
		}
		
		try {
		
			em = getEm();
		
			TypedQuery<Disputante> query = em.createQuery("SELECT d FROM Disputante d JOIN d.modalidade m "
					+ "WHERE m.id = :idmodalidade", Disputante.class);
			query.setParameter("idmodalidade", modalidade.getId());
			results = query.getResultList();
			
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			
		} finally {
			em.close();
		}

		return results;
		
	}
	
	
	public Disputante  getById(Long id) throws PersistenciaException{
		
		EntityManager em = getEm();
		Disputante resultado = null;
		try {
			resultado = em.find(Disputante.class, id);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			System.out.println("exceção no metodo getbyid do disputante dao");
			
		} finally {
			em.close();
		}

		return resultado;
		
	}
	
	
	
	public Modalidade recuperarModalidade(Disputante disputante) throws PersistenciaException{
		
		EntityManager em = null;
		Modalidade results = null;
		
		try {
			
			em = getEm();
			TypedQuery<Modalidade> query = em.createQuery("SELECT m FROM Modalidade m JOIN m.disputantes d "
				+ " WHERE d.id = :iddisputante", 
				Modalidade.class);
			
			query.setParameter("iddisputante", disputante.getId());
			results = query.getSingleResult();
		
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			System.out.println("exceção no metodo getbyid do disputante dao");
			
		} finally {
			em.close();
		}

		return results;
		
	}
	
	
	public List<Disputante> retornarDisputantesDeUmEventoOrdemNome(Evento evento) throws PersistenciaException{
		
		EntityManager em = getEm();
		List<Disputante> resultado = null;
		try {
			
			TypedQuery<Disputante> query = em.createQuery("SELECT d FROM Disputante d JOIN d.scores s JOIN s.evento e "
					+ " WHERE e.id = :idevento ORDER BY s.jogaEmCasa DESC, d.nome "
					, Disputante.class);
			query.setParameter("idevento", evento.getId());
			resultado = query.getResultList();
			
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			
		} finally {
			em.close();
		}

		return resultado;
		
	}
	
	public Disputante retornarDisputanteDeScore(Score score) throws PersistenciaException{
		
		EntityManager em = getEm();
		Disputante resultado = null;
		try {
			TypedQuery<Disputante> query = em.createQuery("SELECT d FROM Disputante d JOIN d.scores s "
					+ " WHERE s.id = :idscore "
					, Disputante.class);
			query.setParameter("idscore", score.getId());
			resultado = query.getSingleResult();
			
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			
		} finally {
			em.close();
		}

		return resultado;
		
	}
	

	

	




	
}
