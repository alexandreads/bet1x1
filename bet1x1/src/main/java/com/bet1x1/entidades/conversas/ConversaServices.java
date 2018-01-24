package com.bet1x1.entidades.conversas;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.bet1x1.entidades.excecoes.PersistenciaException;
import com.bet1x1.entidades.excecoes.service.ServiceException;
import com.bet1x1.entidades.modalidades.Modalidade;
import com.bet1x1.entidades.usuarios.Usuario;



public class ConversaServices implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7803325791425670859L;
	
	private ConversaDAO instanciaDAO = new ConversaDAO();
	
	public void save(Conversa instancia)  {
	
		try {
			instanciaDAO.save(instancia);
		}catch (PersistenciaException e) {
			System.out.println(e.getMessage());
		}

	}
	
	public void update(Conversa instancia)  {
		
		try {
			instanciaDAO.update(instancia);
		}catch (PersistenciaException e) {
			System.out.println(e.getMessage());
		}

	}
	
	public void deletar(Long id) throws ServiceException {
		
		Conversa instancia = null;
		
		try {
			instancia = getById(id);
		} catch (ServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			instanciaDAO.delete(instancia);
		}catch (PersistenciaException e) {
			System.out.println(e.getMessage());
		}
		
	}
	

	
	
	
	public Conversa getById(Long id) throws ServiceException {
		
		Conversa disputante = null;
		try {
			disputante = instanciaDAO.getById(id);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("exceção no metodo getbyid do Conversa dao");
		}
		
		return disputante;
	}



	public List<Conversa> pegarDeUmUsuario(Usuario usuario){
		
		
		
		
		List<Conversa> lista = null;
		try {
			lista = instanciaDAO.pegarDeUmUsuario(usuario);
		} catch (PersistenciaException e) {
			e.printStackTrace();
			System.out.println("erro persistencia modalidade");
		}
		
		return lista;
		
	}

	
	
	
	
	public List<Conversa> getAll(){
		
		return instanciaDAO.recuperarTodas();
	}
	
	

	


	
}
