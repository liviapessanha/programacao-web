package br.edu.iff.ccc.bsi.sgvet.exception;

public class AnimalNaoEncontradoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AnimalNaoEncontradoException(long id) {
		super("Animal com ID " + id + " nao encontrado.");
	}
}
