package com.bet1x1.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.bet1x1.entidades.apostas.Aposta;
import com.bet1x1.entidades.apostas.ApostaServices;
import com.bet1x1.entidades.apostas.EstadosDaAposta;
import com.bet1x1.entidades.competicoes.Competicao;
import com.bet1x1.entidades.competicoes.CompeticaoServices;
import com.bet1x1.entidades.eventos.EstadosDoEvento;
import com.bet1x1.entidades.eventos.Evento;
import com.bet1x1.entidades.eventos.EventoServices;
import com.bet1x1.entidades.excecoes.service.ServiceException;
import com.bet1x1.entidades.modalidades.Modalidade;
import com.bet1x1.entidades.modalidades.ModalidadeServices;
import com.bet1x1.entidades.usuarios.Usuario;
import com.bet1x1.entidades.usuarios.UsuarioServices;
import com.bet1x1.utilitarios.EnderecoPaginas;


@ViewScoped
@ManagedBean
public class MercadoApostas extends AbstractBean {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5976838804313515033L;

	//services
	private ModalidadeServices modalidadeServices = new ModalidadeServices();
	private CompeticaoServices competicaoServices = new CompeticaoServices();
	private EventoServices eventoServices = new EventoServices();
	private ApostaServices apostaServices = new ApostaServices();
	private UsuarioServices usuarioServices = new UsuarioServices();
	
	
	//listas
	private List<Modalidade> modalidades;
	private List<Competicao> competicoes;
	private List<Evento> eventos;
	
	private List<Aposta> apostas;

	
	//filtros
	private Modalidade modalidadeFiltro;
	private Competicao competicaoFiltro;
	private Evento eventoFiltro;
	private Date dataAte;
	private Date dataAPartir;
	private Float valorPegarMin;
	private Float valorPegarMax;
	private Float valorRetornoMin;
	private Float valorRetornoMax;
	
	
	
	
	
	public void init() {
		
		loadModalidades();
		loadCompeticoes();
		loadApostas();
		loadEventos();
		
		
	}
	
	public void teste() {
		
		System.out.println("teste");
	}
	

	public void loadApostas() {
		
		try {
			System.out.println("aqui5");
			apostas = apostaServices.pegarDeAcordoComFiltro(modalidadeFiltro, competicaoFiltro, eventoFiltro, dataAte, dataAPartir, 
					valorPegarMin, valorPegarMax, valorRetornoMin, valorRetornoMax, EstadosDaAposta.ABERTA_NO_MERCADO, null);
			System.out.println("aqui4");

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			reportarMensagemDeErro("Busca falhou");
		}
		
	}
	
	public void loadModalidades() {
		
		modalidades = modalidadeServices.retornarTodasEmOrdemAlfabetica();
		
	}
	
	public void loadCompeticoes() {
		
		competicoes = competicaoServices.retornarTodosDeUmaModalidadeEmOrdemAlfabetica(modalidadeFiltro);
		
	}
	
	public void loadEventos() {
		
		
		eventos = eventoServices.listaFiltro(modalidadeFiltro, competicaoFiltro, null, null, EstadosDoEvento.ABERTO);
	}
	
	public String pegarAposta(Long idAposta) {
		
		
		Usuario usuario = usuarioServices.getUsuarioLogado();
		Aposta aposta = apostaServices.getById(idAposta);
		
		
		if(usuario == null) {
			
			reportarMensagemDeErro("Não existe um usuário logado.");
		}
		
		else if (usuario.getSaldo() < aposta.getValorEsperado()) {

			reportarMensagemDeErro("Você não tem saldo suficiente para pegar esta aposta.");
			
		}
		
		else {
			aposta.setUsuarioAceitador(usuario);
			aposta.setEstado(EstadosDaAposta.COMBINADA_ANTES_DO_INICIO_EVENTO);
			usuario.setSaldo(usuario.getSaldo() - aposta.getValorEsperado());
			usuario.getApostasAceitadoras().add(aposta);
			
			try {
				apostaServices.updateUsuarioEAposta(aposta, usuario);
				reportarMensagemDeSucesso("Aposta aceita com sucesso.");
			} catch (ServiceException e) {
				e.printStackTrace();
				reportarMensagemDeErro("Aposta não conseguiu ser aceita devido a algum erro.");
			}
			
			
			
			 
			
		}
		
		
		return EnderecoPaginas.MERCADO_APOSTAS+EnderecoPaginas.FACES_REDIRECT;
		
	}
	
	
	public Date hoje() {
		
		return new Date();
	}
	
	
	
	
	
	
	//getters and setters

	public ModalidadeServices getModalidadeServices() {
		return modalidadeServices;
	}

	public void setModalidadeServices(ModalidadeServices modalidadeServices) {
		this.modalidadeServices = modalidadeServices;
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

	public List<Competicao> getCompeticoes() {
		return competicoes;
	}

	public void setCompeticoes(List<Competicao> competicoes) {
		this.competicoes = competicoes;
	}

	public Modalidade getModalidadeFiltro() {
		return modalidadeFiltro;
	}

	public void setModalidadeFiltro(Modalidade modalidadeFiltro) {
		this.modalidadeFiltro = modalidadeFiltro;
	}

	
	



	public Competicao getCompeticaoFiltro() {
		return competicaoFiltro;
	}

	public void setCompeticaoFiltro(Competicao competicaoFiltro) {
		this.competicaoFiltro = competicaoFiltro;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public EventoServices getEventoServices() {
		return eventoServices;
	}

	public void setEventoServices(EventoServices eventoServices) {
		this.eventoServices = eventoServices;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public ApostaServices getApostaServices() {
		return apostaServices;
	}

	public void setApostaServices(ApostaServices apostaServices) {
		this.apostaServices = apostaServices;
	}

	public List<Aposta> getApostas() {
		return apostas;
	}

	public void setApostas(List<Aposta> apostas) {
		this.apostas = apostas;
	}

	public UsuarioServices getUsuarioServices() {
		return usuarioServices;
	}

	public void setUsuarioServices(UsuarioServices usuarioServices) {
		this.usuarioServices = usuarioServices;
	}

	public Date getDataAte() {
		return dataAte;
	}

	public void setDataAte(Date dataAte) {
		this.dataAte = dataAte;
	}

	public Date getDataAPartir() {
		return dataAPartir;
	}

	public void setDataAPartir(Date dataAPartir) {
		this.dataAPartir = dataAPartir;
	}

	public Float getValorPegarMin() {
		return valorPegarMin;
	}

	public void setValorPegarMin(Float valorPegarMin) {
		this.valorPegarMin = valorPegarMin;
	}

	public Float getValorPegarMax() {
		return valorPegarMax;
	}

	public void setValorPegarMax(Float valorPegarMax) {
		this.valorPegarMax = valorPegarMax;
	}

	public Float getValorRetornoMin() {
		return valorRetornoMin;
	}

	public void setValorRetornoMin(Float valorRetornoMin) {
		this.valorRetornoMin = valorRetornoMin;
	}

	public Float getValorRetornoMax() {
		return valorRetornoMax;
	}

	public void setValorRetornoMax(Float valorRetornoMax) {
		this.valorRetornoMax = valorRetornoMax;
	}

	public Evento getEventoFiltro() {
		return eventoFiltro;
	}

	public void setEventoFiltro(Evento eventoFiltro) {
		this.eventoFiltro = eventoFiltro;
	}



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
