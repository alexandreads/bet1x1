package com.bet1x1.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean
public class Redirecionamento {
	
	
	
	public static final Integer INDEX = 0;
	
	public static final String facesRedirect = "?faces-redirect=true";
	
	public Integer pagina;


	
	public String redirecionar() {
		
		String resultado = "";
		if(pagina == INDEX) {
			resultado =  "index.xhtml";
		}
		
		
		resultado += facesRedirect;
		
		return resultado;
		
	}
	
	

	public Integer getPagina() {
		return pagina;
	}



	public void setPagina(Integer pagina) {
		this.pagina = pagina;
	}
	
	
	

}
