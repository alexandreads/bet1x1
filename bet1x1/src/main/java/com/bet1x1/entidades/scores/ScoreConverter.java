package com.bet1x1.entidades.scores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.bet1x1.entidades.excecoes.service.ServiceException;



@FacesConverter(forClass = Score.class)
public class ScoreConverter implements Converter {

	private ScoreServices service = new ScoreServices();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		
		if (value == null || value.trim().isEmpty()) {
			return null;
		}

		try {
			System.out.println(value);
			Long id = Long.parseLong(value);
			
			Score instancia = service.getById(id);
			
			return instancia;
			
		} catch (NumberFormatException | ServiceException e) {
			String msgErroStr = String.format(
					"Erro de conversão! Não foi possível realizar a conversão da string '%s' para o tipo esperado.",
					value);
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErroStr, msgErroStr);
			throw new ConverterException(msgErro);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

	    if (value == null) {
	        return "";
	    }

	    if (value instanceof Score) {
	        return String.valueOf(((Score) value).getId());
	    } else {
	        throw new ConverterException(new FacesMessage(value + " is not a valid Score"));
	    }
	}

}