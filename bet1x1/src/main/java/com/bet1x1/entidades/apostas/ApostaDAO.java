package com.bet1x1.entidades.apostas;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.bet1x1.entidades.DAOJPA;
import com.bet1x1.entidades.competicoes.Competicao;
import com.bet1x1.entidades.condicoes.TiposDeCondicao;
import com.bet1x1.entidades.disputantes.Disputante;
import com.bet1x1.entidades.eventos.EstadosDoEvento;
import com.bet1x1.entidades.eventos.Evento;
import com.bet1x1.entidades.eventos.EventoDAO;
import com.bet1x1.entidades.excecoes.PersistenciaException;
import com.bet1x1.entidades.modalidades.Modalidade;
import com.bet1x1.entidades.scores.Score;
import com.bet1x1.entidades.usuarios.Usuario;
import com.bet1x1.entidades.usuarios.UsuarioDAO;
import com.bet1x1.utilitarios.Outros;
import com.bet1x1.utilitarios.Valores;






public class ApostaDAO extends DAOJPA{


	EventoDAO eventoDAO = new EventoDAO();
	UsuarioDAO usuarioDAO = new UsuarioDAO();




	public void save(Aposta instancia) throws PersistenciaException {
		
		
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
	
	public void update(Aposta instancia) throws PersistenciaException {
		
		
		EntityManager em = getEm();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Aposta resultado = instancia;
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
	
	public void updateUsuarioEAposta(Aposta aposta, Usuario usuario) throws PersistenciaException{
		
		
		EntityManager em = getEm();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Aposta apostaAtualizada = aposta;
		Usuario usuarioAtualizado = usuario;
		try {
			apostaAtualizada = em.merge(aposta);
			usuarioAtualizado = em.merge(usuario);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
		
	}
	
	public void updateUsuarioUsuarioEAposta(Usuario usuario1, Usuario usuario2, Aposta aposta) throws PersistenciaException{
		
		
		EntityManager em = getEm();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Aposta apostaAtualizada = aposta;
		Usuario usuario1Atualizado = usuario1;
		Usuario usuario2Atualizado = usuario2;
		try {
			apostaAtualizada = em.merge(aposta);
			usuario1Atualizado = em.merge(usuario1);
			usuario2Atualizado = em.merge(usuario2);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			transaction.rollback();
		} finally {
			em.close();
		}
		
	}

	
	public List<Aposta> recuperarTodas (){
		
		EntityManager em = getEm();
		List<Aposta> resultado = null;
		try {
			TypedQuery<Aposta> query = em.createQuery("SELECT c FROM Aposta c", Aposta.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		
		ArrayList<Aposta> resultadoFinal  = new ArrayList<Aposta>();
		for(Aposta d: resultado) {
			resultadoFinal.add(d);
		}
		return resultadoFinal;
		
		
	}
	
	
	
	public Aposta  getById(Long id) throws PersistenciaException {
		
		EntityManager em = getEm();
		Aposta resultado = null;
		try {
			resultado = em.find(Aposta.class, id);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			
		} finally {
			em.close();
		}

		return resultado;
		
	}
	
	public List<Aposta> pegarDeAcordoComFiltro(Modalidade modalidade, Competicao competicao, Date ate, 
											Date aPartir, Float valorPegarMin, Float valorPegarMax, 
											Float valorRetornoMin, Float valorRetornoMax) throws PersistenciaException {
		
		EntityManager em = null;
		
		List<Aposta> results = null;
		
		try {
			
			em = getEm();
			
			
		
			// variaveis
			Long idModalidade = (long)0;
			if(modalidade != null) {
				idModalidade = modalidade.getId();
			}
			
			Long idCompeticao = (long)0;
			if(competicao != null) {
				idCompeticao =	competicao.getId();
			}
			
			
			String dataString = "";
			if(ate != null) {
				
				dataString = new SimpleDateFormat("yyyy-MM-dd").format(ate);
				dataString += " 23:59:59";
				dataString = "'"+dataString+"'";
			}
			
			
			// se modalidade nula retorna tudo
			
			
			
			String queryFeita = "SELECT a FROM Aposta a ";
			
			if(modalidade == null && competicao == null && ate == null ){
				queryFeita += "";
			}
			
			else if(modalidade != null && competicao == null && ate == null ){
				queryFeita += " JOIN a.evento e JOIN e.competicao c WHERE c.id IN "+
						"(SELECT c2.id FROM Competicao c2 JOIN c2.modalidade m WHERE m.id = "+idModalidade+") ";
			}
			
			else if (modalidade != null && competicao != null && ate == null ){
				queryFeita += " JOIN a.evento e JOIN e.competicao c WHERE c.id = "+idCompeticao+" ";
			}
			
			else if (modalidade != null && competicao != null && ate != null ){
				queryFeita += " JOIN a.evento e JOIN e.competicao c WHERE c.id = "+idCompeticao+" AND e.data < "+dataString+ " ";
			}
			
			else if (modalidade == null && competicao != null && ate == null ){
				queryFeita += " JOIN a.evento e JOIN e.competicao c WHERE c.id = "+idCompeticao+" ";
			}
			
			else if (modalidade == null && competicao == null && ate != null ){
				queryFeita += " JOIN a.evento e WHERE e.data < "+dataString+ " ";
			}
			
	
			String estadoAposta = String.valueOf(EstadosDaAposta.ABERTA_NO_MERCADO);
			// final com estado
			if(modalidade != null || competicao != null || ate != null) {
				queryFeita += " AND a.estado = " +estadoAposta;
			} else {
				queryFeita += " WHERE a.estado = "+estadoAposta;
			}
			
			
			TypedQuery<Aposta> query = em.createQuery(queryFeita, Aposta.class);
			results = query.getResultList();
			
			
		
			} catch (PersistenceException pe) {
				pe.printStackTrace();
			} finally {
				em.close();
			}
			
		return results;
			
			
		
	}
	
	
	public List<Aposta> apostaFiltros(Modalidade modalidade, Competicao competicao, Evento evento, Date ate, 
			Date aPartir, Float valorPegarMin, Float valorPegarMax, 
			Float valorRetornoMin, Float valorRetornoMax, Integer estadoAposta, 
			Usuario usuario) throws PersistenciaException {
		
			EntityManager em = null;
			
			if(evento != null) {
				System.out.println(evento.getId() +" id evento");
			} else {
				System.out.println("evento nulo");
			}
			List<Aposta> resultado = null;
			
			
			if(usuario == null) {
			 System.out.println("nulo usuario");
			}
		
			try {
			
				em = getEm();
				
				String jpql = "SELECT a FROM Aposta a JOIN a.evento e JOIN e.competicao c JOIN a.usuarioDesafiante u1 "
						+ " WHERE 1 = 1 ";
				
				//parte dos join
				
				if (notNull(modalidade)) {
					jpql += "AND c.id IN (SELECT c2.id FROM Competicao c2 JOIN c2.modalidade m WHERE m.id = :idModalidade ) ";
				}
	
				if (notNull(competicao)) {
					jpql += "AND c.id = :idCompeticao ";
				}
				
				if (notNull(evento)) {
					jpql += "AND e.id = :idEvento ";
				}
				
				if(notNull(ate)) {
					jpql += "AND e.data <= :dataAte ";
				}
				
				if(notNull(aPartir)) {
					jpql += "AND e.data >= :dataAPartir ";
				}
				
				if(notNull(valorPegarMin)) {
					jpql += "AND a.valorApostado >= :valorPegarMin ";
				}
				
				if(notNull(valorPegarMax)) {
					jpql += "AND a.valorApostado <= :valorPegarMax ";
				}
				
				if(notNull(valorRetornoMin)) {
					jpql += "AND a.valorEsperado >= :valorRetornoMin ";
				}
				
				if(notNull(valorRetornoMax)) {
					jpql += "AND a.valorEsperado <= :valorRetornoMax ";
				}
				
				if(notNull(estadoAposta)) {
					jpql += "AND a.estado = :estadoAposta ";
				}
				
				if(notNull(usuario)) {
					jpql += "AND u1.id = :idUsuario ";
				}
	
				
				
			
				TypedQuery<Aposta> query = em.createQuery(jpql, Aposta.class);
				
				if (notNull(modalidade)) {
					query.setParameter("idModalidade", modalidade.getId());
				}
	
				if (notNull(competicao)) {
					query.setParameter("idCompeticao", competicao.getId());
				}
				
				if (notNull(evento)) {
					query.setParameter("idEvento", evento.getId());
				}
				
				if(notNull(ate)) {
					query.setParameter("dataAte", ate);
					
				}
				
				if(notNull(aPartir)) {
					query.setParameter("dataAPartir", aPartir);
					
				}
				
				if(notNull(valorPegarMin)) {
					query.setParameter("valorPegarMin", valorPegarMin);
				}
				
				if(notNull(valorPegarMax)) {
					query.setParameter("valorPegarMax", valorPegarMax);
				}
				
				if(notNull(valorRetornoMax)) {
					query.setParameter("valorRetornoMax", valorRetornoMax);
				}
				
				if(notNull(valorRetornoMin)) {
					query.setParameter("valorRetornoMin", valorRetornoMin);
				}
				
				if(notNull(estadoAposta)) {
					query.setParameter("estadoAposta", estadoAposta);
				}
				
				if(notNull(usuario)) {
					query.setParameter("idUsuario", usuario.getId());
				}

				resultado = query.getResultList();
				
				System.out.println(jpql);
			
			
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		}
		
		return resultado;
	}

	
	public List<Aposta> apostasDeUmEventoNumEstado(Evento evento, ArrayList<Integer> estados) throws PersistenciaException {
		
		EntityManager em = getEm();
		List<Aposta> resultado = null;
		
		
		try {
			
			String feita = "SELECT a FROM Aposta a JOIN a.evento e WHERE e.id = :idevento ";
			
			if(estados.size() > 0) {
				
				feita +=  " AND (1 = 2 OR ";
			}
			
			for(Integer inteiro: estados) {
				
				feita += "a.estado = "+inteiro+" OR ";
			}
			
			feita = feita.substring(0, feita.length()-3);
			
			feita += ")";
			
			System.out.println(feita);
			
			
			TypedQuery<Aposta> query = em.createQuery(feita, Aposta.class);
			query.setParameter("idevento", evento.getId());
			resultado = query.getResultList();
			
			System.out.println("metodo ok");
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		
		return resultado;
		
	}
	
	public Float retornarValorScoreDisputanteEvento(Disputante disputante, Evento evento) {
		
		EntityManager em = getEm();
		Score resultado = null;
		try {
			TypedQuery<Score> query = em.createQuery("SELECT s FROM Score s JOIN s.evento e JOIN s.disputante d "
					+ "WHERE e.id = :idevento AND d.id = :iddisputante", Score.class);
			query.setParameter("idevento", evento.getId());
			query.setParameter("iddisputante", disputante.getId());
			

			resultado = query.getSingleResult();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		
		
		return resultado.getValor();
		
		
		
	}
	
	public void resolverApostas(List<Aposta> apostas, Evento evento) throws PersistenciaException{
		
		
		System.out.println("apostas tamanho"+apostas.size());
		EntityManager em = null;
		
		try {
			em = getEm();
			
			for(Aposta aposta: apostas) {
			
			
				Disputante disputante1 = aposta.getCondicao().getDisputante1();
				Disputante disputante2 = aposta.getCondicao().getDisputante2();
		
				
				if(aposta.getCondicao().getTipoCondicao() == TiposDeCondicao.FUTEBOL_QUEM_VENCE) {
					
					//setando as variaveis
					
					Float disputante1Total = 0f;
					// mais o variavel
					disputante1Total += aposta.getCondicao().getVarianteDisputante1(); 
					// mais o resultado
					
					
					TypedQuery<Score> query = em.createQuery("SELECT s FROM Score s JOIN s.evento e JOIN s.disputante d "
							+ "WHERE e.id = :idevento AND d.id = :iddisputante", Score.class);
					query.setParameter("idevento", evento.getId());
					query.setParameter("iddisputante", disputante1.getId());
					Score score1 = query.getSingleResult();
					
					disputante1Total += score1.getValor();
					
					
					Float disputante2Total = 0f;
					// mais o variavel
					disputante2Total += aposta.getCondicao().getVarianteDisputante2(); 
					// mais o resultado
					TypedQuery<Score> query2 = em.createQuery("SELECT s FROM Score s JOIN s.evento e JOIN s.disputante d "
							+ "WHERE e.id = :idevento AND d.id = :iddisputante", Score.class);
					query2.setParameter("idevento", evento.getId());
					query2.setParameter("iddisputante", disputante2.getId());
					Score score2 = query2.getSingleResult();
					
					disputante2Total += score2.getValor();
					
					
					Float valorGanhou = 0f;
					
					if(disputante1Total > disputante2Total) {
						
						// o valor que uma pessoa ganha é oque ela apostou mais 95% do que a outra apostou
						valorGanhou = (aposta.getValorEsperado() * Valores.REDUZ_PARA_95) + aposta.getValorApostado() ;
						ganhouAposta(aposta.getUsuarioDesafiante(), aposta, valorGanhou);
						System.out.println(aposta.getUsuarioDesafiante().getNome()+"ganhou");
					}
					
					else if(disputante2Total > disputante1Total) {
						
						valorGanhou = (aposta.getValorApostado() * Valores.REDUZ_PARA_95) + aposta.getValorEsperado();
						ganhouAposta(aposta.getUsuarioAceitador(), aposta, valorGanhou);
						System.out.println(aposta.getUsuarioAceitador().getNome()+"ganhou");
						
					}
					
					else {
						
						valorGanhou = aposta.getValorApostado();
						empatouAposta(aposta.getUsuarioDesafiante(), aposta, valorGanhou);
						
						valorGanhou = aposta.getValorEsperado();
						empatouAposta(aposta.getUsuarioAceitador(), aposta, valorGanhou);
						
					}
					

					
					
				}
			}
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
	}	
		

		
	public void ganhouAposta(Usuario usuario, Aposta aposta, Float valorGanhou) {
		
		usuario.setSaldo(usuario.getSaldo() + valorGanhou);
		try {
			usuarioDAO.update(usuario);
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		
		
		// settando resultado
		if(aposta.getUsuarioDesafiante().getId() == usuario.getId()) {
			aposta.setResultadoDesafiante(ResultadosDasApostas.GANHOU);
			aposta.setResultadoAceitador(ResultadosDasApostas.PERDEU);
		} 
		
		else {
			aposta.setResultadoDesafiante(ResultadosDasApostas.PERDEU);
			aposta.setResultadoAceitador(ResultadosDasApostas.GANHOU);
		}
		
		//setando a aposta como combinada e com resultado
		
		aposta.setEstado(EstadosDaAposta.COMBINADA_COM_RESULTADO);
		
		try {
			update(aposta);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public void empatouAposta(Usuario usuario, Aposta aposta, Float valorGanhou) {
		
		usuario.setSaldo(usuario.getSaldo() + valorGanhou);
		try {
			usuarioDAO.update(usuario);
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		
		//setando a aposta como combinada esperando resultado

		
		// settando resultado
		aposta.setResultadoDesafiante(ResultadosDasApostas.EMPATOU);
		aposta.setResultadoAceitador(ResultadosDasApostas.EMPATOU);
	
		//setando a aposta como combinada e com resultado
		
		aposta.setEstado(EstadosDaAposta.COMBINADA_COM_RESULTADO);
		
		try {
			update(aposta);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public List<Aposta> retornarResolvidasDeUmUsuario(Usuario usuario) throws PersistenciaException{
		
		EntityManager em = getEm();
		List<Aposta> resultado = null;
		try {
			TypedQuery<Aposta> query = em.createQuery("SELECT a FROM Aposta a JOIN a.usuarioDesafiante u1 JOIN a.usuarioAceitador u2 "
					+ "WHERE (u1.id = :idusuario OR u2.id = :idusuario) AND (a.estado = 2 OR a.estado = 22)", Aposta.class);
			query.setParameter("idusuario", usuario.getId());
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		
		return resultado;
		
	}
	
	
	
	
	
	public List<Aposta> retornarDesafiosParaUmUsuario(Usuario usuario) throws PersistenciaException{
		
		EntityManager em = getEm();
		List<Aposta> resultado = null;
		try {
			TypedQuery<Aposta> query = em.createQuery("SELECT a FROM Aposta a JOIN a.usuarioDesafiado u1 "
					+ "WHERE u1.id = :idusuario AND a.estado = 4", Aposta.class);
			query.setParameter("idusuario", usuario.getId());
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		
		return resultado;
		
	}
	

	


	public List<Aposta> retornarAguardandoResultadoParaUmUsuario(Usuario usuario) throws PersistenciaException{
		
		EntityManager em = getEm();
		List<Aposta> resultado = null;
		try {
			TypedQuery<Aposta> query = em.createQuery("SELECT a FROM Aposta a JOIN a.usuarioDesafiante u1 JOIN a.usuarioAceitador u2 " + 
					"WHERE (u1.id = :idusuario OR u2.id = :idusuario) AND a.estado = 1", Aposta.class);
			query.setParameter("idusuario", usuario.getId());
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} finally {
			em.close();
		}
		
		return resultado;
		
	}
	
	public void setarTodasComoCombinadasDepoisInicioEvento(List<Aposta> apostas) throws PersistenciaException {
		
		
		EntityManager em = getEm();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		
		try {
			for(Aposta a: apostas) {
				a.setEstado(EstadosDaAposta.COMBINADA_DEPOIS_DO_INICIO_EVENTO);
				a  = em.merge(a);
				
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
