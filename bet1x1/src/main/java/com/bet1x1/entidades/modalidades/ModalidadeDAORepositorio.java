package com.bet1x1.entidades.modalidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.bet1x1.entidades.eventos.Evento;
import com.bet1x1.entidades.excecoes.PersistenciaException;




public class ModalidadeDAORepositorio {
	
	
	private static Map<Long, Modalidade> REPOSITORY = new ConcurrentHashMap<Long, Modalidade>(new HashMap<Long, Modalidade>());
	
	
	public ModalidadeDAORepositorio() {
		
		Modalidade futebol = new Modalidade();
		futebol.setId((long)11);
		futebol.setNome("Futemol");
		
		Modalidade automobilismo = new Modalidade();
		automobilismo.setId((long)12);
		automobilismo.setNome("Aumomobilismo");
		
		Modalidade lusta = new Modalidade();
		lusta.setId((long)10);
		lusta.setNome("Lutcha");
		
		
		REPOSITORY.put(futebol.getId(), futebol);
		REPOSITORY.put(automobilismo.getId(), automobilismo);
		REPOSITORY.put(lusta.getId(), lusta);
		
		
	}
	
	
	
	public List<Modalidade> recuperarTodasOrdemNome (){
		
		ArrayList<Modalidade> results = new ArrayList<Modalidade>();
		
		for(Modalidade d: REPOSITORY.values()) {
			results.add(d);
		}
		
			  
		return results;
		
		
		
		
	}
	
	
	public void save(Modalidade modalidade) throws PersistenciaException {
		
	}
	
	public Modalidade findBy(Evento evento) throws PersistenciaException{
		return null;
	}
	
	
	public Modalidade  getById(Long id) {
		
		Modalidade results = REPOSITORY.get(id);
			  
		return results;
		
	}
	
	


}
