package com.bet1x1.entidades.amizades;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.bet1x1.entidades.DAOJPA;
import com.bet1x1.entidades.excecoes.PersistenciaException;
import com.bet1x1.entidades.usuarios.Usuario;







public class AmizadeDAO extends DAOJPA{


	



	public void save(Amizade instancia) throws PersistenciaException {
		
		
		EntityManager em = getEm();
		try {
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
	
	public void update(Amizade instancia) throws PersistenciaException {
		
		
		EntityManager em = getEm();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Amizade resultado = instancia;
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
	
	public void delete(Amizade instancia) throws PersistenciaException {
		
		if(instancia != null)
		System.out.println("aqui cancelar2");
		EntityManager em = getEm();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			instancia = em.find(Amizade.class, instancia.getId());
			em.remove(instancia);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaException();
		} finally {
			em.close();
		}
		
	}


	
	public List<Amizade> recuperarTodas (){
		
		EntityManager em = getEm();
		List<Amizade> resultado = null;
		try {
			TypedQuery<Amizade> query = em.createQuery("SELECT c FROM Amizade c", Amizade.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		
	
		return resultado;
		
		
	}
	
	
	public Amizade  getById(Long id) {
		
		EntityManager em = getEm();
		Amizade resultado = null;
		try {
			resultado = em.find(Amizade.class, id);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			
		} finally {
			em.close();
		}

		return resultado;
		
	}
	
	
	public List<Amizade> recuperarTodasUsuarioEfetivadas (Usuario usuario) throws PersistenciaException {
		
		EntityManager em = getEm();
		List<Amizade> resultado = null;
		try {
			TypedQuery<Amizade> query = em.createQuery("SELECT a FROM Amizade a JOIN a.amigo1 u1 JOIN a.amigo2 u2 "
					+ "WHERE (u1.id = :idusuario OR u2.id = :idusuario) AND a.estado = 1", Amizade.class);
			query.setParameter("idusuario", usuario.getId());
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		
	
		return resultado;
		
		
	}
	
	
	
	
	public List<Amizade> recuperarTodasUsuarioRecebidas (Usuario usuario) throws PersistenciaException {
		
		EntityManager em = getEm();
		List<Amizade> resultado = null;
		try {
			TypedQuery<Amizade> query = em.createQuery("SELECT a FROM Amizade a JOIN a.amigo1 u1 JOIN a.amigo2 u2 "
					+ "WHERE (u2.id = :idusuario) AND a.estado = 0", Amizade.class);
			query.setParameter("idusuario", usuario.getId());
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		
	
		return resultado;
		
	}
	
	
	
	public List<Amizade> recuperarTodasUsuarioEnviados (Usuario usuario) throws PersistenciaException {
		
		EntityManager em = getEm();
		List<Amizade> resultado = null;
		try {
			TypedQuery<Amizade> query = em.createQuery("SELECT a FROM Amizade a JOIN a.amigo1 u1 JOIN a.amigo2 u2 "
					+ "WHERE (u1.id = :idusuario) AND a.estado = 0", Amizade.class);
			query.setParameter("idusuario", usuario.getId());
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		
	
		return resultado;
		
	}
	
	
	
	
	public Amizade entreDoisUsuarios(Usuario usuario1, Usuario usuario2) throws PersistenciaException {
		
		
		EntityManager em = getEm();
		List<Amizade> resultado = null;
		
		try {
				
			TypedQuery<Amizade> query = em.createQuery("SELECT a FROM Amizade a JOIN a.amigo1 u1 JOIN a.amigo2 u2 "
					+ "WHERE (u1.id = :idusuario1 AND u2.id = :idusuario2) OR "
					+ "(u1.id = :idusuario2 AND u2.id = :idusuario1)", Amizade.class);
			query.setParameter("idusuario1", usuario1.getId());
			query.setParameter("idusuario2", usuario2.getId());
			resultado = query.getResultList();
		} catch (PersistenceException e) {
			System.out.println("exceção");
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		if(resultado.size() > 0) {
			return resultado.get(0);
			
		}
	
		return null;
		
		
		
	}
	
	
	
	
	







	
}
