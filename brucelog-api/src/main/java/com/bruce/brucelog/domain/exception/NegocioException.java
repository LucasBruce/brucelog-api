package com.bruce.brucelog.domain.exception;

public class NegocioException extends RuntimeException{
	/*
	 * a serialVersionUId vai nos ajudar a controlar as versoes das instancias por
	 * exemplo se eu tenho uma aplicacao que possui dados que precisão ser
	 * atualizados a todo custo mesmo que estajam em producao então serealisamos a
	 * classe no caso temos uma aplicacao que preza ao maximo a integridade então
	 * caso aja versaos diferentes devido ao acrescimo de novos atributos na hora de
	 * deserializar a instancia ele lançarar uma exception reclamando que a classe
	 * possui dados novos e que a instancia deserializzada e uma instancia de versao
	 * antiga
	 */
    
	private static final long serialVersionUID = 1L;

	public NegocioException(String message) {
		super(message);
	}


}
