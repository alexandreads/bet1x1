package com.bet1x1.beans;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.bet1x1.entidades.excecoes.service.ServiceException;
import com.bet1x1.entidades.modalidades.Modalidade;
import com.bet1x1.entidades.modalidades.ModalidadeServices;
import com.bet1x1.entidades.usuarios.Usuario;
import com.bet1x1.entidades.usuarios.UsuarioServices;
import com.bet1x1.utilitarios.EnderecoPaginas;




@ViewScoped
@ManagedBean
public class EditarUsuario extends AbstractBean{
	
	//services
	private ModalidadeServices modalidadeServices = new ModalidadeServices();
	private UsuarioServices usuarioServices = new UsuarioServices();
	
	//variaveis
	private Modalidade modalidadeEscolhida;
	
	private Usuario usuario;

	

	
	
	
	
	
	
	public void init() {
		
		
	}
	
	
	
	
	public String editar() {
		
			
		try {
			usuarioServices.update(usuario);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportarMensagemDeErro("Usuario não foi atualizado com sucesso!");
		}
		
		
		
		reportarMensagemDeSucesso("Usuario atualizado com sucesso!");
		
		
		return EnderecoPaginas.MENU_USUARIO+EnderecoPaginas.FACES_REDIRECT;
		
		
	}



	public ModalidadeServices getModalidadeServices() {
		return modalidadeServices;
	}


	public void setModalidadeServices(ModalidadeServices modalidadeServices) {
		this.modalidadeServices = modalidadeServices;
	}


	public Modalidade getModalidadeEscolhida() {
		return modalidadeEscolhida;
	}


	public void setModalidadeEscolhida(Modalidade modalidadeEscolhida) {
		this.modalidadeEscolhida = modalidadeEscolhida;
	}




	public UsuarioServices getUsuarioServices() {
		return usuarioServices;
	}




	public void setUsuarioServices(UsuarioServices usuarioServices) {
		this.usuarioServices = usuarioServices;
	}




	public Usuario getUsuario() {
		return usuario;
	}




	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}








	
	
	
	
	
	
	

}

