package com.bet1x1.entidades.mensagens;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.bet1x1.entidades.conversas.Conversa;
import com.bet1x1.entidades.excecoes.PersistenciaException;
import com.bet1x1.entidades.excecoes.service.ServiceException;
import com.bet1x1.entidades.usuarios.Usuario;



public class MensagemServices implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7803325791425670859L;
	
	private MensagemDAO instanciaDAO = new MensagemDAO();
	
	public void save(Mensagem instancia)  {
	
		try {
			instanciaDAO.save(instancia);
		}catch (PersistenciaException e) {
			System.out.println(e.getMessage());
		}

	}
	
	public void update(Mensagem instancia)  {
		
		try {
			instanciaDAO.update(instancia);
		}catch (PersistenciaException e) {
			System.out.println(e.getMessage());
		}

	}
	
	public void deletar(Long id) throws ServiceException {
		
		Mensagem instancia = null;
		
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
	

	
	
	
	public Mensagem getById(Long id) throws ServiceException {
		
		Mensagem disputante = null;
		try {
			disputante = instanciaDAO.getById(id);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("exceção no metodo getbyid do Conversa dao");
		}
		
		return disputante;
	}



	public List<Mensagem> pegarDeUmaConversa(Conversa conversa){
		
		
		
		
		List<Mensagem> lista = null;
		try {
			lista = instanciaDAO.pegarDeUmaConversa(conversa);
		} catch (PersistenciaException e) {
			e.printStackTrace();
			System.out.println("erro persistencia modalidade");
		}
		
		return lista;
		
	}
	
	public void setarTodasComoLidasDeUmUsuario(List<Mensagem> mensagens, Usuario logado) throws ServiceException{
		
		
		ArrayList<Mensagem> restantes = new ArrayList<Mensagem>();
		
		for(int i = mensagens.size()-1; i > -1 ; i--) {
			// se a mensagem n for do usuario logado
			if(mensagens.get(i).getUsuario().getId() != logado.getId()) {
				if(!mensagens.get(i).getLida()) {
					restantes.add(mensagens.get(i));
				} else {
					break;
				}
			}
		}
		
		
		
		
		
		try {
			instanciaDAO.setarTodasComoLidas(restantes);
		} catch (PersistenciaException e) {
			e.printStackTrace();
			System.out.println("erro persistencia mensagens");
		}
		
		
	}


	


	
}
