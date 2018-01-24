package com.bet1x1.beans.validadores;




import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator("senhaValidador")
public class SenhaValidador implements Validator {
	
	


	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String senha = value.toString();
		

		
		if (senha.length() < 5) {
			FacesMessage msg = new FacesMessage("Erro de validação. ", "Senha deve ter no mínimo 6 caracteres.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}

}