package meuTestes.banco;

import java.util.ArrayList;
import java.util.Random;

import com.bet1x1.entidades.competicoes.Competicao;
import com.bet1x1.entidades.modalidades.Modalidade;
import com.bet1x1.entidades.modalidades.ModalidadeServices;


public class AdicionarModalidadeDoida {
	
	
	public static void main(String[] args) {
		
		Modalidade modalidade = new Modalidade();
		ModalidadeServices service = new ModalidadeServices();
		
		modalidade.setNome("Doida");
		Random gerador = new Random();
		
		modalidade.setId((long)gerador.nextInt(1000));

		
		modalidade.setCompeticoes(new ArrayList<Competicao>());
		
		
		service.save(modalidade);
		
		
		
		
		
	}

}
