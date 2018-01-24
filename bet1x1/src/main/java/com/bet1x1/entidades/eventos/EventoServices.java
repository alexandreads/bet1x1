package com.bet1x1.entidades.eventos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.bet1x1.entidades.DAOJPA;
import com.bet1x1.entidades.competicoes.Competicao;
import com.bet1x1.entidades.disputantes.Disputante;
import com.bet1x1.entidades.eventos.Evento;
import com.bet1x1.entidades.eventos.EventoDAO;
import com.bet1x1.entidades.excecoes.PersistenciaException;
import com.bet1x1.entidades.excecoes.service.ServiceException;
import com.bet1x1.entidades.modalidades.Modalidade;



public class EventoServices implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7803325791425670859L;
	
	private EventoDAO instanciaDAO = new EventoDAO();
	
	
	public void save(Evento instancia)  {
	
		try {
			instanciaDAO.save(instancia);
		}catch (PersistenciaException e) {
			System.out.println(e.getMessage());
		}

	}
	
	
	
	public Evento getById(Long id) throws ServiceException {
		
		Evento instancia = null;
		
		try {
			instancia = instanciaDAO.getById(id);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return instancia;
	}




	
	public List<Evento> listaFiltro(Modalidade modalidade, Competicao competicao, 
													Date dataAte, Date dataAPartir, Integer estado){
		
		List<Evento> lista = null;
		try {
			lista = instanciaDAO.listaFiltro(modalidade, competicao, dataAte, dataAPartir, estado);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;
		
	}
	
	public List<Evento> retornarListaEventosAgoraEDaqui3Min(Date agora, Date daqui3Min){
		
		List<Evento> lista = null;
		try {
			lista = instanciaDAO.retornarListaEventosAgoraEDaqui3Min(agora, daqui3Min);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return lista;
		
	}
	
	public Modalidade retornarModalidade(Evento evento) {
		
		if(evento == null) {
			return null;
		}
		
		Modalidade modalidade = null;
		try {
			return instanciaDAO.recuperarModalidade(evento);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return modalidade;
		
	}
	
	
	
	
	public List<Evento> getAll(){
		
		return instanciaDAO.recuperarTodas();
	}
	
	public ArrayList<Disputante> retornarDisputantesEmOrdemAlfaEJogandoEmCasa(Evento evento){
		
		ArrayList<Disputante> result = new ArrayList<Disputante>();
		List<Disputante> disputantes = null;
		
		try {
			disputantes =  instanciaDAO.retornarDisputantesDeUmEventoOrdemNome(evento);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Disputante d: disputantes) {
			result.add(d);
		}
		
		return result;
	}
	
	public void setarComoFechados(List<Evento> eventos) {
		
		try {
			
			instanciaDAO.setarComoFechados(eventos);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	
	


	


	
}
