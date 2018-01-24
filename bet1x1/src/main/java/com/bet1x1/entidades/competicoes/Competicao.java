
package com.bet1x1.entidades.competicoes;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.bet1x1.entidades.eventos.Evento;
import com.bet1x1.entidades.modalidades.Modalidade;



@Entity
public class Competicao {
	
	

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private String nome;
    
    
    @ManyToOne
    @JoinColumn(name="modalidade_id")
	private Modalidade modalidade;
    
	@OneToMany(mappedBy="competicao", cascade = CascadeType.ALL)
	private List<Evento> eventos;


    @Override
    public String toString() {
    	return String.valueOf(id);
    }
    
	@Override
	public boolean equals(Object obj) {
		
		
		Competicao other = (Competicao) obj;
		
		if(other.getNome().equals(this.getNome())) {
			if(other.getId() == this.getId()) {
				return true;
			}
		}
		
		return false;
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		
//		Competicao other = (Competicao) obj;
//		
//		if (nome == null) {
//			if (other.nome != null)
//				return false;
//			
//		} else if (!nome.equals(other.nome)) {
//			return false;
//		} else if (id != other.id) {
//			return false;
//		}
//		return true;
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



	public Modalidade getModalidade() {
		return modalidade;
	}



	public void setModalidade(Modalidade modalidade) {
		this.modalidade = modalidade;
	}







	
	
    
    
	
}
