package br.dev.codein.clientes.rest.exceptions;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class ApiErros {
	
	@Getter
	private List<String> erros;
	
	public ApiErros(List<String> erros) {
		this.erros = erros;
	}
	
	public ApiErros(String message) {
		this.erros = Arrays.asList(message);
	}

}
