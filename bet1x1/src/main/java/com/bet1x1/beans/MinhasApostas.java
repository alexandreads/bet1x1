package com.bet1x1.beans;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.bet1x1.entidades.apostas.Aposta;
import com.bet1x1.entidades.apostas.ApostaServices;
import com.bet1x1.entidades.apostas.EstadosDaAposta;
import com.bet1x1.entidades.competicoes.Competicao;
import com.bet1x1.entidades.competicoes.CompeticaoServices;
import com.bet1x1.entidades.eventos.EventoServices;
import com.bet1x1.entidades.excecoes.service.ServiceException;
import com.bet1x1.entidades.modalidades.Modalidade;
import com.bet1x1.entidades.modalidades.ModalidadeServices;
import com.bet1x1.entidades.usuarios.Usuario;
import com.bet1x1.entidades.usuarios.UsuarioServices;
import com.bet1x1.utilitarios.EnderecoPaginas;


@ViewScoped
@ManagedBean
public class MinhasApostas extends AbstractBean {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5976838804313515033L;

	//services
	private ModalidadeServices modalidadeServices = new ModalidadeServices();
	private CompeticaoServices competicaoServices = new CompeticaoServices();
	private EventoServices eventoServices = new EventoServices();
	private ApostaServices apostaServices = new ApostaServices();
	private UsuarioServices usuarioServices = new UsuarioServices();
	
	
	//listas
	
	private List<Aposta> resolvidas;
	private List<Aposta> desafiosRecebidos;
	private List<Aposta> aguardandoResultado;

	
	//filtros
	private Modalidade modalidadeFiltro;
	private Competicao competicaoFiltro;
	private Date dataLimiteFiltro;
	
	
	
	
	
	
	public void init() {
		
		loadResolvidas();
		loadDesafiosRecebidos();
		loadAguardandoResultado();
		
		
	}
	

	

	public void loadResolvidas() {
		
		resolvidas = apostaServices.retornarResolvidasDeUmUsuario(usuarioServices.getUsuarioLogado());
		
	}
	
	public void loadDesafiosRecebidos() {
		
		desafiosRecebidos = apostaServices.retornarDesafiosParaUmUsuario(usuarioServices.getUsuarioLogado());
		
	}
	
	public void loadAguardandoResultado() {
		
		aguardandoResultado = apostaServices.retornarAguardandoResultadoParaUmUsuario(usuarioServices.getUsuarioLogado());
	}
	
	
	
	public String aceitarDesafio(Long idAposta) {
		
		Usuario usuario = usuarioServices.getUsuarioLogado();
		Aposta aposta = apostaServices.getById(idAposta);
		
		
		if(usuario == null) {
			
			reportarMensagemDeErro("Não existe um usuário logado.");
		}
		
		else if (usuario.getSaldo() < aposta.getValorEsperado()) {

			reportarMensagemDeErro("Você não tem saldo suficiente para pegar esta aposta.");
			
		}
		
		else {
			
			try {
				apostaServices.aceitarDesafio(aposta, usuario);
				reportarMensagemDeSucesso("Aposta aceita com sucesso.");
			} catch (ServiceException e) {
				e.printStackTrace();
				reportarMensagemDeErro("Aposta não conseguiu ser aceita devido a algum erro.");
			}
			
			
		}
		
		
		return EnderecoPaginas.MINHAS_APOSTAS+EnderecoPaginas.FACES_REDIRECT;
		
	}
	
	
	
		
	public String desistir(Long idAposta) {
		
		
		Usuario usuario = usuarioServices.getUsuarioLogado();
		Aposta aposta = apostaServices.getById(idAposta);
		
		
		if(usuario == null) {
			
			reportarMensagemDeErro("Não existe um usuário logado.");
		}
		
		
		else {
			
			try {
				apostaServices.desistirAposta(aposta, usuario);
				reportarMensagemDeSucesso("Aposta desistida com sucesso.");
			} catch (ServiceException e) {
				e.printStackTrace();
				reportarMensagemDeErro("Aposta não conseguiu ser desistida devido a algum erro.");
			}
			
			
			
			 
			
		}
		
		
		return EnderecoPaginas.MERCADO_APOSTAS+EnderecoPaginas.FACES_REDIRECT;
		
	}
	
	

	
	
	
	
	
	//getters and setters

	public ModalidadeServices getModalidadeServices() {
		return modalidadeServices;
	}

	public void setModalidadeServices(ModalidadeServices modalidadeServices) {
		this.modalidadeServices = modalidadeServices;
	}


	
	public Modalidade getModalidadeFiltro() {
		return modalidadeFiltro;
	}

	public void setModalidadeFiltro(Modalidade modalidadeFiltro) {
		this.modalidadeFiltro = modalidadeFiltro;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public EventoServices getEventoServices() {
		return eventoServices;
	}

	public void setEventoServices(EventoServices eventoServices) {
		this.eventoServices = eventoServices;
	}

	public ApostaServices getApostaServices() {
		return apostaServices;
	}

	public void setApostaServices(ApostaServices apostaServices) {
		this.apostaServices = apostaServices;
	}

	public UsuarioServices getUsuarioServices() {
		return usuarioServices;
	}

	public void setUsuarioServices(UsuarioServices usuarioServices) {
		this.usuarioServices = usuarioServices;
	}

	public Date getDataLimiteFiltro() {
		return dataLimiteFiltro;
	}

	public void setDataLimiteFiltro(Date dataLimiteFiltro) {
		this.dataLimiteFiltro = dataLimiteFiltro;
	}

	public List<Aposta> getResolvidas() {
		return resolvidas;
	}

	public void setResolvidas(List<Aposta> resolvidas) {
		this.resolvidas = resolvidas;
	}

	public CompeticaoServices getCompeticaoServices() {
		return competicaoServices;
	}

	public void setCompeticaoServices(CompeticaoServices competicaoServices) {
		this.competicaoServices = competicaoServices;
	}

	public List<Aposta> getDesafiosRecebidos() {
		return desafiosRecebidos;
	}

	public void setDesafiosRecebidos(List<Aposta> desafiosRecebidos) {
		this.desafiosRecebidos = desafiosRecebidos;
	}

	public Competicao getCompeticaoFiltro() {
		return competicaoFiltro;
	}

	public void setCompeticaoFiltro(Competicao competicaoFiltro) {
		this.competicaoFiltro = competicaoFiltro;
	}

	public List<Aposta> getAguardandoResultado() {
		return aguardandoResultado;
	}

	public void setAguardandoResultado(List<Aposta> aguardandoResultado) {
		this.aguardandoResultado = aguardandoResultado;
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
