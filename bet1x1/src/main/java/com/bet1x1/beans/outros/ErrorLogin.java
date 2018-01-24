package com.bet1x1.beans.outros;


import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import com.bet1x1.beans.AbstractBean;

@Named
@RequestScoped
//@ManagedBean
//@ViewScoped
public class ErrorLogin extends AbstractBean {

	/**
	 * 
	 */
	
	private Boolean errorAtributo;
	
	
	private static final long serialVersionUID = 5769269432204249052L;
	
	public void dispararMensagemLoginErro(boolean error){
		
		System.out.println(error+"erros");
		if(error){
			reportarMensagemDeErro("Login e/ou senha errada.");
		}
		
	}
	
	public void dispararMensagemLoginErroAction() {
		
		System.out.println("erro action");
		
		
		if(errorAtributo != null){
			System.out.println(errorAtributo+" atributo");
			reportarMensagemDeErro("Login e/ou senha errada.");
		}
	}

	
	
	public Boolean getErrorAtributo() {
		return errorAtributo;
	}

	public void setErrorAtributo(Boolean errorAtributo) {
		this.errorAtributo = errorAtributo;
	}
	
	

}
