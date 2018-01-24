package com.bet1x1.entidades.disputantes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.bet1x1.entidades.competicoes.Competicao;
import com.bet1x1.entidades.disputantes.Disputante;
import com.bet1x1.entidades.eventos.Evento;
import com.bet1x1.entidades.excecoes.PersistenciaException;
import com.bet1x1.entidades.excecoes.service.ServiceException;
import com.bet1x1.entidades.modalidades.Modalidade;
import com.bet1x1.entidades.scores.Score;
import com.bet1x1.entidades.usuarios.Usuario;



public class DisputanteServices implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7803325791425670859L;
	
	private DisputanteDAO instanciaDAO = new DisputanteDAO();
	
	public void save(Disputante instancia)  {
	
		try {
			instanciaDAO.save(instancia);
		}catch (PersistenciaException e) {
			System.out.println(e.getMessage());
		}

	}
	
	public void update(Disputante instancia)  {
		
		try {
			instanciaDAO.update(instancia);
		}catch (PersistenciaException e) {
			System.out.println(e.getMessage());
		}

	}
	
	public void deletar(Long id) throws ServiceException {
		
		Disputante disputante = null;
		try {
			disputante = getById(id);
		} catch (ServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			instanciaDAO.delete(disputante);
		}catch (PersistenciaException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public List<Disputante> retornarTodosDeUmaModalidade(Modalidade modalidade){
		
		
		try {
			return instanciaDAO.retornarTodosDeUmaModalidade(modalidade);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public List<Disputante> retornarFiltro(Disputante disputante){
		
		
		
		System.out.println("chegou aqui1");
		List<Disputante> result = null;
		
		try {
			
			if(disputante.getModalidade() == null && disputante.getNome() != null) {
				System.out.println("chegou aquia");
				result =  instanciaDAO.retornarFiltroNomeLike(disputante.getNome());
			}
			
			else if(disputante.getModalidade() != null && (disputante.getNome() == null || disputante.getNome().equals(""))) {
				System.out.println("chegou aquib");
				result = instanciaDAO.retornarTodosDeUmaModalidade(disputante.getModalidade());
			}
			
			else if(disputante.getModalidade() != null && disputante.getNome() != null) {
				System.out.println("chegou aquic");
				result = instanciaDAO.recuperarFullFiltro(disputante);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	
	
	public Disputante getById(Long id) throws ServiceException {
		
		Disputante disputante = null;
		try {
			disputante = instanciaDAO.getById(id);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("exceção no metodo getbyid do disputante dao");
		}
		
		return disputante;
	}




	
	public Modalidade retornarModalidade(Disputante disputante) {
		
		if(disputante == null) {
			return null;
		}
		
		Modalidade modalidade = null;
		try {
			modalidade = instanciaDAO.recuperarModalidade(disputante);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return modalidade;
		
	}
	
	
	
	
	public List<Disputante> getAll(){
		
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
	
	public Disputante retornarDisputanteDeScore(Score score) {
		
		
		Disputante disputante = null;
		try {
			disputante = instanciaDAO.retornarDisputanteDeScore(score);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return disputante;
	}


	


	
}
