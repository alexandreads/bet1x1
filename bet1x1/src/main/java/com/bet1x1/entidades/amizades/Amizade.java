package com.bet1x1.entidades.amizades;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.bet1x1.entidades.usuarios.Usuario;
import com.bet1x1.entidades.usuarios.UsuarioServices;


@Entity
public class Amizade {
	
	
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    
    // o amigo1 é oque manda os pedidos de amizade, quando a amizade é rejeitada então é melhor deletar ela do sistema.
    @ManyToOne (fetch= FetchType.EAGER)
    @JoinColumn(name = "amigo1_id")
    private Usuario amigo1;

    @ManyToOne (fetch= FetchType.EAGER)
    @JoinColumn(name = "amigo2_id")
	private Usuario amigo2;
    
    
    
	
    	
	private Integer estado;
	
	
	public String amigoLogin() {
		
		UsuarioServices usuarioServices = new UsuarioServices();
		
		Usuario logado = usuarioServices.getUsuarioLogado();
		
		
		if(logado.getId() == amigo1.getId()) {
			return amigo2.getLogin();
		}
		
		return amigo1.getLogin();
	}
	
	public Long amigoId() {
		
		UsuarioServices usuarioServices = new UsuarioServices();
		
		Usuario logado = usuarioServices.getUsuarioLogado();
		
		
		if(logado.getId() == amigo1.getId()) {
			return amigo2.getId();
		}
		
		return amigo1.getId();
		
	}
	
	
	
	
	@Override
	public String toString() {
		return String.valueOf(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		
		
		
		 Amizade other = (Amizade) obj;
		
		if(other.getId() == this.getId()) {
				return true;
			
		}
		
		return false;
		
			

		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getAmigo1() {
		return amigo1;
	}

	public void setAmigo1(Usuario amigo1) {
		this.amigo1 = amigo1;
	}

	public Usuario getAmigo2() {
		return amigo2;
	}

	public void setAmigo2(Usuario amigo2) {
		this.amigo2 = amigo2;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	


	
	
	
	

}
