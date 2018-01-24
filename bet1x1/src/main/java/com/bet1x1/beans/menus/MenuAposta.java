package com.bet1x1.beans.menus;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.bet1x1.beans.AbstractBean;
import com.bet1x1.entidades.apostas.Aposta;
import com.bet1x1.entidades.apostas.ApostaServices;
import com.bet1x1.entidades.competicoes.Competicao;
import com.bet1x1.entidades.competicoes.CompeticaoServices;
import com.bet1x1.entidades.disputantes.DisputanteServices;
import com.bet1x1.entidades.eventos.Evento;
import com.bet1x1.entidades.eventos.EventoServices;
import com.bet1x1.entidades.excecoes.service.ServiceException;
import com.bet1x1.entidades.excecoes.service.ServiceExceptionCancelarApostaEmEstadoInvalido;
import com.bet1x1.entidades.modalidades.ModalidadeServices;
import com.bet1x1.entidades.usuarios.EstadosDoUsuario;
import com.bet1x1.entidades.usuarios.Usuario;
import com.bet1x1.entidades.usuarios.UsuarioServices;
import com.bet1x1.utilitarios.EnderecoPaginas;


@ViewScoped
@ManagedBean
public class MenuAposta extends AbstractBean {

	
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
	private DisputanteServices disputanteServices = new DisputanteServices();
	
	
	//listas
	private List<Aposta> apostas;

	
	//filtros
	private Long idCompeticao;
	private Long idEvento;
	private Integer estadoAposta;
	private Long idUsuario;
	
	
	
	
	
	
	
	public void init() {
		
		
		loadApostas();
		
		
	}

	

	public void loadApostas() {
		
		Evento evento = null;
		Competicao competicao = null;
		Usuario usuario = null;
		try {
			evento = eventoServices.getById(idEvento);
			competicao = competicaoServices.getById(idCompeticao);
			usuario  = usuarioServices.getById(idUsuario);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		try {
			apostas = apostaServices.pegarDeAcordoComFiltro(null, competicao, evento,
					null, null, null, null, null, null, estadoAposta, usuario);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void cancelarAposta(Long idAposta) {
		
		Aposta aposta  = apostaServices.getById(idAposta);
		
		try {
			apostaServices.cancelarApostaAdmin(aposta);
			reportarMensagemDeSucesso("Aposta cancelada");
		} 	catch (ServiceExceptionCancelarApostaEmEstadoInvalido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportarMensagemDeErro("O estado desta aposta não suporta um cancelamento.");
		}	catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportarMensagemDeErro("Não foi possível cancelar aposta");
		}
		
		
	}
	
	//getters and setters

	public ModalidadeServices getModalidadeServices() {
		return modalidadeServices;
	}

	public void setModalidadeServices(ModalidadeServices modalidadeServices) {
		this.modalidadeServices = modalidadeServices;
	}



	public CompeticaoServices getCompeticaoServices() {
		return competicaoServices;
	}

	public void setCompeticaoServices(CompeticaoServices competicaoServices) {
		this.competicaoServices = competicaoServices;
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



	public DisputanteServices getDisputanteServices() {
		return disputanteServices;
	}



	public void setDisputanteServices(DisputanteServices disputanteServices) {
		this.disputanteServices = disputanteServices;
	}



	public List<Aposta> getApostas() {
		return apostas;
	}



	public void setApostas(List<Aposta> apostas) {
		this.apostas = apostas;
	}



	public Long getIdEvento() {
		return idEvento;
	}



	public void setIdEvento(Long idEvento) {
		this.idEvento = idEvento;
	}



	public Long getIdUsuario() {
		return idUsuario;
	}



	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}



	public Long getIdCompeticao() {
		return idCompeticao;
	}



	public void setIdCompeticao(Long idCompeticao) {
		this.idCompeticao = idCompeticao;
	}



	public Integer getEstadoAposta() {
		return estadoAposta;
	}



	public void setEstadoAposta(Integer estadoAposta) {
		this.estadoAposta = estadoAposta;
	}






	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
