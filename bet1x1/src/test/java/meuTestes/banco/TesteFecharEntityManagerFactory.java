package meuTestes.banco;

import com.bet1x1.entidades.modalidades.ModalidadeDAO;

public class TesteFecharEntityManagerFactory {
	
	
	public static void main(String[] args) {
		
		ModalidadeDAO.closeEntityManagerFactory();
	}
		

	


}
