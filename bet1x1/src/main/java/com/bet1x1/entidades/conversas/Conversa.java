package com.bet1x1.entidades.conversas;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.bet1x1.entidades.mensagens.Mensagem;
import com.bet1x1.entidades.usuarios.Usuario;
import com.bet1x1.entidades.usuarios.UsuarioServices;







@Entity
public class Conversa { 


	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    
	
	
	
    @ManyToOne (fetch= FetchType.EAGER)
    @JoinColumn(name = "remetente_id")
	private Usuario remetente;
    
    @ManyToOne (fetch= FetchType.EAGER)
    @JoinColumn(name = "destinatario_id")
	private Usuario destinatario;
	
    
    
	@OneToMany(mappedBy="conversa", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Mensagem> mensagens;
	
	
	@Override
	public String toString() {
		return String.valueOf(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		
		
		
		Conversa other = (Conversa) obj;
		
		if(other.getRemetente().getLogin().equals(this.getDestinatario().getLogin())) {
			if(other.getId() == this.getId()) {
				return true;
			}
		}
		
		return false;
		
			

		
	}
	
	public String nomeDoOutro() {
		
		
		UsuarioServices usuarioServices = new UsuarioServices();
		
		Usuario logado = usuarioServices.getUsuarioLogado();
		
		
		if(logado.getId() == destinatario.getId()) {
			
			return remetente.getLogin();
		}
		
		else if (logado.getId() == remetente.getId()) {
			
			return destinatario.getLogin();
		}
		
		return "";

		
	}
	
	public String mensagensNovas() {
		
		UsuarioServices usuarioServices = new UsuarioServices();
		
		Usuario logado = usuarioServices.getUsuarioLogado();
		
		Integer count = 0;
		
		
		
		for(Mensagem m: mensagens) {
			
			if(m.getUsuario().getId() != logado.getId()) {
				if(!m.getLida()) {
					
					count ++;
				}
				
			}
		}
		
		if(count == 0) {
			return "Não";
		}
		
		return String.valueOf(count);
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getRemetente() {
		return remetente;
	}

	public void setRemetente(Usuario remetente) {
		this.remetente = remetente;
	}

	public Usuario getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Usuario destinatario) {
		this.destinatario = destinatario;
	}

	public List<Mensagem> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}
	
	
	
	
	

}
