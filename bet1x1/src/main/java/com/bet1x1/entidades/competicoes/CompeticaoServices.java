package com.bet1x1.entidades.competicoes;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.bet1x1.entidades.eventos.Evento;
import com.bet1x1.entidades.excecoes.PersistenciaException;
import com.bet1x1.entidades.excecoes.service.ServiceException;
import com.bet1x1.entidades.modalidades.Modalidade;




public class CompeticaoServices implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7803325791425670859L;
	
	private CompeticaoDAO instanciaDAO = new CompeticaoDAO();
	
	public void save(Competicao instancia)  {
	
		try {
			instanciaDAO.save(instancia);
		}catch (PersistenciaException e) {
			System.out.println(e.getMessage());
		}

	}
	
	public List<Competicao> retornarTodosDeUmaModalidadeEmOrdemAlfabetica(Modalidade modalidade){
		
		List<Competicao> lista = null;
		try {
			lista = instanciaDAO.recuperarTodasDeUmaModalidadeOrdemNome(modalidade);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;
		
	}
	
	public Competicao getById(Long id) {
		
		return instanciaDAO.getById(id);
	}



	


	


	
}
