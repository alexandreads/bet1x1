package com.bet1x1.utilitarios;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Outros {

	
	public static Date localDateToDate(LocalDateTime data) {
		
		
		Date retorno = Date.from(data.atZone(ZoneId.systemDefault()).toInstant());
		
		return retorno;
		
		
	}
	
	public static LocalDateTime dateToLocaldateTime(Date data) {
		
		
		LocalDateTime dataRetornada = LocalDateTime.ofInstant(data.toInstant(), ZoneId.systemDefault());
		
		return dataRetornada;
		
	}
	
	
	public static String dateToFormatoBD(Date data) {
		
		String dataString = "";
		
		dataString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(data);
		dataString = "'"+dataString+"'";
		
		return dataString;
	}
	
	public static String dateToFormatoBDUltimoSegundoDoDia(Date data) {
		
		String dataString = "";
		
			
		dataString = new SimpleDateFormat("yyyy-MM-dd").format(data);
		dataString += " 23:59:59";
		dataString = "'"+dataString+"'";
		
		return dataString;
	}
}
