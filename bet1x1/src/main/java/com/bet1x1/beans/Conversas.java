package com.bet1x1.beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang.StringEscapeUtils;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

import com.bet1x1.entidades.apostas.ApostaServices;
import com.bet1x1.entidades.competicoes.CompeticaoServices;
import com.bet1x1.entidades.conversas.Conversa;
import com.bet1x1.entidades.conversas.ConversaServices;
import com.bet1x1.entidades.eventos.EventoServices;
import com.bet1x1.entidades.modalidades.ModalidadeServices;
import com.bet1x1.entidades.usuarios.UsuarioServices;


@ViewScoped
@ManagedBean
public class Conversas extends AbstractBean {

	
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
	private ConversaServices conversaServices = new ConversaServices();
	
	
	//listas
	
	private List<Conversa> conversasLista;
 
	
	
	
	
	
	
	public void init() {
		
		loadConversas();
		
		
		
	}
	
	public void loadConversas() {
		
		
		conversasLista = conversaServices.pegarDeUmUsuario(usuarioServices.getUsuarioLogado());
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

	
	public ConversaServices getConversaServices() {
		return conversaServices;
	}

	public void setConversaServices(ConversaServices conversaServices) {
		this.conversaServices = conversaServices;
	}

	public List<Conversa> getConversasLista() {
		return conversasLista;
	}

	public void setConversasLista(List<Conversa> conversasLista) {
		this.conversasLista = conversasLista;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
