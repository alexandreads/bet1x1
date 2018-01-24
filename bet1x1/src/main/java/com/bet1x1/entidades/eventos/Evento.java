package com.bet1x1.entidades.eventos;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bet1x1.entidades.apostas.Aposta;
import com.bet1x1.entidades.competicoes.Competicao;
import com.bet1x1.entidades.disputantes.Disputante;
import com.bet1x1.entidades.disputantes.DisputanteDAO;
import com.bet1x1.entidades.excecoes.PersistenciaException;
import com.bet1x1.entidades.scores.Score;
import com.bet1x1.utilitarios.Outros;



@Entity
public class Evento {
	
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
    
    
	
	
	private String nome;
	
	private Integer estado;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	
    @ManyToOne
    @JoinColumn(name="competicao_id")
	private Competicao competicao;

	@OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
	private List<Score> scores;
	
	@OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
	private List<Aposta> apostas;
	
	
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
		Evento other = (Evento) obj;
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

	
	
	
	public String chamada() {
		String result =  "";
		
		DisputanteDAO disputanteDAO = new DisputanteDAO();
		
		List<Disputante> disputantes = null;
		
		try {
			disputantes = disputanteDAO.retornarDisputantesDeUmEventoOrdemNome(this);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(disputantes == null) {
			return "";
		}
		if(disputantes.size() < 2) {
			return "";
		}
		
		if(getCompeticao().getModalidade().getNome().equals("Futebol")) {
			
			result += disputantes.get(0).getNome()+ " X ";
			result += disputantes.get(1).getNome()+"; ";
			result += getCompeticao().getNome();
			
			
		}
		
		else if(getCompeticao().getModalidade().getNome().equals("Automobilismo")) {
			
			result = getNome()+"; ";
			result += getCompeticao().getNome();
			
			
		}
		
		else if(getCompeticao().getModalidade().getNome().equals("Luta")) {
			
			result += disputantes.get(0).getNome()+ " X ";
			result += disputantes.get(1).getNome()+"; ";
			result += getCompeticao().getNome();
			
			
		}
		
		return result;
		
	}
	
	
	public Date dataComTimeZone(Integer dif) {
		
		// é necessário alterar o sinal pois um evento na ing é 3 horas antes daqui mas aqui agora é 3 horas antes que lá
		dif = dif *(-1);
		
		
		LocalDateTime local = Outros.dateToLocaldateTime(data);
		
		local = local.plusHours(dif);
		
		Date retorno = Outros.localDateToDate(local);
		
		return retorno;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getNome() {
		return nome;
	}
	

	public void setNome(String nome) {
		this.nome = nome;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Score> getScores() {
		return scores;
	}

	public void setScores(List<Score> scores) {
		this.scores = scores;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	
	public Competicao getCompeticao() {
		return competicao;
	}

	public void setCompeticao(Competicao competicao) {
		this.competicao = competicao;
	}

	public List<Aposta> getApostas() {
		return apostas;
	}

	public void setApostas(List<Aposta> apostas) {
		this.apostas = apostas;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	
	
	
	
	
	
	

}
