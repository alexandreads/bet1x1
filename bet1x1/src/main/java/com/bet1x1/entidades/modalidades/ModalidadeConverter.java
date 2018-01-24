package com.bet1x1.entidades.modalidades;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.bet1x1.entidades.excecoes.service.ServiceException;



@FacesConverter(forClass = Modalidade.class)
public class ModalidadeConverter implements Converter {

	private ModalidadeServices service = new ModalidadeServices();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		
//		Modalidade modalidade = new Modalidade();
//		modalidade.setId((long)12);
//		modalidade.setNome("ta ai");
//		
//		return modalidade;
		
		
		if (value == null || value.trim().isEmpty()) {
			System.out.println(value);
			System.out.println("nulo service");
			return null;
		}

		try {
			System.out.println(value);
			Long id = Long.parseLong(value);
			
			Modalidade modalidade = service.getById(id);
			
			return modalidade;
			
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

	    if (value instanceof Modalidade) {
	        return String.valueOf(((Modalidade) value).getId());
	    } else {
	        throw new ConverterException(new FacesMessage(value + " is not a valid Modalidade"));
	    }
	}

}