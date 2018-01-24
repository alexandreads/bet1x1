package com.bet1x1.entidades.scores;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.bet1x1.entidades.disputantes.Disputante;
import com.bet1x1.entidades.eventos.Evento;
import com.bet1x1.entidades.modalidades.Modalidade;


@Entity
public class Score {
	
	
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    
    
    @ManyToOne (fetch= FetchType.EAGER)
    @JoinColumn(name = "evento_id")
    private Evento evento;

    @ManyToOne (fetch= FetchType.EAGER)
    @JoinColumn(name = "disputante_id")
	private Disputante disputante;
	
    
    
	private Float valor;
	
	private Boolean jogaEmCasa;
	
	
	@Override
	public String toString() {
		return String.valueOf(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		
		
		
		 Score other = (Score) obj;
		
		if(other.getId() == this.getId()) {
				return true;
			
		}
		
		return false;
		
			

		
	}

	public Disputante getDisputante() {
		return disputante;
	}

	public void setDisputante(Disputante disputante) {
		this.disputante = disputante;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}


	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public Boolean getJogaEmCasa() {
		return jogaEmCasa;
	}

	public void setJogaEmCasa(Boolean jogaEmCasa) {
		this.jogaEmCasa = jogaEmCasa;
	}




	
	
	
	

}
