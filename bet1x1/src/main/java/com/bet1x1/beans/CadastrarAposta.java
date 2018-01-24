package com.bet1x1.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import com.bet1x1.entidades.apostas.Aposta;
import com.bet1x1.entidades.apostas.ApostaServices;
import com.bet1x1.entidades.apostas.EstadosDaAposta;
import com.bet1x1.entidades.condicoes.Condicao;
import com.bet1x1.entidades.condicoes.TiposDeCondicao;
import com.bet1x1.entidades.disputantes.Disputante;
import com.bet1x1.entidades.eventos.Evento;
import com.bet1x1.entidades.eventos.EventoServices;
import com.bet1x1.entidades.excecoes.service.ServiceException;
import com.bet1x1.entidades.handicaps.Handicap;
import com.bet1x1.entidades.modalidades.Modalidade;
import com.bet1x1.entidades.usuarios.Usuario;
import com.bet1x1.entidades.usuarios.UsuarioServices;
import com.bet1x1.utilitarios.EnderecoPaginas;
import com.bet1x1.utilitarios.RetornadorValores;




@ViewScoped
@ManagedBean
public class CadastrarAposta extends AbstractBean{
	
	//services
	private EventoServices eventoServices = new EventoServices();
	private UsuarioServices usuarioServices = new UsuarioServices();
	private ApostaServices apostaServices = new ApostaServices();
	
	
	//variaveis
	private Evento evento;
	
	private Modalidade modalidade;
	
	private Condicao condicao;
	
	private Aposta aposta;
	
	private Integer tipoCondicao;
	
	private Usuario desafiado;
	
	
	
	
	

	
	
	// arrays
	private List<Handicap> opcoesHandicapFutebol = RetornadorValores.handicapFutebol();
	
	
	private ArrayList<Disputante> disputantes = new ArrayList<Disputante>();
	
	private List<Usuario> potenciaisDesafiados;
	
	
	
	
	
	
	
	public void init() {
		
		//setando a modalidade
		modalidade = eventoServices.retornarModalidade(evento);

		//
		
		potenciaisDesafiados = usuarioServices.retornarPotenciaisDesafiados(usuarioServices.getUsuarioLogado());
		
		
		//setando disputantes
		disputantes = eventoServices.retornarDisputantesEmOrdemAlfaEJogandoEmCasa(evento);
		System.out.println("quantos disputantes"+disputantes.size());
		
		//setando condicão
		condicao = new Condicao();
		
		//setando aposta
		aposta = new Aposta();
		
		//
	}
	
	
	public boolean isFutebol() {
		
		if(modalidade == null) {
			return false;
		}
		if(modalidade.getNome().equals("Futebol")) {
			return true;
		}
		
		return false;
	}
	
	public boolean isAutomobilismo() {
		
		
		
		if(modalidade == null) {
			return false;
		}
		if(modalidade.getNome().equals("Automobilismo")) {
			return true;
		}
		
		return false;
	}
	
	
	
	public void teste() {
		
		System.out.println("teste");
	}
	
	
	public String cadastrarMercado() {
		
		
		Usuario usuario = null;
		
		usuario = usuarioServices.getUsuarioLogado();
		
		if(usuario == null) {
			reportarMensagemDeErro("Usuário não está logado.");
		}
		
		else if (usuario.getSaldo() < aposta.getValorApostado()) {
			
			reportarMensagemDeErro("Seu saldo é insuficiente, deposite mais dinheiro na sua conta.");
		}
		
		// se eiste um usuario logado então pode cadastrar um tipo de condicao
		else {
			
			aposta.setEstado(EstadosDaAposta.ABERTA_NO_MERCADO);
			
			if(tipoCondicao == TiposDeCondicao.FUTEBOL_QUEM_VENCE) {
				
				// se for do tipo futebol tem de botar o disputante 2 na mao
				cadastrarApostaTipoFutebolQuemVence(tipoCondicao, usuario);
				
			}
			
			else if(tipoCondicao == TiposDeCondicao.AUTO_QUEM_CHEGA_NA_FRENTE) {
					
					// se for do tipo futebol tem de botar o disputante 2 na mao
					cadastrarApostaTipoFutebolQuemVence(tipoCondicao, usuario);
					
				}
			
			
			
			// se não tem desafiado então cadastra no mercado mesmo a aposta
			if(aposta.getUsuarioDesafiado() == null) {
				
				
				
			
			}
			
		}
		
		

		
		
		
		
		return "index.xhtml?faces-redirect=true"; 
		
	}
	
	
	public String cadastrarDirecionada() {
		
		
		Usuario usuario = null;
		
		usuario = usuarioServices.getUsuarioLogado();
		
		if(usuario == null) {
			reportarMensagemDeErro("Usuário não está logado.");
		}
		
		else if (usuario.getSaldo() < aposta.getValorApostado()) {
			
			reportarMensagemDeErro("Seu saldo é insuficiente, deposite mais dinheiro na sua conta.");
		}
		
		// se eiste um usuario logado então pode cadastrar um tipo de condicao
		else {
			
			aposta.setEstado(EstadosDaAposta.ABERTA_PARA_UM_ESPECIFICO);
			
			if(tipoCondicao == TiposDeCondicao.FUTEBOL_QUEM_VENCE) {
				
				// se for do tipo futebol tem de botar o disputante 2 na mao
				cadastrarApostaTipoFutebolQuemVenceDirecionada(tipoCondicao, usuario, desafiado);
				
			}
			
			else if(tipoCondicao == TiposDeCondicao.AUTO_QUEM_CHEGA_NA_FRENTE) {
					//TODO direcionada
					// se for do tipo futebol tem de botar o disputante 2 na mao
					cadastrarApostaTipoFutebolQuemVence(tipoCondicao, usuario);
					
				}
			
			
			
			// se não tem desafiado então cadastra no mercado mesmo a aposta
			if(aposta.getUsuarioDesafiado() == null) {
				
				
				
			
			}
			
		}
		
		

		
		
		
		
		return "index.xhtml?faces-redirect=true"; 
		
	}
	
	
	
	
	public void cadastrarApostaTipoFutebolQuemVence(Integer tipoCondicao, Usuario usuario) {
		
		Disputante disputante2 = retornarOutroDisputante(condicao.getDisputante1());
		condicao.setTipoCondicao(tipoCondicao);
		condicao.setDisputante2(disputante2);
		aposta.setCondicao(condicao);
		
		condicao.setAposta(aposta);
		aposta.setEvento(evento);
		
		
		aposta.setUsuarioDesafiante(usuario);
		
		usuario.setSaldo(usuario.getSaldo() - aposta.getValorApostado());
		usuario.getApostasDesafiantes().add(aposta);
		
		
		
		try {
			apostaServices.updateUsuarioEAposta(aposta, usuario);
			reportarMensagemDeSucesso("Aposta cadastrada.");
		} catch (ServiceException e) {
			e.printStackTrace();
			reportarMensagemDeErro("Aposta não cadastrada devido à algum erro.");
		}
		
	}
	
	public void cadastrarApostaTipoFutebolQuemVenceDirecionada(Integer tipoCondicao, Usuario usuario, Usuario desafiado) {
		
		Disputante disputante2 = retornarOutroDisputante(condicao.getDisputante1());
		condicao.setTipoCondicao(tipoCondicao);
		condicao.setDisputante2(disputante2);
		aposta.setCondicao(condicao);
		
		condicao.setAposta(aposta);
		aposta.setEvento(evento);
		
		
		aposta.setUsuarioDesafiante(usuario);
		aposta.setUsuarioDesafiado(desafiado);
		
		
		
		usuario.setSaldo(usuario.getSaldo() - aposta.getValorApostado());
		usuario.getApostasDesafiantes().add(aposta);
		
		desafiado.getApostasDesafiadas().add(aposta);
		
		try {
			apostaServices.updateUsuarioEAposta(aposta, usuario);
			usuarioServices.update(desafiado);
			reportarMensagemDeSucesso("Aposta desafiada!");
		} catch (ServiceException e) {
			e.printStackTrace();
			reportarMensagemDeErro("Aposta não cadastrada devido à algum erro.");
		}
		
	}
	
	public void cadastrarApostaTipoAutoQuemChegaNaFrente(Integer tipoCondicao, Usuario usuario) {
		
		
		condicao.setTipoCondicao(tipoCondicao);
		aposta.setCondicao(condicao);
		
		condicao.setAposta(aposta);
		aposta.setEvento(evento);
		
		aposta.setUsuarioDesafiante(usuario);
		aposta.setEstado(EstadosDaAposta.ABERTA_NO_MERCADO);
		
		usuario.setSaldo(usuario.getSaldo() - aposta.getValorApostado());
		usuario.getApostasDesafiantes().add(aposta);
		
		try {
			usuarioServices.update(usuario);
			reportarMensagemDeSucesso("Aposta cadastrada.");
		} catch (ServiceException e) {
			e.printStackTrace();
			reportarMensagemDeErro("Aposta não cadastrada devido à algum erro.");
		}
		
	}
	
	
	public Disputante retornarOutroDisputante(Disputante disputante1) {
		
		
		
		if(disputantes.get(0).getId() == disputante1.getId()) {
			
			return disputantes.get(1);
		}
		
		return disputantes.get(0);
		
	
		
	}
	
	
	public void setarTipoCondicao(Integer tipoCondicao) {
		
		this.tipoCondicao = tipoCondicao;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// getters e setters
	
	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Modalidade getModalidade() {
		return modalidade;
	}

	public void setModalidade(Modalidade modalidade) {
		this.modalidade = modalidade;
	}


	public ApostaServices getApostaServices() {
		return apostaServices;
	}


	public void setApostaServices(ApostaServices apostaServices) {
		this.apostaServices = apostaServices;
	}

	public EventoServices getEventoServices() {
		return eventoServices;
	}


	public void setEventoServices(EventoServices eventoServices) {
		this.eventoServices = eventoServices;
	}


	public ArrayList<Disputante> getDisputantes() {
		return disputantes;
	}


	public void setDisputantes(ArrayList<Disputante> disputantes) {
		this.disputantes = disputantes;
	}




	public Condicao getCondicao() {
		return condicao;
	}


	public void setCondicao(Condicao condicao) {
		this.condicao = condicao;
	}


	public Aposta getAposta() {
		return aposta;
	}


	public void setAposta(Aposta aposta) {
		this.aposta = aposta;
	}


	public UsuarioServices getUsuarioServices() {
		return usuarioServices;
	}


	public void setUsuarioServices(UsuarioServices usuarioServices) {
		this.usuarioServices = usuarioServices;
	}


	public List<Handicap> getOpcoesHandicapFutebol() {
		return opcoesHandicapFutebol;
	}


	public void setOpcoesHandicapFutebol(List<Handicap> opcoesHandicapFutebol) {
		this.opcoesHandicapFutebol = opcoesHandicapFutebol;
	}


	public Integer getTipoCondicao() {
		return tipoCondicao;
	}


	public void setTipoCondicao(Integer tipoCondicao) {
		this.tipoCondicao = tipoCondicao;
	}


	public Usuario getDesafiado() {
		return desafiado;
	}


	public void setDesafiado(Usuario desafiado) {
		this.desafiado = desafiado;
	}


	public List<Usuario> getPotenciaisDesafiados() {
		return potenciaisDesafiados;
	}


	public void setPotenciaisDesafiados(List<Usuario> potenciaisDesafiados) {
		this.potenciaisDesafiados = potenciaisDesafiados;
	}

	


	
	
	
	
	
	
	

}
