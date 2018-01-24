package com.bet1x1.entidades.scores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.bet1x1.entidades.DAOJPA;
import com.bet1x1.entidades.eventos.Evento;
import com.bet1x1.entidades.excecoes.PersistenciaException;
import com.bet1x1.entidades.usuarios.Usuario;






public class ScoreDAO extends DAOJPA{


	



	public void save(Score instancia) throws PersistenciaException {
		
		
		EntityManager em = getEm();
		try {
			em.persist(instancia);
			em.getTransaction().commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		
		
		
	}
	
	public void update(Score instancia) throws PersistenciaException {
		
		
		EntityManager em = getEm();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Score resultado = instancia;
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

	
	public List<Score> recuperarTodas (){
		
		EntityManager em = getEm();
		List<Score> resultado = null;
		try {
			TypedQuery<Score> query = em.createQuery("SELECT c FROM Score c", Score.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		
		ArrayList<Score> resultadoFinal  = new ArrayList<Score>();
		for(Score d: resultado) {
			resultadoFinal.add(d);
		}
		return resultadoFinal;
		
		
	}
	
	public List<Score> recuperarTodasOrdemNome () throws PersistenciaException{
		
		
		EntityManager em = getEm();
		List<Score> results = null;
		try {
			TypedQuery<Score> query = em.createQuery("SELECT c FROM Score c ORDER BY c.nome", Score.class);
			results = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			
		} finally {
			em.close();
		}

		return results;
		
		
	}
	
	public Score  getById(Long id) {
		
		EntityManager em = getEm();
		Score resultado = null;
		try {
			resultado = em.find(Score.class, id);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			
		} finally {
			em.close();
		}

		return resultado;
		
	}
	
	public List<Score> retornarListaBaseEvento(Evento evento) throws PersistenciaException{
		
		EntityManager em = getEm();
		List<Score> results = null;
		try {
			TypedQuery<Score> query = em.createQuery("SELECT s FROM Score s JOIN s.evento e WHERE "
					+ "e.id = :idevento ORDER BY s.jogaEmCasa DESC", Score.class);
			query.setParameter("idevento", evento.getId());
			results = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			
		} finally {
			em.close();
		}

		return results;
		
		
	}
	

	







	
}
