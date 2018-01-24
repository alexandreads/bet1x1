package com.bet1x1.beans.sockets;

import javax.faces.application.FacesMessage;

import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.annotation.Singleton;
import org.primefaces.push.impl.JSONDecoder;
import org.primefaces.push.impl.JSONEncoder;

@PushEndpoint("/chat")
@Singleton
public class NotifyChat {

	
	
	@OnMessage(encoders = { JSONEncoder.class }, decoders = { JSONDecoder.class })
	public String onMessage(String count) {
		System.out.println(count);
		return count;
	}
	
	
	
}
