package com.bet1x1.entidades.amizades;

import java.io.Serializable;
import java.util.List;

import com.bet1x1.entidades.excecoes.PersistenciaException;
import com.bet1x1.entidades.excecoes.service.ServiceException;
import com.bet1x1.entidades.usuarios.Usuario;




public class AmizadeServices implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7803325791425670859L;
	
	private AmizadeDAO instanciaDAO = new AmizadeDAO();
//	private ModalidadeDAORepositorio instanciaDAO = new ModalidadeDAORepositorio();
	
	public void save(Amizade instancia)  {
	
		try {
			instanciaDAO.save(instancia);
		}catch (PersistenciaException e) {
			System.out.println(e.getMessage());
		}

	}
	
	
	public Amizade getById(Long id) throws ServiceException {
		
		return instanciaDAO.getById(id);
		
	}
	
	
	
	public void update(Amizade instancia)  {
		
		try {
			instanciaDAO.update(instancia);
		}catch (PersistenciaException e) {
			System.out.println(e.getStackTrace());
		}

	}
	
	public List<Amizade> recuperarTodasUsuarioEfetivadas(Usuario usuario){
		
		
		List<Amizade> lista = null;
		try {
			lista = instanciaDAO.recuperarTodasUsuarioEfetivadas(usuario);
		}catch (PersistenciaException e) {
			System.out.println(e.getStackTrace());
		}
		

		
		return lista;
		
	}
	
	
	
	public List<Amizade> recuperarTodasUsuarioRecebidas(Usuario usuario){
		
		
		List<Amizade> lista = null;
		try {
			lista = instanciaDAO.recuperarTodasUsuarioRecebidas(usuario);
		}catch (PersistenciaException e) {
			System.out.println(e.getStackTrace());
		}
		

		
		return lista;
		
	}
	
	
	
	
	public List<Amizade> recuperarTodasUsuarioEnviados(Usuario usuario){
		
		
		List<Amizade> lista = null;
		try {
			lista = instanciaDAO.recuperarTodasUsuarioEnviados(usuario);
		}catch (PersistenciaException e) {
			System.out.println(e.getStackTrace());
		}
		

		
		return lista;
		
	}
	
	
	public Amizade entreDoisUsuarios(Usuario usuario1, Usuario usuario2) {
		
		Amizade lista = null;
		
		try {
			lista = instanciaDAO.entreDoisUsuarios(usuario1, usuario2);
			
		}catch (PersistenciaException e) {
			System.out.println(e.getStackTrace());
		}
		
		return lista;
	}
	
	public void solicitarAmizade(Amizade amizade, Usuario usuario1, Usuario usuario2) {
		
		
		if(amizade == null ) {
			
			amizade = new Amizade();
			amizade.setAmigo1(usuario1);
			amizade.setAmigo2(usuario2);
			amizade.setEstado(EstadosDaAmizade.PEDIDO_ENVIADO);
			
			Amizade amizadeTeste = entreDoisUsuarios(usuario1, usuario2);
			
			try {
				instanciaDAO.save(amizade);
			} catch (PersistenciaException e) {
				System.out.println(e.getStackTrace());
			}
				
		}

	}
	
	public void rejeitarPedido(Amizade amizade) {
		try {
			
			instanciaDAO.delete(amizade);
		} catch (PersistenciaException e) {
			System.out.println(e.getStackTrace());
		}
		
	}
	
	public void cancelarPedido(Amizade amizade) {
		
		
		try {
			
			instanciaDAO.delete(amizade);
		} catch (PersistenciaException e) {
			System.out.println(e.getStackTrace());
		}
	}
	
	

	


	
}
