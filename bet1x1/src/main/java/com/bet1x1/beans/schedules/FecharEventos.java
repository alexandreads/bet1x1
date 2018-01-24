package com.bet1x1.beans.schedules;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.bet1x1.entidades.apostas.Aposta;
import com.bet1x1.entidades.apostas.ApostaServices;
import com.bet1x1.entidades.eventos.Evento;
import com.bet1x1.entidades.eventos.EventoServices;
import com.bet1x1.utilitarios.Outros;

public class FecharEventos implements Runnable{
	

    @Override
    public void run() {
    	
//    	
//    	EventoServices eventoServices = new EventoServices();
//    	ApostaServices apostaServices = new ApostaServices();
//    	
//       	Date agora = new Date();
//       	
//       	LocalDateTime local = Outros.dateToLocaldateTime(agora);
//       	local = local.plusMinutes(3);
//       	
//       	Date agora3Min = Outros.localDateToDate(local);
//       	
//       	System.out.println(agora3Min+ "data agora");
//       	
//       	
//       	
//       	List<Evento> eventosDaquiA3Min = null;
//       	
//		eventosDaquiA3Min = eventoServices.retornarListaEventosAgoraEDaqui3Min(agora, agora3Min);
//
//       	if(eventosDaquiA3Min.size() > 0) {
//       		eventoServices.setarComoFechados(eventosDaquiA3Min);
//       		
//       		// para cada evento eu tenho agora de pegar as apostas dele setar como esperando resultado
//       		for(Evento e: eventosDaquiA3Min) {
//       			
//       			List<Aposta> apostasAntes = apostaServices.todasApostasCombinadasAntesDoInicioEvento(e);
//       			
//       			if(apostasAntes.size() > 0) {
//       				
//       				apostaServices.setarTodasComoCombinadasDepoisInicioEvento(apostasAntes);
//       			}
//       			
//       			System.out.println("tamanho apostas"+apostasAntes.size());
//       		}
//       	}
//       	
//       	System.out.println(eventosDaquiA3Min.size()+" tamanhooo");
    }


}
