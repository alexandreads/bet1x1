package meuTestes.banco;

import java.util.List;

import com.bet1x1.entidades.disputantes.Disputante;
import com.bet1x1.entidades.eventos.Evento;
import com.bet1x1.entidades.eventos.EventoServices;

public class TesteBancoDisputantes {
	
	public static void main(String[] args) {
		
		Evento evento = new Evento();
		evento.setId((long)10);
		
		EventoServices eventoServices = new EventoServices();
		List<Disputante> disputantes = eventoServices.retornarDisputantesEmOrdemAlfaEJogandoEmCasa(evento);
		
		System.out.println(disputantes.size());
		for(Disputante d: disputantes) {
			System.out.println("tamaho"+disputantes.size());
			System.out.println(d.getNome());
		}
	}
	


}
