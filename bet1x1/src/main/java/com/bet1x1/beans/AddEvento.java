package com.bet1x1.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.bet1x1.entidades.competicoes.Competicao;
import com.bet1x1.entidades.competicoes.CompeticaoServices;
import com.bet1x1.entidades.disputantes.Disputante;
import com.bet1x1.entidades.disputantes.DisputanteServices;
import com.bet1x1.entidades.eventos.Evento;
import com.bet1x1.entidades.eventos.EventoServices;
import com.bet1x1.entidades.modalidades.Modalidade;
import com.bet1x1.entidades.modalidades.ModalidadeServices;
import com.bet1x1.entidades.scores.Score;
import com.bet1x1.utilitarios.EnderecoPaginas;




@ViewScoped
@ManagedBean
public class AddEvento extends AbstractBean{
	
	//services
	private CompeticaoServices competicaoServices = new CompeticaoServices();
	private ModalidadeServices modalidadeServices = new ModalidadeServices();
	private DisputanteServices disputanteServices = new DisputanteServices();
	private EventoServices eventoServices = new EventoServices();
	
	
	//variaveis
	private Modalidade modalidadeFiltro;
	
	private Competicao competicaoFiltro;
	
	private Disputante disputanteAdicionar;
	
	private Evento evento;
	
	private Boolean emCasa;

	private String nomeEvento;
	
	private Date data;
	

	
	
	// arrays
	
	private List<Modalidade> modalidades = new ArrayList<Modalidade>();
	private List<Competicao> competicoes = new ArrayList<Competicao>();
	private List<Score> scores = new ArrayList<Score>();
	private List<Disputante> disputantes = new ArrayList<Disputante>();
	
	
	
	
	
	
	public void init() {
		
		evento = new Evento();
		//setando a modalidade
		loadModalidades();
		loadCompeticoes();
		loadDisputantes();
		
		
	
	}
	
	public boolean isModalidadeSelected() {
		
		if(modalidadeFiltro != null) {
			return true;
		}
		
		return false;
	}
	
	public void loadCompeticoes(){
		competicoes =  competicaoServices.retornarTodosDeUmaModalidadeEmOrdemAlfabetica(modalidadeFiltro);

	}
	
	
	public void loadModalidades() {
		modalidades = modalidadeServices.retornarTodasEmOrdemAlfabetica();
		
	}
	
	public void loadDisputantes() {
		disputantes = disputanteServices.retornarTodosDeUmaModalidade(modalidadeFiltro);
		
	}
	
	public void loadCompeticoesEDisputantes() {
		loadCompeticoes();
		loadDisputantes();
		
	}
	
	
	public void adicionarDisputanteAoEvento() {
		
		if(disputanteAdicionar == null) {
			
			reportarMensagemDeErro("Disputante nulo.");
		}
		
		else if(competicaoFiltro == null) {
			
			reportarMensagemDeErro("Competição nula.");
		}
		
		
		else {
			
			Score score = new Score();
			
			score.setDisputante(disputanteAdicionar);
			score.setEvento(evento);
			score.setJogaEmCasa(emCasa);
			scores.add(score);
			
		}
		
		
	}
	

	
	
	public void adicionarEvento() {
		

		evento.setNome(nomeEvento);
		evento.setScores(scores);
		evento.setCompeticao(competicaoFiltro);
		evento.setData(data);
		
		eventoServices.save(evento);
		
		reportarMensagemDeSucesso("Evento cadastrado com sucesso.");
		
//		return EnderecoPaginas.PAGINA_PRINCIPAL+EnderecoPaginas.FACES_REDIRECT; 
		
	}



	public ModalidadeServices getModalidadeServices() {
		return modalidadeServices;
	}

	public void setModalidadeServices(ModalidadeServices modalidadeServices) {
		this.modalidadeServices = modalidadeServices;
	}

	public Modalidade getModalidadeFiltro() {
		return modalidadeFiltro;
	}

	public void setModalidadeFiltro(Modalidade modalidadeFiltro) {
		this.modalidadeFiltro = modalidadeFiltro;
	}

	public List<Modalidade> getModalidades() {
		return modalidades;
	}


	public void setModalidades(List<Modalidade> modalidades) {
		this.modalidades = modalidades;
	}

	


	public CompeticaoServices getCompeticaoServices() {
		return competicaoServices;
	}

	public void setCompeticaoServices(CompeticaoServices competicaoServices) {
		this.competicaoServices = competicaoServices;
	}

	public EventoServices getEventoServices() {
		return eventoServices;
	}

	public void setEventoServices(EventoServices eventoServices) {
		this.eventoServices = eventoServices;
	}

	public Competicao getCompeticaoFiltro() {
		return competicaoFiltro;
	}

	public void setCompeticaoFiltro(Competicao competicaoFiltro) {
		this.competicaoFiltro = competicaoFiltro;
	}

	public List<Competicao> getCompeticoes() {
		return competicoes;
	}

	public void setCompeticoes(List<Competicao> competicoes) {
		this.competicoes = competicoes;
	}

	public List<Score> getScores() {
		return scores;
	}

	public void setScores(List<Score> scores) {
		this.scores = scores;
	}

	public List<Disputante> getDisputantes() {
		return disputantes;
	}

	public void setDisputantes(List<Disputante> disputantes) {
		this.disputantes = disputantes;
	}

	public DisputanteServices getDisputanteServices() {
		return disputanteServices;
	}

	public void setDisputanteServices(DisputanteServices disputanteServices) {
		this.disputanteServices = disputanteServices;
	}

	public Disputante getDisputanteAdicionar() {
		return disputanteAdicionar;
	}

	public void setDisputanteAdicionar(Disputante disputanteAdicionar) {
		this.disputanteAdicionar = disputanteAdicionar;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Boolean getEmCasa() {
		return emCasa;
	}

	public void setEmCasa(Boolean emCasa) {
		this.emCasa = emCasa;
	}

	public String getNomeEvento() {
		return nomeEvento;
	}

	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	
	
	


	
	
	
	
	
	
	

}
