package com.bet1x1.utilitarios.emails;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailEsqueciSenha {
	
	
	public void enviaEmailSimples(String nomeRementente, String emailRemetente,
	        String nomeDestinatario, String emailDestinatario,
	        String assunto, String mensagem) throws EmailException {

	    SimpleEmail email = new SimpleEmail();
	    email.setHostName("smtp.googlemail.com"); // o servidor SMTP para envio do e-mail
	    email.addTo(emailDestinatario, nomeDestinatario); //destinatário
	    email.setFrom(emailRemetente, nomeRementente); // remetente
	    email.setSubject(assunto); // assunto do e-mail
	    email.setMsg(mensagem.toString()); //conteudo do e-mail
	    email.setAuthentication("utilitaformularios@gmail.com", "123456uti");
	    email.setCharset("UTF-8");
	    email.setSmtpPort(465);
	    email.setSmtpPort(587);
	    email.setSSL(true);
//	    email.setTLS(true);        
	    email.send();
	}

}
