package meuTestes.banco;

import java.util.List;

import com.bet1x1.entidades.apostas.Aposta;
import com.bet1x1.entidades.apostas.ApostaServices;
import com.bet1x1.entidades.eventos.Evento;

public class TesteApostas {
	
	public static void main(String[] args) {
		
		Evento evento = new Evento();
		evento.setId((10));
		ApostaServices apostaServices = new ApostaServices();
		List<Aposta> apostas = apostaServices.todasApostasCombinadasEsperandoResultadoDeUmEvento(evento);
		
		System.out.println(apostas.size());
		
		
	}

}
