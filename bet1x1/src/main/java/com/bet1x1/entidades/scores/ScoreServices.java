package com.bet1x1.entidades.scores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.bet1x1.entidades.eventos.Evento;
import com.bet1x1.entidades.excecoes.PersistenciaException;
import com.bet1x1.entidades.excecoes.service.ServiceException;
import com.bet1x1.entidades.usuarios.Usuario;




public class ScoreServices implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7803325791425670859L;
	
	private ScoreDAO instanciaDAO = new ScoreDAO();
//	private ModalidadeDAORepositorio instanciaDAO = new ModalidadeDAORepositorio();
	
	public void save(Score instancia)  {
	
		try {
			instanciaDAO.save(instancia);
		}catch (PersistenciaException e) {
			System.out.println(e.getMessage());
		}

	}
	
	
	public Score getById(Long id) throws ServiceException {
		
		return instanciaDAO.getById(id);
		
	}
	
	public List<Score> retornarTodasEmOrdemAlfabetica(){
		
		List<Score> modalidades = null;
		try {
			modalidades = instanciaDAO.recuperarTodasOrdemNome();
		} catch (PersistenciaException e) {
			e.printStackTrace();
			System.out.println("erro persistencia modalidade");
		}
		
		return modalidades;
		
	}
	
	
	public void update(Score instancia)  {
		
		try {
			instanciaDAO.update(instancia);
		}catch (PersistenciaException e) {
			System.out.println(e.getStackTrace());
		}

	}
	
	public ArrayList<Score> retornarScoresComBaseEvento(Evento evento){
		
		
		List<Score> lista = null;
		try {
			lista = instanciaDAO.retornarListaBaseEvento(evento);
		}catch (PersistenciaException e) {
			System.out.println(e.getStackTrace());
		}
		
		ArrayList<Score> scores  = new ArrayList<>();
		
		for(Score s : lista) {
			
			scores.add(s);
		}
		
		return scores;
		
	}
	
	
	
	



	


	
}
