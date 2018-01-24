package com.bet1x1.entidades.modalidades;

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






public class ModalidadeDAO extends DAOJPA{


	



	public void save(Modalidade instancia) throws PersistenciaException {
		
		
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
	
	public void update(Modalidade instancia) throws PersistenciaException {
		
		
		EntityManager em = getEm();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Modalidade resultado = instancia;
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

	
	public List<Modalidade> recuperarTodas (){
		
		EntityManager em = getEm();
		List<Modalidade> resultado = null;
		try {
			TypedQuery<Modalidade> query = em.createQuery("SELECT c FROM Modalidade c", Modalidade.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		
		ArrayList<Modalidade> resultadoFinal  = new ArrayList<Modalidade>();
		for(Modalidade d: resultado) {
			resultadoFinal.add(d);
		}
		return resultadoFinal;
		
		
	}
	
	public List<Modalidade> recuperarTodasOrdemNome () throws PersistenciaException{
		
		
		EntityManager em = getEm();
		List<Modalidade> results = null;
		try {
			TypedQuery<Modalidade> query = em.createQuery("SELECT c FROM Modalidade c ORDER BY c.nome", Modalidade.class);
			results = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			
		} finally {
			em.close();
		}

		return results;
		
		
	}
	
	public Modalidade  getById(Long id) {
		
		EntityManager em = getEm();
		Modalidade resultado = null;
		try {
			resultado = em.find(Modalidade.class, id);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			
		} finally {
			em.close();
		}

		return resultado;
		
	}
	

	







	
}
