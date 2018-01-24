package com.bet1x1.entidades.eventos;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.bet1x1.entidades.excecoes.service.ServiceException;



@FacesConverter(forClass = Evento.class)
public class EventoConverter implements Converter {

	private EventoServices service = new EventoServices();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		
	
		
		if (value == null || value.trim().isEmpty()) {
			System.out.println(value);
			System.out.println("nulo service");
			return null;
		}

		try {
			System.out.println(value);
			Long id = Long.parseLong(value);
			
			return service.getById(id);
			
			
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

	    if (value instanceof Evento) {
	        return String.valueOf(((Evento) value).getId());
	    } else {
	        throw new ConverterException(new FacesMessage(value + " is not a valid Evento"));
	    }
	}

}