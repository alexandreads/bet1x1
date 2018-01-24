package com.bet1x1.beans;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.bet1x1.entidades.amizades.Amizade;
import com.bet1x1.entidades.amizades.AmizadeServices;
import com.bet1x1.entidades.amizades.EstadosDaAmizade;
import com.bet1x1.entidades.apostas.ApostaServices;
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
public class Amizades extends AbstractBean {

	
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
	
	
	//listas
	
	private List<Amizade> amizadesEfetivadas;
	private List<Amizade> pedidosRecebidos;
	private List<Amizade> pedidosEnviados;
	
	private List<Usuario> usuariosEncontrados;

	
	//filtros
	private String loginFiltro;
	
	
	
	
	
	
	public void init() {
		
		loadAmizadesEfetivadas();
		loadPedidosRecebidos();
		loadPedidosEnviados();
		
		
		
	}
	

	public void loadAmizadesEfetivadas() {
		
		Usuario logado = usuarioServices.getUsuarioLogado();
		
		
		amizadesEfetivadas = amizadeServices.recuperarTodasUsuarioEfetivadas(logado);
		
	}
	
	public void loadPedidosRecebidos() {
		
		Usuario logado = usuarioServices.getUsuarioLogado();
		
		pedidosRecebidos = amizadeServices.recuperarTodasUsuarioRecebidas(logado);
	}
	
	public void loadPedidosEnviados() {
		
		Usuario logado = usuarioServices.getUsuarioLogado();
		
		pedidosEnviados = amizadeServices.recuperarTodasUsuarioEnviados(logado);
	}
	
	
	
	public void loadUsuariosEncontrados() {
		
		Usuario u = new Usuario();

		
		u.setLogin(loginFiltro);
		
		usuariosEncontrados = usuarioServices.retornarDeFiltro(u);
	}
	
	public String rejeitarPedido(Long idAmizade) {
		
		Amizade amizadeRejeitada = null;
		try {
			amizadeRejeitada = amizadeServices.getById(idAmizade);
			amizadeServices.rejeitarPedido(amizadeRejeitada);
			
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return EnderecoPaginas.AMIZADES+EnderecoPaginas.FACES_REDIRECT;
		
		
	}
	
	public String desfazerConvite(Long idAmizade) {
		
		
		Amizade amizadeCancelada = null;
		try {
			amizadeCancelada = amizadeServices.getById(idAmizade);
			amizadeServices.cancelarPedido(amizadeCancelada);
			
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		reportarMensagemDeSucesso("Pedido de amizade cancelado com sucesso!");
		
		return EnderecoPaginas.AMIZADES+EnderecoPaginas.FACES_REDIRECT;
		
	}
	
	
	
	public String aceitarPedido(Long idAmizade) {
		
		
		Amizade amizadeAceita = null;
		try {
			amizadeAceita = amizadeServices.getById(idAmizade);
			amizadeAceita.setEstado(EstadosDaAmizade.AMIZADE_ACEITA);
			amizadeServices.update(amizadeAceita);
			
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		reportarMensagemDeSucesso("Pedido de amizade aceito com sucesso!");
		
		return EnderecoPaginas.AMIZADES+EnderecoPaginas.FACES_REDIRECT;
		
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


	public List<Amizade> getAmizadesEfetivadas() {
		return amizadesEfetivadas;
	}


	public void setAmizadesEfetivadas(List<Amizade> amizadesEfetivadas) {
		this.amizadesEfetivadas = amizadesEfetivadas;
	}


	public String getLoginFiltro() {
		return loginFiltro;
	}


	public void setLoginFiltro(String loginFiltro) {
		this.loginFiltro = loginFiltro;
	}


	public List<Usuario> getUsuariosEncontrados() {
		return usuariosEncontrados;
	}


	public void setUsuariosEncontrados(List<Usuario> usuariosEncontrados) {
		this.usuariosEncontrados = usuariosEncontrados;
	}


	public List<Amizade> getPedidosRecebidos() {
		return pedidosRecebidos;
	}


	public void setPedidosRecebidos(List<Amizade> pedidosRecebidos) {
		this.pedidosRecebidos = pedidosRecebidos;
	}


	public List<Amizade> getPedidosEnviados() {
		return pedidosEnviados;
	}


	public void setPedidosEnviados(List<Amizade> pedidosEnviados) {
		this.pedidosEnviados = pedidosEnviados;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
