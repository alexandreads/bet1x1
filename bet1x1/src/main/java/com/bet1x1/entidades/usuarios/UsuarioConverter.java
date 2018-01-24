package com.bet1x1.entidades.usuarios;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;


@FacesConverter(forClass= Usuario.class)
public class UsuarioConverter implements Converter {

	private UsuarioServices service = new UsuarioServices();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty()) {
			System.out.println(value);
			System.out.println("nulo service");
			return null;
		}

		try {
			System.out.println(value);

			System.out.println(value);
			Long id = Long.parseLong(value);
			
			Usuario instancia = service.getById(id);
			
			return instancia;
			
		} catch (NumberFormatException e) {
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

	    if (value instanceof Usuario) {
	        return String.valueOf(((Usuario) value).getId());
	    } else {
	        throw new ConverterException(new FacesMessage(value + " is not a valid Usuario"));
	    }
		
	
		
	}

}