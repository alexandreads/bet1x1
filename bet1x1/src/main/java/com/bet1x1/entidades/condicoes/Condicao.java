package com.bet1x1.entidades.condicoes;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.bet1x1.entidades.apostas.Aposta;
import com.bet1x1.entidades.disputantes.Disputante;


@Entity
public class  Condicao{
	
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	private Integer tipoCondicao;
	
	//disputante1 esta intimamente ligado com usuarioDesafiante
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name="disputante1_id")
	private Disputante disputante1;
	
	//disputante1 esta intimamente ligado com usuarioAceitador
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name="disputante2_id")
	private Disputante disputante2;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="aposta_id")
	private Aposta aposta;
	
	// variantes tem valor padrao 0
	private Float varianteDisputante1 = 0f;
	private Float varianteDisputante2 = 0f;
	
	private Integer maiorOuMenor;
	
	

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTipoCondicao() {
		return tipoCondicao;
	}

	public void setTipoCondicao(Integer tipoCondicao) {
		this.tipoCondicao = tipoCondicao;
	}



	public Disputante getDisputante1() {
		return disputante1;
	}

	public void setDisputante1(Disputante disputante1) {
		
		System.out.println("chegou em setdisp 1");
		this.disputante1 = disputante1;
	}

	public Disputante getDisputante2() {
		return disputante2;
	}

	public void setDisputante2(Disputante disputante2) {
		this.disputante2 = disputante2;
	}

	public Float getVarianteDisputante1() {
		return varianteDisputante1;
	}

	public void setVarianteDisputante1(Float varianteDisputante1) {
		
		System.out.println("chegou set variante");
		this.varianteDisputante1 = varianteDisputante1;
	}

	public Float getVarianteDisputante2() {
		return varianteDisputante2;
	}

	public void setVarianteDisputante2(Float varianteDisputante2) {
		this.varianteDisputante2 = varianteDisputante2;
	}

	public Integer getMaiorOuMenor() {
		return maiorOuMenor;
	}

	public void setMaiorOuMenor(Integer maiorOuMenor) {
		this.maiorOuMenor = maiorOuMenor;
	}

	public Aposta getAposta() {
		return aposta;
	}

	public void setAposta(Aposta aposta) {
		this.aposta = aposta;
	}
	
	
	

}
