package com.bet1x1.beans.validadores;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.bet1x1.entidades.usuarios.Usuario;
import com.bet1x1.entidades.usuarios.UsuarioServices;

@FacesValidator("loginValidador")
public class LoginValidador implements Validator {
	
	


	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String login = value.toString();
		
		UsuarioServices usuarioServices = new UsuarioServices();

		Usuario existe = usuarioServices.getByLogin(login);
		
		if (existe != null) {
			FacesMessage msg = new FacesMessage("Erro de validação. ", "Este login já existe.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}

}