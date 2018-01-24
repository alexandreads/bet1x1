package com.bet1x1.beans.menus;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.bet1x1.beans.AbstractBean;
import com.bet1x1.entidades.apostas.ApostaServices;
import com.bet1x1.entidades.competicoes.CompeticaoServices;
import com.bet1x1.entidades.disputantes.Disputante;
import com.bet1x1.entidades.disputantes.DisputanteServices;
import com.bet1x1.entidades.eventos.EventoServices;
import com.bet1x1.entidades.excecoes.service.ServiceException;
import com.bet1x1.entidades.modalidades.Modalidade;
import com.bet1x1.entidades.modalidades.ModalidadeServices;
import com.bet1x1.entidades.usuarios.UsuarioServices;
import com.bet1x1.utilitarios.EnderecoPaginas;


@ViewScoped
@ManagedBean
public class MenuDisputante extends AbstractBean {

	
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
	private List<Modalidade> modalidades;
	private List<Disputante> disputantes;
 
	
	//filtros
	private Disputante disputanteFiltro;
	
	
	
	
	
	
	public void init() {
		
		loadModalidades();
		
		disputantes = new ArrayList<Disputante>();
		
		disputanteFiltro = new Disputante();
		
		
	}

	public void loadModalidades() {
		
		
		
		modalidades = modalidadeServices.retornarTodasEmOrdemAlfabetica();
		
	}
	

	public void pesquisar() {
		
		disputantes = disputanteServices.retornarFiltro(disputanteFiltro);
		
		
	}
	
	public String deletar(Long id) {
		
		try {
			disputanteServices.deletar(id);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Deu ruim");
		}
		
		reportarMensagemDeSucesso("Disputante removido com sucesso!");
		
		
		return EnderecoPaginas.MENU_DISPUTANTE+EnderecoPaginas.FACES_REDIRECT;
		
	}
	
	
	
	//getters and setters

	public ModalidadeServices getModalidadeServices() {
		return modalidadeServices;
	}

	public void setModalidadeServices(ModalidadeServices modalidadeServices) {
		this.modalidadeServices = modalidadeServices;
	}


	public List<Modalidade> getModalidades() {
		return modalidades;
	}

	public void setModalidades(List<Modalidade> modalidades) {
		this.modalidades = modalidades;
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

	public List<Disputante> getDisputantes() {
		return disputantes;
	}

	public void setDisputantes(List<Disputante> disputantes) {
		this.disputantes = disputantes;
	}

	public Disputante getDisputanteFiltro() {
		return disputanteFiltro;
	}

	public void setDisputanteFiltro(Disputante disputanteFiltro) {
		this.disputanteFiltro = disputanteFiltro;
	}



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
