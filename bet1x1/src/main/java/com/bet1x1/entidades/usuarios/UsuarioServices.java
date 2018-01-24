package com.bet1x1.entidades.usuarios;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.Base64;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.bet1x1.entidades.excecoes.PersistenciaException;
import com.bet1x1.entidades.excecoes.service.ServiceException;




public class UsuarioServices implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7803325791425670859L;
	
	private UsuarioDAO instanciaDAO = new UsuarioDAO();
	
	public void save(Usuario instancia)  throws ServiceException {
	
		try {
			
			calcularHashDaSenha(instancia);
			instanciaDAO.save(instancia);
		}catch (PersistenciaException e) {
			System.out.println(e.getMessage());
		}

	}
	
	public void update(Usuario instancia)  throws ServiceException{
		
		try {
			instanciaDAO.update(instancia);
		}catch (PersistenciaException e) {
			System.out.println(e.getMessage());
		}

	}
	
	public List<Usuario> retornarPotenciaisDesafiados(Usuario usuario){
		
		List<Usuario> resultado = null;
		
		try {
			resultado  = instanciaDAO.retornarPotenciaisDesafiados(usuario);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return resultado;
		
		
	}
	
	
	
	public Usuario getById(Long id)  {
		
		Usuario u = null;
		try {
			u =  instanciaDAO.getById(id);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return u;
	}
	
	public Usuario getByLogin(String login)  {
		
		Usuario u = null;
		try {
			u =  instanciaDAO.getByLogin(login);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return u;
	}


	public Usuario getUsuarioLogado() {
		Usuario resultado = null;
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		Principal userPrincipal = externalContext.getUserPrincipal();
		
		if(userPrincipal == null) {
			return null;
		}
		
		try {
			resultado  = instanciaDAO.getByLogin(userPrincipal.getName());
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return resultado;
	}


	
	
	public List<Usuario> getAll(){
		
		return instanciaDAO.recuperarTodas();
	}
	
	public boolean isLogado() {
	
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		Principal userPrincipal = externalContext.getUserPrincipal();
		if (userPrincipal != null) {
			return true;
		}
		
		return false;
	}
	
	//partes de senha
	
	private String calcularHashDaSenha(Usuario usuario)  {
		usuario.setSenha(hash(usuario.getSenha()));
		return usuario.getSenha();
	}
	
	private String hash(String password){
		MessageDigest md;
		
		String output = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes("UTF-8"));
			byte[] digest = md.digest();
			//BigInteger bigInt = new BigInteger(1, digest);
			//String output = bigInt.toString(16);
			output = Base64.getEncoder().encodeToString(digest);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return output;
		
		
	}
	
	public List<Usuario> retornarDeFiltro(Usuario usuario){
		
		
		List<Usuario> resultado = null;
		
		try {
			resultado  = instanciaDAO.retornarDeFiltro(usuario);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return resultado;
		
	}
	
	


	
}
