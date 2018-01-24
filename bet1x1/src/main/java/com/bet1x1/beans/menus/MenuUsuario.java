package com.bet1x1.beans.menus;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.bet1x1.beans.AbstractBean;
import com.bet1x1.entidades.apostas.ApostaServices;
import com.bet1x1.entidades.competicoes.CompeticaoServices;
import com.bet1x1.entidades.disputantes.DisputanteServices;
import com.bet1x1.entidades.eventos.EventoServices;
import com.bet1x1.entidades.excecoes.service.ServiceException;
import com.bet1x1.entidades.modalidades.Modalidade;
import com.bet1x1.entidades.modalidades.ModalidadeServices;
import com.bet1x1.entidades.usuarios.EstadosDoUsuario;
import com.bet1x1.entidades.usuarios.Usuario;
import com.bet1x1.entidades.usuarios.UsuarioServices;
import com.bet1x1.utilitarios.EnderecoPaginas;


@ViewScoped
@ManagedBean
public class MenuUsuario extends AbstractBean {

	
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
	private List<Usuario> usuarios;
 
	
	//filtros
	private Usuario usuario;
	
	
	
	
	
	
	public void init() {
		
		
		usuarios = new ArrayList<Usuario>();
		
		usuario = new Usuario();
		
		
	}

	

	public void pesquisar() {
		
		usuarios = usuarioServices.retornarDeFiltro(usuario);
		
		
	}
	
	
	
	public String desabilitar(Long id) {
		
		Usuario u = usuarioServices.getById(id);
		
		u.setEstado(EstadosDoUsuario.DESATIVADO);
		
		
		try {
			usuarioServices.update(u);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		reportarMensagemDeSucesso("Usuario desativado");
		
		
		return EnderecoPaginas.MENU_USUARIO+EnderecoPaginas.FACES_REDIRECT;
		
		
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



	public DisputanteServices getDisputanteServices() {
		return disputanteServices;
	}



	public void setDisputanteServices(DisputanteServices disputanteServices) {
		this.disputanteServices = disputanteServices;
	}



	public List<Usuario> getUsuarios() {
		return usuarios;
	}



	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}



	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}




	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
