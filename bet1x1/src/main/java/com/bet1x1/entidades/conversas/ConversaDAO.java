package com.bet1x1.entidades.conversas;

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
import com.bet1x1.entidades.usuarios.Usuario;







public class ConversaDAO extends DAOJPA{


	



	public void save(Conversa instancia) throws PersistenciaException {
		
		
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
	
	public void update(Conversa instancia) throws PersistenciaException {
		
		EntityManager em = getEm();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Conversa resultado = instancia;
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

	
	public void delete(Conversa obj) throws  PersistenciaException{
		EntityManager em = getEm();
		try {
			em.getTransaction().begin();
			obj = em.find(Conversa.class, obj.getId());
			em.remove(obj);
			em.getTransaction().commit();
			
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		}
		finally {
			em.close();
		}
	
	}

	
	public List<Conversa> recuperarTodas (){
		
		EntityManager em = getEm();
		List<Conversa> resultado = null;
		try {
			TypedQuery<Conversa> query = em.createQuery("SELECT c FROM Conversa c", Conversa.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		
		ArrayList<Conversa> resultadoFinal  = new ArrayList<Conversa>();
		for(Conversa d: resultado) {
			resultadoFinal.add(d);
		}
		return resultadoFinal;
		
		
	}
	
	
	
	public List<Conversa> pegarDeUmUsuario(Usuario usuario) throws PersistenciaException{
		
		
		
		EntityManager em = getEm();
		List<Conversa> resultado = null;
		try {
			TypedQuery<Conversa> query = em.createQuery("SELECT c FROM Conversa c JOIN c.remetente r JOIN c.destinatario d "
					+ "WHERE (r.id = :idusuario OR d.id = :idusuario)", Conversa.class);
			query.setParameter("idusuario", usuario.getId());
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		

		return resultado;
	}
 	
	
	
	public Conversa  getById(Long id) throws PersistenciaException{
		
		EntityManager em = getEm();
		Conversa resultado = null;
		try {
			resultado = em.find(Conversa.class, id);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			System.out.println("exceção no metodo getbyid do Conversa dao");
			
		} finally {
			em.close();
		}

		return resultado;
		
	}
	
	

	

	




	
}
