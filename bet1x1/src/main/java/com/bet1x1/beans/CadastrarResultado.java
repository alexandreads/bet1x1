package com.bet1x1.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import com.bet1x1.entidades.apostas.Aposta;
import com.bet1x1.entidades.apostas.ApostaServices;
import com.bet1x1.entidades.condicoes.Condicao;
import com.bet1x1.entidades.condicoes.TiposDeCondicao;
import com.bet1x1.entidades.disputantes.Disputante;
import com.bet1x1.entidades.disputantes.DisputanteServices;
import com.bet1x1.entidades.eventos.Evento;
import com.bet1x1.entidades.eventos.EventoServices;
import com.bet1x1.entidades.handicaps.Handicap;
import com.bet1x1.entidades.modalidades.Modalidade;
import com.bet1x1.entidades.scores.Score;
import com.bet1x1.entidades.scores.ScoreServices;
import com.bet1x1.entidades.usuarios.Usuario;
import com.bet1x1.entidades.usuarios.UsuarioServices;
import com.bet1x1.utilitarios.RetornadorValores;




@ViewScoped
@ManagedBean
public class CadastrarResultado extends AbstractBean{
	
	//services
	private EventoServices eventoServices = new EventoServices();
	private UsuarioServices usuarioServices = new UsuarioServices();
	private ApostaServices apostaServices = new ApostaServices();
	private ScoreServices scoreServices = new ScoreServices();
	private DisputanteServices disputanteServices = new DisputanteServices();
	
	
	//variaveis
	private Evento evento;
	
	private Modalidade modalidade;
	
	private Condicao condicao;
	
	private Aposta aposta;
	

	

	
	
	// arrays
	private List<Handicap> opcoesHandicapFutebol = RetornadorValores.handicapFutebol();
	
	private ArrayList<Disputante > disputantes = new ArrayList<>();
	private ArrayList<Score> scores = new ArrayList<Score>();
	
	
	
	
	
	public void init() {
		
		
		loadScores();

		
	}
	
	
	public boolean isFutebol() {
		
		
		
		if(modalidade == null) {
			return false;
		}
		if(modalidade.getNome().equals("Futebol")) {
			return true;
		}
		
		return false;
	}
	
	public void teste() {
		
		System.out.println("teste");
	}
	
	
	public synchronized String adicionar() {
		
		
		Usuario usuario = null;
		
		usuario = usuarioServices.getUsuarioLogado();
		
		if(usuario == null) {
			reportarMensagemDeErro("Usuário não está logado.");
		}
		
		// se eiste um usuario logado então pode cadastrar um resultado
		else {
			
			System.out.println("aqui chegou");
			
			for(Score s: scores) {
				
				System.out.println(s.getValor()+"valor");
				scoreServices.update(s);
				
			}
			
			

			
			try {
				wait(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			List<Aposta> apostas = apostaServices.todasApostasCombinadasEsperandoResultadoDeUmEvento(evento);
			
			
			apostaServices.resolverApostas(apostas, evento);
			
			reportarMensagemDeSucesso("Resultado cadastrado com sucesso.");
			
		}
		
		

		
		
		
		
		return "index.xhtml?faces-redirect=true"; 
		
	}
	
	
	

	
	public void loadScores(){
		
		scores = scoreServices.retornarScoresComBaseEvento(evento);
	}
	

	
	// getters e setters
	
	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Modalidade getModalidade() {
		return modalidade;
	}

	public void setModalidade(Modalidade modalidade) {
		this.modalidade = modalidade;
	}


	public ApostaServices getApostaServices() {
		return apostaServices;
	}


	public void setApostaServices(ApostaServices apostaServices) {
		this.apostaServices = apostaServices;
	}

	public EventoServices getEventoServices() {
		return eventoServices;
	}


	public void setEventoServices(EventoServices eventoServices) {
		this.eventoServices = eventoServices;
	}




	public Condicao getCondicao() {
		return condicao;
	}


	public void setCondicao(Condicao condicao) {
		this.condicao = condicao;
	}


	public Aposta getAposta() {
		return aposta;
	}


	public void setAposta(Aposta aposta) {
		this.aposta = aposta;
	}


	public UsuarioServices getUsuarioServices() {
		return usuarioServices;
	}


	public void setUsuarioServices(UsuarioServices usuarioServices) {
		this.usuarioServices = usuarioServices;
	}


	public List<Handicap> getOpcoesHandicapFutebol() {
		return opcoesHandicapFutebol;
	}


	public void setOpcoesHandicapFutebol(List<Handicap> opcoesHandicapFutebol) {
		this.opcoesHandicapFutebol = opcoesHandicapFutebol;
	}


	public ScoreServices getScoreServices() {
		return scoreServices;
	}


	public void setScoreServices(ScoreServices scoreServices) {
		this.scoreServices = scoreServices;
	}


	public ArrayList<Score> getScores() {
		return scores;
	}


	public void setScores(ArrayList<Score> scores) {
		this.scores = scores;
	}


	public DisputanteServices getDisputanteServices() {
		return disputanteServices;
	}


	public void setDisputanteServices(DisputanteServices disputanteServices) {
		this.disputanteServices = disputanteServices;
	}


	public ArrayList<Disputante> getDisputantes() {
		return disputantes;
	}


	public void setDisputantes(ArrayList<Disputante> disputantes) {
		this.disputantes = disputantes;
	}




	
	
	
	
	
	
	

}
