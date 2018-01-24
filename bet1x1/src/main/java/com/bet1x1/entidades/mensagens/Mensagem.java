package com.bet1x1.entidades.mensagens;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bet1x1.entidades.conversas.Conversa;
import com.bet1x1.entidades.usuarios.Usuario;
import com.bet1x1.entidades.usuarios.UsuarioServices;



@Entity
public class Mensagem { 


	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    
	private String texto;
	
	private Boolean lida;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	
	
    @ManyToOne (fetch= FetchType.EAGER)
    @JoinColumn(name = "conversa_id")
	private Conversa conversa;
	
    @ManyToOne (fetch= FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@Override
	public String toString() {
		return String.valueOf(id);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		
		
		Mensagem other = (Mensagem) obj;
		
		if(other.getTexto().equals(this.getTexto())) {
			if(other.getId() == this.getId()) {
				return true;
			}
		}
		
		return false;
		
			

		
	}
	
	
	public String retornarTextoTrabalhado() {
		
		
		UsuarioServices usuarioServices = new UsuarioServices();
		Usuario logado  = usuarioServices.getUsuarioLogado();
		
		String prefixo = "";
		
		if(logado.getId() == usuario.getId()) {
			prefixo = "Você disse: ";
			
		} else {
			prefixo = conversa.nomeDoOutro() +" disse: ";
		}
		
		return prefixo + texto;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Conversa getConversa() {
		return conversa;
	}

	public void setConversa(Conversa conversa) {
		this.conversa = conversa;
	}


	public Boolean getLida() {
		return lida;
	}


	public void setLida(Boolean lida) {
		this.lida = lida;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}

	
	
	
	
	
	
	

}
