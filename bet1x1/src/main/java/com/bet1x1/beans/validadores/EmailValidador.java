package com.bet1x1.beans.validadores;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("emailValidador")
public class EmailValidador implements Validator {

	private Pattern pattern = Pattern
			.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		Matcher matcher = pattern.matcher(value.toString().trim());

		if (!matcher.matches()) {
			FacesMessage msg = new FacesMessage("Erro de validação de e-mail.", "Formato esperado: abcd@abc.com");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}

}