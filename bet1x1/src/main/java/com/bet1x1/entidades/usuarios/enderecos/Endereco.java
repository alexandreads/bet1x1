package com.bet1x1.entidades.usuarios.enderecos;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;








@Entity
public class Endereco { 


	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
	
	private String numero;
	
	private String logradouro;
	
	private String bairro;
	
	private String cidade;
	
	private String estado;
	
	private String cep;
	
    
	
	@Override
	public String toString() {
		return String.valueOf(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		
		
		
		Endereco other = (Endereco) obj;
		
		if(other.getLogradouro().equals(this.getLogradouro())) {
			if(other.getId() == this.getId()) {
				return true;
			}
		}
		
		return false;
		
			

		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	
	
	
	
	
	

}
