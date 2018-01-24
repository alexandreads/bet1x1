package com.bet1x1.beans;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.bet1x1.entidades.amizades.Amizade;
import com.bet1x1.entidades.amizades.AmizadeServices;
import com.bet1x1.entidades.amizades.EstadosDaAmizade;
import com.bet1x1.entidades.apostas.ApostaServices;
import com.bet1x1.entidades.eventos.EventoServices;
import com.bet1x1.entidades.usuarios.Usuario;
import com.bet1x1.entidades.usuarios.UsuarioServices;
import com.bet1x1.utilitarios.EnderecoPaginas;




@ViewScoped
@ManagedBean
public class PerfilUsuario extends AbstractBean{
	
	//services
	private EventoServices eventoServices = new EventoServices();
	private UsuarioServices usuarioServices = new UsuarioServices();
	private ApostaServices apostaServices = new ApostaServices();
	private AmizadeServices amizadeServices = new AmizadeServices();
	
	
	//variaveis

	private Usuario usuario;
	
	private Amizade amizade;
	
	

	
	
	
	
	
	
	
	public void init() {
		
		loadAmizade();
		
		
	}
	
	public String solicitarAmizade() {
		
		Usuario logado = usuarioServices.getUsuarioLogado();
		
			
		amizadeServices.solicitarAmizade(amizade, logado, usuario);
		
		
		reportarMensagemDeSucesso("Pedido de amizade enviado com sucesso!");
		
		return EnderecoPaginas.PERFIL_USUARIO+EnderecoPaginas.FACES_REDIRECT+"usuario="+usuario.getId();
		
	}
	
	public String desfazerConvite() {
		
		
		amizadeServices.cancelarPedido(amizade);
		
		reportarMensagemDeSucesso("Pedido de amizade cancelado com sucesso!");
		
		return EnderecoPaginas.PERFIL_USUARIO+EnderecoPaginas.FACES_REDIRECT+"usuario="+usuario.getId();
		
	}
	
	
	
	

	public void loadAmizade() {
		
		Usuario logado = usuarioServices.getUsuarioLogado();
		amizade  = amizadeServices.entreDoisUsuarios(logado, usuario);
	}
	
	
	
	
	
	public boolean naoAmigos() {
		
		if(amizade == null) {
			
			return true;
		}
		
		return false;
	}
	
	
	public boolean amizadeEnviada() {
		
		if(amizade != null) {
			
			if(amizade.getEstado() == EstadosDaAmizade.PEDIDO_ENVIADO) {
				return true;
				
			}
		}
		
		return false;
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
