package com.bet1x1.entidades.mensagens;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.bet1x1.entidades.DAOJPA;
import com.bet1x1.entidades.conversas.Conversa;
import com.bet1x1.entidades.disputantes.Disputante;
import com.bet1x1.entidades.eventos.Evento;
import com.bet1x1.entidades.excecoes.PersistenciaException;
import com.bet1x1.entidades.modalidades.Modalidade;
import com.bet1x1.entidades.scores.Score;
import com.bet1x1.entidades.usuarios.Usuario;







public class MensagemDAO extends DAOJPA{


	



	public void save(Mensagem instancia) throws PersistenciaException {
		
		
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
	
	public void update(Mensagem instancia) throws PersistenciaException {
		
		EntityManager em = getEm();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Mensagem resultado = instancia;
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

	
	public void delete(Mensagem obj) throws  PersistenciaException{
		EntityManager em = getEm();
		try {
			em.getTransaction().begin();
			obj = em.find(Mensagem.class, obj.getId());
			em.remove(obj);
			em.getTransaction().commit();
			
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		}
		finally {
			em.close();
		}
	
	}

	
	public List<Mensagem> recuperarTodas (){
		
		EntityManager em = getEm();
		List<Mensagem> resultado = null;
		try {
			TypedQuery<Mensagem> query = em.createQuery("SELECT c FROM Mensagem c", Mensagem.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		

		return resultado;
		
		
	}
	
	
	
 	
	
	
	public Mensagem  getById(Long id) throws PersistenciaException{
		
		EntityManager em = getEm();
		Mensagem resultado = null;
		try {
			resultado = em.find(Mensagem.class, id);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			System.out.println("exceção no metodo getbyid do Conversa dao");
			
		} finally {
			em.close();
		}

		return resultado;
		
	}
	
	
	public List<Mensagem> pegarDeUmaConversa(Conversa conversa) throws PersistenciaException{
		
		EntityManager em = getEm();
		List<Mensagem> resultado = null;
		
		if(conversa == null) {
			
			return new ArrayList<Mensagem>();
		}
		
		try {
			TypedQuery<Mensagem> query = em.createQuery("SELECT m FROM Mensagem m JOIN m.conversa c "
					+ "WHERE c.id = :idconversa ORDER BY m.data ", Mensagem.class);
			query.setParameter("idconversa", conversa.getId());
			resultado = query.getResultList();
			
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		

		return resultado;
		
		
	}
	
	public void setarTodasComoLidas(List<Mensagem> mensagens) throws PersistenciaException {
		

		EntityManager em = getEm();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		
		try {
			for(Mensagem m: mensagens) {
				m.setLida(true);
				m = em.merge(m);
				
			}
			
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
		
	}
	

	

	




	
}
