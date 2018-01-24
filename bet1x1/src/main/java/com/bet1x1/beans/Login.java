package com.bet1x1.beans;

import java.io.IOException;
import java.security.Principal;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.bet1x1.entidades.usuarios.Usuario;
import com.bet1x1.entidades.usuarios.UsuarioServices;
import com.bet1x1.utilitarios.EnderecoPaginas;


@SessionScoped
@ManagedBean
public class Login extends AbstractBean{
	
	private UsuarioServices usuarioServices = new UsuarioServices();
	
	private Integer timeZoneDiference  = 0;
	
	private Boolean erro;
	
	
	
	
	
	public boolean isUserInRole(String role) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		return externalContext.isUserInRole(role);
	}
	
	public String getUserLogin() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		Principal userPrincipal = externalContext.getUserPrincipal();
		if (userPrincipal == null) {
			return "";
		}
		
		return userPrincipal.getName();
	}
	
	public boolean logado() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		Principal userPrincipal = externalContext.getUserPrincipal();
		if (userPrincipal != null) {
			return true;
		}
		
		return false;
	}
	
	public void deslogar() {
			FacesContext fc = FacesContext.getCurrentInstance();
			ExternalContext ec = fc.getExternalContext();
			HttpSession session = (HttpSession) ec.getSession(false);
			session.invalidate();
			try {
				ec.redirect(ec.getApplicationContextPath() + EnderecoPaginas.PAGINA_PRINCIPAL);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public void dispararMensagemLoginErro(boolean error){
		if(error){
			reportarMensagemDeErro("Login e/ou senha errada.");
		}
	}
	
	
	public String retornarLoginESaldo() {
		
		Usuario usuario = usuarioServices.getUsuarioLogado();
		
		if(usuario == null) {
			return "";
		}
		
		return "Olá, "+usuario.getLogin()+", R$ "+usuario.getSaldo();
	}
	
	
	public void timeZone() {
		
		String timeZone = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("timeZone");
		
		try {
			
			timeZoneDiference =  Integer.parseInt(timeZone);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	public void dispararMensagemLoginErro() {
		
		System.out.println(erro+" erro");
//		if(error){
//			reportarMensagemDeErro("Login e/ou senha errada.");
//		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	public UsuarioServices getUsuarioServices() {
		return usuarioServices;
	}

	public void setUsuarioServices(UsuarioServices usuarioServices) {
		this.usuarioServices = usuarioServices;
	}

	public Integer getTimeZoneDiference() {
		return timeZoneDiference;
	}

	public void setTimeZoneDiference(Integer timeZoneDiference) {
		this.timeZoneDiference = timeZoneDiference;
	}

	public Boolean getErro() {
		return erro;
	}

	public void setErro(Boolean erro) {
		this.erro = erro;
	}

	
	

}
