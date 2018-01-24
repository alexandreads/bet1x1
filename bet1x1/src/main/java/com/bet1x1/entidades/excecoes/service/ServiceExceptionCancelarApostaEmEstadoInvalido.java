package com.bet1x1.entidades.excecoes.service;

public class ServiceExceptionCancelarApostaEmEstadoInvalido  extends ServiceException{

	public ServiceExceptionCancelarApostaEmEstadoInvalido() {
		super("Tentou cancelar aposta num serviço inválido.");
	}

}
