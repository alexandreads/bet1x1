package com.bet1x1.entidades.disputantes;

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
import javax.persistence.OneToOne;

import com.bet1x1.entidades.condicoes.Condicao;
import com.bet1x1.entidades.modalidades.Modalidade;
import com.bet1x1.entidades.scores.Score;


@Entity
public class Disputante {
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    
	private String nome;
	
	
	@OneToMany(mappedBy = "disputante", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Score> scores;
	
	
	@ManyToOne
	@JoinColumn(name="modalidade_id")
	private Modalidade modalidade;
	

	
	

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
		Disputante other = (Disputante) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome)) {
			return false;
		} else if (id != other.id) {
			return false;
		}

		return true;
	}
	
	public Disputante() {
	}
	
	public Disputante(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Score> getScores() {
		return scores;
	}

	public void setScores(List<Score> scores) {
		this.scores = scores;
	}

	public Modalidade getModalidade() {
		return modalidade;
	}

	public void setModalidade(Modalidade modalidade) {
		this.modalidade = modalidade;
	}
	
	
	
	
	

}
