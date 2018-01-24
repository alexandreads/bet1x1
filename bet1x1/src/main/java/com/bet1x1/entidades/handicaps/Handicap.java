package com.bet1x1.entidades.handicaps;


public class Handicap {
	
	
	
	public String nome;
	
	public Float valor;
	
	public Handicap() {
		
	}
	
	public Handicap(String nome, Float valor) {
		
		this.nome = nome;
		this.valor = valor;
		
	}
	
	
	
	@Override
	public String toString() {
		return String.valueOf(valor);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Handicap other = (Handicap) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome)) {
			return false;
		} else if (valor != other.valor) {
			return false;
		}

		return true;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}
	
	
	
	
	

}
