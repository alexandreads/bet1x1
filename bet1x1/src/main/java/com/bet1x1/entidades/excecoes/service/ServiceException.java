package com.bet1x1.entidades.excecoes.service;


public class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3082351960302866350L;

	public ServiceException(String mensagem) {
		super(mensagem);
	}

	public ServiceException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
