package com.bet1x1.entidades.modalidades;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.bet1x1.entidades.competicoes.Competicao;







@Entity
public class Modalidade { 


	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    
	private String nome;
	
	
	
	@OneToMany(mappedBy="modalidade", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Competicao> competicoes;
	
	
	@Override
	public String toString() {
		return String.valueOf(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		
		
		
		Modalidade other = (Modalidade) obj;
		
		if(other.getNome().equals(this.getNome())) {
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
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Competicao> getCompeticoes() {
		return competicoes;
	}

	public void setCompeticoes(List<Competicao> competicoes) {
		this.competicoes = competicoes;
	}

	
	
	
	

}
