package com.bet1x1.entidades.eventos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.bet1x1.entidades.DAOJPA;
import com.bet1x1.entidades.apostas.Aposta;
import com.bet1x1.entidades.competicoes.Competicao;
import com.bet1x1.entidades.disputantes.Disputante;
import com.bet1x1.entidades.eventos.Evento;
import com.bet1x1.entidades.excecoes.PersistenciaException;
import com.bet1x1.entidades.mensagens.Mensagem;
import com.bet1x1.entidades.modalidades.Modalidade;
import com.bet1x1.utilitarios.Outros;






public class EventoDAO extends DAOJPA{


	



	public void save(Evento instancia) throws PersistenciaException {
		
		
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

	
	public List<Evento> recuperarTodas (){
		
		EntityManager em = getEm();
		List<Evento> resultado = null;
		try {
			TypedQuery<Evento> query = em.createQuery("SELECT c FROM Evento c", Evento.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		
		ArrayList<Evento> resultadoFinal  = new ArrayList<Evento>();
		for(Evento d: resultado) {
			resultadoFinal.add(d);
		}
		return resultadoFinal;
		
		
	}
	
	public Evento getEventoByAposta(Aposta aposta) {
		
		EntityManager em = getEm();
		Evento resultado = null;
		try {
			TypedQuery<Evento> query = em.createQuery("SELECT e FROM Evento e JOIN e.apostas a "
					+ "WHERE a.id = :idaposta", Evento.class);
			query.setParameter("idaposta", aposta.getId());
			resultado = query.getSingleResult();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		
		return resultado;
		
	}
	
	public List<Evento> recuperarTodasOrdemNome (){
		EntityManager em = getEm();
		
		TypedQuery<Evento> query = em.createQuery("SELECT c FROM Evento c ORDER BY c.nome", Evento.class);
		List<Evento> results = query.getResultList();
			  
		return results;
		
		
	}
	
	public Evento  getById(Long id) throws PersistenciaException {
		
		EntityManager em = null;
		Evento resultado = null;
		
		if(id == null) {
			
			return null;
		}
		try {
			em = getEm();
			
			resultado = em.find(Evento.class, id);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			
		} finally {
			em.close();
		}

		return resultado;
		
	}
	
	public List<Evento> listaFiltro(Modalidade modalidade, Competicao competicao, Date dataAte, 
								Date dataAPartir, Integer estado) throws PersistenciaException{
		
		EntityManager em = null;
		
		List<Evento> resultado = null;
		
		
	
		try {
		
			em = getEm();
			
			String jpql = "SELECT e FROM Evento e JOIN e.competicao c JOIN c.modalidade m  WHERE 1 = 1 ";
			
			if (notNull(modalidade)) {
				jpql += "AND m.id = :idModalidade ";
			}

			if (notNull(competicao)) {
				jpql += "AND c.id = :idCompeticao ";
			}
			
			if(notNull(dataAte)) {
				jpql += "AND e.data <= :dataAte ";
			}
			
			if(notNull(dataAPartir)) {
				jpql += "AND e.data >= :dataAPartir ";
			}
			
			if(notNull(estado)) {
				jpql += "AND e.estado = :estado ";
			}
						
			
		
			TypedQuery<Evento> query = em.createQuery(jpql, Evento.class);
			
			if (notNull(modalidade)) {
				query.setParameter("idModalidade", modalidade.getId());
			}

			if (notNull(competicao)) {
				query.setParameter("idCompeticao", competicao.getId());
			}
			
			if(notNull(dataAte)) {
				query.setParameter("dataAte", dataAte);
				
			}
			
			if(notNull(dataAPartir)) {
				query.setParameter("dataAPartir", dataAPartir);
				
			}
			
			if(notNull(estado)) {
				query.setParameter("estado", estado);
				
			}
			
			
			resultado = query.getResultList();
		
		
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		}
	
		return resultado;
		
	}
	
	
	public List<Evento> retornarListaEventosAgoraEDaqui3Min(Date agora, Date agora3Min) throws PersistenciaException{
		
		// se modalidade nula retorna tudo
		
		
		
		
		EntityManager em = null;
		
		List<Evento> results = null;
		
		String agoraString = Outros.dateToFormatoBD(agora);
		String agora3MinString  = Outros.dateToFormatoBD(agora3Min);
		
		System.out.println(agoraString +"   "+agora3MinString);
		
		
		try {
			em = getEm();
		
							
			TypedQuery<Evento> query = em.createQuery("SELECT e FROM Evento e WHERE "
					+ "e.data > "+agoraString+" AND e.data < "+agora3MinString+" AND e.estado = 0 ", Evento.class);
			
			results = query.getResultList();
					
		
			
		} catch (PersistenceException pe) {
			
			System.out.println(pe.getMessage());
			pe.printStackTrace();
			
		
		} finally {
			em.close();
		}
		
		if(results == null) {
			
			System.out.println("nulo nada");
		}
		
		else {
			System.out.println("alguma coisa");
		}

		return results;
		
	}
	
	public Modalidade recuperarModalidade(Evento evento) throws PersistenciaException{
		
		EntityManager em = null;
		
		Modalidade results = null;
		
		try {
			em = getEm();
			

			
			TypedQuery<Modalidade> query = em.createQuery("SELECT m FROM Modalidade m JOIN m.competicoes c JOIN c.eventos e "
					+ " WHERE e.id = :idevento", 
					Modalidade.class);
			
			query.setParameter("idevento", evento.getId());
			results = query.getSingleResult();
		
			
			
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			
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
	
	

	public void setarComoFechados(List<Evento> eventos) throws PersistenciaException {
		

		EntityManager em = getEm();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		
		try {
			for(Evento e: eventos) {
				e.setEstado(EstadosDoEvento.FECHADO);
				e  = em.merge(e);
				
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
