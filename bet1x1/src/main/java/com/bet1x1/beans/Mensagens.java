package com.bet1x1.beans;


import java.util.Date;
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
import com.bet1x1.entidades.excecoes.service.ServiceException;
import com.bet1x1.entidades.mensagens.Mensagem;
import com.bet1x1.entidades.mensagens.MensagemServices;
import com.bet1x1.entidades.modalidades.ModalidadeServices;
import com.bet1x1.entidades.usuarios.Usuario;
import com.bet1x1.entidades.usuarios.UsuarioServices;
import com.bet1x1.utilitarios.EnderecoPaginas;


@ViewScoped
@ManagedBean
public class Mensagens extends AbstractBean {

	
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
	private MensagemServices mensagemServices = new MensagemServices();
	
	
	
	private Conversa conversa;
	
	private String textoEnviar;
	
	
	private List<Mensagem> mensagensLista;
	
	
	
	
	
	
	public void init() {
		
//		verificarSeUsuarioPodeVer();
		
		loadMensagens();
		
		Usuario logado = usuarioServices.getUsuarioLogado();
		
		try {
			mensagemServices.setarTodasComoLidasDeUmUsuario(mensagensLista, logado);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void loadMensagens() {
		
		mensagensLista = mensagemServices.pegarDeUmaConversa(conversa);
		
		
	}
	
	public String verificarSeUsuarioPodeVer() {
		
		System.out.println("aqui1");
		Usuario logado = usuarioServices.getUsuarioLogado();
		
		String resultado = "";
		
		if(logado.getId() != conversa.getDestinatario().getId() && logado.getId() != conversa.getRemetente().getId()) {
				System.out.println("aqui");
				resultado = EnderecoPaginas.PAGINA_PRINCIPAL+EnderecoPaginas.FACES_REDIRECT;
				
			
		} 
		
		
		return resultado;
		
		
	}
	
	
	
	
	public void notificarPush() {
		

		String CHANNEL = "/chat";
		
		EventBus eventBus = EventBusFactory.getDefault().eventBus();
		eventBus.publish(CHANNEL);
	}
	
	
	public void enviarMensagem() {
		
		Usuario logado = usuarioServices.getUsuarioLogado();
		
		Mensagem mensagem = new Mensagem();
		
		mensagem.setTexto(getTextoEnviar());
		
		mensagem.setLida(false);
		mensagem.setConversa(conversa);
		mensagem.setUsuario(logado);
		mensagem.setData(new Date());

		conversa.getMensagens().add(mensagem);
		
		conversaServices.update(conversa);
		
		conversa.setMensagens(mensagensLista);
		setTextoEnviar("");
		
		notificarPush();
		
		
//		String result = EnderecoPaginas.MENSAGENS+"?conversa=1"+EnderecoPaginas.FACES_REDIRECT;
//		
//		return result;
		
		
	
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




	public String getTextoEnviar() {
		return textoEnviar;
	}

	public void setTextoEnviar(String textoEnviar) {
		this.textoEnviar = textoEnviar;
	}





	public ConversaServices getConversaServices() {
		return conversaServices;
	}





	public void setConversaServices(ConversaServices conversaServices) {
		this.conversaServices = conversaServices;
	}





	public Conversa getConversa() {
		return conversa;
	}





	public void setConversa(Conversa conversa) {
		this.conversa = conversa;
	}


	public MensagemServices getMensagemServices() {
		return mensagemServices;
	}


	public void setMensagemServices(MensagemServices mensagemServices) {
		this.mensagemServices = mensagemServices;
	}


	public List<Mensagem> getMensagensLista() {
		return mensagensLista;
	}


	public void setMensagensLista(List<Mensagem> mensagensLista) {
		this.mensagensLista = mensagensLista;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
