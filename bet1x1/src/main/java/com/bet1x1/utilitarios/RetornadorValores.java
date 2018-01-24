package com.bet1x1.utilitarios;

import java.util.ArrayList;
import java.util.List;

import com.bet1x1.entidades.handicaps.Handicap;

public class RetornadorValores {
	
	
	
	
	public static List<Handicap> handicapFutebol() {
		
		List<Handicap> lista = new ArrayList<Handicap>();
		
		

		lista.add(new Handicap("0", 0f));
		lista.add(new Handicap("+1", 1f));
		lista.add(new Handicap("+2", 2f));
		lista.add(new Handicap("+3", 3f));
		lista.add(new Handicap("+4", 4f));
		lista.add(new Handicap("+5", 5f));
		
		return lista;
		
	}
	
	public static String retornarNomeHandicap(Float valor) {
		
		String r = String.valueOf(valor);
		
		if(valor > 0) {
			r = "+"+r;
		} 
		else if(valor < 0) {
			r = "-"+r;
		}
		
		return r;
		
	}

}
