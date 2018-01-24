package com.bet1x1.entidades.usuarios;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.bet1x1.entidades.amizades.Amizade;
import com.bet1x1.entidades.apostas.Aposta;
import com.bet1x1.entidades.mensagens.Mensagem;
import com.bet1x1.entidades.usuarios.enderecos.Endereco;

@Entity
public class Usuario {
	
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
	private String nome;
    
	@Column(unique=true)
    private String login;
    
    private String email;
    
    private String cpf;
    
    private String senha;
    
    private String tipo;
    
    private Float saldo = 0f;
    
    private Integer estado = EstadosDoUsuario.ATIVO;
    
    @OneToMany(mappedBy="usuarioDesafiante" , fetch=FetchType.EAGER, cascade= {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE})
    private List<Aposta> apostasDesafiantes;
    
    @OneToMany(mappedBy="usuarioDesafiado" , fetch=FetchType.EAGER, cascade= {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE})
    private List<Aposta> apostasDesafiadas;
    
    @OneToMany(mappedBy="usuarioAceitador" , fetch=FetchType.EAGER, cascade= {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE})
    private List<Aposta> apostasAceitadoras;
    
	@OneToMany(mappedBy="usuario", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Mensagem> mensagens;
	
	
	@OneToMany(mappedBy = "amigo1", cascade = CascadeType.ALL)
	private List<Amizade> amizades1;
    
	@OneToMany(mappedBy = "amigo2", cascade = CascadeType.ALL)
	private List<Amizade> amizades2;
	
	
	
	@OneToOne (cascade=CascadeType.ALL)
	@JoinColumn(name="endereco_id")
	private Endereco endereco;
	
	
	
	
	
	
	
	
	
    
    @Override
	public String toString() {
		return String.valueOf(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login)) {
			return false;
			
		}
		return true;
	}
	
    public String estadoString() {
    	
    	
    	if(estado == EstadosDoUsuario.ATIVO) {
    		
    		return "ATIVO";
    	}
    	
    	else if(estado == EstadosDoUsuario.DESATIVADO) {
    		
    		return "DESATIVADO";
    	}
    	
    	else if(estado == EstadosDoUsuario.SUSPENSO) {
    		
    		return "SUSPENSO";
    	}
    	
    	return "";
    	
    }
    
    public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getSaldo() {
		return saldo;
	}

	public void setSaldo(Float saldo) {
		this.saldo = saldo;
	}

	public List<Aposta> getApostasDesafiantes() {
		return apostasDesafiantes;
	}

	public void setApostasDesafiantes(List<Aposta> apostasDesafiantes) {
		this.apostasDesafiantes = apostasDesafiantes;
	}

	public List<Aposta> getApostasDesafiadas() {
		return apostasDesafiadas;
	}

	public void setApostasDesafiadas(List<Aposta> apostasDesafiadas) {
		this.apostasDesafiadas = apostasDesafiadas;
	}

	public List<Aposta> getApostasAceitadoras() {
		return apostasAceitadoras;
	}

	public void setApostasAceitadoras(List<Aposta> apostasAceitadoras) {
		this.apostasAceitadoras = apostasAceitadoras;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public List<Mensagem> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}

	public List<Amizade> getAmizades1() {
		return amizades1;
	}

	public void setAmizades1(List<Amizade> amizades1) {
		this.amizades1 = amizades1;
	}

	public List<Amizade> getAmizades2() {
		return amizades2;
	}

	public void setAmizades2(List<Amizade> amizades2) {
		this.amizades2 = amizades2;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	

	


    
    
    
    

}
