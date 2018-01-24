package com.bet1x1.entidades.usuarios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.bet1x1.entidades.DAOJPA;
import com.bet1x1.entidades.excecoes.PersistenciaException;





public class UsuarioDAO extends DAOJPA{


	



	public void save(Usuario instancia) throws PersistenciaException {
		
		
		EntityManager em = null;
		
		try {
			
			em = getEm();
			em.getTransaction().begin();
			
			em.persist(instancia);
			
			em.getTransaction().commit();
			
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}

		
	}
	
	public void update(Usuario instancia) throws PersistenciaException {
		
		EntityManager em = getEm();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Usuario resultado = instancia;
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

	
	public List<Usuario> recuperarTodas (){
		
		EntityManager em = getEm();
		List<Usuario> resultado = null;
		try {
			TypedQuery<Usuario> query = em.createQuery("SELECT c FROM Usuario c", Usuario.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		
		ArrayList<Usuario> resultadoFinal  = new ArrayList<Usuario>();
		for(Usuario d: resultado) {
			resultadoFinal.add(d);
		}
		return resultadoFinal;
		
		
	}
	
	public List<Usuario> recuperarTodasOrdemNome (){
		
		
		EntityManager em = null;
		List<Usuario> results = null;
		
		try {
			em = getEm();
			TypedQuery<Usuario> query = em.createQuery("SELECT c FROM Usuario c ORDER BY c.nome", Usuario.class);
			results = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		
			  
		return results;
		
		
	}
	
	public Usuario  getById(Long id) throws PersistenciaException {
		
		EntityManager em = getEm();
		Usuario resultado = null;
		
		if(id == null) {
			return null;
		}
		
		try {
			resultado = em.find(Usuario.class, id);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			
		} finally {
			em.close();
		}

		return resultado;
		
	}
	
	public Usuario  getByLogin(String login) throws PersistenciaException {
		
		EntityManager em = getEm();
		Usuario resultado = null;
		
		try {
			TypedQuery<Usuario> query = em.createQuery("SELECT c FROM Usuario c WHERE c.login = :loginUsuario", Usuario.class);
			query.setParameter("loginUsuario", login);
			resultado = query.getSingleResult();
		} catch (NoResultException e) {
			resultado = new Usuario();
		} catch (NonUniqueResultException e) {
				resultado = new Usuario();
		}catch (PersistenceException pe) {
			pe.printStackTrace();
			
		} finally {
			em.close();
		}
		

		return resultado;
		
	}
	
	
	public List<Usuario> retornarPotenciaisDesafiados(Usuario usuario) throws PersistenciaException {
		
		
		EntityManager em = null;
		List<Usuario> resultado = null;

		try {
			
			em = getEm();
			
			String jpql = "SELECT u FROM Usuario u WHERE u.id != :idusuario ";
			
			TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
			query.setParameter("idusuario", usuario.getId());
			

			resultado = query.getResultList();
			
			
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		}
		
		return resultado;
		
	}
	
	public List<Usuario> retornarDeFiltro(Usuario usuario) throws PersistenciaException {
		
		
		EntityManager em = null;
		List<Usuario> resultado = null;

		try {
			
			em = getEm();
			
			String jpql = "SELECT u FROM Usuario u WHERE 1 = 1 ";
			
			// First name
			if (notEmpty(usuario.getNome())) {
				jpql += "AND u.nome LIKE :nome ";
			}

			// Last name
			if (notEmpty(usuario.getLogin())) {
				jpql += "AND u.login LIKE :login ";
			}

		
			TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
			
			// First name
			if (notEmpty(usuario.getNome())) {
				query.setParameter("nome", "%" + usuario.getNome() + "%");
			}

			// Last name
			if (notEmpty(usuario.getLogin())) {
				query.setParameter("login", "%" + usuario.getLogin() + "%");
			}

			resultado = query.getResultList();
			
			
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		}
		
		return resultado;
		
	}
	
	

	
		

	




	
}
