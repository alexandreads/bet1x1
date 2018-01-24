package com.bet1x1.beans;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.mail.EmailException;

import com.bet1x1.entidades.amizades.AmizadeServices;
import com.bet1x1.entidades.apostas.ApostaServices;
import com.bet1x1.entidades.competicoes.CompeticaoServices;
import com.bet1x1.entidades.eventos.EventoServices;
import com.bet1x1.entidades.modalidades.ModalidadeServices;
import com.bet1x1.entidades.usuarios.UsuarioServices;
import com.bet1x1.utilitarios.emails.EmailEsqueciSenha;

@ViewScoped
@ManagedBean
public class EsqueciSenha extends AbstractBean {

	
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
	private AmizadeServices amizadeServices = new AmizadeServices();
	

	
	//filtros
	private String email;
	
	
	
	
	
	
	public void init() {
		

		
		
	}
	
	public void enviarEmail() {
		
		EmailEsqueciSenha emailEsqueciSenha = new EmailEsqueciSenha();
		
		try {
			emailEsqueciSenha.enviaEmailSimples("utilita", "utilitaformularios@gmail.com", "aleandre", 
					"alexandregmf@gmail.com", "testeassunto", "testes");
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(email);
	}
	
	
	
	

	
	//getters and setters

	public ModalidadeServices getModalidadeServices() {
		return modalidadeServices;
	}

	public void setModalidadeServices(ModalidadeServices modalidadeServices) {
		this.modalidadeServices = modalidadeServices;
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


	public CompeticaoServices getCompeticaoServices() {
		return competicaoServices;
	}

	public void setCompeticaoServices(CompeticaoServices competicaoServices) {
		this.competicaoServices = competicaoServices;
	}


	public AmizadeServices getAmizadeServices() {
		return amizadeServices;
	}


	public void setAmizadeServices(AmizadeServices amizadeServices) {
		this.amizadeServices = amizadeServices;
	}






	public String getEmail() {
		return email;
	}






	public void setEmail(String email) {
		this.email = email;
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
