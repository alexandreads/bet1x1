package meuTestes.banco;

import java.time.LocalDateTime;
import java.util.Date;

import com.bet1x1.utilitarios.Outros;

public class Datas {
	
	
	public static void main(String[] args) {
		
		
		
		Date h = new Date();
		
		LocalDateTime local = Outros.dateToLocaldateTime(h);
		
		local = local.plusMinutes(3);
		
		Date h3 = Outros.localDateToDate(local);
		
		
		
		
		System.out.println(h);
		System.out.println(h3);

	}

}
