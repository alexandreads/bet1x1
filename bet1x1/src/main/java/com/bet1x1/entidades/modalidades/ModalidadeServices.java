package com.bet1x1.entidades.modalidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.bet1x1.entidades.excecoes.PersistenciaException;
import com.bet1x1.entidades.excecoes.service.ServiceException;
import com.bet1x1.entidades.usuarios.Usuario;




public class ModalidadeServices implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7803325791425670859L;
	
	private ModalidadeDAO instanciaDAO = new ModalidadeDAO();
//	private ModalidadeDAORepositorio instanciaDAO = new ModalidadeDAORepositorio();
	
	public void save(Modalidade instancia)  {
	
		try {
			instanciaDAO.save(instancia);
		}catch (PersistenciaException e) {
			System.out.println(e.getMessage());
		}

	}
	
	
	public Modalidade getById(Long id) throws ServiceException {
		
		return instanciaDAO.getById(id);
		
	}
	
	public List<Modalidade> retornarTodasEmOrdemAlfabetica(){
		
		List<Modalidade> modalidades = null;
		try {
			modalidades = instanciaDAO.recuperarTodasOrdemNome();
		} catch (PersistenciaException e) {
			e.printStackTrace();
			System.out.println("erro persistencia modalidade");
		}
		
		return modalidades;
		
	}
	
	
	public void update(Modalidade instancia)  {
		
		try {
			instanciaDAO.update(instancia);
		}catch (PersistenciaException e) {
			System.out.println(e.getStackTrace());
		}

	}
	
	
	



	


	
}
