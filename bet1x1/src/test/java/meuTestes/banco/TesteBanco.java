package meuTestes.banco;

import java.util.ArrayList;
import java.util.List;

import com.bet1x1.entidades.competicoes.Competicao;
import com.bet1x1.entidades.eventos.Evento;
import com.bet1x1.entidades.eventos.EventoServices;
import com.bet1x1.entidades.modalidades.Modalidade;
import com.bet1x1.entidades.modalidades.ModalidadeServices;


public class TesteBanco {
	
	
	public static void main(String[] args) {
		
		Modalidade modalidade = new Modalidade();
		ModalidadeServices service = new ModalidadeServices();
		EventoServices eventoServices = new EventoServices();
		
		
		modalidade.setNome("Automobilismo");
		modalidade.setId((long)10);
//		
//		
//		List<Competicao>  competicoes = competicaoServices.retornarTodosDeUmaModalidadeEmOrdemAlfabetica(evento);
//		
//		
//		
//		for(Competicao c: competicoes) {
//			
//			System.out.println(c.getNome());
//		}

		Competicao c = new Competicao();
		c.setId((long)52);
		c.setNome("f1");
		c.setModalidade(modalidade);
		
		
		
		modalidade.setCompeticoes(new ArrayList<Competicao>());
		modalidade.getCompeticoes().add(c);
		
		
		
		Evento evento = new Evento();
		
		evento.setId(10);
		
		Modalidade mo = eventoServices.retornarModalidade(evento);
		
		
		
		
		
	}

}
