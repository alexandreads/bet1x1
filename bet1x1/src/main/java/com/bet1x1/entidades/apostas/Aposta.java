package com.bet1x1.entidades.apostas;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.bet1x1.entidades.condicoes.Condicao;
import com.bet1x1.entidades.condicoes.TiposDeCondicao;
import com.bet1x1.entidades.disputantes.Disputante;
import com.bet1x1.entidades.eventos.Evento;
import com.bet1x1.entidades.usuarios.Usuario;
import com.bet1x1.entidades.usuarios.UsuarioServices;


@Entity
public class Aposta {
	
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
    @OneToOne(mappedBy="aposta", cascade= CascadeType.ALL, fetch= FetchType.EAGER)
	private Condicao condicao;
	
    @ManyToOne (fetch= FetchType.EAGER)
    @JoinColumn(name="evento_id")
	private Evento evento;
	
    @ManyToOne (fetch= FetchType.EAGER)
    @JoinColumn(name="usuarioDesafiante_id")
	private Usuario usuarioDesafiante;
	
    @ManyToOne (fetch= FetchType.EAGER)
    @JoinColumn(name="usuarioDesafiado_id")
	private Usuario usuarioDesafiado;
	
    @ManyToOne (fetch= FetchType.EAGER)
    @JoinColumn(name="usuarioAceitador_id")
	private Usuario usuarioAceitador;
	
    
    private Integer estado = EstadosDaAposta.ESTADO_INICIAL;
	
	private Float valorApostado;
	
	private Float valorEsperado;
	
	private String resultadoDesafiante;
	
	private String resultadoAceitador;
	
	
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
		Aposta other = (Aposta) obj;
		if (id != other.getId()) {
			return false;
		} 

		return true;
	}
	
	
	public String estadoEmString() {
		
		
		if(estado == EstadosDaAposta.EXCECAO) {
			return "EXCECAO";
		}
		
		if(estado == EstadosDaAposta.ABERTA_NO_MERCADO) {
			return "ABERTA NO MERCADO";
		}
		
		if(estado == EstadosDaAposta.COMBINADA_ANTES_DO_INICIO_EVENTO) {
			return "COMBINADA ANTES DO INICIO EVENTO";
		}
		
		if(estado == EstadosDaAposta.COMBINADA_DEPOIS_DO_INICIO_EVENTO) {
			return "COMBINADA DEPOIS DO INICIO EVENTO";
		}
		
		if(estado == EstadosDaAposta.COMBINADA_COM_RESULTADO) {
			return "COMBINADA COM RESULTADO";
		}
		
		if(estado == EstadosDaAposta.COMBINADA_DESISTIDA) {
			return "COMBINADA DESISTIDA";
		}
		
		if(estado == EstadosDaAposta.ESPIRADA) {
			return "ESPIRADA";
		}
		
		if(estado == EstadosDaAposta.ABERTA_PARA_UM_ESPECIFICO) {
			return "ABERTA PARA UM ESPECIFICO";
		}
		
		if(estado == EstadosDaAposta.CANCELADA) {
			return "CANCELADA";
		}
		
		if(estado == EstadosDaAposta.SUSPENSA) {
			return "SUSPENSA";
		}
		
		if(estado == EstadosDaAposta.ESTADO_INICIAL) {
			return "ESTADO INICIAL";
		}
		
		return "ERROR";
		
		
	}
	
	public String chamadaDaAposta() {
		
		String resultado =  "";
		
		if(condicao.getTipoCondicao() == null) {
			resultado = "nulo";
		}
		
		else if(condicao.getTipoCondicao() == TiposDeCondicao.FUTEBOL_QUEM_VENCE ) {
			
			resultado = condicao.getDisputante1().getNome() + " vence "+condicao.getDisputante2().getNome();
		}
		
		else if(condicao.getTipoCondicao() == TiposDeCondicao.AUTO_QUEM_CHEGA_NA_FRENTE ) {
			
			resultado = condicao.getDisputante1().getNome() + " chega na frente de "+condicao.getDisputante2().getNome();
		}
		
		return resultado;
		
		
		
	}
	
	public String resultadoParaLogado() {
		
		UsuarioServices usuarioServices = new UsuarioServices();
		
		Usuario usuarioLogado = usuarioServices.getUsuarioLogado();
		
		if(usuarioLogado.getId() == usuarioDesafiante.getId()) {
			
			return resultadoDesafiante;
		}
		
		else if(usuarioLogado.getId() == usuarioAceitador.getId()) {
			
			return resultadoAceitador;
		}
		
		
		return "";
	}
	
	public String desafianteOuDesafiado() {
		
		UsuarioServices usuarioServices = new UsuarioServices();
		
		Usuario usuarioLogado = usuarioServices.getUsuarioLogado();
		
		
		if(usuarioDesafiante != null) {
			if(usuarioLogado.getId() == usuarioDesafiante.getId()) {
				
				return "DESAFIANTE";
			}
		}
		
		if(usuarioAceitador != null) {
			if(usuarioLogado.getId() == usuarioAceitador.getId()) {
				
				return "DESAFIADO";
			}
		}
		
		return "";
		
	}



	public Condicao getCondicao() {
		return condicao;
	}

	public void setCondicao(Condicao condicao) {
		this.condicao = condicao;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Usuario getUsuarioDesafiante() {
		return usuarioDesafiante;
	}

	public void setUsuarioDesafiante(Usuario usuarioDesafiante) {
		this.usuarioDesafiante = usuarioDesafiante;
	}

	public Usuario getUsuarioAceitador() {
		return usuarioAceitador;
	}

	public void setUsuarioAceitador(Usuario usuarioAceitador) {
		this.usuarioAceitador = usuarioAceitador;
	}

	public Usuario getUsuarioDesafiado() {
		return usuarioDesafiado;
	}

	public void setUsuarioDesafiado(Usuario usuarioDesafiado) {
		this.usuarioDesafiado = usuarioDesafiado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getValorApostado() {
		return valorApostado;
	}

	public void setValorApostado(Float valorApostado) {
		this.valorApostado = valorApostado;
	}

	public Float getValorEsperado() {
		return valorEsperado;
	}

	public void setValorEsperado(Float valorEsperado) {
		this.valorEsperado = valorEsperado;
	}



	public Integer getEstado() {
		return estado;
	}



	public void setEstado(Integer estado) {
		this.estado = estado;
	}



	public String getResultadoDesafiante() {
		return resultadoDesafiante;
	}



	public void setResultadoDesafiante(String resultadoDesafiante) {
		this.resultadoDesafiante = resultadoDesafiante;
	}



	public String getResultadoAceitador() {
		return resultadoAceitador;
	}



	public void setResultadoAceitador(String resultadoAceitador) {
		this.resultadoAceitador = resultadoAceitador;
	}

	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	

}
