package com.bet1x1.entidades.apostas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bet1x1.entidades.competicoes.Competicao;
import com.bet1x1.entidades.condicoes.TiposDeCondicao;
import com.bet1x1.entidades.disputantes.Disputante;
import com.bet1x1.entidades.eventos.Evento;
import com.bet1x1.entidades.eventos.EventoDAO;
import com.bet1x1.entidades.excecoes.PersistenciaException;
import com.bet1x1.entidades.excecoes.service.ServiceException;
import com.bet1x1.entidades.excecoes.service.ServiceExceptionCancelarApostaEmEstadoInvalido;
import com.bet1x1.entidades.modalidades.Modalidade;
import com.bet1x1.entidades.usuarios.Usuario;
import com.bet1x1.entidades.usuarios.UsuarioDAO;
import com.bet1x1.utilitarios.Valores;



public class ApostaServices implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7803325791425670859L;
	
	private ApostaDAO instanciaDAO = new  ApostaDAO();
	
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private EventoDAO eventoDAO = new EventoDAO();
	
	
	public List<Aposta> removerUmaApostaDesafiada(Usuario usuario, Aposta aposta) {
		
		List<Aposta> apostasDesafiadas = usuario.getApostasDesafiadas();
		apostasDesafiadas.remove(aposta);

		
		List<Aposta> resultado = new ArrayList<Aposta>();
		for(Aposta a:  apostasDesafiadas) {
			

			if(a.getId() != aposta.getId()) {
				
				System.out.println(a.getId());
				System.out.println(aposta.getId());
				resultado.add(a);
			}
			
		}
		
		System.out.println(resultado.size()+"sa");
		
		return resultado;
		
	}
	
	
	
	
	public void save(Aposta instancia)  {
	
		try {
			instanciaDAO.save(instancia);
		}catch (PersistenciaException e) {
			System.out.println(e.getMessage());
		}

	}
	
	public void update(Aposta instancia)  {
		
		try {
			instanciaDAO.update(instancia);
		}catch (PersistenciaException e) {
			System.out.println(e.getMessage());
		}

	}
	
	public void updateUsuarioEAposta( Aposta aposta, Usuario usuario) throws ServiceException{
		
		try {
			instanciaDAO.updateUsuarioEAposta(aposta, usuario);
		}catch (PersistenciaException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void updateUsuarioUsuarioEAposta( Usuario usuario1, Usuario usuario2, Aposta aposta) throws ServiceException{
		
		try {
			instanciaDAO.updateUsuarioUsuarioEAposta(usuario1, usuario2, aposta);
		}catch (PersistenciaException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	
	
	public Aposta getById(Long id)  {
		
		Aposta u = null;
		try {
			u =  instanciaDAO.getById(id);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return u;
	}

	
	
	public List<Aposta> getAll(){
		
		return instanciaDAO.recuperarTodas();
	}
	
	
	public List<Aposta> pegarDeAcordoComFiltro(Modalidade modalidade, Competicao competicao, Evento evento, 
			Date ate, Date aPartir, Float valorPegarMin, Float valorPegarMax, Float valorRetornoMin, 
			Float valorRetornoMax, Integer estadoAposta, Usuario usuario) throws ServiceException{
		
		List<Aposta> resultado = null;
		System.out.println("aqui3");

		
		try {
			resultado =  instanciaDAO.apostaFiltros(modalidade, competicao, evento, ate, aPartir, valorPegarMin, 
					valorPegarMax, valorRetornoMin, valorRetornoMax, estadoAposta, usuario);
			System.out.println("aqui2");

		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			System.out.println("aqui");
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	
	public List<Aposta> todasApostasCombinadasEsperandoResultadoDeUmEvento(Evento evento){
		
		List<Aposta> resultado = null;
		
		ArrayList<Integer> estados = new ArrayList<Integer>();
		estados.add(EstadosDaAposta.COMBINADA_ANTES_DO_INICIO_EVENTO);
		estados.add(EstadosDaAposta.COMBINADA_DEPOIS_DO_INICIO_EVENTO);
		
		try {
			resultado =  instanciaDAO.apostasDeUmEventoNumEstado(evento, estados);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("apostas tamanho"+resultado.size());
		return resultado;
	}
	
	public List<Aposta> todasApostasCombinadasAntesDoInicioEvento(Evento evento){
		
		List<Aposta> resultado = null;
		
		ArrayList<Integer> estados = new ArrayList<Integer>();
		estados.add(EstadosDaAposta.COMBINADA_ANTES_DO_INICIO_EVENTO);
		
		try {
			resultado =  instanciaDAO.apostasDeUmEventoNumEstado(evento, estados);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("apostas tamanho"+resultado.size());
		return resultado;
	}
	
	public void cancelarApostaAdmin(Aposta aposta) throws ServiceException, ServiceExceptionCancelarApostaEmEstadoInvalido{
		
		
		if(aposta.getEstado() == EstadosDaAposta.CANCELADA || 
				aposta.getEstado() == EstadosDaAposta.ABERTA_NO_MERCADO ||
				aposta.getEstado() == EstadosDaAposta.ABERTA_PARA_UM_ESPECIFICO ||
				aposta.getEstado() == EstadosDaAposta.ESPIRADA
				 ) {
			throw new ServiceExceptionCancelarApostaEmEstadoInvalido();
		}
		
		
		Usuario desafiante = aposta.getUsuarioDesafiante();
		Usuario aceitante = aposta.getUsuarioAceitador();
		
		//se ninguem pegou a aposta então é só devolver o dinheiro
		if(aceitante == null) {
			
			desafiante.setSaldo(desafiante.getSaldo() + aposta.getValorApostado());
			
			aposta.setEstado(EstadosDaAposta.CANCELADA);
			
			try {
				instanciaDAO.updateUsuarioEAposta(aposta, desafiante);
			} catch (PersistenciaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		else if (aceitante != null && desafiante != null) {
			
			//estado da aposta
			if(aposta.getEstado() == EstadosDaAposta.COMBINADA_ANTES_DO_INICIO_EVENTO || 
					aposta.getEstado() == EstadosDaAposta.COMBINADA_DEPOIS_DO_INICIO_EVENTO) {
				
				desafiante.setSaldo(desafiante.getSaldo() + aposta.getValorApostado());
				aceitante.setSaldo(aceitante.getSaldo() + aposta.getValorEsperado());
				
			}
			
			else if(aposta.getEstado() == EstadosDaAposta.COMBINADA_COM_RESULTADO) {
				
				if(aposta.getResultadoDesafiante().equals(ResultadosDasApostas.GANHOU)) {
					
					desafiante.setSaldo(desafiante.getSaldo() - (aposta.getValorEsperado() * Valores.REDUZ_PARA_95));
					aceitante.setSaldo(aceitante.getSaldo() + aposta.getValorEsperado());
				}
				
				else if(aposta.getResultadoDesafiante().equals(ResultadosDasApostas.PERDEU)) {
					
					desafiante.setSaldo(desafiante.getSaldo() + aposta.getValorApostado());
					aceitante.setSaldo(aceitante.getSaldo() - ( aposta.getValorApostado() * Valores.REDUZ_PARA_95));
				}
				
				else if( aposta.getResultadoDesafiante().equals(ResultadosDasApostas.EMPATOU)) {
					
				}
				
				
			}
			
			else if(aposta.getEstado() == EstadosDaAposta.COMBINADA_DESISTIDA) {
				
				if(aposta.getResultadoDesafiante().equals(ResultadosDasApostas.GANHOU_POR_DESISTENCIA)) {
					
					desafiante.setSaldo(desafiante.getSaldo() - (aposta.getValorEsperado() * Valores.REDUZ_PARA_10));
					aceitante.setSaldo(aceitante.getSaldo() + (aposta.getValorEsperado() * Valores.REDUZ_PARA_15));
				}
				
				else if(aposta.getResultadoDesafiante().equals(ResultadosDasApostas.PERDEU_POR_DESISTENCIA)) {
					
					
					desafiante.setSaldo(desafiante.getSaldo() + (aposta.getValorApostado() * Valores.REDUZ_PARA_15));
					aceitante.setSaldo(aceitante.getSaldo() - (aposta.getValorApostado() * Valores.REDUZ_PARA_10));
				}
				

				
			}
			
			
			aposta.setEstado(EstadosDaAposta.CANCELADA);
			
			try {
				instanciaDAO.updateUsuarioUsuarioEAposta(desafiante, aceitante, aposta);
			} catch (PersistenciaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
	}
	
	public void resolverAposta(Aposta aposta) {
		
		Evento evento = eventoDAO.getEventoByAposta(aposta);
		
		Disputante disputante1 = aposta.getCondicao().getDisputante1();
		Disputante disputante2 = aposta.getCondicao().getDisputante2();

		
		if(aposta.getCondicao().getTipoCondicao() == TiposDeCondicao.FUTEBOL_QUEM_VENCE) {
			
			//setando as variaveis
			
			Float disputante1Total = 0f;
			// mais o variavel
			disputante1Total += aposta.getCondicao().getVarianteDisputante1(); 
			// mais o resultado
			disputante1Total += instanciaDAO.retornarValorScoreDisputanteEvento(disputante1, evento);
			
			
			Float disputante2Total = 0f;
			// mais o variavel
			disputante2Total += aposta.getCondicao().getVarianteDisputante2(); 
			// mais o resultado
			disputante2Total += instanciaDAO.retornarValorScoreDisputanteEvento(disputante2, evento);
			
			
			Float valorGanhou = 0f;
			
			if(disputante1Total > disputante2Total) {
				
				valorGanhou = aposta.getValorEsperado();
				ganhouAposta(aposta.getUsuarioDesafiante(), aposta, valorGanhou);
				System.out.println(aposta.getUsuarioDesafiante().getNome() + "ganhou");
			}
			
			else if(disputante2Total > disputante1Total) {
				
				valorGanhou = aposta.getValorApostado();
				ganhouAposta(aposta.getUsuarioAceitador(), aposta, valorGanhou);
				System.out.println(aposta.getUsuarioAceitador().getNome() + "ganhou");
				
			}
			
			else {
				System.out.println("empates");
			}
			
			
		}
		
		
		
	}
	
	public void resolverApostas(List<Aposta> apostas, Evento evento) {
		
		
		try {
			instanciaDAO.resolverApostas(apostas, evento);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public void ganhouAposta(Usuario usuario, Aposta aposta, Float valorGanhou) {
		
		usuario.setSaldo(usuario.getSaldo() + valorGanhou);
		try {
			usuarioDAO.update(usuario);
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Aposta> retornarResolvidasDeUmUsuario(Usuario usuario){
		
		List<Aposta> result = null; 
		
		if(usuario == null) {
			return null;
		}
		try {
			result =  instanciaDAO.retornarResolvidasDeUmUsuario(usuario);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public void desistirAposta(Aposta aposta, Usuario desistente) throws ServiceException {
		
		
		Usuario persistente = null;
		aposta.setEstado(EstadosDaAposta.COMBINADA_DESISTIDA);
		
		if(desistente.getId() == aposta.getUsuarioDesafiante().getId()) {
			
			aposta.setResultadoDesafiante(ResultadosDasApostas.PERDEU_POR_DESISTENCIA);
			aposta.setResultadoAceitador(ResultadosDasApostas.GANHOU_POR_DESISTENCIA);
			
			persistente = aposta.getUsuarioAceitador();
			//5% para o site e 10% para o jogador adversário
			desistente.setSaldo(desistente.getSaldo() + (aposta.getValorApostado() * 0.85f));
			persistente.setSaldo(persistente.getSaldo() + (aposta.getValorApostado() * 0.1f) + (aposta.getValorEsperado()));
			
			
			
		} 
		
		else if(desistente.getId() ==  aposta.getUsuarioAceitador().getId()) {
			
			aposta.setResultadoDesafiante(ResultadosDasApostas.GANHOU_POR_DESISTENCIA);
			aposta.setResultadoAceitador(ResultadosDasApostas.PERDEU_POR_DESISTENCIA);
			
			persistente = aposta.getUsuarioDesafiante();
			
			desistente.setSaldo(desistente.getSaldo() + (aposta.getValorEsperado() * 0.85f));
			persistente.setSaldo(persistente.getSaldo() + (aposta.getValorEsperado() * 0.1f) + (aposta.getValorApostado()));
		}
		
		updateUsuarioUsuarioEAposta(desistente, persistente, aposta);
		 
		
		
	}
	
	public List<Aposta> retornarDesafiosParaUmUsuario(Usuario usuario){
		
		List<Aposta> result = null; 
		
		if(usuario == null) {
			return null;
		}
		try {
			result =  instanciaDAO.retornarDesafiosParaUmUsuario(usuario);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(result.size()+"tamanho desafiadas");
		
		return result;
		
	}
	

	
	
	public List<Aposta> retornarAguardandoResultadoParaUmUsuario(Usuario usuario){
		
		List<Aposta> result = null; 
		
		if(usuario == null) {
			return null;
		}
		try {
			result =  instanciaDAO.retornarAguardandoResultadoParaUmUsuario(usuario);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(result.size()+"tamanho desafiadas");
		
		return result;
		
	}
	
	public void setarTodasComoCombinadasDepoisInicioEvento(List<Aposta> apostas) {
		
		
		
		try {
			
			instanciaDAO.setarTodasComoCombinadasDepoisInicioEvento(apostas);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
