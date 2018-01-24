package com.bet1x1.beans;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.bet1x1.entidades.competicoes.Competicao;
import com.bet1x1.entidades.competicoes.CompeticaoServices;
import com.bet1x1.entidades.eventos.EstadosDoEvento;
import com.bet1x1.entidades.eventos.Evento;
import com.bet1x1.entidades.eventos.EventoServices;
import com.bet1x1.entidades.modalidades.Modalidade;
import com.bet1x1.entidades.modalidades.ModalidadeServices;
import com.bet1x1.entidades.usuarios.Usuario;
import com.bet1x1.entidades.usuarios.UsuarioServices;
import com.bet1x1.utilitarios.EnderecoPaginas;


@ViewScoped
@ManagedBean
public class Index extends AbstractBean {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5976838804313515033L;

	//services
	private ModalidadeServices modalidadeServices = new ModalidadeServices();
	private CompeticaoServices competicaoServices = new CompeticaoServices();
	private EventoServices eventoServices = new EventoServices();
	private UsuarioServices usuarioServices = new UsuarioServices();
	
	
	//listas
	private List<Modalidade> modalidades;
	private List<Competicao> competicoes;
	private List<Evento> eventos;

	
	//filtros
	private Modalidade modalidadeFiltro;
	private Competicao competicaoFiltro;
	private Date dataAte;
	private Date dataAPartir;
	
	
	private Usuario desafiado;
	
	

	
	
	public void init() {
		
		
		loadModalidades();
		loadCompeticoes();
		loadEventos();
	
		
	}
	
	
	public boolean isModalidadeSelected() {
		
		if(modalidadeFiltro != null) {
			return true;
		}
		
		return false;
	}
	
	public String cadastrarAposta(Long eventoId) {
		
		String eventoParam = "evento="+eventoId;
		
		String desafiadoParam = "";
		if(desafiado != null) {
			desafiadoParam = "&desafiado="+desafiado.getId();
		} else {
			
			System.out.println("nulooo");
		}
		
		
		String retorno = "";
		
		
		retorno = EnderecoPaginas.CADASTRAR_APOSTA+EnderecoPaginas.FACES_REDIRECT+eventoParam+desafiadoParam;
		System.out.println(retorno);
		
		return retorno;
	}
	
	

	
	public void loadCompeticoesEEventos() {
		
		competicoes =  competicaoServices.retornarTodosDeUmaModalidadeEmOrdemAlfabetica(modalidadeFiltro);
		eventos = eventoServices.listaFiltro(modalidadeFiltro, competicaoFiltro , dataAte, dataAPartir, EstadosDoEvento.ABERTO);
	}
	
	
	public void loadCompeticoes(){

		
		competicoes =  competicaoServices.retornarTodosDeUmaModalidadeEmOrdemAlfabetica(modalidadeFiltro);

	}
	
	public void loadModalidades() {
		
		modalidades = modalidadeServices.retornarTodasEmOrdemAlfabetica();
		
	}
	
	public void loadEventos() {
		
		eventos = eventoServices.listaFiltro(modalidadeFiltro, competicaoFiltro, dataAte, dataAPartir, EstadosDoEvento.ABERTO);
		
	}
	
	
	public String efetuarLogout() {
		
		return "";
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


	public UsuarioServices getUsuarioServices() {
		return usuarioServices;
	}


	public void setUsuarioServices(UsuarioServices usuarioServices) {
		this.usuarioServices = usuarioServices;
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


	public Usuario getDesafiado() {
		return desafiado;
	}


	public void setDesafiado(Usuario desafiado) {
		this.desafiado = desafiado;
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


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
