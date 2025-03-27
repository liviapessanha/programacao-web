package br.edu.iff.ccc.bsi.sgvet.exception;

public class EspecieNaoEncontradaException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EspecieNaoEncontradaException(String especie) {
		super ("Especie" + especie + " nao encontrada.");
	}
}
