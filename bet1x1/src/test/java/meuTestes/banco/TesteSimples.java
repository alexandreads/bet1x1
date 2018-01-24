package meuTestes.banco;

import java.util.ArrayList;
import java.util.List;

import com.bet1x1.entidades.eventos.Evento;
import com.bet1x1.entidades.eventos.EventoServices;
import com.bet1x1.entidades.excecoes.service.ServiceException;
import com.bet1x1.entidades.modalidades.Modalidade;

public class TesteSimples {
	
	
	public static void main(String[] args) {
		
		EventoServices eventoServices = new EventoServices();
		
		Evento evento = null;
		try {
			evento = eventoServices.getById((long)11);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		Modalidade modalidade = eventoServices.retornarModalidade(evento);
		
//		
//		List<Evento> eventos = eventoServices.getAll();
	}
	
	
	

}
